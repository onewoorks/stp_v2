package com.stp.auth.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stp.auth.model.Pembelian;
import com.stp.auth.model.Penerbangan;
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
import com.stp.auth.service.RefJawatanService;
import com.stp.auth.service.RefRoleService;
import com.stp.auth.service.SecurityService;
import com.stp.auth.service.SendHTMLEmail;
import com.stp.auth.service.UserService;
import com.stp.auth.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private PermohonanService permohonanService;

	@Autowired
	private PenerbanganService penerbanganService;

	@Autowired
	private DariLokasiService dariLokasiService;

	@Autowired
	private DaftarPenggunaService daftarPenggunaService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private RefRoleService refRoleService;

	@Autowired
	private RefJawatanService refJawatanService;

	ArrayList<Permohonan> permohonan = new ArrayList<Permohonan>();

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getNoKP());

		return "redirect:/welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		System.out.println("in here====================");
		System.out.println("hibernate veraion" + org.hibernate.Version.getVersionString());
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model, HttpSession session) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = daftarPenggunaService.findByUsername(username);

		List<Permohonan> userForm = permohonanService.findByStatusPermohonan("Baru");

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) daftarPenggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
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
		
		List<Penerbangan> listPenerbangan = new ArrayList<>();
		ArrayList<Penerbangan> penerbangan = new ArrayList<>();
		
		for (Permohonan jb : userForm) {
			if (jb.getNamaPelulus().equals(user.getNamaStaff())) {
				model.addAttribute("welcome", userForm);
				
				penerbangan = (ArrayList<Penerbangan>) penerbanganService.findByPermohonan(jb);
				
				for (Penerbangan jb2 : penerbangan) {
					listPenerbangan.add(jb2);
				}
			}
		}
		
		model.addAttribute("Penerbangan", listPenerbangan);
		model.addAttribute("kemaskiniPermohon", new Permohonan());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("namaStaff", user.getNamaStaff());
		System.out.println(pengguna + "=============================" + pengguna.getRefJawatan().getJawatanDesc());

		return "welcome";
	}

	@RequestMapping(value = { "/pengesahan" }, method = RequestMethod.GET)
	public String pengesahan(Model model, HttpSession session, Permohonan permohonan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);

		Pengguna pengguna = daftarPenggunaService.findByUsername(username);

		ArrayList<Pengguna> pengguna2 = new ArrayList<>();

		pengguna2 = (ArrayList<Pengguna>) daftarPenggunaService.findByNoKP(user.getNoKP());

		for (Pengguna jb : pengguna2) {

			if (jb.getUsername().equals(user.getUsername())) {
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

		model.addAttribute("welcome", permohonanService.findByStatusPermohonan("Batal"));
		for (Permohonan userForm : permohonanService.findByStatusPermohonan("Batal")) {
			model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(userForm));
		}
		model.addAttribute("kemaskiniPengesahan", new Permohonan());
		model.addAttribute("jawatan", pengguna.getRefJawatan().getJawatanDesc());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("namaStaff", user.getNamaStaff());
		return "pengesahan";
	}

}
