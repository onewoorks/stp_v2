package com.stp.auth.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.stp.auth.model.Barangan;
import com.stp.auth.model.BaranganTemp;
import com.stp.auth.model.Pembelian;
import com.stp.auth.model.PembelianTemp;
import com.stp.auth.model.Penerbangan;
import com.stp.auth.model.PenerbanganTemp;
import com.stp.auth.model.Pengguna;
//import com.stp.auth.model.Penerbangan;
import com.stp.auth.model.Permohonan;
import com.stp.auth.model.PermohonanTemp;
import com.stp.auth.model.RefCawangan;
import com.stp.auth.model.RefJawatan;
import com.stp.auth.model.RefLokasi;
import com.stp.auth.model.RefPeruntukan;
import com.stp.auth.model.RefPesawat;
import com.stp.auth.model.RefRole;
import com.stp.auth.model.RefUnitBahagian;
import com.stp.auth.model.User;
import com.stp.auth.service.BaranganService;
import com.stp.auth.service.DaftarPenggunaService;
import com.stp.auth.service.DariLokasiService;
import com.stp.auth.service.PembelianService;
import com.stp.auth.service.PenerbanganService;
import com.stp.auth.service.PermohonanService;
import com.stp.auth.service.RefCawanganService;
import com.stp.auth.service.RefJawatanService;
import com.stp.auth.service.RefLokasiService;
import com.stp.auth.service.RefPeruntukanService;
import com.stp.auth.service.RefPesawatService;
import com.stp.auth.service.RefRoleService;
import com.stp.auth.service.RefUnitBahagianService;
import com.stp.auth.service.SendHTMLEmail;
import com.stp.auth.service.UserService;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

@Controller
public class PermohonanController {

	@Value("${path.file}")
	private String path;
	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private PermohonanService permohonanService;

	@Autowired
	private UserService userService;

	@Autowired
	private DaftarPenggunaService penggunaService;

	@Autowired
	private PenerbanganService penerbanganService;

	@Autowired
	private BaranganService baranganService;

	@Autowired
	private DariLokasiService dariLokasiService;

	@Autowired
	private PembelianService pembelianService;

	@Autowired
	private RefPeruntukanService refPeruntukanService;

	@Autowired
	private RefPesawatService refPesawatService;

	@Autowired
	private RefCawanganService refCawanganService;

	@Autowired
	private RefLokasiService refLokasiService;

	@Autowired
	private RefUnitBahagianService refUnitBahagianService;

	@Autowired
	private RefRoleService refRoleService;

	@Autowired
	private RefJawatanService refJawatanService;

	@Autowired
	private ResourceLoader resourceLoader;
	ArrayList<PenerbanganTemp> pt = new ArrayList<PenerbanganTemp>();
	ArrayList<BaranganTemp> barangant = new ArrayList<BaranganTemp>();
	ArrayList<Permohonan> permohonan = new ArrayList<>();

	String kemasKiniPeruntukan = "";

	@RequestMapping(value = "/permohonanTiket", method = RequestMethod.GET)
	public String permohonan(Model model, HttpSession session, Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Permohonan> permohonan = new ArrayList<>();
		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		List<Pengguna> jawatanUser = new ArrayList<>();

		for (Pengguna jb : penggunaService.findAll()) {
			System.out.println("nama pengurus ::::: " + jb.getRefJawatan().getRefRole().getRoleId());
			if (jb.getRefJawatan().getRefRole().getRoleId() == 2) {
				jawatanUser.add(jb);
				model.addAttribute("jawatanUser", jawatanUser);
			}
		}

		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("lokasi", dariLokasiService.getAll());
		System.out.println("tengok niiiiiiiii" + dariLokasiService.getAll());
		model.addAttribute("peruntukan", refPeruntukanService.getAll());
		System.out.println("tengok niiiiiiiii haaaaaaaa" + refPeruntukanService.getAll());
		model.addAttribute("pesawat", refPesawatService.getAll());
		// model.addAttribute("listPenerbangan",
		// penerbanganService.findByPermohonan(permohonan));
		System.out.println("tengok niiiiiiiii haaaaaaaa pulakkkkkkk" + refPesawatService.getAll());
		model.addAttribute("permohonanOpen", new PermohonanTemp());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		model.addAttribute("permohonan", permohonan);
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("noKP", user.getNoKP());
		model.addAttribute("unit", user.getUnit());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("noTelefon", user.getNoTelefon());
		model.addAttribute("namaPengurus", user.getNamaPengurus());
		model.addAttribute("passport", user.getPassport());
		model.addAttribute("enrichNo", user.getEnrichNo());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		System.out.println(username + "=============================" + pengguna.getRefJawatan().getJawatanDesc());

		return "permohonanTiket";
	}

