<%-- page 지시자: WAS에서 JSP 해석하는 방법을 기술 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
	<html>
		<head>
		<!-- Latest compiled and minified CSS -->
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
			<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=divice-width, initial-scale=1.0">
			<title>Insert title here</title>
		</head>
		<body>
		  	<div class="d-flex flex-column vh-100"> <!-- 뷰포트의 높이만쿰 설정 -->
		 	  <%-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ --%> <!-- 자바코드가 주로 들어간다 -->
		 	  
		 	  <%-- include 지시자는 소스 복사 개념 --%>
		  		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		  		<div class="flex-grow-1 m-2">
		  			<div class="d-flex row">
		  				<div class="col-md-4">
		  					<%-- include 액션은 외부에서 실행하고 결과만 삽입 --%>
		  					<jsp:include page="/WEB-INF/views/common/menu.jsp"></jsp:include>
		  				</div>
		  					
		  				<div class="col-md-8">
		  					<!-- ##################################### --> <!-- 응답내용에 포함되지 않음  -->
		  					<div class="card">
		  						<div class="card-header">header</div>
		  						<div class="card-body">
		  							content
		  						</div>
		  					</div>
		  						<!-- ################################## -->
		  				</div>
		  			</div>
		  		</div>
		  	</div>	
		</body>
	</html>