
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
	$(function() {
		var check = $('#open').val();
		if (check > 0) {

			$('#modal-permohonanOpen').modal('show');
		}
	});
</script>
<script>

	var kemasKiniClicked = false;

	$(function() {

		// Get the form fields and hidden div
		var checkbox3 = $("#wakil3");
		var hidden3 = $("#hidden3");

		// Hide the fields.
		// Use JS to do this in case the user doesn't have JS 
		// enabled.
		hidden3.hide();

		// Setup an event listener for when the state of the 
		// checkbox changes.
		checkbox3.change(function() {
			// Check to see if the checkbox is checked.
			// If it is, show the fields and populate the input.
			// If not, hide the fields.
			if (checkbox3.is(':checked')) {
				// Show the hidden fields.
				hidden3.show();
				// Populate the input.
			} else {
				// Make sure that the hidden fields are indeed
				// hidden.
				hidden3.hide();

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

var globalPenerbanganId = 0;

function kemasKini3(id,penerbanganId) {
	var pId = penerbanganId;
	globalPenerbanganId = penerbanganId;
	
	$.get("/kemasKiniUpdate?id="+id, function (data){
		var listPenerbanganDetails = JSON.parse(data);
		console.log(listPenerbanganDetails);
		for(var i = 0;i<listPenerbanganDetails.length;i++){
			if(listPenerbanganDetails[i].penerbanganId == pId){
				var destinasi = document.getElementById('destinasi3');
				var dariLokasi = document.getElementById('dariLokasi3');
				var noPesawat = document.getElementById('noPesawat3');
				var jenisPesawat = document.getElementById('jenisPesawat3');
				var waktuTiba = document.getElementById('waktuTiba3');
				var tarikhPergi = document.getElementById('tarikhPergi3');
				var waktuBerlepas = document.getElementById('waktuBerlepas3');
				var penerbanganId = document.getElementById('penerbanganId3');

				if(listPenerbanganDetails[i].penerbangan != null){
					penerbanganId.value = listPenerbanganDetails[i].penerbangan;
				}
				if(listPenerbanganDetails[i].tarikhPergi != null){
					tarikhPergi.value = listPenerbanganDetails[i].tarikhPergi;
				}
				if(listPenerbanganDetails[i].waktuBerlepas != null){
					waktuBerlepas.value = listPenerbanganDetails[i].waktuBerlepas;
				}
				if(listPenerbanganDetails[i].waktuTiba != null){
					waktuTiba.value = listPenerbanganDetails[i].waktuTiba;
				}
				if(listPenerbanganDetails[i].jenisPesawat != null){
					jenisPesawat.value = listPenerbanganDetails[i].jenisPesawat;
				}
				if(listPenerbanganDetails[i].noPesawat != null){
					noPesawat.value = listPenerbanganDetails[i].noPesawat;
				}
				if(listPenerbanganDetails[i].dariLokasi != null){
					dariLokasi.value = listPenerbanganDetails[i].dariLokasi;
				}
				if(listPenerbanganDetails[i].destinasi != null){
					destinasi.value = listPenerbanganDetails[i].destinasi;
				}


				getRowToDelete3(listPenerbanganDetails[i]);
				
			}			
		}

	});

	kemasKiniClicked = true;

	var btnTambah = document.getElementById('btnTambahDlmTable3');
	btnTambah.innerText = "Kemaskini";
	
}
</script>
<script>

	var whichRowClicked = 0;

	function getRowToDelete3(listPenerbanganDetails){

		var detailsCompare = listPenerbanganDetails;
		var table = document.getElementById('tablePenerbanganId3');

		for(var i = 0; i<table.rows.length; i++){
			var row = table.rows[i];

			var penerbangan = row.cells[0].innerText;
			var tarikPenerbangan = row.cells[1].innerText;
			var waktuBerlepas = row.cells[2].innerText;
			var waktuTiba = row.cells[3].innerText;

			if(penerbangan == detailsCompare.penerbangan && tarikPenerbangan == detailsCompare.tarikhPergi && waktuBerlepas == detailsCompare.waktuBerlepas && waktuTiba == detailsCompare.waktuTiba){
				whichRowClicked = i;
			}
			
			
		}
		
	}
</script>
<script>
	function changeTab5() {
		$('[href="#timeline3"]').tab('show');
	}
	function changeTab6() {
		$('[href="#settings3"]').tab('show');
	}
</script>

<script>
	var baranganCount2 = 1;
	var totalAnggaran2 = 0;

	function baranganTableTambah3() {
		var baranganDibawa2 = document.getElementById('baranganDibawa2').value;
		var jumlah2 = document.getElementById('jumlah2').value;
		var anggaranBerat2 = document.getElementById('anggaranBerat2').value;
		var total2 = document.getElementById('total2');
		var table3 = document.getElementById('tableBarangan2');
		totalAnggaran2 = totalAnggaran2 + parseInt(anggaranBerat2, 10);

		$(
				"<tr><td>" + baranganCount2 + "</td><td>" + baranganDibawa2
						+ "</td><td>" + jumlah2 + "</td><td>" + anggaranBerat2
						+ "</td></tr>").appendTo(table3);
		baranganCount2++;
		total2.value = totalAnggaran2;

		var butiranBarangan2 = {
			"baranganDibawa" : baranganDibawa2,
			"anggaranBerat" : anggaranBerat2,
			"jumlah" : jumlah2,
			"total" : totalAnggaran2
		};

		$.ajax({
			type : "POST",
			//the url where you want to sent the userName and password to
			url : '/baranganTemp',
			contentType : 'application/json',
			data : JSON.stringify(butiranBarangan1),
			success : function() {

			}
		})
		var baranganDibawa3 = document.getElementById('baranganDibawa2');
		var jumlah3 = document.getElementById('jumlah2');
		var anggaranBerat3 = document.getElementById('anggaranBerat2');
		baranganDibawa3.setValue = "";
		jumlah3.setValue = "";
		anggaranBerat3.setValue = "";
	}
</script>

<script>
	function tambahDlmTable3() {
		var destinasi3 = document.getElementById('destinasi3');
		var dariLokasi3 = document.getElementById('dariLokasi3');
		var noPesawat3 = document.getElementById('noPesawat3');
		var jenisPesawat3 = document.getElementById('jenisPesawat3');
		var waktuTiba3 = document.getElementById('waktuTiba3');
		var tarikhPergi3 = document.getElementById('tarikhPergi3');
		var waktuBerlepas3 = document.getElementById('waktuBerlepas3');
		var penerbanganId3 = document.getElementById('penerbanganId3');
		var table3 = document.getElementById('tablePenerbanganId3');
		
		$(
				"<tr><td>" + penerbanganId3.value + "</td><td>"
						+ tarikhPergi3.value + "</td><td>"
						+ waktuBerlepas3.value + "</td><td>" + waktuTiba3.value
						+ "</td><td>" + noPesawat3.value + "</td><td>"
						+ dariLokasi3.value + "</td><td>" + destinasi3.value
						+ "</td></tr>").appendTo(table3);
		
		if(kemasKiniClicked){

			var butiranPenerbangan3 = {
					"penerbanganId": globalPenerbanganId,
					"penerbangan" : penerbanganId3.value,
					"tarikhPergi" : tarikhPergi3.value,
					"waktuBerlepas" : waktuBerlepas3.value,
					"waktuTiba" : waktuTiba3.value,
					"noPesawat" : noPesawat3.value,
					"dariLokasi" : dariLokasi3.value,
					"destinasi" : destinasi3.value,
					"jenisPesawat" : jenisPesawat3.value,
				};
			
			var btnTambah = document.getElementById('btnTambahDlmTable3');
			btnTambah.innerText = "Tambah";
			kemasKiniClicked = false;

			$.ajax({
				type : "POST",
				//the url where you want to sent the userName and password to
				url : '/kemasPenerbanganTemp',
				contentType : 'application/json',
				data : JSON.stringify(butiranPenerbangan3),
				success : function() {
				}
			})

			table3.deleteRow(whichRowClicked);

			whichRowClicked = 0;
			globalPenerbanganId = 0;
			

			
		}else{
			var butiranPenerbangan3 = {
					"penerbangan" : penerbanganId3.value,
					"tarikhPergi" : tarikhPergi3.value,
					"waktuBerlepas" : waktuBerlepas3.value,
					"waktuTiba" : waktuTiba3.value,
					"noPesawat" : noPesawat3.value,
					"dariLokasi" : dariLokasi3.value,
					"destinasi" : destinasi3.value,
					"jenisPesawat" : jenisPesawat3.value,
				};
			
			$.ajax({
				type : "POST",
				//the url where you want to sent the userName and password to
				url : '/penerbanganTemp',
				contentType : 'application/json',
				data : JSON.stringify(butiranPenerbangan3),
				success : function() {
				}
			})
		}

		
		destinasi3.value = "";
		dariLokasi3.value = "";
		noPesawat3.value = "";
		jenisPesawat3.value = "";
		waktuTiba3.value = "";
		tarikhPergi3.value = "";
		waktuBerlepas3.value = "";
		penerbanganId3.value = "";
	}
</script>
<script>
	function show3() {

		var pembangunanHidden3 = document.getElementById('pembangunanHidden3');
		pembangunanHidden3.style.display = "none";

		var bomHidden3 = document.getElementById('bomHidden3');
		bomHidden3.style.display = "none";

		var muatNaikHidden3 = document.getElementById('muatNaikHidden3');
		muatNaikHidden3.style.display = "none";

		var peruntukanId3 = document.getElementById('peruntukanId3').value;

		if (peruntukanId3 == 'Pembangunan') {
			pembangunanHidden3.style.display = "inline";
			bomHidden3.style.display = "inline";
			muatNaikHidden3.style.display = "inline";
		} else if (peruntukanId3 == "Operasi") {
			pembangunanHidden3.style.display = "none";
			bomHidden3.style.display = "none";
			muatNaikHidden3.style.display = "none";
		}
	};
</script>

<div class="modal fade" id="modal-permohonanOpen">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Permohonan Open Tiket</h4>
			</div>
			<div class="modal-body">
				<div class="box-body">
					<div class="col-md-12">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#activity3" data-toggle="tab">Maklumat
										Penerbangan</a></li>
								<li><a href="#timeline3" data-toggle="tab">Butiran
										Penerbangan</a></li>
								<li><a href="#settings3" data-toggle="tab">Butiran
										Barangan</a></li>
							</ul>
							<div class="tab-content">
								<div class="active tab-pane" id="activity3">
									<div class="box-body">
										<form:form method="POST" modelAttribute="permohonanOpen"
											action="${contextPath}/permohonanOpen"
											enctype="multipart/form-data" class="form-horizontal">
											<spring:bind path="id">
												<form:input type="hidden" class="form-control" path="id"
													id="open"></form:input>
											</spring:bind>
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-2 control-label">Nama</label>

												<div class="col-sm-4">
													<spring:bind path="nama">
														<form:input type="text" class="form-control" path="nama"
															placeholder="Seperti Dalam Kad Pengenalan"
															readonly="true" id="nama" value="${user.namaStaff}"></form:input>
													</spring:bind>
												</div>
												<label for="inputEmail3" class="col-sm-2 control-label">Nama
													Pelulus</label>

												<div class="col-sm-4">
													<spring:bind path="namaPelulus">
														<form:input type="text" class="form-control"
															path="namaPelulus"
															placeholder="Seperti Dalam Kad Pengenalan"
															readonly="true" value="${user.namaPengurus}"></form:input>
													</spring:bind>
												</div>
											</div>
											<div class="form-group">
												<label for="inputPassword3" class="col-sm-2 control-label">No.
													KP</label>

												<div class="col-sm-4">
													<spring:bind path="kp">
														<form:input type="text" class="form-control" path="kp"
															id="kp" value="${user.noKP}" readonly="true"></form:input>
													</spring:bind>
												</div>
												<label for="inputPassword3" class="col-sm-2 control-label">Unit</label>

												<div class="col-sm-4">
													<spring:bind path="bahagian">
														<form:input type="text" class="form-control"
															path="bahagian" readonly="true" value="${user.unit}"></form:input>
													</spring:bind>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-2 control-label">Emel</label>

												<div class="col-sm-4">
													<spring:bind path="emel">
														<form:input type="email" class="form-control" path="emel"
															readonly="true" value="${user.email}"></form:input>
													</spring:bind>
												</div>
												<label for="inputPassword3" class="col-sm-2 control-label">No.
													Pasport</label>

												<div class="col-sm-4">
													<spring:bind path="passport">
														<form:input type="text" class="form-control"
															path="passport" readonly="true" value="${user.passport}"></form:input>
													</spring:bind>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-2 control-label">Tujuan</label>

												<div class="col-sm-4">
													<spring:bind path="tujuan">
														<form:input type="text" class="form-control" path="tujuan"
															readonly="true" value="${tujuan}"></form:input>
													</spring:bind>
												</div>
												<label for="inputPassword3" class="col-sm-2 control-label">Tempat
													Bertugas</label>

												<div class="col-sm-4">
													<spring:bind path="tempatBertugas">
														<form:input type="text" class="form-control"
															path="tempatBertugas" readonly="true" value="${tempat}"></form:input>
													</spring:bind>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-2 control-label">Tarikh
													Mula Bertugas</label>

												<div class="col-sm-4">
													<spring:bind path="tarikhMula">
														<form:input type="date" class="form-control"
															path="tarikhMula"></form:input>
													</spring:bind>
												</div>
												<label for="inputPassword3" class="col-sm-2 control-label">Tarikh
													Tamat Bertugas</label>

												<div class="col-sm-4">
													<spring:bind path="tarikhTamat">
														<form:input type="date" class="form-control"
															path="tarikhTamat"></form:input>
													</spring:bind>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-2 control-label">No.
													Telefon Bimbit</label>

												<div class="col-sm-4">
													<spring:bind path="noTelefonBimbit">
														<form:input type="text" class="form-control"
															path="noTelefonBimbit" readonly="true"
															value="${user.noTelefon}"></form:input>
													</spring:bind>
												</div>
												<label for="inputPassword3" class="col-sm-2 control-label">No
													Enrich</label>

												<div class="col-sm-4">
													<spring:bind path="enrichNo">
														<form:input type="text" class="form-control"
															path="enrichNo" value="${user.enrichNo}" readonly="true"></form:input>
													</spring:bind>
												</div>

											</div>
											<div class="form-group">
												<label for="inputPassword3" class="col-sm-2 control-label"
													data-toogle="tooltip" data-placement="left"
													title="Sila maklumkan pilihan makanan dan tempat duduk sekiranya perlu. Tertakluk kepada kesediaan">Catatan</label>
												<div class="col-sm-4">
													<spring:bind path="catatan">
														<form:input type="text" class="form-control"
															path="catatan" id="catatan" readonly="true" value="${catatan}"></form:input>
													</spring:bind>
												</div>
												<label for="inputPassword3" class="col-sm-2 control-label">Peruntukan</label>
												<div class="col-sm-4">
													<spring:bind path="peruntukan">
														<form:select path="peruntukan" class="form-control"
															id="peruntukanId2" onchange="show2()" readonly="true">
															<c:if test="${peruntukanPermohonan == 'Pembangunan'}">
																<option value="Operasi">Operasi</option>
																<option value="Pembangunan" selected>Pembangunan</option>
															</c:if>
															<c:if test="${peruntukanPermohonan == 'Operasi'}">
																<option value="Operasi" selected>Operasi</option>
																<option value="Pembangunan">Pembangunan</option>
															</c:if>
														</form:select>
													</spring:bind>
												</div>
											</div>

											<div class="form-group">
												<div id="pembangunanHidden3">
													<label for="inputPassword3" class="col-sm-2 control-label">Pembangunan</label>
													<div class="col-sm-4">
														<spring:bind path="pembangunan">
															<form:select path="pembangunan" class="form-control"
																readonly="true">
																<c:forEach var="peruntukan" items="${peruntukan}">

																	<c:if
																		test="${pembangunan == peruntukan.peruntukanDesc}">
																		<option value="${peruntukan.peruntukanDesc}" selected><c:out
																				value="${peruntukan.peruntukanDesc}" /></option>
																	</c:if>
																	<c:if
																		test="${pembangunan != peruntukan.peruntukanDesc}">
																		<option value="${peruntukan.peruntukanDesc}"><c:out
																				value="${peruntukan.peruntukanDesc}" /></option>
																	</c:if>

																</c:forEach>
															</form:select>
														</spring:bind>
													</div>
												</div>
												<div id="bomHidden3">
													<label for="inputPassword3" class="col-sm-2 control-label">No.
														Bil BOM</label>

													<div class="col-sm-4">
														<spring:bind path="noBilBom">
															<form:input type="text" class="form-control"
																path="noBilBom" value="${noBilBom}" readonly="true"></form:input>
														</spring:bind>
													</div>
												</div>
											</div>

											<div class="form-group" id="muatNaikHidden3">
												<label for="exampleInputFile" class="col-sm-2 control-label">Muatnaik
													Lampiran</label>
												<div class="col-sm-4">
													<spring:bind path="muatNaikBom">
														<form:input type="file" class="form-control-file"
															path="muatNaikBom" id="exampleInputFile"></form:input>
													</spring:bind>
												</div>
											</div>

											<div class="box-footer">
												<button type="button" onclick="changeTab5()"
													class="btn btn-info pull-right">Seterusnya</button>
											</div>
									</div>
									<!-- /.box-body -->

									<!-- /.box-footer -->
								</div>
								<div class="tab-pane" id="timeline3">
									<!-- /.box-header -->
									<div class="box-body">
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Penerbangan</label>
											<div class="col-sm-6">
												<spring:bind path="penerbangan">
													<form:select path="penerbangan" class="form-control"
														id="penerbanganId3" onchange="showPenerbangan3()"
														readonly="true">
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
													<form:input type="date" id="tarikhPergi3"
														class="form-control" path="tarikhPergi"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Waktu
												Berlepas</label>

											<div class="col-sm-6">
												<spring:bind path="waktuBerlepas">
													<form:input id="waktuBerlepas3" type="time"
														class="form-control" path="waktuBerlepas"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Waktu
												Tiba</label>

											<div class="col-sm-6">
												<spring:bind path="waktuTiba">
													<form:input id="waktuTiba3" type="time"
														class="form-control" path="waktuTiba"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Jenis
												Pesawat</label>
											<div class="col-sm-6">
												<spring:bind path="jenisPesawat">
													<form:select id="jenisPesawat3" path="jenisPesawat"
														class="form-control" readonly="true">
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
													<form:input id="noPesawat3" type="text"
														class="form-control" path="noPesawat" readonly="true"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Dari
												Lokasi</label>
											<div class="col-sm-6">
												<spring:bind path="dariLokasi">
													<form:select path="dariLokasi" id="dariLokasi3"
														class="form-control" readonly="true">
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
													<form:select path="destinasi" id="destinasi3"
														class="form-control" readonly="true">
														<c:forEach var="test" items="${lokasi}">
															<option value="${test.lokasiDesc}"><c:out
																	value="${test.lokasiDesc}" /></option>
														</c:forEach>
													</form:select>
												</spring:bind>

											</div>
										</div>
										<div class="form-group" id="btnTambahAntarabandarHidden3"
											onchange="showPenerbangan3()">
											<div class="col-sm-12 control-label">
												<button type="button" class="btn btn-info pull-right"
													id="btnTambahDlmTable3" onclick="tambahDlmTable3()">Tambah</button>
											</div>
										</div>
										<div class="form-group" id="tablePenerbangan3"
											onchange="showPenerbangan3()">
											<table id="tablePenerbanganId3"
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
														<th>Tindakan</th>
													</tr>
												</thead>
												<tbody>
													<%
														int x = 1;
													%>
													<c:forEach var="Penerbangan" items="${penerbangan}">
														<tr>
															<td><spring:bind path="penerbangan">
																	${Penerbangan.penerbangan}
																</spring:bind></td>
															<td><spring:bind path="tarikhPergi">
																	${Penerbangan.tarikhPergi }
																</spring:bind></td>
															<td><spring:bind path="waktuBerlepas">
																	${Penerbangan.waktuBerlepas }
																</spring:bind></td>
															<td><spring:bind path="waktuTiba">
																	${Penerbangan.waktuTiba}
																</spring:bind></td>
															<td><spring:bind path="noPesawat">${Penerbangan.noPesawat}
																</spring:bind></td>
															<td><spring:bind path="dariLokasi">${Penerbangan.dariLokasi }
																</spring:bind></td>
															<td><spring:bind path="destinasi">
																	${Penerbangan.destinasi}
																</spring:bind></td>
															<td><button type="button" value="${Penerbangan}"
																	onclick="kemasKini3(${Penerbangan.permohonan.id},${Penerbangan.penerbanganId})"
																	id="tambah" class="btn btn-info pull-right">Kemaskini</button></td>
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
											<button type="button" onclick="changeTab6()"
												class="btn btn-info pull-right">Seterusnya</button>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="settings3">
									<div class="box-body">
										<table id="tableBarangan2"
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
															<form:input type="text" id="baranganDibawa2"
																class="form-control" path="baranganDibawa"></form:input>
														</spring:bind></td>
													<td><spring:bind path="jumlah">
															<form:input type="number" id="jumlah2"
																class="form-control" path="jumlah"></form:input>
														</spring:bind></td>
													<td><spring:bind path="anggaranBerat">
															<form:input type="number" id="anggaranBerat2"
																class="form-control" path="anggaranBerat"></form:input>
														</spring:bind></td>
													<td><button type="button" id="tambahBarangan2"
															class="btn btn-info pull-right"
															onclick="baranganTableTambah3()">Tambah</button></td>

												</tr>
												<%
													
												%>
											</tbody>
										</table>
										<!-- <div class="box-footer">
										
									</div> -->
										<div class="box-footer">
											<spring:bind path="total">
												<form:input type="number" id="total2" class="form-control"
													path="total" placeholder="Jumlah Anggaran Berat"
													disabled="true"></form:input>
											</spring:bind>
										</div>

										<div class="box-footer">
											<button type="submit" class="btn btn-danger pull-right">Mohon</button>
										</div>

									</div>
								</div>
								</form:form>
							</div>
							<!-- /.tab-content -->
						</div>
						<!-- /.nav-tabs-custom -->
					</div>
					<!-- /.box-body -->
					<!-- /.box-footer -->
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal-content -->
</div>