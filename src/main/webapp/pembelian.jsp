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
<!-- DataTables -->
<script
	src="${contextPath}/resources/css/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script
	src="${contextPath}/resources/css/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
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
				<section class="content-header">


					<div class="row"></div>
				</section>

				<!-- Main content -->
				<section class="content-header">
					<h1>Maklumat Penerbangan</h1>
					<section class="content">
						<!-- /.row -->
						<div class="row">
							<div class="col-xs-12">
								<div class="box">
									<!-- /.box-header -->
									<div class="box-body">
										<div class="table-responsive">
											<table id="pembelianTable"
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
															<td>${pemohon.tarikhMohon}</td>
															<td>${pemohon.tarikhMula}</td>
															<td>${pemohon.nama}</td>
															<td>${pemohon.tujuan}</td>
															<td>${pemohon.tempatBertugas}</td>
															<td>${pemohon.peruntukan}</td>
															<td>${pemohon.statusPermohonan}</td>
															<td><spring:url
																	value="/updateStatus?id=${pemohon.id}"
																	var="updateStatus" /> <c:if
																	test="${pemohon.statusPermohonan == 'Proses'}">
																	<button type="button" class="btn btn-info "
																		data-toggle="modal"
																		data-target="#modal-lulus${pemohon.id}">Pembelian</button>
																</c:if> 
																<c:forEach items="${Penerbangan}" var="penerbangan">
																<c:if
																	test="${pemohon.id == penerbangan.permohonan.id}">
																	<div class="modal fade"
																		id="modal-beliTiket${pemohon.id}${penerbangan.penerbanganId}">
																		<div class="modal-dialog modal-lg">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close"
																						data-dismiss="modal" aria-label="Close">
																						<span aria-hidden="true">&times;</span>
																					</button>
																					<h4 class="modal-title">Pembelian</h4>
																				</div>
																				<div class="modal-body">
																					<form:form method="POST"
																						modelAttribute="updatePembelian"
																						action="${contextPath}/updatePembelianForm"
																						class="form-horizontal">

																						<spring:bind path="permohonan">
																							<form:input type="hidden" class="form-control"
																								path="permohonan" value="${pemohon.id}"></form:input>
																						</spring:bind>

																						<spring:bind path="penerbangan">
																							<form:input type="hidden" class="form-control"
																								path="penerbangan"
																								value="${penerbangan.penerbanganId}"></form:input>
																						</spring:bind>

																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-2 control-label">Cara Beli</label>
																							<div class="col-sm-6">
																								<spring:bind path="caraBeli">
																									<form:select path="caraBeli"
																										class="form-control"
																										id="caraBeliId${penerbangan.penerbanganId}"
																										onchange="showCaraBeli(${penerbangan.penerbanganId})">
																										<option></option>
																										<option value="KadKredit">Kad Kredit</option>
																										<option value="Waran">Waran</option>
																									</form:select>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputEmail3"
																								class="col-sm-2 control-label">Harga
																								Tiket</label>
																							<div class="col-sm-6">
																								<spring:bind path="hargaTiket">
																									<form:input id="hargaTiket" type="text"
																										class="form-control" path="hargaTiket"
																										placeholder="Harga Tiket"
																										onchange="myFunction()"></form:input>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<div id="waranHidden${penerbangan.penerbanganId}" >
																								<label for="inputPassword3"
																									class="col-sm-2 control-label">Waran</label>
																								<div class="col-sm-6">
																									<spring:bind path="waran">
																										<form:input id="hargaWaran" type="text"
																											class="form-control" path="waran"
																											placeholder="Harga Waran"
																											onchange="myFunction()"></form:input>
																									</spring:bind>
																								</div>
																							</div>
																						</div>
																						<div class="form-group">
																							<div id="hargaPenguranganHidden${penerbangan.penerbanganId}">
																								<label for="inputEmail3"
																									class="col-sm-2 control-label">Harga
																									Pengurangan</label>

																								<div class="col-sm-6">
																									<spring:bind path="hargaPengurangan">
																										<form:input type="text" class="form-control"
																											id="hargaKurang" path="hargaPengurangan"
																											placeholder="Harga Pengurangan"
																											onchange="myFunction()"></form:input>
																									</spring:bind>
																								</div>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="exampleInputFile"
																								class="col-sm-2 control-label">Muatnaik
																								Tiket</label>
																							<div class="col-sm-6">
																								<spring:bind path="muatNaikTiket">
																									<form:input type="file" class="form-control"
																										path="muatNaikTiket"></form:input>
																								</spring:bind>
																							</div>
																						</div>

																						<!-- /.box-body -->
																						<div class="box-footer">
																							<button type="submit"
																								class="btn btn-info pull-right">Hantar</button>
																						</div>
																					</form:form>
																				</div>
																			</div>
																		</div>
																	</div>
																	</c:if>
																</c:forEach> <c:forEach items="${Penerbangan}" var="penerbangan">
																	<div class="modal fade"
																		id="modal-kemaskini${pemohon.id}${penerbangan.penerbanganId}">
																		<div class="modal-dialog modal-lg">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close"
																						data-dismiss="modal" aria-label="Close">
																						<span aria-hidden="true">&times;</span>
																					</button>
																					<h4 class="modal-title">Pembelian</h4>
																				</div>
																				<div class="modal-body">
																					<form:form method="POST"
																						modelAttribute="updateKemaskiniTiket"
																						action="${contextPath}/updateKemaskiniForm"
																						class="form-horizontal">

																						<spring:bind path="permohonan">
																							<form:input type="hidden" class="form-control"
																								path="permohonan" value="${pemohon.id}"></form:input>
																						</spring:bind>

																						<spring:bind path="penerbanganId">
																							<form:input type="hidden" class="form-control"
																								path="penerbanganId"
																								value="${penerbangan.penerbanganId}"></form:input>
																						</spring:bind>

																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">Penerbangan</label>
																							<div class="col-sm-6">
																								<spring:bind path="penerbangan">
																									<form:select path="penerbangan"
																										class="form-control" id="penerbanganId"
																										onchange="showPenerbangan()">
																										<option></option>
																										<option value="Pergi">Pergi</option>
																										<option value="Balik">Balik</option>
																										<option value="Antarabandar">Antarabandar</option>
																									</form:select>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">Tarikh
																								Penerbangan</label>

																							<div class="col-sm-6">
																								<spring:bind path="tarikhPergi">
																									<form:input type="date" id="tarikhPergi"
																										class="form-control" path="tarikhPergi"></form:input>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">Waktu
																								Berlepas</label>

																							<div class="col-sm-6">
																								<spring:bind path="waktuBerlepas">
																									<form:input id="waktuBerlepas" type="time"
																										class="form-control" path="waktuBerlepas"></form:input>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">Waktu
																								Tiba</label>

																							<div class="col-sm-6">
																								<spring:bind path="waktuTiba">
																									<form:input id="waktuTiba" type="time"
																										class="form-control" path="waktuTiba"></form:input>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">Jenis
																								Pesawat</label>
																							<div class="col-sm-6">
																								<spring:bind path="jenisPesawat">
																									<form:select id="jenisPesawat"
																										path="jenisPesawat" class="form-control">
																										<c:forEach var="pesawat" items="${pesawat}">
																											<option value="${pesawat.pesawatDesc}"><c:out
																													value="${pesawat.pesawatDesc}" /></option>
																										</c:forEach>
																									</form:select>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">No.
																								Pesawat</label>

																							<div class="col-sm-6">
																								<spring:bind path="noPesawat">
																									<form:input id="noPesawat" type="text"
																										class="form-control" path="noPesawat"></form:input>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">Dari
																								Lokasi</label>
																							<div class="col-sm-6">
																								<spring:bind path="dariLokasi">
																									<form:select path="dariLokasi"
																										class="form-control">
																										<c:forEach var="test" items="${lokasi}">
																											<option value="${test.lokasiDesc}"><c:out
																													value="${test.lokasiDesc}" /></option>
																										</c:forEach>
																									</form:select>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-4 control-label">Destinasi</label>
																							<div class="col-sm-6">
																								<spring:bind path="destinasi">
																									<form:select path="destinasi"
																										class="form-control">
																										<c:forEach var="test" items="${lokasi}">
																											<option value="${test.lokasiDesc}"><c:out
																													value="${test.lokasiDesc}" /></option>
																										</c:forEach>
																									</form:select>
																								</spring:bind>

																							</div>
																						</div>
																						<!-- /.box-body -->
																						<div class="box-footer">
																							<button type="submit"
																								class="btn btn-info pull-right">Hantar</button>
																						</div>
																					</form:form>
																				</div>
																			</div>
																		</div>
																	</div>
																</c:forEach>


																<div class="modal fade" id="modal-lulus${pemohon.id}">
																	<div class="modal-dialog modal-lg">
																		<div class="modal-content">
																			<div class="modal-header">
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																				<h4 class="modal-title">Pembelian</h4>
																			</div>
																			<div class="modal-body">
																				<form:form method="POST"
																					modelAttribute="kemaskiniPermohon"
																					action="${contextPath}/updateStatusLulus"
																					enctype="multipart/form-data"
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
																							<spring:bind path="tujuan">
																								<form:input type="hidden" class="form-control"
																									path="tujuan" value="${pemohon.tujuan}"></form:input>
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
																							<spring:bind path="penerbanganId">
																								<form:input type="hidden" class="form-control"
																									path="penerbanganId"
																									value="${penerbangan.penerbanganId}"></form:input>
																							</spring:bind>
																							<spring:bind path="penerbangan">
																								<form:input type="hidden" class="form-control"
																									path="penerbangan"
																									value="${penerbangan.penerbangan}"></form:input>
																							</spring:bind>
																							<spring:bind path="tarikhPergi">
																								<form:input type="hidden" class="form-control"
																									path="tarikhPergi"
																									value="${penerbangan.tarikhPergi}"></form:input>
																							</spring:bind>
																							<spring:bind path="waktuBerlepas">
																								<form:input type="hidden" class="form-control"
																									path="waktuBerlepas"
																									value="${penerbangan.waktuBerlepas}"></form:input>
																							</spring:bind>
																							<spring:bind path="waktuTiba">
																								<form:input type="hidden" class="form-control"
																									path="waktuTiba"
																									value="${penerbangan.waktuTiba}"></form:input>
																							</spring:bind>
																							<spring:bind path="dariLokasi">
																								<form:input type="hidden" class="form-control"
																									path="dariLokasi"
																									value="${penerbangan.dariLokasi}"></form:input>
																							</spring:bind>
																							<spring:bind path="destinasi">
																								<form:input type="hidden" class="form-control"
																									path="destinasi"
																									value="${penerbangan.destinasi}"></form:input>
																							</spring:bind>
																							<spring:bind path="jenisPesawat">
																								<form:input type="hidden" class="form-control"
																									path="jenisPesawat"
																									value="${penerbangan.jenisPesawat}"></form:input>
																							</spring:bind>


																							<label for="inputEmail3"
																								class="col-sm-2 control-label">Nama
																								Pemohon</label>

																							<div class="col-sm-6">
																								<spring:bind path="nama">
																									<form:input type="text" class="form-control"
																										id="nama" path="nama" value="${pemohon.nama}"></form:input>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-2 control-label">Tarikh
																								Permohonan</label>

																							<div class="col-sm-6">
																								<spring:bind path="tarikhMohon">
																									<form:input type="text" class="form-control"
																										id="tarikhMohon" path="tarikhMohon"
																										value="${pemohon.tarikhMohon}"></form:input>
																								</spring:bind>
																							</div>
																						</div>

																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-2 control-label">Unit</label>

																							<div class="col-sm-6">
																								<spring:bind path="bahagian">
																									<form:input type="text" class="form-control"
																										id="bahagian" path="bahagian"
																										value="${pemohon.bahagian}"></form:input>
																								</spring:bind>
																							</div>
																						</div>

																						<div class="form-group">
																							<label for="inputPassword3"
																								class="col-sm-2 control-label">Peruntukan</label>

																							<div class="col-sm-6">
																								<spring:bind path="peruntukan">
																									<form:input type="text" class="form-control"
																										id="peruntukan" path="peruntukan"
																										value="${pemohon.peruntukan}"></form:input>
																								</spring:bind>
																							</div>
																						</div>
																						<div class="table-responsive">
																							<table class="table table-bordered table-hover"
																								id="tablePembelian${pemohon.id}">
																								<thead>
																									<tr>
																										<th>Penerbangan</th>
																										<th>Tarikh Penerbangan</th>
																										<th>Waktu Berlepas</th>
																										<th>Waktu Tiba</th>
																										<th>Dari Lokasi</th>
																										<th>Destinasi</th>
																										<th>Tindakan</th>
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
																												<td>${penerbangan.dariLokasi}</td>
																												<td>${penerbangan.destinasi}</td>
																												<td>
																													<button type="button"
																														class="btn btn-primary"
																														data-dismiss="modal" data-toggle="modal"
																														data-target="#modal-beliTiket${pemohon.id}${penerbangan.penerbanganId}">Beli
																														Tiket</button>
																													<button type="button"
																														class="btn btn-success "
																														data-dismiss="modal" data-toggle="modal"
																														data-target="#modal-kemaskini${pemohon.id}${penerbangan.penerbanganId}">Kemaskini</button>
																												</td>
																											</tr>
																										</c:if>
																										<%
																											x++;
																										%>
																										
												<script>
													var waranHidden = document
															.getElementById('waranHidden${penerbangan.penerbanganId}');
													waranHidden.style.display = "none";
													var hargaPenguranganHidden = document
															.getElementById('hargaPenguranganHidden${penerbangan.penerbanganId}');
													hargaPenguranganHidden.style.display = "none";

													function showCaraBeli(iD) {
														var caraBeliId = document
																.getElementById('caraBeliId'+iD).value;
		 											console.log(caraBeliId);
		 											console.log(iD);
														
														if (caraBeliId == 'Waran') {
															var waranHidden2 = document
															.getElementById('waranHidden'+iD);

															var hargaPenguranganHidden2 = document
															.getElementById('hargaPenguranganHidden'+iD);
															
															waranHidden2.style.display = "inline";
															hargaPenguranganHidden2.style.display = "inline";
														} else if (caraBeliId == "KadKredit") {
															var waranHidden2 = document
															.getElementById('waranHidden'+iD);

															var hargaPenguranganHidden2 = document
															.getElementById('hargaPenguranganHidden'+iD);
															
															waranHidden2.style.display = "none";
															hargaPenguranganHidden2.style.display = "none";
														}
													}
												</script>
														<script>
												function myFunction(val) {
													var hargaWaran = document.getElementById('hargaWaran').value;
													var hargaTiket = document.getElementById('hargaTiket').value;

													
													var hargaKurang = document.getElementById('hargaKurang')
														hargaKurang.value = hargaTiket - hargaWaran;
													
												}	
												</script>

																									</c:forEach>
																								</tbody>
																							</table>
																						</div>
																				</form:form>
																			</div>
																			<!-- /.box-footer -->
																		</div>
																	</div>
																	<!-- /.modal-content -->
																</div> <!-- /.modal-dialog --></td>
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

	</c:if>
</body>
</html>