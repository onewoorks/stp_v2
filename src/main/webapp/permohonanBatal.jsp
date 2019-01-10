
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
	$(function() {
		var check = $('#batal').val();
		if (check > 0) {

			$('#modal-batal').modal('show');
		}
	});
</script>
<script>
	$('#batalTable').DataTable({
		'paging' : true,
		'lengthChange' : false,
		'searching' : false,
		'ordering' : true,
		'info' : true,
		'autoWidth' : false
	})
</script>

<div class="modal fade" id="modal-batal${pemohon.id}">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Batal</h4>
			</div>
			<div class="modal-body">
				<form:form method="POST" modelAttribute="permohonanBatal"
					action="${contextPath}/batalPermohonan" class="form-horizontal">
					<div class="box-body">
						<div class="form-group">
							<spring:bind path="id">
								<form:input type="hidden" class="form-control" path="id"
									id="batal"></form:input>
							</spring:bind>
							<spring:bind path="namaPelulus">
								<form:input type="hidden" class="form-control"
									path="namaPelulus" value="${pemohon.namaPelulus}"></form:input>
							</spring:bind>
							<spring:bind path="kp">
								<form:input type="hidden" class="form-control" path="kp"
									value="${pemohon.kp}"></form:input>
							</spring:bind>
							<spring:bind path="emel">
								<form:input type="hidden" class="form-control" path="emel"
									value="${pemohon.emel}"></form:input>
							</spring:bind>
							<spring:bind path="passport">
								<form:input type="hidden" class="form-control" path="passport"
									value="${pemohon.passport}"></form:input>
							</spring:bind>
							<spring:bind path="noTelefonBimbit">
								<form:input type="hidden" class="form-control"
									path="noTelefonBimbit" value="${pemohon.noTelefonBimbit}"></form:input>
							</spring:bind>
							<spring:bind path="peruntukan">
								<form:input type="hidden" class="form-control" path="peruntukan"
									value="${pemohon.peruntukan}"></form:input>
							</spring:bind>
							<spring:bind path="pembangunan">
								<form:input type="hidden" class="form-control"
									path="pembangunan" value="${pemohon.pembangunan}"></form:input>
							</spring:bind>
							<%-- <spring:bind path="jenisPesawat">
								<form:input type="hidden" class="form-control"
									path="jenisPesawat" value="${pemohon.jenisPesawat}"></form:input>
							</spring:bind> --%>
							<spring:bind path="noBilBom">
								<form:input type="hidden" class="form-control" path="noBilBom"
									value="${pemohon.noBilBom}"></form:input>
							</spring:bind>
							<spring:bind path="namaPemohon">
								<form:input type="hidden" class="form-control"
									path="namaPemohon" value="${pemohon.namaPemohon}"></form:input>
							</spring:bind>
							<spring:bind path="tarikhMula">
								<form:input type="hidden" class="form-control" path="tarikhMula"
									value="${pemohon.tarikhMula}"></form:input>
							</spring:bind>

							<label for="inputEmail3" class="col-sm-3 control-label">Nama
								Pemohon</label>

							<div class="col-sm-8">
								<spring:bind path="nama">
									<form:input type="text" class="form-control" id="nama"
										path="nama" value="${pemohon.nama}" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">Tarikh
								Permohonan</label>

							<div class="col-sm-8">
								<spring:bind path="tarikhMohon">
									<form:input type="text" class="form-control" id="tarikhMohon"
										path="tarikhMohon" value="${pemohon.tarikhMohon}" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">Unit</label>

							<div class="col-sm-8">
								<spring:bind path="bahagian">
									<form:input type="text" class="form-control" id="bahagian"
										path="bahagian" value="${pemohon.bahagian}" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">Tempat
								Bertugas</label>

							<div class="col-sm-8">
								<spring:bind path="tempatBertugas">
									<form:input type="text" class="form-control"
										id="tempatBertugas" path="tempatBertugas"
										value="${pemohon.tempatBertugas}" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">Tujuan</label>

							<div class="col-sm-8">
								<spring:bind path="tujuan">
									<form:input type="text" class="form-control" id="tujuan"
										path="tujuan" value="${pemohon.tujuan}" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>
						<div class="form-group">
							<table id="batalTable" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Bil</th>
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
									<c:forEach var="penerbangan" items="${Penerbangan}">
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
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">Catatan</label>

							<div class="col-sm-8">
								<spring:bind path="alasan">
									<form:input type="text" class="form-control" id="alasan"
										path="alasan"></form:input>
								</spring:bind>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-info pull-right">Batal</button>
					</div>
					<!-- /.box-footer -->
				</form:form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
