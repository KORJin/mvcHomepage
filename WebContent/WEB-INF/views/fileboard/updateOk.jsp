<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
	
	<c:if test="${check > 0}">
		<script type="text/javascript">
			alert("수정되었습니다.");
			location.href="${root}/fileboard/list.do?pageNumber=${pageNumber}";
		</script>
	</c:if>

	<c:if test="${check==0}">
		<script type="text/javascript">
			alert("수정되지않았습니다.");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>