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

<title>STP</title>

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
								<div class="box-header with-border">
									<h3 class="box-title">Maklumat Pengguna</h3>

									<div class="box-tools pull-right">
										<button type="button" class="btn btn-box-tool"
											data-widget="collapse" data-toggle="tooltip" title="Collapse">
											<i class="fa fa-minus"></i>
										</button>
										<button type="button" class="btn btn-box-tool"
											data-widget="remove" data-toggle="tooltip" title="Remove">
											<i class="fa fa-times"></i>
										</button>
									</div>
								</div>
								<form:form method="POST" modelAttribute="infoPenggunaForm"
									action="${contextPath}/infoPengguna" class="form-horizontal">
									<spring:bind path="id">
										<form:input type="hidden" class="form-control" path="id"
											value="${pengguna.id}"></form:input>
									</spring:bind>
									<div class="box-body">
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-2 control-label">Nombor
												Staf</label>

											<div class="col-sm-4">
												<spring:bind path="staffNo">
													<form:input type="text" class="form-control" path="staffNo"
														placeholder="Staf No" value="${noStaff}"></form:input>
												</spring:bind>
											</div>
											<label for="inputEmail3" class="col-sm-2 control-label">Nama
												Pengguna</label>

											<div class="col-sm-4">
												<spring:bind path="username">
													<form:input type="text" class="form-control"
														path="username" placeholder="Nama Pengguna"
														value="${namaPengguna}"></form:input>
												</spring:bind>
											</div>

										</div>


										<div class="form-group">
											<label for="inputEmail3" class="col-sm-2 control-label">Nama</label>

											<div class="col-sm-4">
												<spring:bind path="namaStaff">
													<form:input type="text" class="form-control"
														path="namaStaff" placeholder="Nama Staf"
														value="${namaStaff}"></form:input>
												</spring:bind>
											</div>
											<label for="inputEmail3" class="col-sm-2 control-label">Enrich
												No</label>

											<div class="col-sm-4">
												<spring:bind path="enrichNo">
													<form:input type="text" class="form-control"
														path="enrichNo" placeholder="Enrich No"
														value="${enrichNo}"></form:input>
												</spring:bind>
											</div>
										</div>

										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">No.
												KP</label>

											<div class="col-sm-4">
												<spring:bind path="noKP">
													<form:input type="text" class="form-control" path="noKP"
														placeholder="Nombor Kad Pengenalan" value="${noKp}"></form:input>
												</spring:bind>
											</div>
											<label class="col-sm-2 control-label">Passport</label>

											<div class="col-sm-4">
												<spring:bind path="passport">
													<form:input type="text" class="form-control"
														path="passport" placeholder="No Passport"
														value="${passport}"></form:input>
												</spring:bind>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">Emel</label>

											<div class="col-sm-4">
												<spring:bind path="email">
													<form:input type="text" class="form-control" path="email"
														placeholder="email(exp:xxx@yahoo.com)" value="${emelAdd}"></form:input>
												</spring:bind>
											</div>
											<label class="col-sm-2 control-label">No. Telefon
												Bimbit</label>

											<div class="col-sm-4">
												<spring:bind path="noTelefon">
													<form:input type="text" class="form-control"
														path="noTelefon" placeholder="No telefon Bimbit"
														value="${noPhone}"></form:input>
												</spring:bind>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">Jawatan</label>

											<div class="col-sm-4">
												<spring:bind path="refJawatan">
													<form:select path="refJawatan" class="form-control">
														<c:forEach var="testJawatan" items="${listJawatan}">

															<c:if test="${userJawatan == testJawatan.jawatanId}">
																<option value="${testJawatan.jawatanId}" selected><c:out
																		value="${testJawatan.jawatanDesc}" /></option>
															</c:if>
															<c:if test="${userJawatan != testJawatan.jawatanId}">
																<option value="${testJawatan.jawatanId}"><c:out
																		value="${testJawatan.jawatanDesc}" /></option>
															</c:if>
														</c:forEach>
													</form:select>
												</spring:bind>
											</div>
											<label class="col-sm-2 control-label">Status</label>

											<div class="col-sm-4">
												<form:select path="status" class="form-control">
													<option></option>
													<c:if test="${userStatus == 'Aktif'}">
														<option value="Aktif" selected>Aktif</option>
														<option value="Tidak Aktif">Tidak Aktif</option>
													</c:if>
													<c:if test="${userStatus == 'Tidak Aktif'}">
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
													<form:select path="cawangan" class="form-control">
														<c:forEach var="listCawangan" items="${listCawangan}">

															<c:if test="${userCawangan == listCawangan.cawanganDesc}">
																<option value="${listCawangan.cawanganDesc}" selected><c:out
																		value="${listCawangan.cawanganDesc}" /></option>
															</c:if>
															<c:if test="${userCawangan != listCawangan.cawanganDesc}">
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
													<form:select path="unit" class="form-control">
														<c:forEach var="unitBahagian" items="${unitBahagian}">

															<c:if test="${userUnit == unitBahagian.unitBahagianDesc}">
																<option value="${unitBahagian.unitBahagianDesc}"
																	selected><c:out
																		value="${unitBahagian.unitBahagianDesc}" /></option>
															</c:if>
															<c:if test="${userUnit != unitBahagian.unitBahagianDesc}">
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
													<form:select path="namaPengurus" class="form-control">
														<c:forEach var="test" items="${jawatanUser}">

															<c:if test="${userPengurus == test.namaStaff}">
																<option value="${test.namaStaff}" selected><c:out
																		value="${test.namaStaff}" /></option>
															</c:if>
															<c:if test="${userPengurus != test.namaStaff}">
																<option value="${test.namaStaff}"><c:out
																		value="${test.namaStaff}" /></option>
															</c:if>
														</c:forEach>
													</form:select>
												</spring:bind>

											</div>
										</div>
										<button type="submit" class="btn btn-info pull-right">Kemaskini</button>
									</div>
								</form:form>
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
	</c:if>
</body>
</html>

