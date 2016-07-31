package com.lib.service.user.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.FileInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.OfficeConvert;
import com.lib.utils.CompressUtil;
import com.lib.utils.JudgeUtils;
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
			f.setFileBrief("无");
			fileinfoDao.insert(f);
			uuids.add(f.getFileUuid());
		}
		return uuids;
	}

	@Override
	public void translateFile(String uuid) {
		// 设置文件问后台处理中
		fileinfoDao.setFileStateByUuid(uuid, 3);
		FileInfo file = fileinfoDao.getFileInfoByUuid(uuid);
		LOG.debug("开始转化文件" + uuid);
		if (JudgeUtils.isOfficeFile(file.getFileExt())) {
			// 文档转化
			
			officeConvert.convertToPDF(new File(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt()),
					new File(Const.ROOT_PATH + file.getFilePath() + ".pdf"));
			// 获取pdf缩略图 路径为 + Const.ROOT_PATH + file.getFilePath()+".png"
			
			ThumbnailUtils.pdfGetThumb(Const.ROOT_PATH + file.getFilePath() + ".pdf",
					Const.ROOT_PATH + file.getFilePath() + ".png");

		} else if (JudgeUtils.isVideoFile(file.getFileExt())) {

			// ffmpeg不支持的格式,使用memcoder
			if (file.getFileExt() == "wmv9" || file.getFileExt() == "rm" || file.getFileExt() == "rmvb") {
				if (TranslateUtils.processAVI(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
						Const.ROOT_PATH + file.getFilePath() + ".avi"))
				{

					/*try {
						File newFile = new File(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt());
						newFile.delete();
					} catch (Exception e) {
						LOG.error("删除视频文件失败" + file.getFileName());
					}*/
					// 视频文件后缀修改
					/*fileinfoDao.modifyFileExeById(file.getFileId(), "avi");*/
				}
			}

			// ffmpeg转换成flv
			if (TranslateUtils.processFLV(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
					Const.STREAM_PATH + file.getFileUuid() + ".flv")) {
				/*try {
					File newFile = new File(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt());
					newFile.delete();
				} catch (Exception e) {
					LOG.error("删除视频文件失败" + file.getFileName());
				}*/
				/*// 视频文件后缀修改
				fileinfoDao.modifyFileExeById(file.getFileId(), "flv");*/
				// 获取视频缩略图
				ThumbnailUtils.videoGetThumb(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
						Const.ROOT_PATH + file.getFilePath() + ".png");
			}

			
		}else if(JudgeUtils.isImageFile(file.getFileExt())){
			
			
			if(TranslateUtils.toPNG(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt(),
					Const.ROOT_PATH + file.getFilePath() + ".png"))
			{
				/*try {
					File newFile = new File(Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt());  
					newFile.delete();  
				} catch (Exception e) {
					LOG.error("删除图片文件失败" + file.getFileName());
				}*/
				/*// 图片文件后缀修改
				fileinfoDao.modifyFileExeById(file.getFileId(), "png");*/
			}
			

		}
		// 全文检索创立索引
		

		// 修改文件为私有可以查看
		fileinfoDao.setFileStateByUuid(uuid, 6);
	}

	@Override
	public FileInfoVO getFileInfoByUuid(String uuid) {
		return fileinfoDao.getFileInfoByUuid(uuid);//TODO 判断文件是否私有
	}

}
