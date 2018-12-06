package com.stp.auth.web;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.Permohonan;
import com.stp.auth.model.RefJawatan;
import com.stp.auth.model.RefRole;
import com.stp.auth.model.User;
import com.stp.auth.service.DaftarPenggunaService;
import com.stp.auth.service.RefCawanganService;
import com.stp.auth.service.RefJawatanService;
import com.stp.auth.service.RefRoleService;
import com.stp.auth.service.RefUnitBahagianService;

@Controller
public class AdminController {
	@Autowired
	private DaftarPenggunaService userService;

	@Autowired
	private RefCawanganService refCawanganService;

	@Autowired
	private RefUnitBahagianService refUnitBahagianService;
	
	@Autowired
	private RefJawatanService refJawatanService;
	
	@Autowired
	private RefRoleService refRoleService;

	ArrayList<Pengguna> user = new ArrayList<>();

	@RequestMapping(value = "/admin/daftarPengguna", method = RequestMethod.GET)
	public String daftarPengguna(Model model, HttpSession session) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Pengguna pengguna = userService.findByUsername(username);
		session.setAttribute("user", user);
		
		model.addAttribute("listPengguna", userService.findAll());
		model.addAttribute("cawangan", refCawanganService.getAll());
		model.addAttribute("listJawatan", refJawatanService.getAll());

//		ArrayList<Permohonan> permohonan = new ArrayList<>();
//		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) userService.findByNoKP(pengguna.getNoKP());

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
		
		List<Pengguna> jawatanUser = new ArrayList<>();

		for (Pengguna jb : userService.findAll()) {
			System.out.println("nama pengurus ::::: "+jb.getRefJawatan().getRefRole().getRoleId());
			if (jb.getRefJawatan().getRefRole().getRoleId() == 2) {
				jawatanUser.add(jb);
				System.out.println("masuk sini" + jawatanUser);
				model.addAttribute("jawatanUser", jawatanUser);
			}
		}

		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());
		model.addAttribute("namaStaff", pengguna.getNamaStaff());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());

		return "daftarPengguna";
	}

	@RequestMapping(value = "/admin/daftarPengguna", method = RequestMethod.POST)
	public String daftarPengguna(@ModelAttribute("daftarPenggunaForm") Pengguna daftarPenggunaForm,
			BindingResult bindingResult, Model model) {

		daftarPenggunaForm.setPassword(daftarPenggunaForm.getNoKP());
		userService.save(daftarPenggunaForm);
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());

		return "redirect:/admin/daftarPengguna";
	}

	@RequestMapping(value = "/admin/kemaskiniPengguna", method = RequestMethod.GET)
	public String kemaskiniPengguna(@RequestParam("id") Long id, Model model, HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Pengguna pengguna = userService.findByUsername(username);
		session.setAttribute("user", user);
		
		model.addAttribute("listPengguna", userService.findAll());
		model.addAttribute("cawangan", refCawanganService.getAll());
		model.addAttribute("listJawatan", refJawatanService.getAll());

//		ArrayList<Permohonan> permohonan = new ArrayList<>();
//		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) userService.findByNoKP(pengguna.getNoKP());

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

		List<Pengguna> jawatanUser = new ArrayList<>();

		for (Pengguna jb : userService.findAll()) {
			System.out.println("nama pengurus ::::: "+jb.getRefJawatan().getRefRole().getRoleId());
			if (jb.getRefJawatan().getRefRole().getRoleId() == 2) {
				jawatanUser.add(jb);
				System.out.println("masuk sini" + jawatanUser);
				model.addAttribute("jawatanUser", jawatanUser);
			}
		}
		
		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", userService.findById(id));
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());
		model.addAttribute("namaStaff", pengguna.getNamaStaff());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());

		return "daftarPengguna";
	}

	@RequestMapping(value = "/admin/lihatPengguna", method = RequestMethod.GET)
	public String lihatPengguna(@RequestParam("id") Long id, Model model, HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Pengguna pengguna = userService.findByUsername(username);
		session.setAttribute("user", user);
		
		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("listPengguna", userService.findAll());
		model.addAttribute("cawangan", refCawanganService.getAll());
		model.addAttribute("listJawatan", refJawatanService.getAll());

//		ArrayList<Permohonan> permohonan = new ArrayList<>();
//		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) userService.findByNoKP(pengguna.getNoKP());

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

		List<Pengguna> jawatanUser = new ArrayList<>();

		for (Pengguna jb : userService.findAll()) {
			System.out.println("nama pengurus ::::: "+jb.getRefJawatan().getRefRole().getRoleId());
			if (jb.getRefJawatan().getRefRole().getRoleId() == 2) {
				jawatanUser.add(jb);
				System.out.println("masuk sini" + jawatanUser);
				model.addAttribute("jawatanUser", jawatanUser);
			}
		}
		
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", userService.findById(id));
		model.addAttribute("namaStaff", pengguna.getNamaStaff());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());

		return "daftarPengguna";
	}

	@RequestMapping(value = "/admin/padamPengguna", method = RequestMethod.GET)
	public String padamPengguna(@RequestParam("id") Long id, Model model, HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Pengguna pengguna = userService.findByUsername(username);
		session.setAttribute("user", user);
		
		model.addAttribute("listPengguna", userService.findAll());
		model.addAttribute("cawangan", refCawanganService.getAll());
		model.addAttribute("listJawatan", refJawatanService.getAll());

//		ArrayList<Permohonan> permohonan = new ArrayList<>();
//		permohonan = (ArrayList<Permohonan>) permohonanService.findByNama(user.getNamaStaff());

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) userService.findByNoKP(pengguna.getNoKP());

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

		List<Pengguna> jawatanUser = new ArrayList<>();

		for (Pengguna jb : userService.findAll()) {
			System.out.println("nama pengurus ::::: "+jb.getRefJawatan().getRefRole().getRoleId());
			if (jb.getRefJawatan().getRefRole().getRoleId() == 2) {
				jawatanUser.add(jb);
				System.out.println("masuk sini" + jawatanUser);
				model.addAttribute("jawatanUser", jawatanUser);
			}
		}
		
		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", userService.findById(id));
		model.addAttribute("namaStaff", pengguna.getNamaStaff());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());

		return "daftarPengguna";
	}

	@RequestMapping(value = "/admin/padamPengguna", method = RequestMethod.POST)
	public String padamPengguna(@ModelAttribute("daftarPenggunaForm") Pengguna daftarPenggunaForm,
			BindingResult bindingResult, Model model) {

		daftarPenggunaForm.setPassword(daftarPenggunaForm.getNoKP());
		userService.remove(daftarPenggunaForm);
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());

		return "redirect:/admin/daftarPengguna";
	}

}