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
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">Maklumat Pengguna</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">

								<form:form method="POST" modelAttribute="permohonanForm"
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

								<section class="content-header">
									<div class="row">
										<div class="col-xs-2">
											<label>Tarikh Permohonan</label>
										</div>
										<div class="col-xs-2">
											<label>Tarikh Penerbangan</label>
										</div>
										<div class="col-xs-2">
											<label>Tarikh Mula Bertugas</label>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-2">
											<input type="date" class="form-control" id="unit1">
										</div>
										<div class="col-xs-2">
											<input type="date" class="form-control" id="unit2">
										</div>
										<div class="col-xs-2">
											<input type="date" class="form-control" id="unit3">
										</div>
										<div class="col-xs-2">
											<button type="button" class="btn btn-info form-control">Carian</button>
										</div>
										<div class="col-xs-2">
											<button type="button" class="btn btn-info form-control"
												data-toggle="modal" data-target="#modal-permohonan"
												id="permohonanBaru">Permohonan</button>
										</div>
									</div>
								</section>
								<br />
								<c:forEach items="${user.username}" var="user">
									<div class="table-responsive">
										<table id="permohonanTiketTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Tarikh Permohonan</th>
													<th>Tarikh Penerbangan</th>
													<th>Tujuan</th>
													<th>Tempat Bertugas</th>
													<th>Peruntukan</th>
													<th>Status</th>
													<th>Tindakan</th>
												</tr>
											</thead>
											<%
												int i = 1;
											%>
											<c:forEach var="pemohon" items="${welcome}">
												<tr>
													<td>${pemohon.tarikhMohon}</td>
													<td>${pemohon.tarikhMula}</td>
													<td>${pemohon.tujuan}</td>
													<td>${pemohon.tempatBertugas}</td>
													<td>${pemohon.peruntukan}</td>
													<td>${pemohon.statusPermohonan}</td>
													<td><spring:url
															value="/hapusPermohonan?id=${pemohon.id}" var="deleteUrl" />
														<spring:url value="/permohonanOpen?id=${pemohon.id}"
															var="updateUrl" /> <spring:url
															value="/batalPermohonan?id=${pemohon.id}" var="batalUrl" />
														<spring:url
															value="/batalPermohonanProses?id=${pemohon.id}"
															var="batalProsesUrl" /> <spring:url
															value="/permohonanKemaskiniTemp?id=${pemohon.id}"
															var="kemaskiniUrl" /> <spring:url
															value="/downloadTiket?id=${pemohon.id}" var="downloadUrl" />
														<c:if test="${pemohon.statusPermohonan == 'Baru'}">
															<button id="btnHapus" class="btn btn-warning btn-block"
																onclick="location.href='${deleteUrl}'">Hapus</button>
															<button id="btnPermohonanSemula"
																class="btn btn-primary btn-block"
																onclick="location.href='${kemaskiniUrl}'">Kemaskini</button>
														</c:if> <c:if
															test="${pemohon.statusPermohonan == 'Tiket Terbuka'}">
															<button id="btnPermohonanSemula"
																class="btn btn-primary btn-block"
																onclick="location.href='${updateUrl}'">Permohonan
																Semula</button>
														</c:if> <c:if test="${pemohon.statusPermohonan == 'Proses'}">
															<button id="btnBatal" class="btn btn-danger btn-block"
																onclick="location.href='${batalProsesUrl}'">Batal</button>
														</c:if> <c:if test="${pemohon.statusPermohonan == 'Selesai'}">
															<button id="btnBatal" class="btn btn-danger btn-block"
																onclick="location.href='${batalUrl}'">Batal</button>
															<form:form method="POST"
																modelAttribute="downloadBorangSelesai"
																action="${contextPath}/downloadBorang"
																class="form-horizontal">
																<button type="submit" id="btnDownload"
																	class="btn btn-primary btn-block">Borang</button>
															</form:form>
															<form:form method="GET"
																modelAttribute="downloadTiketSelesai"
																action="${contextPath}/downloadTiket"
																class="form-horizontal">
																<button type="submit" id="btnDownload"
																	class="btn btn-info btn-block">Muat Turun</button>
															</form:form>
														</c:if>
												</tr>
												<%
													i++;
												%>
											</c:forEach>
										</table>
									</div>
								</c:forEach>
								<!-- Add permohonan  -->
								<jsp:include page="${contextPath}/permohonanBaru.jsp" />
								<jsp:include page="${contextPath}/permohonanHapus.jsp" />
								<jsp:include page="${contextPath}/permohonanOpenTiket.jsp" />
								<jsp:include page="${contextPath}/permohonanBatal.jsp" />
								<jsp:include page="${contextPath}/permohonanUpdate.jsp" />
								<jsp:include page="${contextPath}/permohonanBatalProses.jsp" />
							</div>
						</div>
					</div>
				</div>
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
			$('#permohonanTiketTable').DataTable({
				'paging' : true,
				'lengthChange' : false,
				'searching' : false,
				'ordering' : true,
				'info' : true,
				'autoWidth' : false
			})
		</script>
	</c:if>
</body>
</html>

