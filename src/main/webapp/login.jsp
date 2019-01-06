<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<style>
body {
	background-image: url("../resources/img/flight.jpg");
	background-size: cover;
}

div.login-box-header {
	background-color: white;
	padding: 20px;
}
</style>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Sistem Tempahan Penerbangan</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/plugins/iCheck/square/blue.css">
<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">



<title>Sistem Tempahan Tiket Penerbangan</title>


</head>

<body class="hold-transition fixed">
	<div class="login-box">
		<!-- /.login-logo -->
		<div class="login-box-header">
			<img class="img.responsive"
				src="${contextPath}/resources/img/mpc-header.png"
				style="width: 100%;">
		</div>
		<div class="login-box-body" style="background: rgba(0, 0, 0, 0.3);">
			<form method="POST" action="${contextPath}/login" class="form-signin">
				<div class="box-header with-border">
					<h3 align="center" style="color: white;">
						<strong>SISTEM TEMPAHAN TIKET PENERBANGAN</strong>
					</h3>
				</div>
				<div class="form-group ${error != null ? 'has-error' : ''}">
					<div class="form-group has-feedback" style="align: center;">
						<span style='color: white; font-weight: bold; align: center;'>${message}</span>
					</div>
					<div class="form-group has-feedback">
						<input name="username" type="text" class="form-control"
							placeholder="Nama Pengguna"> <span
							class="glyphicon glyphicon-user form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input name="password" type="password" class="form-control"
							placeholder="Kata Laluan"> <span
							class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<button type="submit" class="btn btn-primary btn-block"
							style='align: center;'>Log Masuk</button>
					</div>
					<span>${error}</span> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="row" align="center">
						<div class="col-xs-12">
							<div class="checkbox icheck">
								<input type="checkbox" name="remember"
									style='color: white; font-weight: bold;'>
								<h10 align="center" style="color: white;"> Remember Me </h10>
							</div>
						</div>
						<!-- <div class="col-xs-6"
							style='color: white; font-weight: bold; align: center;'>Remember
							me</div> -->

					</div>
				</div>

			</form>
		</div>


	</div>

	<!-- jQuery 3 -->
	<script
		src="${contextPath}/resources/css/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="${contextPath}/resources/css/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="${contextPath}/resources/css/plugins/iCheck/icheck.min.js"></script>
	<script>
		function reset_noti() {

			alert("reset");
		}

		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' /* optional */
			});
		});
	</script>


</body>
</html>
