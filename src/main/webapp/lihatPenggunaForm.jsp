
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
	$(function() {
		var check = $('#lihat').val();
		if (check > 0) {

			$('#modal-lihatPenggunaForm').modal('show');
		}
	});
</script>
<div class="modal fade" id="modal-lihatPenggunaForm">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Lihat Pengguna</h4>
			</div>
			<form:form method="POST" modelAttribute="lihatPenggunaForm"
				action="${contextPath}/daftarPengguna" class="form-horizontal">
				<div class="modal-body">

					<spring:bind path="id">
						<form:input type="hidden" class="form-control" path="id"
							id="lihat"></form:input>
					</spring:bind>
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Nombor
								Staf</label>

							<div class="col-sm-4">
								<spring:bind path="staffNo">
									<form:input type="text" class="form-control" path="staffNo"
										placeholder="Staf No" readonly="true"></form:input>
								</spring:bind>
							</div>
							<label for="inputEmail3" class="col-sm-2 control-label">Nama
								Pengguna</label>

							<div class="col-sm-4">
								<spring:bind path="username">
									<form:input type="text" class="form-control" path="username"
										placeholder="Nama Pengguna" readonly="true"></form:input>
								</spring:bind>
							</div>

						</div>


						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Nama</label>

							<div class="col-sm-4">
								<spring:bind path="namaStaff">
									<form:input type="text" class="form-control" path="namaStaff"
										placeholder="Nama Staf" readonly="true"></form:input>
								</spring:bind>
							</div>
							<label for="inputEmail3" class="col-sm-2 control-label">Enrich
								No</label>

							<div class="col-sm-4">
								<spring:bind path="enrichNo">
									<form:input type="text" class="form-control" path="enrichNo"
										placeholder="Enrich No" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">No.
								KP</label>

							<div class="col-sm-4">
								<spring:bind path="noKP">
									<form:input type="text" class="form-control" path="noKP"
										placeholder="Nombor Kad Pengenalan" readonly="true"></form:input>
								</spring:bind>
							</div>
							<label class="col-sm-2 control-label">Passport</label>

							<div class="col-sm-4">
								<spring:bind path="passport">
									<form:input type="text" class="form-control" path="passport"
										placeholder="No Passport" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Emel</label>

							<div class="col-sm-4">
								<spring:bind path="email">
									<form:input type="text" class="form-control" path="email"
										placeholder="email(exp:xxx@yahoo.com)" readonly="true"></form:input>
								</spring:bind>
							</div>
							<label class="col-sm-2 control-label">No. Telefon Bimbit</label>

							<div class="col-sm-4">
								<spring:bind path="noTelefon">
									<form:input type="text" class="form-control" path="noTelefon"
										placeholder="No telefon Bimbit" readonly="true"></form:input>
								</spring:bind>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Jawatan</label>

							<div class="col-sm-4">
								<spring:bind path="refJawatan">
									<form:select path="refJawatan" class="form-control" readonly="true">
										<c:forEach var="testJawatan" items="${listJawatan}">

											<c:if test="${jawatan == testJawatan.jawatanDesc}">
												<option value="${testJawatan.jawatanDesc}" selected><c:out
														value="${testJawatan.jawatanDesc}" /></option>
											</c:if>
											<c:if test="${jawatan != testJawatan.jawatanDesc}">
												<option value="${testJawatan.jawatanDesc}"><c:out
														value="${testJawatan.jawatanDesc}" /></option>
											</c:if>
										</c:forEach>
									</form:select>
								</spring:bind>
							</div>
							<label class="col-sm-2 control-label">Status</label>

							<div class="col-sm-4">
								<form:select path="status" class="form-control" readonly="true">
									<option></option>
									<c:if test="${penggunaStatus == 'Aktif'}">
										<option value="Aktif" selected>Aktif</option>
										<option value="Tidak Aktif">Tidak Aktif</option>
									</c:if>
									<c:if test="${penggunaStatus == 'Tidak Aktif'}">
										<option value="Aktif">Aktif</option>
										<option value="Tidak Aktif" selected>Tidak Aktif</option>
									</c:if>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Cawangan</label>
							<div class="col-sm-4">
								<spring:bind path="cawangan">
									<form:select path="cawangan" class="form-control" readonly="true">
										<c:forEach var="listCawangan" items="${cawangan}">

											<c:if test="${penggunaCawangan == listCawangan.cawanganDesc}">
												<option value="${listCawangan.cawanganDesc}" selected><c:out
														value="${listCawangan.cawanganDesc}" /></option>
											</c:if>
											<c:if test="${penggunaCawangan != listCawangan.cawanganDesc}">
												<option value="${listCawangan.cawanganDesc}"><c:out
														value="${listCawangan.cawanganDesc}" /></option>
											</c:if>
										</c:forEach>
									</form:select>
								</spring:bind>
							</div>
							<label class="col-sm-2 control-label">Unit</label>
							<div class="col-sm-4">
								<spring:bind path="unit">
									<form:select path="unit" class="form-control" readonly="true">
										<c:forEach var="unitBahagian" items="${unitBahagian}">

											<c:if test="${penggunaUnit == unitBahagian.unitBahagianDesc}">
												<option value="${unitBahagian.unitBahagianDesc}" selected><c:out
														value="${unitBahagian.unitBahagianDesc}" /></option>
											</c:if>
											<c:if test="${penggunaUnit != unitBahagian.unitBahagianDesc}">
												<option value="${unitBahagian.unitBahagianDesc}"><c:out
														value="${unitBahagian.unitBahagianDesc}" /></option>
											</c:if>
										</c:forEach>
									</form:select>
								</spring:bind>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Nama
								Pengurus</label>

							<div class="col-sm-4">

								<spring:bind path="namaPengurus">
									<form:select path="namaPengurus" class="form-control" readonly="true">

										<c:forEach var="test" items="${jawatanUser}">

											<c:if test="${penggunaPengurus == test.namaStaff}">
												<option value="${test.namaStaff}" selected><c:out
														value="${test.namaStaff}" /></option>
											</c:if>
											<c:if test="${penggunaPengurus != test.namaStaff}">
												<option value="${test.namaStaff}"><c:out
														value="${test.namaStaff}" /></option>
											</c:if>
										</c:forEach>

									</form:select>
								</spring:bind>

							</div>
						</div>
					</div>
				</div>
		</div>

		<!-- /.box-body -->
		</form:form>

		<!-- /.box-footer -->
	</div>
</div>
<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>