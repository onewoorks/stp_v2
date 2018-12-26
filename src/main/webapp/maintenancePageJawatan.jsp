
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
								<div class="box-header">
									<h3 class="box-title">Maklumat Jawatan</h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<div class="form-group col-sm-2 pull-right">
										<button type="button" class="btn btn-info form-control"
											data-toggle="modal" data-target="#modal-tambahJawatan"
											id="permohonanBaru" title="Tambah Jawatan"><i class="fa fa-plus"></i></button>
									</div>
									<table id="tableJawatan"
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
											<c:forEach items="${refJawatan}" var="refJawatanAll">

												<tr>
													<td><%=x%></td>
													<td>${refJawatanAll.jawatanDesc}</td>
													<td>
														<button type="button" class="btn btn-info btn-block "
															data-toggle="modal"
															data-target="#modal-jawatan${refJawatanAll.jawatanId}" title="Kemaskini"><i class="fa fa-file-text-o"></i></button>

														<div class="modal fade"
															id="modal-jawatan${refJawatanAll.jawatanId}">
															<div class="modal-dialog modal-lg">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																		<h4 class="modal-title">Jawatan</h4>
																	</div>
																	<div class="modal-body">
																		<form:form method="POST"
																			modelAttribute="kemaskiniJawatan"
																			action="${contextPath}/updateJawatan"
																			class="form-horizontal">
																			<div class="box-body">
																			
																				<div class="form-group">
																					<div class="col-sm-12">
																						<spring:bind path="jawatanId">
																							<form:input type="hidden" class="form-control"
																								path="jawatanId"
																								value="${refJawatanAll.jawatanId}"></form:input>
																						</spring:bind>

																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Jawatan :</label>
																						<div class="col-sm-8">
																							<spring:bind path="jawatanDesc">
																								<form:input type="text" class="form-control"
																									id="jawatanDesc" path="jawatanDesc"
																									value="${refJawatanAll.jawatanDesc}" style="width: 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																				</div>
																				<div class="form-group">
																					<div class="col-sm-12">
																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Peranan:</label>

																						<div class="col-sm-8">
																							<spring:bind path="refRole">
																								<form:select path="refRole" class="form-control" style="width: 100%">
																									<c:forEach var="role" items="${roleAll}">
																										<option value="${role.roleId}"><c:out
																												value="${role.roleDesc}" /></option>
																									</c:forEach>
																								</form:select>
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

														<div class="modal fade" id="modal-tambahJawatan">
															<div class="modal-dialog modal-lg">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																		<h4 class="modal-title">Jawatan</h4>
																	</div>
																	<div class="modal-body">
																		<form:form method="POST"
																			modelAttribute="kemaskiniJawatan"
																			action="${contextPath}/updateJawatan"
																			class="form-horizontal">
																			<div class="box-body">
																				<div class="form-group">
																					<div class="col-sm-12">
																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Jawatan :</label>

																						<div class="col-sm-8">
																							<spring:bind path="jawatanDesc">
																								<form:input type="text" class="form-control"
																									id="jawatanDesc" path="jawatanDesc" style="width: 100%"></form:input>
																							</spring:bind>
																						</div>
																					</div>
																				</div>
																				<div class="form-group">
																					<div class="col-sm-12">
																						<label for="inputEmail3"
																							class="col-sm-3 control-label">Peranan:</label>

																						<div class="col-sm-8">
																							<spring:bind path="refRole">
																								<form:select path="refRole" class="form-control">
																									<c:forEach var="role" items="${roleAll}" style="width: 100%">
																										<option value="${role.roleId}"><c:out
																												value="${role.roleDesc}" /></option>
																									</c:forEach>
																								</form:select>
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
			$('#tableJawatan').DataTable({
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
