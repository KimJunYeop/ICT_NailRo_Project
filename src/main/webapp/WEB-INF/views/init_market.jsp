<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action = "init" method = "post" enctype="multipart/form-data">
		가게 이름 : <input type = "text" name = "market_name" size = "30"> <br/>
		지역 : <input type = "text" name = "area" size = "20"><br/>
		등록할 쿠폰번호 : <input type = "text" name = "serialNum" size = 30> <br/>
		사진 : <input type = "file" name = "uploadfile"><br/>
		<input type = "submit" value = "등록"><input type = "reset" value = "초기화">
	</form>
</body>
</html>