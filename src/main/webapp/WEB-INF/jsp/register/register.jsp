<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="resource/amazeui/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(":input[name='email']").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			
			if(val != ""){
				var url = "${pageContext.request.contextPath}/register-checkEmail";
				var args = {"userEmail":val,"time":new Date()};
				$.post(url,args,function(data){
					$("#message").html(data);
				});
			}
		});
	})
</script>
</head>
<body>
	<h2>注册激活</h2>  
 <form action="register?action=register" method="post">  
  	用户名<input type="text" id="userName" name="userName" value="" > 
 	密码<input type="password" id="userPassword" name="userPassword" value="" > 
     Email:<input type="email" id="email" name="email" value="" >  
     <br>
     <div id="message"></div>
     <input type="submit" value="提交">  
 </form>  
</body>
</html>