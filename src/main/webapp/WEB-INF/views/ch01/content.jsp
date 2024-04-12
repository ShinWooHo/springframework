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
		  		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		  		<div class="flex-grow-1 m-2">
		  			<div class="d-flex row">
		  				<div class="col-md-4">
		  					<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		  				</div>
		  					
		  				<div class="col-md-8">
		  					<!-- ##################################### -->
		  					<div class="card">
		  						<div class="card-header">content</div>
		  						<div class="card-body">
		  						
		  						</div>
		  					</div>
		  						<!-- ################################## -->
		  				</div>
		  			</div>
		  		</div>
		  	</div>	
		</body>
	</html>