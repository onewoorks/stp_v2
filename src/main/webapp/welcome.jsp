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
			<jsp:include page="${contextPath}/template/mainSideBar.jsp" />
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->
				<!-- <section class="content-header">
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
				</section> -->

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
									<div class="table-responsive">
										<table id="kelulusanKPegawai"
											class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Tarikh Permohonan</th>
													<th>Tarikh Penerbangan</th>
													<th>Nama Pemohon</th>
													<th>Tujuan</th>
													<th>Tempat Bertugas</th>
													<th>Peruntukan</th>
													<th>Status</th>
													<th>Tindakan</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${welcome}" var="pemohon">
													<tr>
														<td data-toggle="modal" data-target="#modal-lulus">${pemohon.tarikhMohon}</td>
														<td>${pemohon.tarikhMula}</td>
														<td>${pemohon.nama}</td>
														<td>${pemohon.tujuan}</td>
														<td>${pemohon.tempatBertugas}</td>
														<td>${pemohon.peruntukan}</td>
														<td>${pemohon.statusPermohonan}</td>
														<td width="15%"><spring:url
																value="/updateStatus?id=${pemohon.id}"
																var="updateStatus" /> <c:if
																test="${pemohon.statusPermohonan == 'Baru'}">
																<button type="button" class="btn btn-info "
																	data-toggle="modal"
																	data-target="#modal-lulus${pemohon.id}">Lulus</button>
																<button type="button" class="btn btn-danger "
																	data-toggle="modal"
																	data-target="#modal-tolak${pemohon.id}">Tolak</button>
															</c:if> <c:if test="${pemohon.statusPermohonan == 'Proses'}">
																<button type="button" class="btn btn-danger "
																	data-toggle="modal"
																	data-target="#modal-tolak${pemohon.id}">Tolak</button>
															</c:if> <c:if test="${pemohon.statusPermohonan == 'Tolak'}">
																<button type="button" class="btn btn-info "
																	data-toggle="modal"
																	data-target="#modal-lulus${pemohon.id}">Lulus</button>
															</c:if>

															<div class="modal fade" id="modal-lulus${pemohon.id}">
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
																			<form:form method="POST"
																				modelAttribute="kemaskiniPermohon"
																				action="${contextPath}/updateStatusLulus"
																				class="form-horizontal">
																				<div class="box-body">
																					<div class="form-group">
																						<spring:bind path="id">
																							<form:input type="hidden" class="form-control"
																								path="id" value="${pemohon.id}"></form:input>
																						</spring:bind>
																						<spring:bind path="namaPelulus">
																							<form:input type="hidden" class="form-control"
																								path="namaPelulus"
																								value="${pemohon.namaPelulus}"></form:input>
																						</spring:bind>
																						<spring:bind path="kp">
																							<form:input type="hidden" class="form-control"
																								path="kp" value="${pemohon.kp}"></form:input>
																						</spring:bind>
																						<spring:bind path="emel">
																							<form:input type="hidden" class="form-control"
																								path="emel" value="${pemohon.emel}"></form:input>
																						</spring:bind>
																						<spring:bind path="passport">
																							<form:input type="hidden" class="form-control"
																								path="passport" value="${pemohon.passport}"></form:input>
																						</spring:bind>
																						<spring:bind path="noTelefonBimbit">
																							<form:input type="hidden" class="form-control"
																								path="noTelefonBimbit"
																								value="${pemohon.noTelefonBimbit}"></form:input>
																						</spring:bind>
																						<spring:bind path="peruntukan">
																							<form:input type="hidden" class="form-control"
																								path="peruntukan" value="${pemohon.peruntukan}"></form:input>
																						</spring:bind>
																						<spring:bind path="pembangunan">
																							<form:input type="hidden" class="form-control"
																								path="pembangunan"
																								value="${pemohon.pembangunan}"></form:input>
																						</spring:bind>
																						<%-- <spring:bind path="jenisPesawat">
																						<form:input type="hidden" class="form-control"
																							path="jenisPesawat"
																							value="${pemohon.jenisPesawat}"></form:input>
																					</spring:bind> --%>
																						<spring:bind path="noBilBom">
																							<form:input type="hidden" class="form-control"
																								path="noBilBom" value="${pemohon.noBilBom}"></form:input>
																						</spring:bind>
																						<spring:bind path="kelulusan">
																							<form:input type="hidden" class="form-control"
																								path="kelulusan" value="${pemohon.kelulusan}"></form:input>
																						</spring:bind>
																						<spring:bind path="namaPemohon">
																							<form:input type="hidden" class="form-control"
																								path="namaPemohon"
																								value="${pemohon.namaPemohon}"></form:input>
																						</spring:bind>
																						<spring:bind path="tarikhMula">
																							<form:input type="hidden" class="form-control"
																								path="tarikhMula" value="${pemohon.tarikhMula}"></form:input>
																						</spring:bind>

																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Nama
																							Pemohon</label>

																						<div class="col-sm-8">
																							<spring:bind path="nama">
																								<form:input type="text" class="form-control"
																									id="nama" path="nama" value="${pemohon.nama}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Tarikh
																							Permohonan</label>

																						<div class="col-sm-8">
																							<spring:bind path="tarikhMohon">
																								<form:input type="text" class="form-control"
																									id="tarikhMohon" path="tarikhMohon"
																									value="${pemohon.tarikhMohon}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>

																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Unit</label>

																						<div class="col-sm-8">
																							<spring:bind path="bahagian">
																								<form:input type="text" class="form-control"
																									id="bahagian" path="bahagian"
																									value="${pemohon.bahagian}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Tempat
																							Bertugas</label>

																						<div class="col-sm-8">
																							<spring:bind path="tempatBertugas">
																								<form:input type="text" class="form-control"
																									id="tempatBertugas" path="tempatBertugas"
																									value="${pemohon.tempatBertugas}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Tujuan</label>

																						<div class="col-sm-8">
																							<spring:bind path="tujuan">
																								<form:input type="text" class="form-control"
																									id="tujuan" path="tujuan"
																									value="${pemohon.tujuan}" style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Peruntukan</label>

																						<div class="col-sm-8">
																							<spring:bind path="peruntukan">
																								<form:input type="text" class="form-control"
																									id="peruntukan" path="peruntukan"
																									value="${pemohon.peruntukan}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<c:if test="${pemohon.peruntukan == 'Pembangunan'}">
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-3 control-label">Fail BOM</label>
																							<a
																								href="${contextPath}/downloadBom?id=${pemohon.id}"
																								class="col-sm-2 control-label">Muat Turun
																								Bom</a>
																						</div>
																					</c:if>
																					<div class="form-group">
																						<div class="table-responsive">
																							<table class="table table-bordered table-hover"
																								id="tableKelulusan${pemohon.id}">
																								<thead>
																									<tr>
																										<th>Penerbangan</th>
																										<th>Tarikh Pergi</th>
																										<th>Waktu Berlepas</th>
																										<th>Waktu Tiba</th>
																										<th>No Pesawat</th>
																										<th>Dari Lokasi</th>
																										<th>Destinasi</th>
																									</tr>
																								</thead>
																								<tbody>
																									<%
																										int i = 1;
																									%>
																									<c:forEach var="penerbangan"
																										items="${Penerbangan}">
																										<c:set var="penerbanganLongId"
																											value="${penerbangan.permohonan.id}"></c:set>
																										<c:set var="permohonanLongId"
																											value="${pemohon.id}"></c:set>
																										<c:if
																											test="${penerbanganLongId == permohonanLongId}">
																											<tr>
																												<td>${penerbangan.penerbangan}</td>
																												<td>${penerbangan.tarikhPergi}</td>
																												<td>${penerbangan.waktuBerlepas}</td>
																												<td>${penerbangan.waktuTiba}</td>
																												<td>${penerbangan.noPesawat}</td>
																												<td>${penerbangan.dariLokasi}</td>
																												<td>${penerbangan.destinasi}</td>
																											</tr>
																											<%
																												i++;
																											%>
																										</c:if>
																									</c:forEach>
																								</tbody>
																							</table>
																						</div>
																					</div>

																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Catatan</label>

																						<div class="col-sm-8">
																							<spring:bind path="alasan">
																								<form:input type="text" class="form-control"
																									id="alasan" path="alasan" style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																				</div>
																				<!-- /.box-body -->
																				<div class="box-footer">
																					<button type="submit"
																						class="btn btn-info pull-right">Lulus</button>
																				</div>
																				<!-- /.box-footer -->
																			</form:form>
																		</div>
																	</div>
																	<!-- /.modal-content -->
																</div>
																<!-- /.modal-dialog -->
															</div>

															<div class="modal fade" id="modal-tolak${pemohon.id}">
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
																			<form:form method="POST"
																				modelAttribute="kemaskiniPermohon"
																				action="${contextPath}/updateStatus"
																				class="form-horizontal">
																				<div class="box-body">
																					<div class="form-group">
																						<spring:bind path="id">
																							<form:input type="hidden" class="form-control"
																								path="id" value="${pemohon.id}"></form:input>
																						</spring:bind>
																						<spring:bind path="namaPelulus">
																							<form:input type="hidden" class="form-control"
																								path="namaPelulus"
																								value="${pemohon.namaPelulus}"></form:input>
																						</spring:bind>
																						<spring:bind path="kp">
																							<form:input type="hidden" class="form-control"
																								path="kp" value="${pemohon.kp}"></form:input>
																						</spring:bind>
																						<spring:bind path="emel">
																							<form:input type="hidden" class="form-control"
																								path="emel" value="${pemohon.emel}"></form:input>
																						</spring:bind>
																						<spring:bind path="passport">
																							<form:input type="hidden" class="form-control"
																								path="passport" value="${pemohon.passport}"></form:input>
																						</spring:bind>
																						<spring:bind path="noTelefonBimbit">
																							<form:input type="hidden" class="form-control"
																								path="noTelefonBimbit"
																								value="${pemohon.noTelefonBimbit}"></form:input>
																						</spring:bind>
																						<spring:bind path="peruntukan">
																							<form:input type="hidden" class="form-control"
																								path="peruntukan" value="${pemohon.peruntukan}"></form:input>
																						</spring:bind>
																						<spring:bind path="pembangunan">
																							<form:input type="hidden" class="form-control"
																								path="pembangunan"
																								value="${pemohon.pembangunan}"></form:input>
																						</spring:bind>
																						<%-- <spring:bind path="jenisPesawat">
																						<form:input type="hidden" class="form-control"
																							path="jenisPesawat"
																							value="${pemohon.jenisPesawat}"></form:input>
																					</spring:bind> --%>
																						<spring:bind path="noBilBom">
																							<form:input type="hidden" class="form-control"
																								path="noBilBom" value="${pemohon.noBilBom}"></form:input>
																						</spring:bind>
																						<spring:bind path="kelulusan">
																							<form:input type="hidden" class="form-control"
																								path="kelulusan" value="${pemohon.kelulusan}"></form:input>
																						</spring:bind>
																						<spring:bind path="namaPemohon">
																							<form:input type="hidden" class="form-control"
																								path="namaPemohon"
																								value="${pemohon.namaPemohon}"></form:input>
																						</spring:bind>
																						<spring:bind path="tarikhMula">
																							<form:input type="hidden" class="form-control"
																								path="tarikhMula" value="${pemohon.tarikhMula}"></form:input>
																						</spring:bind>



																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Nama
																							Pemohon</label>

																						<div class="col-sm-8">
																							<spring:bind path="nama">
																								<form:input type="text" class="form-control"
																									id="namaTolak" path="nama"
																									value="${pemohon.nama}" style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label class="col-sm-3 control-label">Tarikh
																							Permohonan</label>

																						<div class="col-sm-8">
																							<spring:bind path="tarikhMohon">
																								<form:input type="text" class="form-control"
																									id="kp" path="tarikhMohon"
																									value="${pemohon.tarikhMohon}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label class="col-sm-3 control-label">Unit</label>

																						<div class="col-sm-8">
																							<spring:bind path="bahagian">
																								<form:input type="text" class="form-control"
																									id="unit" path="bahagian"
																									value="${pemohon.bahagian}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Tempat
																							Bertugas</label>

																						<div class="col-sm-8">
																							<spring:bind path="tempatBertugas">
																								<form:input type="text" class="form-control"
																									id="tempatBertugas" path="tempatBertugas"
																									value="${pemohon.tempatBertugas}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Tujuan</label>

																						<div class="col-sm-8">
																							<spring:bind path="tujuan">
																								<form:input type="text" class="form-control"
																									id="tujuan" path="tujuan"
																									value="${pemohon.tujuan}" style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<div class="form-group">
																						<label for="inputPassword3"
																							class="col-sm-3 control-label">Peruntukan</label>

																						<div class="col-sm-8">
																							<spring:bind path="peruntukan">
																								<form:input type="text" class="form-control"
																									id="peruntukan" path="peruntukan"
																									value="${pemohon.peruntukan}"
																									style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																					<c:if test="${pemohon.peruntukan == 'Pembangunan'}">
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-3 control-label">Fail BOM</label>
																							<a
																								href="${contextPath}/downloadBom?id=${pemohon.id}"
																								class="col-sm-2 control-label">Muat Turun
																								Bom</a>
																						</div>
																					</c:if>
																					<div class="form-group">
																						<div class="table-responsive">
																							<table class="table table-bordered table-hover"
																								id="tableTolak${pemohon.id}">
																								<thead>
																									<tr>
																										<th>Penerbangan</th>
																										<th>Tarikh Pergi</th>
																										<th>Waktu Berlepas</th>
																										<th>Waktu Tiba</th>
																										<th>No Pesawat</th>
																										<th>Dari Lokasi</th>
																										<th>Destinasi</th>
																									</tr>
																								</thead>
																								<tbody>
																									<%
																										int x = 1;
																									%>
																									<c:forEach var="penerbangan"
																										items="${Penerbangan}">
																										<c:set var="penerbanganLongId"
																											value="${penerbangan.permohonan.id}"></c:set>
																										<c:set var="permohonanLongId"
																											value="${pemohon.id}"></c:set>
																										<c:if
																											test="${penerbanganLongId == permohonanLongId}">
																											<tr>
																												<td>${penerbangan.penerbangan}</td>
																												<td>${penerbangan.tarikhPergi}</td>
																												<td>${penerbangan.waktuBerlepas}</td>
																												<td>${penerbangan.waktuTiba}</td>
																												<td>${penerbangan.noPesawat}</td>
																												<td>${penerbangan.dariLokasi}</td>
																												<td>${penerbangan.destinasi}</td>
																											</tr>
																											<%
																												x++;
																											%>
																										</c:if>
																									</c:forEach>
																								</tbody>
																							</table>
																						</div>
																					</div>

																					<div class="form-group">
																						<label class="col-sm-3 control-label">Catatan</label>

																						<div class="col-sm-8">
																							<spring:bind path="alasan">
																								<form:input type="text" class="form-control"
																									id="unit" path="alasan" style="width : 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																				</div>
																				<!-- /.box-body -->
																				<div class="box-footer">
																					<button type="submit"
																						class="btn btn-info pull-right">Tolak</button>
																				</div>
																				<!-- /.box-footer -->
																			</form:form>
																		</div>
																	</div>
																</div>
																<!-- /.modal-content -->
															</div>
															</div></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
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
		<script>
			$('#tableKelulusan${pemohon.id}').DataTable({
				'paging' : true,
				'lengthChange' : false,
				'searching' : false,
				'ordering' : true,
				'info' : true,
				'autoWidth' : false
			})
			$('#tableTolak${pemohon.id}').DataTable({
				'paging' : true,
				'lengthChange' : false,
				'searching' : false,
				'ordering' : true,
				'info' : true,
				'autoWidth' : false
			})
		</script>
		<script>
			$('#kelulusanKPegawai').DataTable({
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