	@RequestMapping(value = "/hapusPermohonan", method = RequestMethod.GET)
	public String permohonanHapus(@RequestParam("id") Long id, Model model, HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Permohonan> permohonan = new ArrayList<>();
		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));
		model.addAttribute("permohonanForm", new PermohonanTemp());
		// model.addAttribute("permohonanForm", new Permohonan());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new PermohonanTemp());
		model.addAttribute("penghapusanPermohonan", permohonanService.findById(id));
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("permohonan", permohonan);
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("noKP", user.getNoKP());
		model.addAttribute("unit", user.getUnit());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("noTelefon", user.getNoTelefon());
		model.addAttribute("namaPengurus", user.getNamaPengurus());
		model.addAttribute("passport", user.getPassport());
		model.addAttribute("enrichNo", user.getEnrichNo());

		return "permohonanTiket";

	}

	@RequestMapping(value = "/kemasKiniPenerbangan", method = RequestMethod.GET)
	public String getPenerbangan(@RequestParam("id") Long id) {
		Penerbangan pnbng = new Penerbangan();
		pnbng.setPenerbanganId(id);

		return pnbng.getDariLokasi();

	}

	@RequestMapping(value = "/permohonanOpen", method = RequestMethod.GET)
	public String permohonanOpen(@RequestParam("id") Long id, Model model, HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		Permohonan permohonan = permohonanService.findById(id);

		ArrayList<Penerbangan> penerbangan = new ArrayList<>();
		penerbangan = (ArrayList<Penerbangan>) penerbanganService.findByPermohonan(permohonan);

		Penerbangan contoh = null;
		List<Penerbangan> list = new ArrayList<>();
		for (Penerbangan jb : penerbangan) {
			if (jb.getPenerbanganId() != null) {

				contoh = jb;
				list.add(jb);
			}
		}

		model.addAttribute("penerbangan", list);

		//
		// List<Permohonan> permohonan1 = permohonanService.getAll();

		Long id3 = id;

		PermohonanTemp temp = new PermohonanTemp();

		temp.setId(id3);

		// model.addAttribute("welcome", permohonanService.getAll());
		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));
		// model.addAttribute("permohonanForm", new Permohonan());
		// Permohonan permohonanOpen = permohonanService.findById(id);
		// System.out.println("Masuk La sini==========" +
		// permohonanOpen.getNama());
		model.addAttribute("lokasi", dariLokasiService.getAll());
		model.addAttribute("peruntukan", refPeruntukanService.getAll());
		model.addAttribute("pesawat", refPesawatService.getAll());
		model.addAttribute("permohonanOpen", temp);
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("noKP", user.getNoKP());
		model.addAttribute("unit", user.getUnit());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("noTelefon", user.getNoTelefon());
		model.addAttribute("namaPengurus", user.getNamaPengurus());
		model.addAttribute("passport", user.getPassport());
		model.addAttribute("enrichNo", user.getEnrichNo());
		model.addAttribute("noBilBom", permohonan.getNoBilBom());
		model.addAttribute("pembangunan", permohonan.getPembangunan());
		model.addAttribute("peruntukanPermohonan", permohonan.getPeruntukan());
		model.addAttribute("catatan", permohonan.getCatatan());
		model.addAttribute("tarikhMula", permohonan.getTarikhMula());
		model.addAttribute("tarikhTamat", permohonan.getTarikhTamat());
		model.addAttribute("tujuan", permohonan.getTujuan());
		model.addAttribute("tempat", permohonan.getTempatBertugas());

		return "permohonanTiket";

	}

	@RequestMapping(value = "/permohonanKemaskini", method = RequestMethod.GET)
	public String permohonanKemaskini(@RequestParam("id") Long id, Model model, HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Permohonan> permohonan = new ArrayList<>();
		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		// model.addAttribute("welcome", permohonanService.getAll());
		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));
		model.addAttribute("lokasi", dariLokasiService.getAll());
		model.addAttribute("peruntukan", refPeruntukanService.getAll());
		model.addAttribute("pesawat", refPesawatService.getAll());
		model.addAttribute("permohonanOpen", new PermohonanTemp());
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanKemaskini", permohonanService.findById(id));
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("noKP", user.getNoKP());
		model.addAttribute("unit", user.getUnit());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("noTelefon", user.getNoTelefon());
		model.addAttribute("namaPengurus", user.getNamaPengurus());
		model.addAttribute("passport", user.getPassport());
		model.addAttribute("enrichNo", user.getEnrichNo());

		return "permohonanTiket";

	}

	@RequestMapping(value = "/permohonanKemaskiniTemp", method = RequestMethod.GET)
	public String permohonanKemaskiniTemp(@RequestParam("id") Long id, Model model, HttpSession session,
			HttpServletRequest req) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		Permohonan permohonan = permohonanService.findById(id);
		session.setAttribute("permohonan", permohonan);

		Long id3 = id;

		PermohonanTemp temp = new PermohonanTemp();
		temp.setId(id3);

		ArrayList<Penerbangan> penerbangan = new ArrayList<>();
		penerbangan = (ArrayList<Penerbangan>) penerbanganService.findByPermohonan(permohonan);

		Penerbangan contoh = null;
		List<Penerbangan> list = new ArrayList<>();
		for (Penerbangan jb : penerbangan) {
			if (jb.getPenerbanganId() != null) {

				contoh = jb;
				list.add(jb);
			}
		}

		model.addAttribute("penerbangan", list);

		ArrayList<Barangan> barangan = new ArrayList<>();
		List<Barangan> list2 = new ArrayList<>();

		barangan = (ArrayList<Barangan>) baranganService.findByPermohonan(permohonan);

		for (Barangan jb2 : barangan) {
			if (jb2.getBaranganId() != null) {
				list2.add(jb2);
				session.setAttribute("anggaranTotalBerat", jb2.getTotal());
			}
		}

		model.addAttribute("barangan", list2);
		// model.addAttribute("welcome", permohonanService.getAll());
		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));
		model.addAttribute("lokasi", dariLokasiService.getAll());
		model.addAttribute("peruntukan", refPeruntukanService.getAll());
		model.addAttribute("pesawat", refPesawatService.getAll());
		model.addAttribute("permohonanOpen", new PermohonanTemp());
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", temp);
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("permohonan", permohonan);
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("namaStaff", user.getNamaStaff());
		// model.addAttribute("jawatan", user.getJawatan());
		// model.addAttribute("id", id);
		// model.addAttribute("namaStaff", permohonan.getNama());
		// model.addAttribute("noKP", permohonan.getKp());
		model.addAttribute("noBilBom", permohonan.getNoBilBom());
		model.addAttribute("pembangunan", permohonan.getPembangunan());
		model.addAttribute("peruntukanPermohonan", permohonan.getPeruntukan());
		model.addAttribute("catatan", permohonan.getCatatan());
		model.addAttribute("tarikhMula", permohonan.getTarikhMula());
		model.addAttribute("tarikhTamat", permohonan.getTarikhTamat());
		model.addAttribute("tujuan", permohonan.getTujuan());
		model.addAttribute("tempat", permohonan.getTempatBertugas());
		model.addAttribute("unit", user.getUnit());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("noTelefon", user.getNoTelefon());
		model.addAttribute("namaPengurus", user.getNamaPengurus());
		model.addAttribute("passport", user.getPassport());
		model.addAttribute("enrichNo", user.getEnrichNo());

		return "permohonanTiket";

	}

	@RequestMapping(value = "/batalPermohonan", method = RequestMethod.GET)
	public String batalPermohonan(@RequestParam("id") Long id, Model model, HttpSession session,
			Permohonan permohonan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		// model.addAttribute("welcome", permohonanService.getAll());

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));
		// model.addAttribute("pemohon",
		// penerbanganService.findBypermohonan(id));
		model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(permohonan));
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new PermohonanTemp());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanBatal", permohonanService.findById(id));
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		// model.addAttribute("permohonanKemaskini", new PenerbanganTemp());
		// model.addAttribute("permohonanKemaskini", new BaranganTemp());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("noKP", user.getNoKP());
		model.addAttribute("unit", user.getUnit());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("noTelefon", user.getNoTelefon());
		model.addAttribute("namaPengurus", user.getNamaPengurus());
		model.addAttribute("passport", user.getPassport());
		model.addAttribute("enrichNo", user.getEnrichNo());

		return "permohonanTiket";

	}

	@RequestMapping(value = "/batalPermohonanProses", method = RequestMethod.GET)
	public String batalPermohonanProses(@RequestParam("id") Long id, Model model, HttpSession session,
			Permohonan permohonan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		// model.addAttribute("welcome", permohonanService.getAll());

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));
		// model.addAttribute("pemohon",
		// penerbanganService.findBypermohonan(id));
		model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(permohonan));
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new PermohonanTemp());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", permohonanService.findById(id));
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		// model.addAttribute("permohonanKemaskini", new PenerbanganTemp());
		// model.addAttribute("permohonanKemaskini", new BaranganTemp());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("noKP", user.getNoKP());
		model.addAttribute("unit", user.getUnit());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("noTelefon", user.getNoTelefon());
		model.addAttribute("namaPengurus", user.getNamaPengurus());
		model.addAttribute("passport", user.getPassport());
		model.addAttribute("enrichNo", user.getEnrichNo());

		return "permohonanTiket";

	}

	@RequestMapping(value = "/baranganTemp", method = RequestMethod.POST, produces = "application/json")
	public void penerbanganTemp(@RequestBody BaranganTemp barangant2) {

		barangant.add(barangant2);

	}

	@RequestMapping(value = "/penerbanganTemp", method = RequestMethod.POST, produces = "application/json")
	public void penerbanganTemp(@RequestBody PenerbanganTemp pt2) {

		pt.add(pt2);

	}

	@RequestMapping(value = "/kemasPenerbanganTemp", method = RequestMethod.POST, produces = "application/json")
	public void kemasPenerbanganTemp(@RequestBody PenerbanganTemp pt2) {

		pt.add(pt2);

	}

	@RequestMapping(value = "/permohonanForm", method = RequestMethod.POST)
	public String registration(@ModelAttribute("permohonanForm") PermohonanTemp temp, HttpSession session,
			HttpServletRequest request) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		DateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dtf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateMohon = new Date();

		Date tarikhMula = null;
		Date tarikhTamat = null;

		try {
			tarikhMula = dtf2.parse(temp.getTarikhMula());
			tarikhTamat = dtf2.parse(temp.getTarikhTamat());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// String tarikhMula = temp.getTarikhMula();

		Permohonan permohonan = new Permohonan();

		permohonan.setNama(temp.getNama());
		permohonan.setNamaPelulus(temp.getNamaPelulus());
		permohonan.setKp(temp.getKp());
		permohonan.setBahagian(temp.getBahagian());
		permohonan.setEmel(temp.getEmel());
		permohonan.setPassport(temp.getPassport());
		permohonan.setTujuan(temp.getTujuan());
		permohonan.setTempatBertugas(temp.getTempatBertugas());
		permohonan.setTarikhMula(dtf2.format(tarikhMula));
		permohonan.setTarikhTamat(dtf2.format(tarikhTamat));
		permohonan.setNoTelefonBimbit(temp.getNoTelefonBimbit());
		permohonan.setPeruntukan(temp.getPeruntukan());
		permohonan.setCatatan(temp.getCatatan());
		permohonan.setTarikhMohon(dtf2.format(dateMohon));
		permohonan.setPembangunan(temp.getPembangunan());
		permohonan.setNoBilBom(temp.getNoBilBom());
		permohonan.setEnrichNo(temp.getEnrichNo());
		permohonan.setStatusPermohonan("Baru");

		if (!temp.getMuatNaikBom().getOriginalFilename().equalsIgnoreCase("")) {
			MultipartFile muatNaikBom = temp.getMuatNaikBom();
			File convertFile = new File(path + muatNaikBom.getOriginalFilename());
			try {
				convertFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(convertFile);
				fout.write(muatNaikBom.getBytes());
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			permohonan.setMuatNaikBom(convertFile.getAbsolutePath());

		}

		permohonanService.save(permohonan);

		if (pt.size() != 0) {

			for (int i = 0; i < pt.size(); i++) {

				Date tarikhPergi = null;

				try {
					tarikhPergi = dtf2.parse(pt.get(i).getTarikhPergi());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Penerbangan penerbangan = new Penerbangan();
				penerbangan.setPenerbangan(pt.get(i).getPenerbangan());
				penerbangan.setTarikhPergi(dtf2.format(tarikhPergi));
				penerbangan.setWaktuBerlepas(pt.get(i).getWaktuBerlepas());
				penerbangan.setWaktuTiba(pt.get(i).getWaktuTiba());
				penerbangan.setNoPesawat(pt.get(i).getNoPesawat());
				penerbangan.setJenisPesawat(pt.get(i).getJenisPesawat());
				penerbangan.setDariLokasi(pt.get(i).getDariLokasi());
				penerbangan.setDestinasi(pt.get(i).getDestinasi());
				penerbangan.setPermohonan(permohonan);
				penerbangan.setStatus(false);

				penerbanganService.save(penerbangan);
			}

		}

		if (barangant.size() != 0) {

			for (int i = 0; i < barangant.size(); i++) {
				Barangan barangan = new Barangan();
				barangan.setAnggaranBerat(barangant.get(i).getAnggaranBerat());
				barangan.setBaranganDibawa(barangant.get(i).getBaranganDibawa());
				barangan.setJumlah(barangant.get(i).getJumlah());
				barangan.setTotal(barangant.get(barangant.size() - 1).getTotal());
				barangan.setPermohonan(permohonan);
				baranganService.save(barangan);

			}

		}

		barangant.removeAll(barangant);
		pt.removeAll(pt);

		String contextPath = request.getContextPath();
		System.out.println(contextPath);

		String emel = null;

		String emel2 = null;

		user.getNamaPengurus();

		ArrayList<Pengguna> pengguna = new ArrayList<>();

		pengguna = (ArrayList<Pengguna>) penggunaService.findAll();

		for (Pengguna jb : pengguna) {
			if (jb.getJawatan() != null) {
				if (jb.getRefJawatan().getRefRole().getRoleId().equals(2)) {
					if (jb.getNamaStaff().equals(user.getNamaPengurus())) {
						emel = jb.getEmail();
					}
				}
			}
		}

		SendHTMLEmail.sendHtmlEmail(emel,
				"<!DOCTYPE html><html><body><style type='text/css'>#imageSize"
						+ " {width: 100%;height:3%;}#footer{background:rgba(226,226,226,1);background:-moz-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1)50%,rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-webkit-gradient(left top, right top,color-stop(0%,rgba(226,226,226,1)), color-stop(50%,rgba(219,219,219,1)), "
						+ "color-stop(51%, rgba(209,209,209,1)),color-stop(100%,rgba(254,254,254,1)));background:-webkit-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1)50%,rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-o-linear-gradient(left, rgba(226,226,226,1)0%,rgba(219,219,219,1) 50%, rgba(209,209,209,1)51%,rgba(254,254,254,1)"
						+ "100%);background: -ms-linear-gradient(left, rgba(226,226,226,1)0%,rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, "
						+ "rgba(254,254,254,1) 100%);background: linear-gradient(toright,rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba"
						+ "(209,209,209,1) 51%, rgba(254,254,254,1)100%);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#e2e2e2',"
						+ " endColorstr='#fefefe', GradientType=1 );width:100%;height:3%;}</style>"
						+ "<img src='C:\\Program Files\\STP"
						+ "mpc-header.png'id='imageSize'/><p>Assalamuaikum dan Salam Sejahtera,Terdapat permohonan baru "
						+ "diterima untuk kelulusan. Sila log masuk <a href=''>disini</a>untuk melihat maklumat permohonan.Sekian, terimakasih."
						+ "</p><p>Maklumat Hubungan:<a href='mailto:flight@mpc.gov.my'>flight@mpc.gov.my</a>.</p><footer><divid='footer'>"
						+ "</div></footer></body></html>");

		for (Pengguna jb2 : pengguna) {
			if (jb2.getJawatan() != null) {
				if (jb2.getRefJawatan().getRefRole().getRoleId().equals(4)) {
					if (jb2.getCawangan().equals(user.getCawangan())) {
						emel2 = jb2.getEmail();
					}
				}
			}
		}

		SendHTMLEmail.sendHtmlEmail(emel2,
				"<!DOCTYPE html><html><body><style type='text/css'>#imageSize"
						+ " {width: 100%;height:3%;}#footer{background:rgba(226,226,226,1);background:-moz-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1)50%,rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-webkit-gradient(left top, right top,color-stop(0%,rgba(226,226,226,1)), color-stop(50%,rgba(219,219,219,1)), "
						+ "color-stop(51%, rgba(209,209,209,1)),color-stop(100%,rgba(254,254,254,1)));background:-webkit-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1)50%,rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-o-linear-gradient(left, rgba(226,226,226,1)0%,rgba(219,219,219,1) 50%, rgba(209,209,209,1)51%,rgba(254,254,254,1)"
						+ "100%);background: -ms-linear-gradient(left, rgba(226,226,226,1)0%,rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, "
						+ "rgba(254,254,254,1) 100%);background: linear-gradient(toright,rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba"
						+ "(209,209,209,1) 51%, rgba(254,254,254,1)100%);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#e2e2e2',"
						+ " endColorstr='#fefefe', GradientType=1 );width:100%;height:3%;}</style>"
						+ "<img src='C:\\Program Files\\STP"
						+ "mpc-header.png'id='imageSize'/><p>Assalamuaikum dan Salam Sejahtera,Terdapat permohonan baru "
						+ "diterima. Sila log masuk <a href=''>disini</a>untuk melihat maklumat permohonan.Sekian, terimakasih."
						+ "</p><p>Maklumat Hubungan:<a href='mailto:flight@mpc.gov.my'>flight@mpc.gov.my</a>.</p><footer><divid='footer'>"
						+ "</div></footer></body></html>");

		return "redirect:/permohonanTiket";
	}

	@RequestMapping(value = "/permohonanKemaskiniTemp", method = RequestMethod.POST)
	public String permohonanKemaskini(@ModelAttribute("permohonanKemaskiniTemp") PermohonanTemp temp,
			HttpSession session, Model model) {

		DateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dtf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateMohon = new Date();

		Date tarikhMula = null;
		Date tarikhTamat = null;

		try {
			tarikhMula = dtf2.parse(temp.getTarikhMula());
			tarikhTamat = dtf2.parse(temp.getTarikhTamat());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// String tarikhMula = temp.getTarikhMula();

		Permohonan permohonan = new Permohonan();

		if (temp.getId() != null) {
			permohonan.setId(temp.getId());
		}

		permohonan.setNama(temp.getNama());
		permohonan.setNamaPelulus(temp.getNamaPelulus());
		permohonan.setKp(temp.getKp());
		permohonan.setBahagian(temp.getBahagian());
		permohonan.setEmel(temp.getEmel());
		permohonan.setPassport(temp.getPassport());
		permohonan.setTujuan(temp.getTujuan());
		permohonan.setTempatBertugas(temp.getTempatBertugas());
		permohonan.setTarikhMula(dtf2.format(tarikhMula));
		permohonan.setTarikhTamat(dtf2.format(tarikhTamat));
		permohonan.setNoTelefonBimbit(temp.getNoTelefonBimbit());
		permohonan.setPeruntukan(temp.getPeruntukan());
		permohonan.setCatatan(temp.getCatatan());
		permohonan.setTarikhMohon(dtf2.format(dateMohon));
		permohonan.setPembangunan(temp.getPembangunan());
		permohonan.setNoBilBom(temp.getNoBilBom());
		permohonan.setEnrichNo(temp.getEnrichNo());
		permohonan.setStatusPermohonan("Baru");

		MultipartFile muatNaikBom = temp.getMuatNaikBom();
		if (muatNaikBom.getOriginalFilename() != null) {
			File convertFile = new File(path + muatNaikBom.getOriginalFilename());
			try {
				convertFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(convertFile);
				fout.write(muatNaikBom.getBytes());
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			permohonan.setMuatNaikBom(convertFile.getAbsolutePath());
		}

		permohonanService.save(permohonan);

		if (pt.size() != 0) {

			for (int i = 0; i < pt.size(); i++) {

				Date tarikhPergi = null;

				if (pt.get(i).getTarikhPergi() != null) {
					try {
						tarikhPergi = dtf2.parse(pt.get(i).getTarikhPergi());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Penerbangan penerbangan = new Penerbangan();
				if (pt.get(i).getPenerbanganId() != null) {
					penerbangan.setPenerbanganId(Long.parseLong(pt.get(i).getPenerbanganId()));
				}

				penerbangan.setPenerbangan(pt.get(i).getPenerbangan());
				penerbangan.setTarikhPergi(dtf2.format(tarikhPergi));
				penerbangan.setWaktuBerlepas(pt.get(i).getWaktuBerlepas());
				penerbangan.setWaktuTiba(pt.get(i).getWaktuTiba());
				penerbangan.setJenisPesawat(pt.get(i).getJenisPesawat());
				penerbangan.setNoPesawat(pt.get(i).getNoPesawat());
				penerbangan.setDariLokasi(pt.get(i).getDariLokasi());
				penerbangan.setDestinasi(pt.get(i).getDestinasi());
				penerbangan.setPermohonan(permohonan);
				penerbangan.setStatus(false);

				penerbanganService.save(penerbangan);
			}

		}

		if (barangant.size() != 0) {

			for (int i = 0; i < barangant.size(); i++) {
				Barangan barangan = new Barangan();
				barangan.setAnggaranBerat(barangant.get(i).getAnggaranBerat());
				barangan.setBaranganDibawa(barangant.get(i).getBaranganDibawa());
				barangan.setJumlah(barangant.get(i).getJumlah());
				barangan.setTotal(barangant.get(barangant.size() - 1).getTotal());
				barangan.setPermohonan(permohonan);
				baranganService.save(barangan);

			}

		}

		barangant.removeAll(barangant);
		pt.removeAll(pt);

		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());

		return "redirect:/permohonanTiket";
	}

	@RequestMapping(value = "/permohonanOpen", method = RequestMethod.POST)
	public String registrationOpenTiket(@ModelAttribute("permohonanOpen") PermohonanTemp temp, HttpSession session,
			Model model) {

		DateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dtf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateMohon = new Date();

		Date tarikhMula = null;
		Date tarikhTamat = null;

		try {
			if (temp.getTarikhMula() != null) {
				tarikhMula = dtf2.parse(temp.getTarikhMula());
			}

			if (temp.getTarikhTamat() != null) {
				tarikhTamat = dtf2.parse(temp.getTarikhTamat());
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// String tarikhMula = temp.getTarikhMula();

		Permohonan permohonan = new Permohonan();

		if (temp.getId() != null) {
			permohonan.setId(temp.getId());
		}

		permohonan.setNama(temp.getNama());
		permohonan.setNamaPelulus(temp.getNamaPelulus());
		permohonan.setKp(temp.getKp());
		permohonan.setBahagian(temp.getBahagian());
		permohonan.setEmel(temp.getEmel());
		permohonan.setPassport(temp.getPassport());
		permohonan.setTujuan(temp.getTujuan());
		permohonan.setTempatBertugas(temp.getTempatBertugas());

		if (tarikhMula != null) {
			permohonan.setTarikhMula(dtf2.format(tarikhMula));
		}

		if (tarikhTamat != null) {
			permohonan.setTarikhTamat(dtf2.format(tarikhTamat));
		}
		permohonan.setNoTelefonBimbit(temp.getNoTelefonBimbit());
		permohonan.setPeruntukan(temp.getPeruntukan());
		permohonan.setCatatan(temp.getCatatan());
		permohonan.setTarikhMohon(dtf2.format(dateMohon));
		permohonan.setPembangunan(temp.getPembangunan());
		permohonan.setNoBilBom(temp.getNoBilBom());
		permohonan.setEnrichNo(temp.getEnrichNo());
		permohonan.setStatusPermohonan("Baru");

		if (temp.getMuatNaikBom() != null) {
			MultipartFile muatNaikBom = temp.getMuatNaikBom();
			File convertFile = new File(path + muatNaikBom.getOriginalFilename());
			try {
				convertFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(convertFile);
				fout.write(muatNaikBom.getBytes());
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			permohonan.setMuatNaikBom(convertFile.getAbsolutePath());
		}

		permohonanService.save(permohonan);

		if (pt.size() != 0) {

			for (int i = 0; i < pt.size(); i++) {

				Date tarikhPergi = null;

				if (pt.get(i).getTarikhPergi() != null) {
					try {
						tarikhPergi = dtf2.parse(pt.get(i).getTarikhPergi());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				Penerbangan penerbangan = new Penerbangan();
				penerbangan.setPenerbangan(pt.get(i).getPenerbangan());

				if (tarikhPergi != null) {
					penerbangan.setTarikhPergi(dtf2.format(tarikhPergi));
				}
				penerbangan.setWaktuBerlepas(pt.get(i).getWaktuBerlepas());
				penerbangan.setWaktuTiba(pt.get(i).getWaktuTiba());
				penerbangan.setJenisPesawat(pt.get(i).getJenisPesawat());
				penerbangan.setNoPesawat(pt.get(i).getNoPesawat());
				penerbangan.setDariLokasi(pt.get(i).getDariLokasi());
				penerbangan.setDestinasi(pt.get(i).getDestinasi());
				penerbangan.setPermohonan(permohonan);
				penerbangan.setStatus(false);

				penerbanganService.save(penerbangan);
			}

		}

		if (barangant.size() != 0) {

			for (int i = 0; i < barangant.size(); i++) {
				Barangan barangan = new Barangan();
				barangan.setAnggaranBerat(barangant.get(i).getAnggaranBerat());
				barangan.setBaranganDibawa(barangant.get(i).getBaranganDibawa());
				barangan.setJumlah(barangant.get(i).getJumlah());
				barangan.setTotal(barangant.get(barangant.size() - 1).getTotal());
				barangan.setPermohonan(permohonan);
				baranganService.save(barangan);

			}

		}

		barangant.removeAll(barangant);
		pt.removeAll(pt);

		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());

		return "redirect:/permohonanTiket";
	}

	// @RequestMapping(value = "/permohonanOpen", method = RequestMethod.POST)
	// public String registrationOpenTiket(@ModelAttribute("permohonanForm")
	// Permohonan userForm, Model model,
	// HttpSession session) {
	// String username =
	// SecurityContextHolder.getContext().getAuthentication().getName();
	//
	// User user = userService.findByUsername(username);
	// session.setAttribute("user", user);
	//
	// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	// Date date = new Date();
	//
	// userForm.setTarikhMohon(dateFormat.format(date));
	// userForm.setStatusPermohonan("Open Tiket Baru");
	// model.addAttribute("jawatan", user.getJawatan());
	// model.addAttribute("username", user.getUsername());
	// permohonanService.save(userForm);
	//
	// return "redirect:/permohonanTiket";
	// }

	@RequestMapping(value = "/penerbanganForm", method = RequestMethod.POST)
	public String registrationOpenTiket(@ModelAttribute("permohonanPenerbangan") Penerbangan userForm) {

		// penerbanganService.save(userForm);
		// permohonanService.save(userForm);

		return "redirect:/permohonanTiket";
	}

	// @RequestMapping(value = "/downloadTiket", method = RequestMethod.GET)
	// public String downloadTiket(@RequestParam("id") Long id, Model model,
	// HttpSession session) {
	// String username =
	// SecurityContextHolder.getContext().getAuthentication().getName();
	//
	// User user = userService.findByUsername(username);
	// session.setAttribute("user", user);
	//
	// // model.addAttribute("welcome", permohonanService.getAll());x
	// model.addAttribute("welcome",
	// permohonanService.findByNama(user.getNamaStaff()));
	// // model.addAttribute("permohonanForm", new Permohonan());
	// model.addAttribute("permohonanOpen", new Permohonan());
	// model.addAttribute("permohonanForm", new PermohonanTemp());
	// model.addAttribute("permohonanPenerbangan", new Penerbangan());
	// model.addAttribute("permohonanBarangan", new Barangan());
	// model.addAttribute("penghapusanPermohonan", new Permohonan());
	// model.addAttribute("permohonanKemaskini", new Permohonan());
	// model.addAttribute("permohonanBatal", new Permohonan());
	// model.addAttribute("downloadTiketSelesai",
	// permohonanService.findById(id));
	// model.addAttribute("jawatan", user.getJawatan());
	// model.addAttribute("username", user.getUsername());
	//
	// return "permohonanTiket";
	//
	// }

	@RequestMapping(value = "/downloadTiket", method = RequestMethod.GET)
	@ResponseBody
	public void downloadTiket(@ModelAttribute("downloadTiketSelesai") Permohonan userForm, Model model,
			HttpServletResponse response, HttpSession session) throws IOException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		// penerbanganService.save(userForm);
		// permohonanService.save(userForm);

		ArrayList<Penerbangan> penerbangan = new ArrayList<>();

		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));

		System.out.println("tengok sini mana tahu :" + userForm);

		ArrayList<Pembelian> pembelian = new ArrayList<>();

		ArrayList<Permohonan> permohonan = new ArrayList<>();

		byte[] zip = null;
		File directory = new File(path);

		ArrayList<String> files = new ArrayList<String>();

		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());
		for (Permohonan prJb : permohonan) {
			penerbangan = (ArrayList<Penerbangan>) penerbanganService.findByPermohonan(prJb);

			for (Penerbangan jb : penerbangan) {
				model.addAttribute("downloadTiketSelesai", pembelianService.findByPenerbangan(jb));
				pembelian = (ArrayList<Pembelian>) pembelianService.findByPenerbangan(jb);

				System.out.println("hoiiiiiiiiiii :::" + pembelian);
				List<Pembelian> pembelianTiket = new ArrayList<>();

				for (Pembelian pembelianForm : pembelian) {
					if (pembelianForm.getId() != null) {

						if (pembelianForm.getMuatNaikTiket() != null) {
							String[] fileName = splitPath(pembelianForm.getMuatNaikTiket());
							files.add(fileName[fileName.length - 1]);

						}
					}
				}

				zip = zipFiles(directory, files);

				ServletOutputStream sos = response.getOutputStream();
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment; filename=ticket.zip");

				sos.write(zip);
				sos.flush();

			}
		}
	}

	public static String[] splitPath(String path) {
		String backslash = ((char) 92) + "";
		if (path.contains(backslash)) {
			ArrayList<String> parts = new ArrayList<>();
			int start = 0;
			int end = 0;
			for (int c : path.toCharArray()) {
				if (c == 92) {
					parts.add(path.substring(start, end));
					start = end + 1;
				}
				end++;
			}
			parts.add(path.substring(start));
			return parts.toArray(new String[parts.size()]);
		}
		return path.split("/");
	}

	private byte[] zipFiles(File directory, ArrayList<String> files) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte bytes[] = new byte[2048];

		for (String fileName : files) {
			FileInputStream fis = new FileInputStream(directory + "\\" + fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);

			zos.putNextEntry(new ZipEntry(fileName));

			int bytesRead;
			while ((bytesRead = bis.read(bytes)) != -1) {
				zos.write(bytes, 0, bytesRead);
			}
			zos.closeEntry();
			bis.close();
			fis.close();
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();

		return baos.toByteArray();
	}

	@RequestMapping(value = "/downloadBom", method = RequestMethod.GET)
	@ResponseBody
	public void downloadBom(@RequestParam("id") Long id, HttpServletResponse response, HttpSession session)
			throws IOException {

		Long idPemohon = id;

		Permohonan permohonan = permohonanService.findById(idPemohon);

		File file = new File(permohonan.getMuatNaikBom());

		// guess contenty type
		String mime = URLConnection.guessContentTypeFromName(file.getPath());

		response.setContentType(mime);
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getPath());

		BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = inStrem.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		outStream.flush();
		inStrem.close();

	}

	@RequestMapping(value = "/downloadBorang", method = RequestMethod.POST)
	public void export(ModelAndView model, HttpServletResponse response, HttpSession session)
			throws IOException, JRException, SQLException {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		ArrayList<Permohonan> permohonan = new ArrayList<>();

		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		for (Permohonan prJb : permohonan) {
			prJb.getNama();

			String path = resourceLoader.getResource("classpath:/reports/Report1.jrxml").getURI().getPath();

			JasperReport jasperReport = JasperCompileManager.compileReport(path);

			// Parameters for report

			Map<String, Object> item = new HashMap<String, Object>();
			item.put("penerbanganID", "");
			item.put("Nama", prJb.getNama());
			item.put("Kp", "");
			item.put("Unit", "");
			item.put("Emel", "");
			item.put("NoPasport", "");
			item.put("Tujuan", "");
			item.put("tempatBertugas", "");
			item.put("tarikhMula", "");
			item.put("tarikhTamat", "");
			item.put("Peruntukan", "");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(permohonanService.report());

			JasperPrint print = JasperFillManager.fillReport(jasperReport, item, dataSource);

			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(print, out);
		}
	}

	// @RequestMapping(value = "/downloadBorang", method = RequestMethod.POST)
	// public String downloadBorang(@ModelAttribute("downloadBorangSelesai")
	// Permohonan userForm, Model model,
	// HttpServletResponse response, HttpSession session) throws IOException {
	// String username =
	// SecurityContextHolder.getContext().getAuthentication().getName();
	//
	// User user = userService.findByUsername(username);
	// session.setAttribute("user", user);
	// // penerbanganService.save(userForm);
	// // permohonanService.save(userForm);
	//
	// ArrayList<Penerbangan> penerbangan = new ArrayList<>();
	//
	// model.addAttribute("welcome",
	// permohonanService.findByNama(user.getNamaStaff()));
	//
	// System.out.println("tengok sini mana tahu :" + userForm);
	//
	// ArrayList<Pembelian> pembelian = new ArrayList<>();
	//
	// ArrayList<Permohonan> permohonan = new ArrayList<>();
	//
	// permohonan = (ArrayList<Permohonan>)
	// permohonanService.findByNama(user.getNamaStaff());
	// //
	// // Permohonan permohonan = (Permohonan)
	// // permohonanService.findByNama(user.getNamaStaff());
	//
	// // pembelianForm = (ArrayList<Pembelian>)
	// // pembelianService.findByPermohonan(permohonan);
	// // for (Permohonan prJb : permohonan) {
	// // penerbangan = (ArrayList<Penerbangan>)
	// // penerbanganService.findByPermohonan(prJb);
	// //
	// // for (Penerbangan jb : penerbangan) {
	// // model.addAttribute("downloadTiketSelesai",
	// // pembelianService.findByPenerbangan(jb));
	// // pembelian = (ArrayList<Pembelian>)
	// // pembelianService.findByPenerbangan(jb);
	// // for (Pembelian pembelianForm : pembelian) {
	// //
	// // PrintWriter out = response.getWriter();
	// //
	// // if (pembelianForm.getMuatNaikTiket() != null) {
	// // response.setContentType(pembelianForm.getMuatNaikTiket());
	// // File file = new File(pembelianForm.getMuatNaikTiket());
	// // FileInputStream fileIn = new FileInputStream(file + path);
	// //
	// // int i;
	// //
	// // while ((i = fileIn.read()) != -1) {
	// // out.write(i);
	// // }
	// // fileIn.close();
	// // out.close();
	// // } else {
	// // System.out.println("no file to download");
	// // }
	// // // ServletOutputStream out = response.getOutputStream();
	// // }
	// // }
	// // }
	// return "redirect:/permohonanTiket";
	// }

	/*
	 * @RequestMapping(value = "/hapusPermohonan", method = RequestMethod.GET)
	 * public String hapusPermohonan( @ModelAttribute("penghapusanPermohonan")
	 * Permohonan userForm, Model model) {
	 * 
	 * System.out.println("masuk hapus view"); model.addAttribute("welcome",
	 * permohonanService.getAll()); model.addAttribute("permohonanForm", new
	 * Permohonan()); model.addAttribute("permohonanOpen", new Permohonan());
	 * model.addAttribute("penghapusanPermohonan", new Permohonan());
	 * model.addAttribute("permohonanBatal", new Permohonan()); return
	 * "permohonanTiket";
	 * 
	 * }
	 */

	@RequestMapping(value = "/hapusPermohonan", method = RequestMethod.POST)
	public String hapusPermohonan(@ModelAttribute("penghapusanPermohonan") Permohonan userForm,
			Penerbangan penerbanganForm, Barangan baranganForm, BindingResult bindingResult, Model model,
			HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));
		// penerbanganService.remove(penerbanganForm);
		// baranganService.remove(baranganForm);
		permohonanService.remove(userForm);
		// penerbanganService.remove(penerbanganForm);
		// baranganService.remove(baranganForm);

		model.addAttribute("permohonanForm", new PermohonanTemp());
		// model.addAttribute("permohonanForm", new Permohonan());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new Permohonan());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());

		return "redirect:/permohonanTiket";

	}

	@RequestMapping(value = "/batalPermohonan", method = RequestMethod.POST)
	public String batakPermohonan(@ModelAttribute("permohonanBatal") Permohonan userForm, Model model,
			HttpSession session, HttpServletRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		// model.addAttribute("welcome", permohonanService.getAll());
		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));

		userForm.setStatusPermohonan("Batal");
		permohonanService.save(userForm);

		model.addAttribute("permohonanForm", new PermohonanTemp());
		// model.addAttribute("permohonanForm", new Permohonan());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new Permohonan());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());
		String contextPath = request.getContextPath();
		System.out.println(contextPath);

		String emel = null;

		String emel2 = null;

		user.getNamaPengurus();

		ArrayList<Pengguna> pengguna = new ArrayList<>();

		pengguna = (ArrayList<Pengguna>) penggunaService.findAll();

		for (Pengguna jb : pengguna) {
			if (jb.getRefJawatan() != null) {
				if (jb.getRefJawatan().getRefRole().getRoleId().equals(3)) {
					if (jb.getCawangan().equals(user.getCawangan())) {
						emel = jb.getEmail();
					}
				}
			}
		}

		System.out.println("emel >" + emel);

		SendHTMLEmail.sendHtmlEmail(emel,
				"<!DOCTYPE html><html><body><style type='text/css'>#imageSize"
						+ " {width: 100%;height: 3%;}#footer{background:rgba(226,226,226,1);background:-moz-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-webkit-gradient(left top, right top, color-stop(0%, rgba(226,226,226,1)), color-stop(50%, rgba(219,219,219,1)), "
						+ "color-stop(51%, rgba(209,209,209,1)), color-stop(100%,rgba(254,254,254,1)));background: -webkit-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%,rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-o-linear-gradient(left, rgba(226,226,226,1) 0%,rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%,rgba(254,254,254,1)"
						+ "100%);background: -ms-linear-gradient(left, rgba(226,226,226,10%, rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, "
						+ "rgba(254,254,254,1) 100%);background: linear-gradient(to right,rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba"
						+ "(209,209,209,1) 51%, rgba(254,254,254,1) 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#e2e2e2',"
						+ " endColorstr='#fefefe', GradientType=1 );width: 100%;height:3%;}</style>"
						+ "<img src='C:\\Users\\saufirasid\\Desktop\\STP\\STP\\src"
						+ "mpc-header.png' id='imageSize'/><p>Assalamuaikum dan Salam Sejahtera,Terdapatpermohonan PEMBATALAN "
						+ "diterima untuk kelulusan. Sila log masuk <a href='http://localhost:8090/login'>di sini</a> untuk melihat maklumat permohonan pembatalan.Sekian, terima kasih."
						+ "</p><p>Maklumat Hubungan: <a href='mailto:flight@mpc.gov.my'>flight@mpc.gov.my</a>.</p><footer><divid='footer'>"
						+ "</div></footer></body></html>");

		for (Pengguna jb2 : pengguna) {
			if (jb2.getRefJawatan() != null) {
				if (jb2.getRefJawatan().getRefRole().getRoleId().equals(4)) {
					if (jb2.getCawangan().equals(user.getCawangan())) {
						emel2 = jb2.getEmail();
					}
				}
			}
		}

		SendHTMLEmail.sendHtmlEmail(emel2,
				"<!DOCTYPE html><html><body><style type='text/css'>#imageSize"
						+ " {width: 100%;height: 3%;}#footer{background:rgba(226,226,226,1);background:-moz-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-webkit-gradient(left top, right top, color-stop(0%, rgba(226,226,226,1)), color-stop(50%, rgba(219,219,219,1)), "
						+ "color-stop(51%, rgba(209,209,209,1)), color-stop(100%,rgba(254,254,254,1)));background: -webkit-linear-gradient(left,"
						+ " rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%,rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
						+ "-o-linear-gradient(left, rgba(226,226,226,1) 0%,rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%,rgba(254,254,254,1)"
						+ "100%);background: -ms-linear-gradient(left, rgba(226,226,226,10%, rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, "
						+ "rgba(254,254,254,1) 100%);background: linear-gradient(to right,rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba"
						+ "(209,209,209,1) 51%, rgba(254,254,254,1) 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#e2e2e2',"
						+ " endColorstr='#fefefe', GradientType=1 );width: 100%;height:3%;}</style>"
						+ "<img src='C:\\Program Files\\STP"
						+ "mpc-header.png' id='imageSize'/><p>Assalamuaikum dan Salam Sejahtera,Terdapatpermohonan PEMBATALAN "
						+ "diterima untuk tindakan. Sila log masuk <a href='http://localhost:8090/login'>di sini</a> untuk melihat maklumat permohonan pembatalan.Sekian, terima kasih."
						+ "</p><p>Maklumat Hubungan: <ahref='mailto:flight@mpc.gov.my'>flight@mpc.gov.my</a>.</p><footer><divid='footer'>"
						+ "</div></footer></body></html>");

		return "redirect:/permohonanTiket";

	}

	@RequestMapping(value = "/batalPermohonanProses", method = RequestMethod.POST)
	public String batalPermohonanProses(@ModelAttribute("permohonanBatalProses") Permohonan userForm, Model model,
			HttpSession session, HttpServletRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		// model.addAttribute("welcome", permohonanService.getAll());
		model.addAttribute("welcome", permohonanService.findByNama(user.getNamaStaff()));

		userForm.setStatusPermohonan("Dibatalkan");
		permohonanService.save(userForm);

		model.addAttribute("permohonanForm", new PermohonanTemp());
		// model.addAttribute("permohonanForm", new Permohonan());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new Permohonan());
		model.addAttribute("penghapusanPermohonan", new Permohonan());
		model.addAttribute("permohonanBatal", new Permohonan());
		model.addAttribute("permohonanBatalProses", new Permohonan());
		model.addAttribute("permohonanKemaskini", new PermohonanTemp());
		model.addAttribute("permohonanKemaskiniTemp", new PermohonanTemp());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());
		// String contextPath = request.getContextPath();
		// System.out.println(contextPath);
		//
		// SendHTMLEmail.sendHtmlEmail("mohdsyafiksyaaban@gmail.com",
		// "<!DOCTYPE html><html><body><style type='text/css'>#imageSize"
		// + " {width: 100%;height: 3%;}#footer
		// {background:rgba(226,226,226,1);background:
		// -moz-linear-gradient(left,"
		// + " rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%,
		// rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
		// + "-webkit-gradient(left top, right top, color-stop(0%,
		// rgba(226,226,226,1)), color-stop(50%, rgba(219,219,219,1)), "
		// + "color-stop(51%, rgba(209,209,209,1)), color-stop(100%,
		// rgba(254,254,254,1)));background: -webkit-linear-gradient(left,"
		// + " rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%,
		// rgba(209,209,209,1) 51%, rgba(254,254,254,1) 100%);background: "
		// + "-o-linear-gradient(left, rgba(226,226,226,1) 0%,
		// rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%,
		// rgba(254,254,254,1)"
		// + "100%);background: -ms-linear-gradient(left, rgba(226,226,226,1)
		// 0%, rgba(219,219,219,1) 50%, rgba(209,209,209,1) 51%, "
		// + "rgba(254,254,254,1) 100%);background: linear-gradient(to right,
		// rgba(226,226,226,1) 0%, rgba(219,219,219,1) 50%, rgba"
		// + "(209,209,209,1) 51%, rgba(254,254,254,1) 100%);filter:
		// progid:DXImageTransform.Microsoft.gradient( startColorstr='#e2e2e2',"
		// + " endColorstr='#fefefe', GradientType=1 );width: 100%;height:
		// 3%;}</style>"
		// + "<img src='C:\\Users\\saufirasid\\Desktop\\STP\\STP\\src"
		// + "\\main\\webapp\\resources\\img\\mpc-header.psng' id
		// ='imageSize'/><p>Assalamuaikum dan Salam Sejahtera,Terdapat
		// permohonan PEMBATALAN "
		// + "diterima untuk kelulusan. Sila log masuk <a href=''>di sini</a>
		// untuk melihat maklumat permohonan pembatalan.Sekian, terima kasih."
		// + "</p><p>Maklumat Hubungan: <a
		// href='mailto:systempahantiket@gmail.com'>systempahantiket@gmail.com</a>.</p><footer><div
		// id='footer'>"
		// + "</div></footer></body></html>");

		return "redirect:/permohonanTiket";

	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public String updateStatus(@ModelAttribute("kemaskiniPermohon") Permohonan userForm, Model model,
			HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.findByStatusPermohonan("Baru"));

		userForm.setStatusPermohonan("Tolak");
		permohonanService.save(userForm);

		// model.addAttribute("permohonanForm", new Permohonan());
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(userForm));
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new Permohonan());
		model.addAttribute("kemaskiniPermohon", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());

		return "welcome";

	}

	@RequestMapping(value = "/updateStatusLulus", method = RequestMethod.POST)
	public String updateStatusLulus(@ModelAttribute("kemaskiniPermohon") Permohonan userForm, Model model,
			HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.findByStatusPermohonan("Baru"));

		userForm.setStatusPermohonan("Lulus");
		permohonanService.save(userForm);

		// model.addAttribute("permohonanForm", new Permohonan());
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(userForm));
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new Permohonan());
		model.addAttribute("kemaskiniPermohon", new PermohonanTemp());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());

		return "welcome";

	}

	@RequestMapping(value = "/updateStatusPengesahan", method = RequestMethod.POST)
	public String updateStatusPengesahan(@ModelAttribute("kemaskiniPengesahan") Permohonan userForm, Model model,
			HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.getAll());

		userForm.setStatusPermohonan("Tiket Terbuka");
		permohonanService.save(userForm);

		// model.addAttribute("permohonanForm", new Permohonan());
		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(userForm));
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("permohonanOpen", new Permohonan());
		model.addAttribute("kemaskiniPengesahan", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());

		return "redirect:/pengesahan";

	}

	@RequestMapping(value = "/updatePembelianForm", method = RequestMethod.POST)
	public String pembelian(@ModelAttribute("updatePembelian") PembelianTemp temp, Model model, HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.getAll());

		System.out.println("bohhhh ::" + temp);

		Pembelian pembelianForm = new Pembelian();

		File convertFile = null;

		if (temp.getMuatNaikTiket() != null) {
			MultipartFile muatNaikBom = temp.getMuatNaikTiket();
			convertFile = new File(path + muatNaikBom.getOriginalFilename());
			try {
				convertFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(convertFile);
				fout.write(muatNaikBom.getBytes());
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//

		if (convertFile != null) {
			pembelianForm.setMuatNaikTiket(convertFile.getAbsolutePath());
		}
		pembelianForm.setId(temp.getId());
		pembelianForm.setAlasan(temp.getAlasan());
		pembelianForm.setCaraBeli(temp.getCaraBeli());
		pembelianForm.setPenerbangan(temp.getPenerbangan());
		pembelianForm.setHargaPengurangan(temp.getHargaPengurangan());
		pembelianForm.setWaran(temp.getWaran());
		pembelianForm.setHargaTiket(temp.getHargaTiket());
		pembelianForm.setPermohonan(temp.getPermohonan());

		model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(pembelianForm.getPermohonan()));

		ArrayList<Penerbangan> penerbangan = new ArrayList<>();

		ArrayList<Penerbangan> penerbangan2 = new ArrayList<>();

		Boolean check = true;

		Long id = pembelianForm.getPenerbangan().getPenerbanganId();

		System.out.println("tengok ni : " + id);

		penerbangan = (ArrayList<Penerbangan>) penerbanganService.findByPermohonan(pembelianForm.getPermohonan());

		penerbangan2 = (ArrayList<Penerbangan>) penerbanganService.findByPenerbanganId(id);

		for (Penerbangan jb2 : penerbangan2) {

			if (jb2.getStatus().equals(false)) {
				jb2.setStatus(true);
			} else if (jb2.getStatus().equals(true)) {
				jb2.setStatus(true);
			}
		}

		for (Penerbangan jb : penerbangan) {

			if (jb.getStatus().equals(false)) {
				check = false;
			}
		}

		if (check) {
			pembelianService.save(pembelianForm);
			Permohonan userForm = pembelianForm.getPermohonan();
			userForm.setStatusPermohonan("Selesai");
			permohonanService.save(userForm);
		} else {
			pembelianService.save(pembelianForm);
			return "redirect:/pembelian";
		}

		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("kemaskiniPermohon", new Permohonan());
		model.addAttribute("updatePembelian", new Pembelian());

		return "redirect:/pembelian";
	}

	@RequestMapping(value = "/updateKemaskiniForm", method = RequestMethod.POST)
	public String pembelianKemaskiniTiket(@ModelAttribute("updateKemaskiniTiket") Penerbangan penerbanganForm,
			Model model, HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.getAll());

		// model.addAttribute("Penerbangan",
		// penerbanganService.findByPermohonan(pembelianForm.getPermohonan()));

		ArrayList<Penerbangan> penerbangan = new ArrayList<>();

		ArrayList<Penerbangan> penerbangan2 = new ArrayList<>();

		Boolean check = true;

		// if(pembelianForm.getPenerbangan().getPenerbanganId() != null){
		Long id = penerbanganForm.getPenerbanganId();

		System.out.println("tengok ni : " + id);

		// penerbangan = (ArrayList<Penerbangan>)
		// penerbanganService.findByPermohonan(pembelianForm.getPermohonan());

		penerbangan2 = (ArrayList<Penerbangan>) penerbanganService.findByPenerbanganId(id);

		System.out.println("sini :" + penerbangan2);

		DateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dtf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date tarikhPergi = null;
		try {
			if (penerbanganForm.getTarikhPergi() != null) {
				tarikhPergi = dtf2.parse(penerbanganForm.getTarikhPergi());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tarikhPergi != null) {
			penerbanganForm.setTarikhPergi(dtf2.format(tarikhPergi));
		}

		System.out.println("haaaaaaaaa" + penerbanganForm);
		penerbanganService.save(penerbanganForm);

		model.addAttribute("permohonanForm", new PermohonanTemp());
		model.addAttribute("permohonanPenerbangan", new Penerbangan());
		model.addAttribute("permohonanBarangan", new Barangan());
		model.addAttribute("kemaskiniPermohon", new Permohonan());
		model.addAttribute("updatePembelian", new Pembelian());
		model.addAttribute("updateKemaskiniTiket", new PenerbanganTemp());

		return "redirect:/pembelian";
	}

	@RequestMapping(value = { "/maintenancePage" }, method = RequestMethod.GET)
	public String updateCawangan(Model model, HttpSession session, RefCawangan refCawangan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}
		model.addAttribute("refCawangan", refCawanganService.getAll());
		model.addAttribute("kemaskiniPengesahan", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("kemaskiniCawangan", new RefCawangan());
		model.addAttribute("username", user.getUsername());
		return "maintenancePage";
	}

	@RequestMapping(value = "/updateCawangan", method = RequestMethod.POST)
	public String updateCawangan(@ModelAttribute("kemaskiniCawangan") RefCawangan userForm, Model model,
			HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		refCawanganService.save(userForm);

		model.addAttribute("refCawangan", refCawanganService.getAll());
		model.addAttribute("kemaskiniCawangan", new RefCawangan());

		return "redirect:/maintenancePage";

	}

	@RequestMapping(value = { "/maintenancePageLokasi" }, method = RequestMethod.GET)
	public String updateLokasi(Model model, HttpSession session, RefLokasi refLokasi) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}
		model.addAttribute("refLokasi", refLokasiService.getAll());
		model.addAttribute("kemaskiniPengesahan", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("kemaskiniLokasi", new RefLokasi());
		model.addAttribute("username", user.getUsername());
		return "maintenancePageLokasi";
	}

	@RequestMapping(value = "/updateLokasi", method = RequestMethod.POST)
	public String updateLokasi(@ModelAttribute("kemaskiniLokasi") RefLokasi userForm, Model model,
			HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		refLokasiService.save(userForm);

		model.addAttribute("refLokasi", refLokasiService.getAll());
		model.addAttribute("kemaskiniLokasi", new RefLokasi());

		return "redirect:/maintenancePageLokasi";

	}

	@RequestMapping(value = { "/maintenancePagePeruntukan" }, method = RequestMethod.GET)
	public String updatePeruntukan(Model model, HttpSession session, RefPeruntukan refPeruntukan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}
		model.addAttribute("refPeruntukan", refPeruntukanService.getAll());
		model.addAttribute("kemaskiniPengesahan", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("kemaskiniPeruntukan", new RefPeruntukan());
		model.addAttribute("username", user.getUsername());
		return "maintenancePagePeruntukan";
	}

	@RequestMapping(value = "/updatePeruntukan", method = RequestMethod.POST)
	public String updatePeruntukan(@ModelAttribute("kemaskiniPeruntukan") RefPeruntukan userForm, Model model,
			HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		refPeruntukanService.save(userForm);

		model.addAttribute("refPeruntukan", refPeruntukanService.getAll());
		model.addAttribute("kemaskiniPeruntukan", new RefPeruntukan());

		return "redirect:/maintenancePagePeruntukan";

	}

	@RequestMapping(value = { "/maintenancePagePesawat" }, method = RequestMethod.GET)
	public String updatePesawat(Model model, HttpSession session, RefPesawat refPeruntukan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}
		model.addAttribute("refPesawat", refPesawatService.getAll());
		model.addAttribute("kemaskiniPengesahan", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("kemaskiniPesawat", new RefPesawat());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("username", user.getUsername());
		return "maintenancePagePesawat";
	}

	@RequestMapping(value = "/updatePesawat", method = RequestMethod.POST)
	public String updatePesawat(@ModelAttribute("kemaskiniPesawat") RefPesawat userForm, Model model,
			HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		refPesawatService.save(userForm);

		model.addAttribute("refPesawat", refPesawatService.getAll());
		model.addAttribute("kemaskiniPesawat", new RefPesawat());

		return "redirect:/maintenancePagePesawat";

	}

	@RequestMapping(value = { "/maintenancePageUnit" }, method = RequestMethod.GET)
	public String updateUnitBahagian(Model model, HttpSession session, RefUnitBahagian refUnitBahagian) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("kemaskiniUnit", new RefUnitBahagian());
		model.addAttribute("username", user.getUsername());
		return "maintenancePageUnit";
	}

	@RequestMapping(value = "/updateUnit", method = RequestMethod.POST)
	public String updateUnitBahagian(@ModelAttribute("kemaskiniUnit") RefUnitBahagian userForm, Model model,
			HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		refUnitBahagianService.save(userForm);

		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("kemaskiniUnit", new RefUnitBahagian());

		return "redirect:/maintenancePageUnit";

	}

	@RequestMapping(value = { "/maintenancePageJawatan" }, method = RequestMethod.GET)
	public String updateJawatan(Model model, HttpSession session, RefJawatan refJawatan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		model.addAttribute("refJawatan", refJawatanService.getAll());
		System.out.println("sini :::::::" + refJawatanService.getAll());
		model.addAttribute("roleAll", refRoleService.getAll());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("namaStaff", user.getNamaStaff());
		model.addAttribute("kemaskiniJawatan", new RefJawatan());
		model.addAttribute("username", user.getUsername());
		return "maintenancePageJawatan";
	}

	@RequestMapping(value = "/updateJawatan", method = RequestMethod.POST)
	public String updateJawatan(@ModelAttribute("kemaskiniJawatan") RefJawatan refJawatan, Model model,
			HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		refJawatanService.save(refJawatan);

		model.addAttribute("refJawatan", refJawatanService.getAll());
		model.addAttribute("role", refRoleService.getAll());
		model.addAttribute("kemaskiniJawatan", new RefJawatan());

		return "redirect:/maintenancePageJawatan";

	}

	@RequestMapping(value = { "/infoPengguna" }, method = RequestMethod.GET)
	public String infoPengguna(Model model, HttpSession session, RefJawatan refJawatan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = penggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId() != null) {
						if (jb2.getRoleId().equals(idRole2)) {
							model.addAttribute("role", jb2.getRoleDesc());
							System.out.println("tengok listrole -----" + jb2.getRoleDesc());
						}
					}
				}
			}
		}

		List<Pengguna> jawatanUser = new ArrayList<>();

		for (Pengguna jb : penggunaService.findAll()) {
			System.out.println("nama pengurus ::::: " + jb.getRefJawatan().getRefRole().getRoleId());
			if (jb.getRefJawatan().getRefRole().getRoleId() == 2) {
				jawatanUser.add(jb);
				model.addAttribute("jawatanUser", jawatanUser);
			}
		}

		model.addAttribute("listPengguna", penggunaService.findAll());
		model.addAttribute("listCawangan", refCawanganService.getAll());
		model.addAttribute("listJawatan", refJawatanService.getAll());
		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("refJawatan", refJawatanService.getAll());
		System.out.println("sini :::::::" + refJawatanService.getAll());
		model.addAttribute("roleAll", refRoleService.getAll());
		model.addAttribute("pengguna", pengguna);
		model.addAttribute("userJawatan", pengguna.getRefJawatan().getJawatanId());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("userPengurus", pengguna.getNamaPengurus());
		model.addAttribute("userUnit", pengguna.getUnit());
		model.addAttribute("userCawangan", pengguna.getCawangan());
		model.addAttribute("namaStaff", pengguna.getNamaStaff());
		model.addAttribute("namaPengguna", pengguna.getUsername());
		model.addAttribute("noStaff", pengguna.getStaffNo());
		model.addAttribute("enrichNo", pengguna.getEnrichNo());
		model.addAttribute("noKp", pengguna.getNoKP());
		model.addAttribute("passport", pengguna.getPassport());
		model.addAttribute("namaStaff", pengguna.getNamaStaff());
		model.addAttribute("emelAdd", pengguna.getEmail());
		model.addAttribute("userStatus", pengguna.getStatus());
		model.addAttribute("noPhone", pengguna.getNoTelefon());
		model.addAttribute("kemaskiniJawatan", new RefJawatan());
		model.addAttribute("username", pengguna.getUsername());
		model.addAttribute("infoPenggunaForm", new Pengguna());
		return "infoPengguna";
	}

	@RequestMapping(value = "/infoPengguna", method = RequestMethod.POST)
	public String infoPengguna(@ModelAttribute("infoPenggunaForm") Pengguna daftarPenggunaForm,
			BindingResult bindingResult, Model model) {

		String noKP = (String) daftarPenggunaForm.getNoKP();
		String a = null;
		String b = null;
		String c = null;
		if (noKP.length() > 6) {
			a = noKP.substring(0, 6);
			// IDT1.setValue(a);
			b = noKP.substring(6, 8);
			// IDT2.setValue(b);
			c = noKP.substring(8, 12);
			// IDT3.setValue(c);
		}

		System.out.println("no kp" + c);

		daftarPenggunaForm.setPassword(c);
		penggunaService.save(daftarPenggunaForm);
		model.addAttribute("infoPenggunaForm", new Pengguna());

		return "redirect:infoPengguna";
	}

}