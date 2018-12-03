package com.stp.auth.web;

import java.util.ArrayList;

import javax.persistence.EntityManager;

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
import com.stp.auth.model.User;
import com.stp.auth.service.DaftarPenggunaService;
import com.stp.auth.service.RefCawanganService;
import com.stp.auth.service.RefUnitBahagianService;

@Controller
public class AdminController {
	@Autowired
	private DaftarPenggunaService userService;

	@Autowired
	private RefCawanganService refCawanganService;

	@Autowired
	private RefUnitBahagianService refUnitBahagianService;

	ArrayList<Pengguna> user = new ArrayList<>();

	@RequestMapping(value = "/admin/daftarPengguna", method = RequestMethod.GET)
	public String daftarPengguna(Model model) {
		model.addAttribute("listPengguna", userService.findAll());
		model.addAttribute("cawangan", refCawanganService.getAll());

		for (Pengguna jb : userService.findAll()) {
			System.out.println("tengok siniiiiiiiii" + userService.findAll());
			if (jb.getJawatan().equals("Ketua Pegawai")) {
				model.addAttribute("jawatan", userService.findByJawatan(jb.getJawatan()));
			}
		}

		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());

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
	public String kemaskiniPengguna(@RequestParam("id") Long id, Model model) {
		model.addAttribute("listPengguna", userService.findAll());
		
		for (Pengguna jb : userService.findAll()) {
			System.out.println("tengok siniiiiiiiii" + userService.findAll());
			if (jb.getJawatan().equals("Ketua Pegawai")) {
				model.addAttribute("jawatan", userService.findByJawatan(jb.getJawatan()));
			}
		}
		
		model.addAttribute("cawangan", refCawanganService.getAll());
		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", userService.findById(id));
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());

		return "daftarPengguna";
	}

	@RequestMapping(value = "/admin/lihatPengguna", method = RequestMethod.GET)
	public String lihatPengguna(@RequestParam("id") Long id, Model model) {
		model.addAttribute("listPengguna", userService.findAll());
		model.addAttribute("cawangan", refCawanganService.getAll());
		
		for (Pengguna jb : userService.findAll()) {
			System.out.println("tengok siniiiiiiiii" + userService.findAll());
			if (jb.getJawatan().equals("Ketua Pegawai")) {
				model.addAttribute("jawatan", userService.findByJawatan(jb.getJawatan()));
			}
		}
		
		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", userService.findById(id));

		return "daftarPengguna";
	}

	@RequestMapping(value = "/admin/padamPengguna", method = RequestMethod.GET)
	public String padamPengguna(@RequestParam("id") Long id, Model model) {
		model.addAttribute("listPengguna", userService.findAll());
		model.addAttribute("cawangan", refCawanganService.getAll());
		model.addAttribute("unitBahagian", refUnitBahagianService.getAll());
		
		for (Pengguna jb : userService.findAll()) {
			System.out.println("tengok siniiiiiiiii" + userService.findAll());
			if (jb.getJawatan().equals("Ketua Pegawai")) {
				model.addAttribute("jawatan", userService.findByJawatan(jb.getJawatan()));
			}
		}
		
		model.addAttribute("daftarPenggunaForm", new Pengguna());
		model.addAttribute("kemaskiniPenggunaForm", new Pengguna());
		model.addAttribute("lihatPenggunaForm", new Pengguna());
		model.addAttribute("padamPenggunaForm", userService.findById(id));

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