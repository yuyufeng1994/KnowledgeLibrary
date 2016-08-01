var viewJudge = {
	// office类型，用pdf呈现
	pdf : function(ext) {
		if (ext == "doc" || ext == "docx" || ext == "xls" || ext == "xlsx"
				|| ext == "ppt" || ext == "pptx" || ext == "pdf") {
			return true;
		} else {
			return false;
		}
	},
	// 图片类型
	jpg : function(ext) {
		if (ext == "png" || ext == "gif" || ext == "jpg" || ext == "jpeg"
				|| ext == "bmp" || ext == "pptx" || ext == "pdf") {
			return true;
		} else {
			return false;
		}
	},
	// 视频类型
	flv : function(ext) {
		if (ext == "mp4" || ext == "avi" || ext == "flv" || ext == "rmvb"
				|| ext == "wmv" || ext == "mkv") {
			return true;
		} else {
			return false;
		}
	},
	txt : function(ext) {
		if (ext == "txt") {
			return true;
		} else {
			return false;
		}
	},
	mp3 : function(ext) {
		if (ext == "mp3") {
			return true;
		} else {
			return false;
		}
	}
}


if (viewJudge.pdf(ext)) {
	PDFObject.embed(fileUrl + "pdf", document.getElementById("main-content")); // pdf预览插件
} else if (viewJudge.jpg(ext)) {
	$content.html("<img src=" + fileUrl + "jpg"
			+ " class='am-img-thumbnail am-radius'>");
	$content.css("height", "auto");
} else if (viewJudge.flv(ext)) {

	var flashvars = {
		f : 'rtmp://' + host + '/lib/' + uuid + '.flv',
		c : 0
	};
	var params = {
		bgcolor : '#FFF',
		allowFullScreen : true,
		allowScriptAccess : 'always',
		wmode : 'transparent'
	};
	CKobject.embedSWF('resource/ckplayer/ckplayer.swf', 'main-content',
			'ckplayer_a1', '100%', '100%', flashvars, params);

} else if (viewJudge.txt(ext)) {
	$content.load(fileUrl + "txt");
} else if (viewJudge.mp3(ext)) {
	var flashvars = {
		f : 'rtmp://' + host + '/lib/' + uuid + '.flv',
		c : 0
	};
	var params = {
		bgcolor : '#FFF',
		allowFullScreen : true,
		allowScriptAccess : 'always',
		wmode : 'transparent'
	};
	CKobject.embedSWF('resource/ckplayer/ckplayer.swf', 'main-content',
			'ckplayer_a1', '100%', '100%', flashvars, params);
}
