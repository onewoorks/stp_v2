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
import com.stp.auth.model.User;
import com.stp.auth.service.DariLokasiService;
import com.stp.auth.service.PembelianService;
import com.stp.auth.service.PenerbanganService;
import com.stp.auth.service.PermohonanService;
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
	private UserValidator userValidator;

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

		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

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

		List<Permohonan> userForm = permohonanService.getAll();

		List<Permohonan> permohonanForm = permohonanService.findByNamaPelulus(user.getNamaPengurus());

		ArrayList<Pengguna> pengguna = new ArrayList<>();

		// pengguna = (ArrayList<Pengguna>) userService.;

		for (Permohonan jb : userForm) {
			if(jb.getNamaPelulus().equals(user.getNamaStaff())){
			model.addAttribute("welcome", userForm);
			model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(jb));
			}
		}

		model.addAttribute("kemaskiniPermohon", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());
		System.out.println(username + "=============================" + user.getJawatan());

		return "welcome";
	}

	@RequestMapping(value = { "/pengesahan" }, method = RequestMethod.GET)
	public String pengesahan(Model model, HttpSession session, Permohonan permohonan) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userService.findByUsername(username);
		session.setAttribute("user", user);
		model.addAttribute("welcome", permohonanService.findByStatusPermohonan("Batal"));
		for (Permohonan userForm : permohonanService.findByStatusPermohonan("Batal")) {
			model.addAttribute("Penerbangan", penerbanganService.findByPermohonan(userForm));
		}
		model.addAttribute("kemaskiniPengesahan", new Permohonan());
		model.addAttribute("jawatan", user.getJawatan());
		model.addAttribute("username", user.getUsername());
		return "pengesahan";
	}

}
