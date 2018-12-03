<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
	$(function() {
		// Get the form fields and hidden div
		var checkbox = $("#wakil");
		var hidden = $("#hidden");
		// Hide the fields.
		// Use JS to do this in case the user doesn't have JS 
		// enabled.
		hidden.hide();
		// Setup an event listener for when the state of the 
		// checkbox changes.
		checkbox.change(function() {
			// Check to see if the checkbox is checked.
			// If it is, show the fields and populate the input.
			// If not, hide the fields.
			if (checkbox.is(':checked')) {
				// Show the hidden fields.
				hidden.show();
				// Populate the input.
			} else {
				// Make sure that the hidden fields are indeed
				// hidden.
				hidden.hide();
				// You may also want to clear the value of the 
				// hidden fields here. Just in case somebody 
				// shows the fields, enters data to them and then 
				// unticks the checkbox.
				//
				// This would do the job:
				//
				// $("#hidden_field").val("");
			}
		});
	})
</script>

<script>
	var baranganCount = 1;
	var totalAnggaran = 0;

	function baranganTableTambah() {
		var baranganDibawa = document.getElementById('baranganDibawa').value;
		var jumlah = document.getElementById('jumlah').value;
		var anggaranBerat = document.getElementById('anggaranBerat').value;
		var total = document.getElementById('total');
		var table = document.getElementById('tableBarangan');
		totalAnggaran = totalAnggaran + parseInt(anggaranBerat, 10);

		$(
				"<tr><td>" + baranganCount + "</td><td>" + baranganDibawa
						+ "</td><td>" + jumlah + "</td><td>" + anggaranBerat
						+ "</td></tr>").appendTo(table);
		baranganCount++;
		total.value = totalAnggaran;

		var butiranBarangan = {
			"baranganDibawa" : baranganDibawa,
			"anggaranBerat" : anggaranBerat,
			"jumlah" : jumlah,
			"total" : totalAnggaran
		};

		$.ajax({
			type : "POST",
			//the url where you want to sent the userName and password to
			url : '/baranganTemp',
			contentType : 'application/json',
			data : JSON.stringify(butiranBarangan),
			success : function() {

			}
		})
		var baranganDibawa = document.getElementById('baranganDibawa');
		var jumlah = document.getElementById('jumlah');
		var anggaranBerat = document.getElementById('anggaranBerat');
		baranganDibawa.setValue = "";
		jumlah.setValue = "";
		anggaranBerat.setValue = "";
	}
</script>

<script>
	function tambahDlmTable() {
		var destinasi = document.getElementById('destinasi');
		var dariLokasi = document.getElementById('dariLokasi');
		var noPesawat = document.getElementById('noPesawat');
		var jenisPesawat = document.getElementById('jenisPesawat');
		var waktuTiba = document.getElementById('waktuTiba');
		var tarikhPergi = document.getElementById('tarikhPergi');
		var waktuBerlepas = document.getElementById('waktuBerlepas');
		var penerbanganId = document.getElementById('penerbanganId');
		var table10 = document.getElementById('tablePenerbanganId');
		$(
				"<tr><td>" + penerbanganId.value + "</td><td>"
						+ tarikhPergi.value + "</td><td>" + waktuBerlepas.value
						+ "</td><td>" + waktuTiba.value + "</td><td>"
						+ noPesawat.value + "</td><td>" + dariLokasi.value
						+ "</td><td>" + destinasi.value + "</td></tr>")
				.appendTo(table10);
		var butiranPenerbangan = {
			"penerbangan" : penerbanganId.value,
			"tarikhPergi" : tarikhPergi.value,
			"waktuBerlepas" : waktuBerlepas.value,
			"waktuTiba" : waktuTiba.value,
			"noPesawat" : noPesawat.value,
			"dariLokasi" : dariLokasi.value,
			"destinasi" : destinasi.value,
			"jenisPesawat" : jenisPesawat.value,
		};

		$.ajax({
			type : "POST",
			//the url where you want to sent the userName and password to
			url : '/penerbanganTemp',
			contentType : 'application/json',
			data : JSON.stringify(butiranPenerbangan),
			success : function() {
			}
		})
		destinasi.value = "";
		dariLokasi.value = "";
		noPesawat.value = "";
		jenisPesawat.value = "";
		waktuTiba.value = "";
		tarikhPergi.value = "";
		waktuBerlepas.value = "";
		penerbanganId.value = "";
	}
