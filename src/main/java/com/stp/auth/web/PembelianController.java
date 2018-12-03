package com.stp.auth.web;

import java.util.ArrayList;

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
import com.stp.auth.model.User;
import com.stp.auth.service.DaftarPenggunaService;
import com.stp.auth.service.DariLokasiService;
import com.stp.auth.service.PembelianService;
import com.stp.auth.service.PenerbanganService;
import com.stp.auth.service.PermohonanService;
import com.stp.auth.service.RefPeruntukanService;
import com.stp.auth.service.RefPesawatService;
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
	private DaftarPenggunaService penggunaService;

	@RequestMapping(value = { "/pembelian" }, method = RequestMethod.GET)
	public String pembelian(Model model, HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Pengguna pengguna = penggunaService.findByUsername(username);
		session.setAttribute("user", pengguna);
		// User user = userService.findByUsername(username);
		// session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.findByStatusPermohonan("Proses"));

		// ArrayList<Pengguna> pengguna = new ArrayList<>();
		//
		// pengguna = (ArrayList<Pengguna>) penggunaService.findAll();
		//
		// for(Pengguna jb : pengguna){
		// model.addAttribute("pengguna",
		// penggunaService.findByCawangan(jb.getCawangan()));
		// }

		ArrayList<Penerbangan> penerbangan = new ArrayList<>();

		for (Permohonan userForm : permohonanService.findByStatusPermohonan("Proses")) {

			penerbangan = (ArrayList<Penerbangan>) penerbanganService.findByPermohonan(userForm);

			for (Penerbangan jb : penerbangan) {
				jb.getPenerbanganId();
				System.out.println("nindiaaaaa : " + jb.getPenerbanganId());

				model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(userForm));
			}
		}
		model.addAttribute("lokasi", dariLokasiService.getAll());
		model.addAttribute("peruntukan", refPeruntukanService.getAll());
		model.addAttribute("pesawat", refPesawatService.getAll());
		model.addAttribute("kemaskiniPermohon", new PermohonanTemp());
		model.addAttribute("updatePembelian", new Pembelian());
		model.addAttribute("updateKemaskiniTiket", new Penerbangan());
		// model.addAttribute("kemaskiniPermohon", new Permohonan());
		model.addAttribute("jawatan", pengguna.getJawatan());
		System.out.println(username + "=============================" + pengguna.getJawatan());

		return "pembelian";
	}

}
