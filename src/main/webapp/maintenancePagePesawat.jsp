
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
				<!-- Main content -->
				<section class="content">
					<!-- /.row -->
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<!-- /.box-header -->
								<div class="box-header with-border">
									<h3 class="box-title">Maklumat Pesawat</h3>

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
								<div class="box-body">
									<div class="form-group col-sm-2 pull-right">
										<button type="button" class="btn btn-info form-control"
											data-toggle="modal" data-target="#modal-tambahPeruntukan"
											id="permohonanBaru" title="Tambah Pesawat">
											<i class="fa fa-plus"></i>
										</button>
									</div>
									<table id="tablePesawat"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>No</th>
												<th>Nama Rujukan</th>
												<th>Tindakan</th>
											</tr>
										</thead>
										<tbody>
											<%
												int x = 1;
											%>
											<c:forEach items="${refPesawat}" var="pesawat">

												<tr>
													<td><%=x%></td>
													<td>${pesawat.pesawatDesc}</td>
													<td>
														<button type="button" class="btn btn-info btn-block "
															data-toggle="modal"
															data-target="#modal-pengesahan${pesawat.pesawatId}"
															title="Kemaskini">
															<i class="fa fa-file-text-o"></i>
														</button>

														<div class="modal fade"
															id="modal-pengesahan${pesawat.pesawatId}">
															<div class="modal-dialog modal-lg">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																		<h4 class="modal-title">Lokasi</h4>
																	</div>
																	<div class="modal-body">
																		<form:form method="POST"
																			modelAttribute="kemaskiniPesawat"
																			action="${contextPath}/updatePesawat"
																			class="form-horizontal">
																			<div class="box-body">
																				<div class="form-group">
																					<div class="col-sm-12">
																						<spring:bind path="pesawatId">
																							<form:input type="hidden" class="form-control"
																								path="pesawatId" value="${pesawat.pesawatId}"></form:input>
																						</spring:bind>

																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Pesawat :</label>
																						<div class="col-sm-8">
																							<spring:bind path="pesawatDesc">
																								<form:input type="text" class="form-control"
																									id="pesawatDesc" path="pesawatDesc"
																									value="${pesawat.pesawatDesc}"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																				</div>
																			</div>
																			<!-- /.box-body -->
																			<div class="box-footer">
																				<button type="submit"
																					class="btn btn-info pull-right">Hantar</button>
																			</div>
																		</form:form>
																		<!-- /.box-footer -->
																	</div>
																</div>
																<!-- /.modal-content -->
															</div>
															<!-- /.modal-dialog -->
														</div>

														<div class="modal fade" id="modal-tambahPeruntukan">
															<div class="modal-dialog modal-lg">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																		<h4 class="modal-title">Lokasi</h4>
																	</div>
																	<div class="modal-body">
																		<form:form method="POST"
																			modelAttribute="kemaskiniPesawat"
																			action="${contextPath}/updatePesawat"
																			class="form-horizontal">
																			<div class="box-body">
																				<div class="form-group">
																					<div class="col-sm-12">
																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Pesawat :</label>

																						<div class="col-sm-8">
																							<spring:bind path="pesawatDesc">
																								<form:input type="text" class="form-control"
																									id="pesawatDesc" path="pesawatDesc"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																				</div>

																			</div>
																			<!-- /.box-body -->
																			<div class="box-footer">
																				<button type="submit"
																					class="btn btn-info pull-right">Hantar</button>
																			</div>
																		</form:form>
																		<!-- /.box-footer -->
																	</div>
																</div>
																<!-- /.modal-content -->
															</div>
															<!-- /.modal-dialog -->
														</div>
													</td>
												</tr>
												<%
													x++;
												%>
											</c:forEach>
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
		<script>
			$('#"tablePesawat"').DataTable({
				'paging' : true,
				'lengthChange' : false,
				'searching' : true,
				'ordering' : true,
				'info' : true,
				'autoWidth' : false
			})
		</script>
	</c:if>
</body>
</html>
