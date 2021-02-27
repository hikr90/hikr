<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
if(${loginVO.mgr}==0){
	alert("권한이 없습니다.");
	location.href="main.do";
}else{
	location.href="vacation_management.do";
}; 

</script>
</head>
<body>

</body>
</html>