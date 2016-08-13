package com.lib.service.user.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.lib.dao.FileInfoDao;
import com.lib.dao.RelationInfoDao;
import com.lib.dao.UserInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.dto.LuceneSearchVo;
import com.lib.dto.PageVo;
import com.lib.entity.FileInfo;
import com.lib.entity.RelationInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.OfficeConvert;
import com.lib.service.user.LuceneService;
import com.lib.utils.CompressUtil;
import com.lib.utils.JudgeUtils;
import com.lib.utils.LuceneIndexUtil;
import com.lib.utils.LuceneSearchUtil;
import com.lib.utils.ThumbnailUtils;
import com.lib.utils.TranslateUtils;

/**
 * 用户处理文件上传和转化
 *
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
	private OfficeConvert officeConvert = TranslateUtils.getOfficeConvert();
	@Autowired
	private FileInfoDao fileinfoDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private RelationInfoDao relationInfoDao;

	@Autowired
	private LuceneService searchService;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public int insertFile(FileInfo fileInfo) {

		return fileinfoDao.insert(fileInfo);
	}

	@Override
	public List<String> compressFile(String name, UserInfo user) throws Exception {
		List<String> uuids = new ArrayList<String>();
		List<FileInfo> files = CompressUtil.startCompress(name, user.getUserId());
		try {
			FileUtils.forceDelete(new File(name));
		} catch (Exception e) {
			LOG.error("删除文件失败" + name);
		}
		for (FileInfo f : files) {
			f.setFileUserId(user.getUserId());
			f.setFileClassId(1l);
			f.setFileBrief("");
			fileinfoDao.insert(f);
			uuids.add(f.getFileUuid());
		}
		return uuids;
	}

	@Override
	public void translateFile(String uuid) throws IOException {
		// 设置文件问后台处理中
		fileinfoDao.setFileStateByUuid(uuid, 3);
		FileInfo file = fileinfoDao.getFileInfoByUuid(uuid);
		LOG.debug("开始转化文件" + uuid);
		if (JudgeUtils.isOfficeFile(file.getFileExt())) {
			// 文档转化

			officeConvert.convertToPDF(new File(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt()),
					new File(Const.ROOT_PATH + file.getFilePath() + ".pdf"));
			// 获取pdf缩略图 路径为 + Const.ROOT_PATH + file.getFilePath()+".png"

			if (new File(Const.ROOT_PATH + file.getFilePath() + ".pdf").exists()) {
				ThumbnailUtils.pdfGetThumb(Const.ROOT_PATH + file.getFilePath() + ".pdf",
						Const.ROOT_PATH + file.getFilePath() + ".png");
			} else {
				fileinfoDao.setFileStateByUuid(uuid, 1);
				return;
			}

		} else if (JudgeUtils.isVideoFile(file.getFileExt())) {

			if (file.getFileExt().equals("flv")) {
				FileUtils.copyFile(new File(Const.ROOT_PATH + "defaultfile/flv.png"),
						new File(Const.ROOT_PATH + file.getFilePath() + ".png"));
				FileUtils.copyFile(new File(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt()),
						new File(Const.STREAM_PATH + file.getFileUuid() + ".flv"));
			} else if (file.getFileExt().equals("mp4")) {
				ThumbnailUtils.videoGetThumb(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
						Const.ROOT_PATH + file.getFilePath() + ".png");
				FileUtils.copyFile(new File(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt()),
						new File(Const.STREAM_PATH + file.getFileUuid() + "." + file.getFileExt()));
			} else {
				ThumbnailUtils.videoGetThumb(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
						Const.ROOT_PATH + file.getFilePath() + ".png");
				// ffmpeg转换成flv
				TranslateUtils.processFLV(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
						Const.STREAM_PATH + file.getFileUuid() + ".flv");
			}

		} else if (JudgeUtils.isImageFile(file.getFileExt())) {

			TranslateUtils.toPNG(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
					Const.ROOT_PATH + file.getFilePath() + ".png");

		} else if (JudgeUtils.isAudioFile(file.getFileExt())) {
			FileUtils.copyFile(new File(Const.ROOT_PATH + "defaultfile/mp3.png"),
					new File(Const.ROOT_PATH + file.getFilePath() + ".png"));
			// ffmpeg转换成flv
			TranslateUtils.processFLV(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
					Const.STREAM_PATH + file.getFileUuid() + ".flv");
		} else {
			FileUtils.copyFile(new File(Const.ROOT_PATH + "defaultfile/question.png"),
					new File(Const.ROOT_PATH + file.getFilePath() + ".png"));
		}
		// 修改文件为私有可以查看

//		fileinfoDao.setFileStateByUuid(uuid, 6);
		file.setFileState(6);
		//创建索引
		searchService.addFileIndex(file, userInfoDao.queryById(file.getFileUserId()).getUserName(),null);
		if(file.getFileBrief()==null||file.getFileBrief().equals("")){
			
			String text = searchService.getSummary(file, 3L).toString();
			file.setFileBrief(text);
		}
		fileinfoDao.updateByUuid(file);

	}

	@Override
	public FileInfoVO getFileInfoByUuid(String uuid) {
		return fileinfoDao.getFileInfoByUuid(uuid);// TODO 判断文件是否私有
	}

	@Override
	public int saveBaseFileInfoByUuid(FileInfo fileInfo) {
		return fileinfoDao.updateByUuid(fileInfo);
	}

	@Override
	public List<FileInfo> searchFileInfoByNameOrId(String searchInfo, Long userId, Integer pageNo) {
		PageHelper.startPage(pageNo, Const.COMMON_PAGE_SIZE, null);
		List<FileInfo> list = fileinfoDao.searchFileInfoByNameOrId("%" + searchInfo + "%", userId);
		return list;
	}

	@Override
	public int autoRelation(String uuid) {
		List<RelationInfo> rs = new ArrayList<>();
		RelationInfo r = null;
		FileInfo file = fileinfoDao.getFileInfoByUuid(uuid);
		List<Long> list = searchService.getRelation(file.getFileName());
		for (Long l : list) {
			if (file.getFileId().equals(l)) {
				continue;
			}
			
			r = new RelationInfo();
			r.setMainFileId(file.getFileId());
			r.setRelationFileId(l);
			rs.add(r);

			// 反向也要关联
			r = new RelationInfo();
			r.setMainFileId(l);
			r.setRelationFileId(file.getFileId());
			rs.add(r);

		}

		int res = 0;
		try {
			res = relationInfoDao.insertList(rs);
		} catch (Exception e) {

		}

		return res / 2;
	}

	@Override
	public int addRelations(Long mainFileId, List<Long> list) {
		List<RelationInfo> rs = new ArrayList<>();
		RelationInfo r = null;
		for (Long l : list) {
			r = new RelationInfo();
			r.setMainFileId(mainFileId);
			r.setRelationFileId(l);
			rs.add(r);

			// 反向也要关联
			r = new RelationInfo();
			r.setMainFileId(l);
			r.setRelationFileId(mainFileId);
			rs.add(r);
		}
		int res = 0;
		try {
			res = relationInfoDao.insertList(rs);
		} catch (Exception e) {

		}

		return res / 2;
	}

	@Override
	public FileInfo getFileInfoByFileId(Long fileId) {
		// TODO Auto-generated method stub
		return fileinfoDao.getFileInfoByFileId(fileId);
	}

	@Override
	public List<FileInfo> searchFileInfoByNameOrId(String searchInfo, Long userId) {
		return fileinfoDao.searchFileInfoByNameOrId("%" + searchInfo + "%", userId);
	}

	@Override
	public List<RelationInfo> getRelations(Long mainFileId) {
		return relationInfoDao.selectList(mainFileId);
	}

	@Override
	@Transactional
	public int delRelations(Long mainFileId, Long relationFileId) {
		RelationInfo r = new RelationInfo();

		r.setMainFileId(mainFileId);
		r.setRelationFileId(relationFileId);
		int rs = relationInfoDao.delete(r);
		// 反向也要删除
		r.setMainFileId(relationFileId);
		r.setRelationFileId(mainFileId);
		relationInfoDao.delete(r);

		return rs;
	}

	@Override
	public int delFileById(Long fileId) throws Exception {

		FileInfo fileInfo = fileinfoDao.getFileInfoByFileId(fileId);
		if (fileInfo.getFileState() != 5 && fileInfo.getFileState() != 6) {
			throw new Exception();
		}
		File file = new File(Const.ROOT_PATH + fileInfo.getFilePath() + "." + fileInfo.getFileExt());
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
		file = new File(Const.ROOT_PATH + fileInfo.getFilePath() + ".png");
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
		file = new File(Const.ROOT_PATH + fileInfo.getFilePath() + ".pdf");
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
		file = new File(Const.ROOT_PATH + fileInfo.getFilePath() + ".flv");
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
		file = new File(Const.STREAM_PATH + fileInfo.getFileUuid() + ".flv");
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
		file = new File(Const.STREAM_PATH + fileInfo.getFileUuid() + ".mp4");
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}

		return fileinfoDao.deleteByPrimaryKey(fileId);
	}

	@Override
	public void addClick(Long userId, Long fileId) {
		try {
			fileinfoDao.insertClickInfo(userId, fileId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
