<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${role}" var="test">
	<c:if test="${test == 'Pegawai'}">
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${contextPath}/resources/css/dist/img/user2-160x160.jpg"
							class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>${namaStaff}</p>
						<a><i class="fa fa-circle text-success"></i>${jawatan}</a>
					</div>
				</div>
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header" style="text-align: center;">SELAMAT DATANG</li>
					<li class="active"><a href="permohonanTiket"><i
							class="fa fa-edit"></i>Permohonan</a></li>

				</ul>
			</section>
		</aside>
	</c:if>
	<c:if test="${test == 'Ketua Pegawai'}">
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${contextPath}/resources/css/dist/img/user2-160x160.jpg"
							class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>${namaStaff}</p>
						<a><i class="fa fa-circle text-success"></i>${jawatan}</a>
					</div>
				</div>
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header" style="text-align: center;">SELAMAT DATANG</li>
					<li class="treeview active"><a href="#"><span>Menu</span>
							<span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span> </a>
						<ul class="treeview-menu">
							<li class="active"><a href="permohonanTiket"><i
									class="fa fa-edit"></i>Permohonan</a></li>
							<li class="active"><a href="welcome"><i
									class="fa fa-check-square-o"></i>Kelulusan</a></li>
						</ul></li>
				</ul>

			</section>
		</aside>
	</c:if>
	<c:if test="${test == 'Pentadbir'}">
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${contextPath}/resources/css/dist/img/user2-160x160.jpg"
							class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>${namaStaff}</p>
						<a><i class="fa fa-circle text-success"></i>${jawatan}</a>
					</div>
				</div>
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header" style="text-align: center;">SELAMAT DATANG</li>
					<li class="treeview"><a href="#"><span>Menu</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span> </a>
						<ul class="treeview-menu">
							<li class="active"><a href="permohonanTiket"><i
									class="fa fa-edit"></i>Permohonan</a></li>
							<li class="active"><a href="pembelian"><i
									class="fa fa-dollar"></i>Pembelian</a></li>
							<!-- <li class="active"><a href="report"><i
								class="fa fa-file-text-o"></i>Laporan</a></li> -->
							<li class="active"><a href="admin/daftarPengguna"><i
									class="fa fa-circle-o"></i>Daftar Pengguna</a></li>
							<!-- <li class="treeview"><a href="#"><i
								class="fa  fa-check-circle-o"></i>Selenggara</a></li> -->
							<li class="treeview"><a href="#"><i
									class="fa fa-circle-o"></i> Selenggara <span
									class="pull-right-container"> <i
										class="fa fa-angle-left pull-right"></i>
								</span> </a>
								<ul class="treeview-menu">
									<li><a href="maintenancePage"><i
											class="fa fa-circle-o"></i> Cawangan</a></li>
									<li><a href="maintenancePageLokasi"><i
											class="fa fa-circle-o"></i> Lokasi</a></li>
									<li><a href="maintenancePagePeruntukan"><i
											class="fa fa-circle-o"></i> Peruntukan</a></li>
									<li><a href="maintenancePagePesawat"><i
											class="fa fa-circle-o"></i> Pesawat</a></li>
									<li><a href="maintenancePageUnit"><i
											class="fa fa-circle-o"></i> Unit</a></li>
									<li><a href="maintenancePageJawatan"><i
											class="fa fa-circle-o"></i> Jawatan</a></li>

								</ul></li>

						</ul></li>
					<li class="treeview"><a href="#"><span>Syarikat
								Penerbangan</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span> </a>
						<ul class="treeview-menu">
							<li class="active"><a
								href="https://www.malaysiaairlines.com" target="_blank"><i
									class="fa fa-plane"></i>MALAYSIA AIRLINES</a></li>
							<li class="active"><a href="https://www.airasia.com"
								target="_blank"><i class="fa fa-plane"></i>AIR ASIA</a></li>
							<li class="active"><a href="https://www.malindoair.com"
								target="_blank"><i class="fa fa-plane"></i>MALINDO</a></li>
							<li class="active"><a href="http://www.fireflyz.com"
								target="_blank"><i class="fa fa-plane"></i>FIREFLY</a></li>
						</ul></li>

				</ul>

			</section>
		</aside>
	</c:if>
	<c:if test="${test == 'Pengarah'}">
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${contextPath}/resources/css/dist/img/user2-160x160.jpg"
							class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>${namaStaff}</p>
						<a><i class="fa fa-circle text-success"></i>${jawatan}</a>
					</div>
				</div>
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header" style="text-align: center;">SELAMAT DATANG</li>
					<li class="treeview"><a href="#"><span>Menu</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span> </a>
						<ul class="treeview-menu">
							<li class="active"><a href="permohonanTiket"><i
									class="fa fa-edit"></i>Permohonan</a></li>
							<li class="active"><a href="pengesahan"><i
									class="fa  fa-check-circle-o"></i>Pengesahan</a></li>
						</ul></li>
				</ul>

			</section>
		</aside>
	</c:if>
</c:forEach>