</script>

<script>
	function changeTab() {
		$('[href="#timeline"]').tab('show');
	}
	function changeTab2() {
		$('[href="#settings"]').tab('show');
	}
</script>
<script>
	function show() {
		var pembangunanHidden = document.getElementById('pembangunanHidden');
		pembangunanHidden.style.display = "none";

		var bomHidden = document.getElementById('bomHidden');
		bomHidden.style.display = "none";

		var muatNaikHidden = document.getElementById('muatNaikHidden');
		muatNaikHidden.style.display = "none";

		var peruntukanId = document.getElementById('peruntukanId').value;

		if (peruntukanId == 'Pembangunan') {
			pembangunanHidden.style.display = "inline";
			bomHidden.style.display = "inline";
			muatNaikHidden.style.display = "inline";
		} else if (peruntukanId == "Operasi") {
			pembangunanHidden.style.display = "none";
			bomHidden.style.display = "none";
			muatNaikHidden.style.display = "none";
		}
	};
</script>
<div class="modal fade" id="modal-permohonan${pemohon.id}">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">

			<div class="modal-header" id="modalHeader">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Permohonan</h4>
			</div>
			<div class="modal-body" id="modalBody">
				<div class="col-md-12">
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#activity" data-toggle="tab">Maklumat
									Penerbangan</a></li>
							<li><a href="#timeline" data-toggle="tab">Butiran
									Penerbangan</a></li>
							<li><a href="#settings" data-toggle="tab">Butiran
									Barangan</a></li>
						</ul>
						<div class="tab-content">
							<div class="active tab-pane" id="activity">
								<div class="box-body">
									<form:form method="POST" modelAttribute="permohonanForm"
										action="${contextPath}/permohonanForm"
										enctype="multipart/form-data" class="form-horizontal">
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-2 control-label">Wakil
												Pemohon</label>

											<div class="col-sm-4">
												<spring:bind path="wakilPermohon">
													<form:checkbox path="wakilPermohon" id="wakil"></form:checkbox>
												</spring:bind>
											</div>

											<div id="hidden">
												<label for="inputEmail3" class="col-sm-2 control-label">Nama
													Wakil</label>

												<div class="col-sm-4">
													<spring:bind path="namaPemohon">
														<form:input type="text" class="form-control"
															path="namaPemohon"
															placeholder="Seperti Dalam Kad Pengenalan"></form:input>
													</spring:bind>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-2 control-label">Nama</label>

											<div class="col-sm-4">
												<spring:bind path="nama">
													<c:forEach items="${user.namaStaff}" var="user">
														<form:input type="text" class="form-control" path="nama"
															placeholder="Seperti Dalam Kad Pengenalan"
															required="required" value="${namaStaff}"></form:input>
													</c:forEach>
												</spring:bind>
											</div>
											<label for="inputEmail3" class="col-sm-2 control-label">Nama
												Pelulus</label>

											<div class="col-sm-4">
												<c:forEach items="${user.namaPengurus}" var="user">
													<spring:bind path="namaPelulus">
														<form:input type="text" class="form-control"
															path="namaPelulus"
															placeholder="Seperti Dalam Kad Pengenalan"
															value="${namaPengurus}"></form:input>
													</spring:bind>
												</c:forEach>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">No.
												KP</label>

											<div class="col-sm-4">
												<c:forEach items="${user.noKP}" var="user">
													<spring:bind path="kp">
														<form:input type="text" class="form-control" path="kp"
															required="required" value="${noKP}"></form:input>
													</spring:bind>
												</c:forEach>
											</div>
											<label for="inputPassword3" class="col-sm-2 control-label">Unit</label>

											<div class="col-sm-4">
												<c:forEach items="${user.unit}" var="user">
													<spring:bind path="bahagian">
														<form:input type="text" class="form-control"
															path="bahagian" required="required" value="${unit}"></form:input>
													</spring:bind>
												</c:forEach>
											</div>
										</div>

										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">Emel</label>

											<div class="col-sm-4">
												<c:forEach items="${user.email}" var="user">
													<spring:bind path="emel">
														<form:input type="email" class="form-control" path="emel"
															required="required" value="${email}"></form:input>
													</spring:bind>
												</c:forEach>
											</div>
											<label for="inputPassword3" class="col-sm-2 control-label">No.
												Pasport</label>

											<div class="col-sm-4">
												<c:forEach items="${user.passport}" var="user">
													<spring:bind path="passport">
														<form:input type="text" class="form-control"
															path="passport" value="${passport}"></form:input>
													</spring:bind>
												</c:forEach>
											</div>
										</div>

										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">Tujuan</label>

											<div class="col-sm-4">
												<spring:bind path="tujuan">
													<form:input type="text" class="form-control" path="tujuan"
														required="required"></form:input>
												</spring:bind>
											</div>
											<label for="inputPassword3" class="col-sm-2 control-label">Tempat
												Bertugas</label>

											<div class="col-sm-4">
												<spring:bind path="tempatBertugas">
													<form:input type="text" class="form-control"
														path="tempatBertugas" required="required"></form:input>
												</spring:bind>
											</div>
										</div>

										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">Tarikh
												Mula Bertugas</label>

											<div class="col-sm-4">
												<spring:bind path="tarikhMula">
													<form:input type="date" id="date" onchange="date()"
														class="form-control" path="tarikhMula" required="required"></form:input>
												</spring:bind>
											</div>
											<label for="inputPassword3" class="col-sm-2 control-label">Tarikh
												Tamat Bertugas</label>

											<div class="col-sm-4">
												<spring:bind path="tarikhTamat">
													<form:input type="date" id="to" class="form-control"
														path="tarikhTamat" required="required"></form:input>
												</spring:bind>
											</div>
										</div>

										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">No.
												Telefon Bimbit</label>

											<div class="col-sm-4">
												<c:forEach items="${user.noTelefon}" var="user">
													<spring:bind path="noTelefonBimbit">
														<form:input type="text" class="form-control"
															path="noTelefonBimbit" required="required"
															value="${noTelefon}"></form:input>
													</spring:bind>
												</c:forEach>
											</div>
											<label for="inputPassword3" class="col-sm-2 control-label">No
												Enrich</label>

											<div class="col-sm-4">
												<c:forEach items="${user.enrichNo}" var="user">
													<spring:bind path="enrichNo">
														<form:input type="text" class="form-control"
															path="enrichNo" required="required" value="${enrichNo}"></form:input>
													</spring:bind>
												</c:forEach>
											</div>

										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">Peruntukan</label>
											<div class="col-sm-4">
												<spring:bind path="peruntukan">
													<form:select path="peruntukan" class="form-control"
														id="peruntukanId" onchange="show()" required="required">
														<option></option>
														<option value="Operasi">Operasi</option>
														<option value="Pembangunan">Pembangunan</option>
													</form:select>
												</spring:bind>
											</div>
											<label for="inputPassword3" class="col-sm-2 control-label">Catatan</label>
											<div class="col-sm-4">
												<spring:bind path="muatNaikBom">
													<form:input type="text" class="form-control" path="catatan"
														id="catatan" rel="textTooltip" data-toogle="tooltip"></form:input>
												</spring:bind>
											</div>

										</div>

										<div class="form-group">
											<!-- <label for="inputPassword3"
																class="col-sm-2 control-label">Kelulusan</label>
															<div class="col-sm-4">
																<spring:bind path="kelulusan">
																	<form:select path="kelulusan" class="form-control"
																		required="required">
																		<option></option>
																		<option value="mas">MAS</option>
																		<option value="airasia">AIRASIA</option>
																		<option value="malindo">MALINDO</option>
																		<option value="firefly">FIREFLY</option>
																	</form:select>
																</spring:bind>
															</div> -->
											<div id="pembangunanHidden">
												<label for="inputPassword3" class="col-sm-2 control-label">Pembangunan</label>
												<div class="col-sm-4">
													<spring:bind path="pembangunan">
														<form:select path="pembangunan" class="form-control">
															<c:forEach var="peruntukan" items="${peruntukan}">
																<option value="${peruntukan.peruntukanDesc}"><c:out
																		value="${peruntukan.peruntukanDesc}" /></option>
															</c:forEach>
														</form:select>
													</spring:bind>
												</div>
											</div>
											<div id="bomHidden">
												<label for="inputPassword3" class="col-sm-2 control-label">No.
													Bil BOM</label>

												<div class="col-sm-4">
													<spring:bind path="noBilBom">
														<form:input type="text" class="form-control"
															path="noBilBom"></form:input>
													</spring:bind>
												</div>
											</div>
										</div>

										<div class="form-group" id="muatNaikHidden">
											<label for="exampleInputFile" class="col-sm-2 control-label">Muatnaik
												Lampiran</label>
											<div class="col-sm-4">
												<spring:bind path="muatNaikBom">
													<form:input type="file" path="muatNaikBom"
														id="exampleInputFile"></form:input>
												</spring:bind>
											</div>
										</div>


										<div class="box-footer">
											<button type="button" onclick="changeTab()"
												class="btn btn-info pull-right">Seterusnya</button>
										</div>
								</div>
								<!-- /.box-body -->

								<!-- /.box-footer -->

							</div>
							<div class="tab-pane" id="timeline">
								<!-- /.box-header -->
								<div class="box-body">
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">Penerbangan</label>
										<div class="col-sm-6">
											<spring:bind path="penerbangan">
												<form:select path="penerbangan" class="form-control"
													id="penerbanganId" onchange="showPenerbangan()">
													<option></option>
													<option value="Pergi">Pergi</option>
													<option value="Balik">Balik</option>
													<option value="Antarabandar">Antarabandar</option>
												</form:select>
											</spring:bind>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">Tarikh
											Penerbangan</label>

										<div class="col-sm-6">
											<spring:bind path="tarikhPergi">
												<form:input type="date" id="tarikhPergi"
													class="form-control" path="tarikhPergi"></form:input>
											</spring:bind>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">Waktu
											Berlepas</label>

										<div class="col-sm-6">
											<spring:bind path="waktuBerlepas">
												<form:input id="waktuBerlepas" type="time"
													class="form-control" path="waktuBerlepas"></form:input>
											</spring:bind>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">Waktu
											Tiba</label>

										<div class="col-sm-6">
											<spring:bind path="waktuTiba">
												<form:input id="waktuTiba" type="time" class="form-control"
													path="waktuTiba"></form:input>
											</spring:bind>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">Jenis
											Pesawat</label>
										<div class="col-sm-6">
											<spring:bind path="jenisPesawat">
												<form:select id="jenisPesawat" path="jenisPesawat"
													class="form-control">
													<c:forEach var="pesawat" items="${pesawat}">
														<option value="${pesawat.pesawatDesc}"><c:out
																value="${pesawat.pesawatDesc}" /></option>
													</c:forEach>
												</form:select>
											</spring:bind>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">No.
											Pesawat</label>

										<div class="col-sm-6">
											<spring:bind path="noPesawat">
												<form:input id="noPesawat" type="text" class="form-control"
													path="noPesawat"></form:input>
											</spring:bind>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">Dari
											Lokasi</label>
										<div class="col-sm-6">
											<spring:bind path="dariLokasi">
												<form:select path="dariLokasi" id="dariLokasi"
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
										<label for="inputPassword3" class="col-sm-4 control-label">Destinasi</label>
										<div class="col-sm-6">
											<spring:bind path="destinasi">
												<form:select path="destinasi" id="destinasi"
													class="form-control">
													<c:forEach var="test" items="${lokasi}">
														<option value="${test.lokasiDesc}"><c:out
																value="${test.lokasiDesc}" /></option>
													</c:forEach>
												</form:select>
											</spring:bind>

										</div>
									</div>
									<div class="form-group" id="btnTambahAntarabandarHidden"
										onchange="showPenerbangan()">
										<div class="col-sm-12 control-label">
											<button type="button" class="btn btn-info pull-right"
												id="btnTambahDlmTable" onclick="tambahDlmTable()">Tambah</button>
										</div>
									</div>
									<div class="form-group" id="tablePenerbangan"
										onchange="showPenerbangan()">
										<table id="tablePenerbanganId"
											class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Penerbangan</th>
													<th>Tarikh Penerbangan</th>
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
												<c:forEach var="Penerbangan" items="${listPenerbangan}">
													<tr>
														<td><%=x%></td>
														<td><spring:bind path="penerbangan">
																<form:input type="text" class="form-control"
																	path="penerbangan"></form:input>
															</spring:bind></td>
														<td><spring:bind path="tarikhPergi">
																<form:input type="text" class="form-control"
																	path="tarikhPergi"></form:input>
															</spring:bind></td>
														<td><spring:bind path="waktuBerlepas">
																<form:input type="text" class="form-control"
																	path="waktuBerlepas"></form:input>
															</spring:bind></td>
														<td><spring:bind path="waktuTiba">
																<form:input type="text" class="form-control"
																	path="waktuTiba"></form:input>
															</spring:bind></td>
														<td><spring:bind path="noPesawat">
																<form:input type="text" class="form-control"
																	path="noPesawat"></form:input>
															</spring:bind></td>
														<td><spring:bind path="dariLokasi">
																<form:input type="text" class="form-control"
																	path="dariLokasi"></form:input>
															</spring:bind></td>
														<td><spring:bind path="destinasi">
																<form:input type="text" class="form-control"
																	path="destinasi"></form:input>
															</spring:bind></td>
														<td><button type="button" id="tambah"
																class="btn btn-info pull-right">Tambah</button></td>
														<!--  <td><input type="text" class="form-control"
																		id="bom"></td>
																	<td><input type="text" class="form-control"
																		id="bom"></td>
																	<td><input type="text" class="form-control"
																		id="bom"></td>
																	<td><input type="text" class="form-control"
																		id="bom"></td>-->
													</tr>
													<%
														x++;
													%>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div class="box-footer">
										<button type="button" onclick="changeTab2()"
											class="btn btn-info pull-right">Seterusnya</button>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="settings">
								<div class="box-body">
									<table id="tableBarangan"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Bil</th>
												<th>Barangan Dibawa</th>
												<th>Jumlah</th>
												<th>Anggaran Berat (Dalam KG)</th>

											</tr>
										</thead>
										<tbody>
											<%
												
											%>
											<tr>
												<td></td>
												<td><spring:bind path="baranganDibawa">
														<form:input type="text" id="baranganDibawa"
															class="form-control" path="baranganDibawa"></form:input>
													</spring:bind></td>
												<td><spring:bind path="jumlah">
														<form:input type="number" id="jumlah" class="form-control"
															path="jumlah"></form:input>
													</spring:bind></td>
												<td><spring:bind path="anggaranBerat">
														<form:input type="number" id="anggaranBerat"
															class="form-control" path="anggaranBerat"></form:input>
													</spring:bind></td>
												<td><button type="button" id="tambahBarangan"
														class="btn btn-info pull-right"
														onclick="baranganTableTambah()">Tambah</button></td>

											</tr>
											<%
												
											%>
										</tbody>
									</table>
									<!-- <div class="box-footer">
										
									</div> -->
									<div class="box-footer">
										<spring:bind path="total">
											<form:input type="number" id="total" class="form-control"
												path="total" placeholder="Jumlah Anggaran Berat"
												disabled="true"></form:input>
										</spring:bind>
									</div>

									<div class="box-footer">
										<button type="submit" class="btn btn-info pull-right">Mohon</button>
									</div>
									</form:form>
								</div>
							</div>
							<!-- /.tab-pane -->
						</div>
						<!-- /.tab-content -->
					</div>
					<!-- /.nav-tabs-custom -->
				</div>
				<!-- col-md-12 -->
			</div>
			<!-- /.modal-body -->
		</div>
		<!-- /.modal-content -->
	</div>
</div>

<script>
	var today = new Date();
	var dd = today.getDate() + 1;
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}
	today = yyyy + '-' + mm + '-' + dd;

	document.getElementById("date").setAttribute("min", today);
	document.getElementById("to").setAttribute("min", today);
	document.getElementById("tarikhPergi").setAttribute("min", today);
</script>