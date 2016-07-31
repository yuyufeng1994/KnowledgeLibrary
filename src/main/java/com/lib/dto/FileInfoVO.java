package com.lib.dto;

import com.lib.entity.FileInfo;
import com.lib.enums.FileStateEnum;
import com.lib.utils.StringValueUtil;

public class FileInfoVO extends FileInfo {
	private String fileStateStr;
	private String fileSizeFormat;

	public String getFileStateStr() {
		return FileStateEnum.stateOf(getFileState()).getStateInfo();
	}

	public String getHiddenedFileName() {
		String str = super.getFileName();
		if (str.length() > 19) {
			//String ext = str.substring(str.lastIndexOf("."), str.length());
			str = str.substring(0, 15)+"...";
		}

		return str;
	}

	public String getFileSizeFormat() {
		if (getFileSize() != null) {

			return StringValueUtil.getDataSize(getFileSize());
		}
		return "0";
	}

	public void setFileSizeFormat(String fileSizeFormat) {
		this.fileSizeFormat = fileSizeFormat;
	}

	@Override
	public String toString() {
		return super.toString() + "FileInfoVO [fileStateStr=" + getFileStateStr() + "]";
	}

}
