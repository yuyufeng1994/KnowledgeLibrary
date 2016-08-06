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
	mp3 : function(ext) {
		if (ext == "mp3") {
			return true;
		} else {
			return false;
		}
	},
	read : function(ext) {
		if (ext == "txt"||ext == "java"||ext == "c"||ext == "php"||ext == "sql"||ext == "cpp"||ext == "js"||ext == "css"||ext == "properties") {
			return true;
		} else {
			return false;
		}
	},
	html : function(ext) {
		if (ext == "html"||ext == "xml"||ext == "jsp") {
			return true;
		} else {
			return false;
		}
	}
}


if (viewJudge.pdf(ext)) {
	PDFObject.embed(fileUrl + "pdf", document.getElementById("main-content")); // pdf预览插件
} else if (viewJudge.jpg(ext)) {
	$content.html("<img src=" + fileUrl + "png"
			+ " class='am-img-thumbnail am-radius'>");
	$content.css("height", "auto");
} else if (viewJudge.flv(ext)) {

	var flashvars = {
		f : 'rtmp://' + host + '/lib/' + uuid + '.flv',
		c : 0
	};
	
	if(ext == "mp4"){
		flashvars = {
				f : 'rtmp://' + host + '/lib/' + uuid + '.mp4',
				c : 0
			};
	}
	var params = {
		bgcolor : '#FFF',
		allowFullScreen : true,
		allowScriptAccess : 'always',
		wmode : 'transparent'
	};
	CKobject.embedSWF('resource/ckplayer/ckplayer.swf', 'main-content',
			'ckplayer_a1', '100%', '100%', flashvars, params);

}  else if (viewJudge.mp3(ext)) {
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
} else if (viewJudge.read(ext)) {
	$content.html("<pre></pre>")
	$content.find("pre").load(readUrl);
	$content.css("height", "auto");
}else if (viewJudge.html(ext)) {
	$content.html("<textarea style='width:100%;height:500px' disabled></textarea>")
	$content.find("textarea").load(readUrl);
	$content.css("height", "auto");
} else{
	$content.html("<center><img src=" + fileUrl + "png"
			+ " class='am-img-thumbnail am-radius'></center>");
}
