
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Sistem Tempahan Penerbangan</title>

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
			<jsp:include page="${contextPath}/template/adminMainSideBar.jsp" />
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->
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
					</div>
				</section>

				<!-- Main content -->
				<section class="content">
					<!-- /.row -->
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header">
									<h3 class="box-title">Maklumat Penerbangan</h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<table id="example1" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Tarikh Permohonan</th>
												<th>Tarikh Penerbangan</th>
												<th>Nama Pemohon</th>
												<td>Tujuan</td>
												<th>Tempat Bertugas</th>
												<th>Peruntukan</th>
												<th>Jenis Pesawat</th>
												<th>Status</th>
												<th>Tindakan</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td data-toggle="modal" data-target="#modal-lulus">10/7/2018</td>
												<td>25/7/2018</td>
												<td>Shahreza</td>
												<td>Kerja</td>
												<td>Sabah</td>
												<td>Operasi</td>
												<td>MAS</td>
												<td>Diluluskan</td>
												<td>
													<button type="button" class="btn btn-info "
														data-toggle="modal" data-target="#modal-lulus">Lulus</button>
													<button type="button" class="btn btn-danger "
														data-toggle="modal" data-target="#modal-tolak">Tolak</button>

													<div class="modal fade" id="modal-lulus">
														<div class="modal-dialog modal-lg">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																	<h4 class="modal-title">Lulus</h4>
																</div>
																<div class="modal-body">
																	<form class="form-horizontal">
																		<div class="box-body">
																			<div class="form-group">
																				<label for="inputEmail3"
																					class="col-sm-2 control-label">Nama Pemohon</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="nama"
																						placeholder="Seperti Dalam Kad Pengenalan">
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Tarikh
																					Permohonan</label>

																				<div class="col-sm-6">
																					<input type="date" class="form-control" id="kp">
																				</div>
																			</div>

																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Unit /
																					Bahagian</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="unit">
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Destinasi</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="unit">
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Tarikh Pergi
																					Dan Masa</label>

																				<div class="col-sm-8">
																					<div class="col-sm-4">
																						<input type="date" class="form-control" id="kp">
																					</div>
																					<div class="col-sm-4">
																						<input type="time" class="form-control" id="unit">
																					</div>
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Tarikh Balik
																					Dan Masa</label>

																				<div class="col-sm-8">
																					<div class="col-sm-4">
																						<input type="date" class="form-control" id="kp">
																					</div>
																					<div class="col-sm-4">
																						<input type="time" class="form-control" id="unit">
																					</div>
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Alasan</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="unit">
																				</div>
																			</div>
																		</div>
																		<!-- /.box-body -->
																		<div class="box-footer">
																			<button type="submit" class="btn btn-info pull-right">Hantar</button>
																		</div>
																		<!-- /.box-footer -->
																	</form>
																</div>
															</div>
															<!-- /.modal-content -->
														</div>
														<!-- /.modal-dialog -->
													</div>

													<div class="modal fade" id="modal-tolak">
														<div class="modal-dialog modal-lg">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																	<h4 class="modal-title">Tolak</h4>
																</div>
																<div class="modal-body">
																	<form class="form-horizontal">
																		<div class="box-body">
																			<div class="form-group">
																				<label for="inputEmail3"
																					class="col-sm-2 control-label">Nama Pemohon</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="nama"
																						placeholder="Seperti Dalam Kad Pengenalan">
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Tarikh
																					Permohonan</label>

																				<div class="col-sm-6">
																					<input type="date" class="form-control" id="kp">
																				</div>
																			</div>

																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Unit /
																					Bahagian</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="unit">
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Destinasi</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="unit">
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Tarikh Pergi
																					Dan Masa</label>

																				<div class="col-sm-8">
																					<div class="col-sm-4">
																						<input type="date" class="form-control" id="kp">
																					</div>
																					<div class="col-sm-4">
																						<input type="time" class="form-control" id="unit">
																					</div>
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Tarikh Balik
																					Dan Masa</label>

																				<div class="col-sm-8">
																					<div class="col-sm-4">
																						<input type="date" class="form-control" id="kp">
																					</div>
																					<div class="col-sm-4">
																						<input type="time" class="form-control" id="unit">
																					</div>
																				</div>
																			</div>
																			<div class="form-group">
																				<label for="inputPassword3"
																					class="col-sm-2 control-label">Alasan</label>

																				<div class="col-sm-6">
																					<input type="text" class="form-control" id="unit">
																				</div>
																			</div>
																		</div>
																		<!-- /.box-body -->
																		<div class="box-footer">
																			<button type="submit" class="btn btn-info pull-right">Hantar</button>
																		</div>
																		<!-- /.box-footer -->
																	</form>
																</div>
															</div>
															<!-- /.modal-content -->
														</div>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</section>

				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
			<footer class="main-footer">
				<div class="pull-right hidden-xs"></div>
				<strong>Copyright &copy; <a href="https://adminlte.io">Perbadanan
						Produktiviti Malaysia</a>.
				</strong> All rights reserved.
			</footer>

		</div>
		<!-- ./wrapper -->

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
	</c:if>
</body>
</html>
