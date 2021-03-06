package com.stp.auth.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stp.auth.model.Barangan;
import com.stp.auth.model.Pembelian;
import com.stp.auth.model.Penerbangan;
import com.stp.auth.model.PenerbanganTemp;
import com.stp.auth.model.Pengguna;
import com.stp.auth.model.Permohonan;
import com.stp.auth.model.PermohonanTemp;
import com.stp.auth.model.RefJawatan;
import com.stp.auth.model.RefRole;
import com.stp.auth.model.User;
import com.stp.auth.service.DaftarPenggunaService;
import com.stp.auth.service.DariLokasiService;
import com.stp.auth.service.PembelianService;
import com.stp.auth.service.PenerbanganService;
import com.stp.auth.service.PermohonanService;
import com.stp.auth.service.RefPeruntukanService;
import com.stp.auth.service.RefPesawatService;
import com.stp.auth.service.RefRoleService;
import com.stp.auth.service.UserService;

@Controller
public class PembelianController {

	@Autowired
	private DariLokasiService dariLokasiService;

	@Autowired
	private PembelianService pembelianService;

	@Autowired
	private RefPeruntukanService refPeruntukanService;

	@Autowired
	private RefPesawatService refPesawatService;

	@Autowired
	private PermohonanService permohonanService;

	@Autowired
	private PenerbanganService penerbanganService;

	@Autowired
	private UserService userService;

	@Autowired
	private RefRoleService refRoleService;

	@Autowired
	private DaftarPenggunaService penggunaService;

	@RequestMapping(value = { "/pembelian" }, method = RequestMethod.GET)
	public String pembelian(Model model, HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Pengguna pengguna = penggunaService.findByUsername(username);
		session.setAttribute("user", pengguna);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) penggunaService.findByNoKP(pengguna.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(pengguna.getUsername())) {
				Long idRole = jb.getRefJawatan().getRefRole().getRoleId();
				pengguna.getRefJawatan().getJawatanDesc();

				Long idRole2 = idRole;
				System.out.println(idRole2);
				ArrayList<RefRole> listRole = new ArrayList<>();

				listRole = (ArrayList<RefRole>) refRoleService.getAll();
				for (RefRole jb2 : listRole) {
					if (jb2.getRoleId().equals(idRole2)) {
						model.addAttribute("role", jb2.getRoleDesc());
						System.out.println("tengok listrole -----" + jb2.getRoleDesc());
					}
				}
			}
		}

		// User user = userService.findByUsername(username);
		// session.setAttribute("user", user);
		
		ArrayList<Permohonan> permohonan2 = new ArrayList<>();
		
		permohonan2 = (ArrayList<Permohonan>) permohonanService.getAll();
		
		List<Permohonan> listPermohonan = new ArrayList<>();
		
		for(Permohonan jb : permohonan2){
			if(jb.getStatusPermohonan().equals("Selesai") || jb.getStatusPermohonan().equals("Lulus") || jb.getStatusPermohonan().equals("Batal") || jb.getStatusPermohonan().equals("Tiket Terbuka")){
				System.out.println("list " + jb);
				listPermohonan.add(jb);
				model.addAttribute("welcome", listPermohonan);
			}
		}
		
//		model.addAttribute("welcome", permohonanService.getAll());

		// ArrayList<Pengguna> pengguna = new ArrayList<>();
		//
		// pengguna = (ArrayList<Pengguna>) penggunaService.findAll();
		//
		// for(Pengguna jb : pengguna){
		// model.addAttribute("pengguna",
		// penggunaService.findByCawangan(jb.getCawangan()));
		// }

		ArrayList<Penerbangan> penerbangan = new ArrayList<>();
		List<Penerbangan> listPenerbangan = new ArrayList<>();
		for (Permohonan userForm : permohonanService.getAll()) {

			userForm.getId();
			
			Permohonan permohonan = permohonanService.findById(userForm.getId());
			
			penerbangan = (ArrayList<Penerbangan>) penerbanganService.findByPermohonan(permohonan);
			
			for (Penerbangan jb : penerbangan) {
				jb.getPenerbanganId();
				System.out.println("nindiaaaaa : " + jb.getPenerbanganId());
				listPenerbangan.add(jb);

			}
		}
		model.addAttribute("Penerbangan", listPenerbangan);
		model.addAttribute("lokasi", dariLokasiService.getAll());
		model.addAttribute("peruntukan", refPeruntukanService.getAll());
		model.addAttribute("pesawat", refPesawatService.getAll());
		model.addAttribute("kemaskiniPermohon", new PermohonanTemp());
		model.addAttribute("updatePembelian", new Pembelian());
		model.addAttribute("updateKemaskiniTiket", new Penerbangan());
		model.addAttribute("namaStaff", pengguna.getNamaStaff());
		// model.addAttribute("kemaskiniPermohon", new Permohonan());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());

		return "pembelian";
	}

}
