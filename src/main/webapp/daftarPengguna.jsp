<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Permohonan</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<jsp:include page="${contextPath}/template/header.jsp" />
<body class="hold-transition skin-blue sidebar-mini fixed">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<div class="wrapper">

			<jsp:include page="${contextPath}/template/mainHeader.jsp" />
			<!-- Left side column. contains the logo and sidebar -->
			<jsp:include page="${contextPath}/template/mainSideBar.jsp" />
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- /.row -->
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<!-- /.box-header -->
								<div class="box-body">

									<form:form method="POST" modelAttribute="daftarPenggunaForm"
										class="form-horizontal">

									</form:form>

									<c:if test="${not empty msg}">
										<div class="alert alert-${css} alert-dismissible" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong>${msg}</strong>
										</div>
									</c:if>
										<div class="row">
											<div class="form-group col-xs-2 pull-right">
												<button type="button" class="btn btn-info form-control "
													data-toggle="modal" data-target="#modal-penggunaForm">
													<b>Tambah Pengguna</b>
												</button>

											</div>
										</div>
									<br />
									<table id="pengguna" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Bil</th>
												<th>Nombor Staf</th>
												<th>Nama Staf</th>
												<th>Nama Pengguna</th>
												<th>Status</th>
												<th>Action</th>
											</tr>
										</thead>
										<%
											int i = 1;
										%>
										<c:forEach var="user" items="${listPengguna}">
											<tr>
												<td><%=i%></td>
												<td>${user.staffNo}</td>
												<td>${user.namaStaff}</td>
												<td>${user.username}</td>
												<td>${user.status}</td>
												<td><spring:url
														value="/admin/lihatPengguna?id=${user.id}" var="userUrl" />
													<spring:url value="/admin/padamPengguna?id=${user.id}"
														var="deleteUrl" /> <spring:url
														value="/admin/kemaskiniPengguna?id=${user.id}"
														var="updateUrl" />

													<button class="btn btn-info btn-block"
														onclick="location.href='${userUrl}'">Lihat</button>
													<button class="btn btn-primary btn-block"
														onclick="location.href='${updateUrl}'">Kemaskini</button>
													<button class="btn btn-danger btn-block"
														onclick="location.href='${deleteUrl}'">Padam</button></td>
											</tr>
											<%
												i++;
											%>
										</c:forEach>
									</table>
									<!-- Add daftar pengguna  	-->
									<jsp:include page="${contextPath}/daftarPenggunaForm.jsp" />
									<jsp:include page="${contextPath}/editPenggunaForm.jsp" />
									<jsp:include page="${contextPath}/lihatPenggunaForm.jsp" />
									<jsp:include page="${contextPath}/padamPenggunaForm.jsp" />

								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<footer class="main-footer">
				<div class="pull-right hidden-xs"></div>
				<strong>Copyright &copy; <a href="https://adminlte.io">Perbadanan
						Produktiviti Malaysia</a>.
				</strong> All rights reserved.
			</footer>
		</div>
		<!--  wrapper -->

		<!-- jQuery 3 -->
		<script
			src="${contextPath}/resources/css/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap 3.3.7 -->
		<script
			src="${contextPath}/resources/css/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- Slimscroll -->
		<script
			src="${contextPath}/resources/css/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<!-- FastClick -->
		<script
			src="${contextPath}/resources/css/bower_components/fastclick/lib/fastclick.js"></script>
		<!-- AdminLTE App -->
		<script src="${contextPath}/resources/css/dist/js/adminlte.min.js"></script>
		<!-- AdminLTE for demo purposes -->
		<script src="${contextPath}/resources/css/dist/js/demo.js"></script>
		<!-- DataTables -->
		<script
			src="${contextPath}/resources/css/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
		<script
			src="${contextPath}/resources/css/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
		<script>
			$('#pengguna').DataTable({
				'paging' : true,
				'lengthChange' : true,
				'searching' : true,
				'ordering' : true,
				'info' : true,
				'autoWidth' : false
			})
		</script>
	</c:if>
</body>
</html>

