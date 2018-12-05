
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
	$(function() {
		var check = $('#kemaskini').val();
		if (check > 0) {

			$('#modal-permohonanKemaskini').modal('show');
		}
	});
</script>
<script>

	var kemasKiniClicked = false;

	$(function() {
		// Get the form fields and hidden div
		var checkbox2 = $("#wakil2");
		var hidden2 = $("#hidden2");
		// Hide the fields.
		// Use JS to do this in case the user doesn't have JS 
		// enabled.
		hidden2.hide();
		// Setup an event listener for when the state of the 
		// checkbox changes.
		checkbox2.change(function() {
			// Check to see if the checkbox is checked.
			// If it is, show the fields and populate the input.
			// If not, hide the fields.
			if (checkbox2.is(':checked')) {
				// Show the hidden fields.
				hidden2.show();
				// Populate the input.
			} else {
				// Make sure that the hidden fields are indeed
				// hidden.
				hidden2.hide();
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

function kemasKini(id,penerbanganId) {

	var pId = penerbanganId;
	globalPenerbanganId = penerbanganId;
	
	$.get("/kemasKiniUpdate?id="+id, function (data){
		var listPenerbanganDetails = JSON.parse(data);
		for(var i = 0;i<listPenerbanganDetails.length;i++){
			if(listPenerbanganDetails[i].penerbanganId == pId){
				var destinasi = document.getElementById('destinasi2');
				var dariLokasi = document.getElementById('dariLokasi2');
				var noPesawat = document.getElementById('noPesawat2');
				var jenisPesawat = document.getElementById('jenisPesawat2');
				var waktuTiba = document.getElementById('waktuTiba2');
				var tarikhPergi = document.getElementById('tarikhPergi2');
				var waktuBerlepas = document.getElementById('waktuBerlepas2');
				var penerbanganId = document.getElementById('penerbanganId2');

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


				getRowToDelete(listPenerbanganDetails[i]);
				
			}			
		}

	});

	kemasKiniClicked = true;

	var btnTambah = document.getElementById('btnTambahDlmTable2');
	btnTambah.innerText = "Kemaskini";
	
}
</script>
<script>

	var whichRowClicked = 0;

	function getRowToDelete(listPenerbanganDetails){

		var detailsCompare = listPenerbanganDetails;
		var table = document.getElementById('tablePenerbanganId2');

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
	function changeTab3() {
		$('[href="#timeline20"]').tab('show');
	}
	function changeTab4() {
		$('[href="#settings20"]').tab('show');
	}

</script>

<script>
	var baranganCount1 = 1;
	var totalAnggaran1 = 0;

	function baranganTableTambah2() {
		var baranganDibawa1 = document.getElementById('baranganDibawa1').value;
		var jumlah1 = document.getElementById('jumlah1').value;
		var anggaranBerat1 = document.getElementById('anggaranBerat1').value;
		var total1 = document.getElementById('total1');
		var table1 = document.getElementById('tableBarangan1');
		totalAnggaran1 = totalAnggaran1 + parseInt(anggaranBerat1, 10);

		if(total1.value != null){
			totalAnggaran1 = totalAnggaran1 + parseInt(total1.value,10);
		}
		
		$(
				"<tr><td>" + baranganCount1 + "</td><td>" + baranganDibawa1
						+ "</td><td>" + jumlah1 + "</td><td>" + anggaranBerat1
						+ "</td></tr>").appendTo(table1);
		baranganCount1++;
		total1.value = totalAnggaran1;

		var butiranBarangan1 = {
			"baranganDibawa" : baranganDibawa1,
			"anggaranBerat" : anggaranBerat1,
			"jumlah" : jumlah1,
			"total" : totalAnggaran1
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
		var baranganDibawa2 = document.getElementById('baranganDibawa1');
		var jumlah2 = document.getElementById('jumlah1');
		var anggaranBerat2 = document.getElementById('anggaranBerat1');
		baranganDibawa2.setValue = "";
		jumlah2.setValue = "";
		anggaranBerat2.setValue = "";
	}
</script>

<script>
	function tambahDlmTable2() {

		var destinasi2 = document.getElementById('destinasi2');
		var dariLokasi2 = document.getElementById('dariLokasi2');
		var noPesawat2 = document.getElementById('noPesawat2');
		var jenisPesawat2 = document.getElementById('jenisPesawat2');
		var waktuTiba2 = document.getElementById('waktuTiba2');
		var tarikhPergi2 = document.getElementById('tarikhPergi2');
		var waktuBerlepas2 = document.getElementById('waktuBerlepas2');
		var penerbanganId2 = document.getElementById('penerbanganId2');
		var table2 = document.getElementById('tablePenerbanganId2');

		$(
				"<tr><td>" + penerbanganId2.value + "</td><td>"
						+ tarikhPergi2.value + "</td><td>"
						+ waktuBerlepas2.value + "</td><td>" + waktuTiba2.value
						+ "</td><td>" + noPesawat2.value + "</td><td>"
						+ dariLokasi2.value + "</td><td>" + destinasi2.value
						+ "</td></tr>").appendTo(table2);
		
		if(kemasKiniClicked){

			var butiranPenerbangan2 = {
					"penerbanganId": globalPenerbanganId,
					"penerbangan" : penerbanganId2.value,
					"tarikhPergi" : tarikhPergi2.value,
					"waktuBerlepas" : waktuBerlepas2.value,
					"waktuTiba" : waktuTiba2.value,
					"noPesawat" : noPesawat2.value,
					"dariLokasi" : dariLokasi2.value,
					"destinasi" : destinasi2.value,
					"jenisPesawat" : jenisPesawat2.value,
				};
			
			var btnTambah = document.getElementById('btnTambahDlmTable2');
			btnTambah.innerText = "Tambah";
			kemasKiniClicked = false;

			$.ajax({
				type : "POST",
				//the url where you want to sent the userName and password to
				url : '/kemasPenerbanganTemp',
				contentType : 'application/json',
				data : JSON.stringify(butiranPenerbangan2),
				success : function() {
				}
			})

			table2.deleteRow(whichRowClicked);

			whichRowClicked = 0;
			globalPenerbanganId = 0;
			

			
		}else{
			var butiranPenerbangan2 = {
					"penerbangan" : penerbanganId2.value,
					"tarikhPergi" : tarikhPergi2.value,
					"waktuBerlepas" : waktuBerlepas2.value,
					"waktuTiba" : waktuTiba2.value,
					"noPesawat" : noPesawat2.value,
					"dariLokasi" : dariLokasi2.value,
					"destinasi" : destinasi2.value,
					"jenisPesawat" : jenisPesawat2.value,
				};
			
			$.ajax({
				type : "POST",
				//the url where you want to sent the userName and password to
				url : '/penerbanganTemp',
				contentType : 'application/json',
				data : JSON.stringify(butiranPenerbangan2),
				success : function() {
				}
			})
		}

		destinasi2.value = "";
		dariLokasi2.value = "";
		noPesawat2.value = "";
		jenisPesawat2.value = "";
		waktuTiba2.value = "";
		tarikhPergi2.value = "";
		waktuBerlepas2.value = "";
		penerbanganId2.value = "";
	}

</script>
<script>
	function show2() {
		var pembangunanHidden2 = document.getElementById('pembangunanHidden2');
		pembangunanHidden2.style.display = "none";

		var bomHidden2 = document.getElementById('bomHidden2');
		bomHidden2.style.display = "none";

		var muatNaikHidden2 = document.getElementById('muatNaikHidden2');
		muatNaikHidden2.style.display = "none";

		var peruntukanId2 = document.getElementById('peruntukanId2').value;

		if (peruntukanId2 == 'Pembangunan') {
			pembangunanHidden2.style.display = "inline";
			bomHidden2.style.display = "inline";
			muatNaikHidden2.style.display = "inline";
		} else if (peruntukanId2 == "Operasi") {
			pembangunanHidden2.style.display = "none";
			bomHidden2.style.display = "none";
			muatNaikHidden2.style.display = "none";
		}
	};
</script>

<div class="modal fade" id="modal-permohonanKemaskini${pemohon.id}">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Kemaskini Permohonan</h4>
			</div>
			<div class="modal-body">
				<div class="box-body">
					<div class="col-md-12">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#activity20" data-toggle="tab">Maklumat
										Penerbangan</a></li>
								<li><a href="#timeline20" data-toggle="tab">Butiran
										Penerbangan</a></li>
								<li><a href="#settings20" data-toggle="tab">Butiran
										Barangan</a></li>
							</ul>
							<div class="tab-content">
								<div class="active tab-pane" id="activity20">
									<div class="box-body">
										<form:form method="POST"
											modelAttribute="permohonanKemaskiniTemp"
											action="${contextPath}/permohonanKemaskiniTemp"
											enctype="multipart/form-data" class="form-horizontal">
											<spring:bind path="id">
												<form:input type="hidden" class="form-control" path="id"
													id="kemaskini" value="${pemohon.id}"></form:input>
											</spring:bind>
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-2 control-label">Wakil
													Pemohon</label>

												<div class="col-sm-4">
													<spring:bind path="wakilPermohon">
														<form:checkbox path="wakilPermohon" id="wakil2"></form:checkbox>
													</spring:bind>
												</div>

												<div id="hidden2">
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
												<label for="inputPassword3" class="col-sm-2 control-label	">No.
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
															path="tarikhMula" value="${tarikhMula}"></form:input>
													</spring:bind>
												</div>
												<label for="inputPassword3" class="col-sm-2 control-label">Tarikh
													Tamat Bertugas</label>

												<div class="col-sm-4">
													<spring:bind path="tarikhTamat">
														<form:input type="date" class="form-control"
															path="tarikhTamat" value="${tarikhTamat}"></form:input>
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
															path="enrichNo" required="required"
															value="${user.enrichNo}"></form:input>
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
															id="peruntukanId2" onchange="show2()">
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
												<div id="pembangunanHidden2">
													<label for="inputPassword3" class="col-sm-2 control-label">Pembangunan</label>
													<div class="col-sm-4">
														<spring:bind path="pembangunan">
															<form:select path="pembangunan" class="form-control">
																<c:forEach var="peruntukan" items="${peruntukan}">
																
																 <c:if test="${pembangunan == peruntukan.peruntukanDesc}">
															        <option value="${peruntukan.peruntukanDesc}" selected><c:out
																			value="${peruntukan.peruntukanDesc}" /></option>
															    </c:if>
															    <c:if test="${pembangunan != peruntukan.peruntukanDesc}">
															    	<option value="${peruntukan.peruntukanDesc}"><c:out
																			value="${peruntukan.peruntukanDesc}" /></option>
															    </c:if>
																			
																</c:forEach>
															</form:select>
														</spring:bind>
													</div>
												</div>
												<div id="bomHidden2">
													<label for="inputPassword3" class="col-sm-2 control-label">No.
														Bil BOM</label>

													<div class="col-sm-4">
														<spring:bind path="noBilBom">
															<form:input type="text" class="form-control"
																path="noBilBom" value="${noBilBom}"></form:input>
														</spring:bind>
													</div>
												</div>
											</div>

											<div class="form-group" id="muatNaikHidden2">
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
												<button type="button" onclick="changeTab3()"
													class="btn btn-info pull-right">Seterusnya</button>
											</div>
									</div>
									<!-- /.box-body -->

									<!-- /.box-footer -->
								</div>
								<div class="tab-pane" id="timeline20">
									<!-- /.box-header -->
									<div class="box-body">
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Penerbangan</label>
											<div class="col-sm-6">
												<c:forEach items="${penerbangan}" var="penerbangan">
													<spring:bind path="penerbanganId">
														<form:input type="hidden" class="form-control"
															path="penerbanganId" value="${penerbangan.penerbanganId}"></form:input>
													</spring:bind>
												</c:forEach>
												<spring:bind path="penerbangan">
													<form:select path="penerbangan" class="form-control"
														id="penerbanganId2" onchange="showPenerbangan1()">
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
													<form:input type="date" id="tarikhPergi2"
														class="form-control" path="tarikhPergi"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Waktu
												Berlepas</label>

											<div class="col-sm-6">
												<spring:bind path="waktuBerlepas">
													<form:input id="waktuBerlepas2" type="time"
														class="form-control" path="waktuBerlepas"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Waktu
												Tiba</label>

											<div class="col-sm-6">
												<spring:bind path="waktuTiba">
													<form:input id="waktuTiba2" type="time"
														class="form-control" path="waktuTiba"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Jenis
												Pesawat</label>
											<div class="col-sm-6">
												<spring:bind path="jenisPesawat">
													<form:select id="jenisPesawat2" path="jenisPesawat"
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
													<form:input id="noPesawat2" type="text"
														class="form-control" path="noPesawat"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Dari
												Lokasi</label>
											<div class="col-sm-6">
												<spring:bind path="dariLokasi">
													<form:select path="dariLokasi" id="dariLokasi2"
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
													<form:select path="destinasi" id="destinasi2"
														class="form-control">
														<c:forEach var="test" items="${lokasi}">
															<option value="${test.lokasiDesc}"><c:out
																	value="${test.lokasiDesc}" /></option>
														</c:forEach>
													</form:select>
												</spring:bind>

											</div>
										</div>
										<div class="form-group" id="btnTambahAntarabandarHidden2"
											onchange="showPenerbangan1()">
											<div class="col-sm-12 control-label">
												<button type="button" class="btn btn-info pull-right"
													id="btnTambahDlmTable2" onclick="tambahDlmTable2()">Tambah</button>
											</div>
										</div>
										<div class="form-group" id="tablePenerbangan2"
											onchange="showPenerbangan1()">
											<table id="tablePenerbanganId2"
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
															<td><button type="button" value="${Penerbangan}" onclick="kemasKini(${Penerbangan.permohonan.id},${Penerbangan.penerbanganId})" id="tambah" class="btn btn-info pull-right">Kemaskini</button></td>
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
											<button type="button" onclick="changeTab4()"
												class="btn btn-info pull-right">Seterusnya</button>
										</div>

									</div>
								</div>
								<div class="tab-pane" id="settings20">
									<div class="box-body">

											<table id="tableBarangan1"
												class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Bil</th>
														<th>Barangan Dibawa</th>
														<th>Jumlah</th>
														<th>Anggaran Berat (Dalam KG)</th>
														<th>Tindakan</th>

													</tr>
												</thead>
				
												<tbody>
													<%
														
													%>

													<tr>
														<td></td>
														<td><spring:bind path="baranganDibawa">
																<form:input type="text" id="baranganDibawa1"
																	class="form-control" path="baranganDibawa"></form:input>
															</spring:bind></td>
														<td><spring:bind path="jumlah">
																<form:input type="number" id="jumlah1"
																	class="form-control" path="jumlah"></form:input>
															</spring:bind></td>
														<td><spring:bind path="anggaranBerat">
																<form:input type="number" id="anggaranBerat1"
																	class="form-control" path="anggaranBerat"></form:input>
															</spring:bind></td>
														<td><button type="button" id="tambahBarangan1"
																class="btn btn-info pull-right"
																onclick="baranganTableTambah2()">Tambah</button></td>

													</tr>
										<c:forEach items="${barangan}" var="barangan">
											<spring:bind path="baranganId">
												<form:input type="hidden" class="form-control"
													path="baranganId" value="${barangan.baranganId}"></form:input>
											</spring:bind>
													
												<tr>
												<td></td>
												<td>${barangan.baranganDibawa}</td>
												<td>${barangan.jumlah}</td>
												<td>${barangan.anggaranBerat}</td>
												</tr>
													
										</c:forEach>
													<%
														
													%>
												</tbody>
											</table>
											<div class="box-footer">
												<spring:bind path="total">
													<form:input type="number" id="total1" class="form-control"
														path="total" placeholder="Jumlah Anggaran Berat"
														disabled="true" value="${anggaranTotalBerat}"></form:input>
												</spring:bind>
											</div>
									

										<div class="box-footer">
											<button type="submit" class="btn btn-danger pull-right">Mohon</button>
										</div>

									</div>
								</div>
								</form:form>
							</div>
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
<script>
	show2();

	$.get("/kemasKiniPeruntukan",function (data){ console.log(data)});
	
</script>
<!-- /.modal-content -->