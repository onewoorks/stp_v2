
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
	$(function() {
		var check = $('#hapus').val();
		if (check > 0) {

			$('#modal-hapus').modal('show');
		}
	});
</script>
<div class="modal fade" id="modal-hapus${pemohon.id}">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Hapus</h4>
			</div>
			<div class="modal-body">
				<form:form method="POST" modelAttribute="penghapusanPermohonan"
					action="${contextPath}/hapusPermohonan" class="form-horizontal">

					<div class="box-body">
						<spring:bind path="id">
							<form:input type="hidden" class="form-control" path="id"
								id="hapus"></form:input>
						</spring:bind>
						<div class="form-group">
							<div class="modal-body">
								<p class="center">Anda pasti untuk menghapuskan permohonan?</p>
							</div>
							<div class="modal-footer">
								<div class="col-sm-2">
									<button type="submit" class="btn btn-default form-control">Ya</button>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-primary form-control"
										data-dismiss="modal">Tidak</button>
								</div>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
					<!-- /.box-footer -->
				</form:form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
