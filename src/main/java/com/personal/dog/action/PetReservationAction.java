package com.personal.dog.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.dog.service.IPetReservationService;
import com.personal.util.pojo.PetBasePojo;

@Controller
@RequestMapping("/reservation")
public class PetReservationAction {

	@Resource
	private IPetReservationService petReservationService;

	@RequestMapping("/showBase")
	public String toIndex(HttpServletRequest request, Model model) {
		PetBasePojo pojo = this.petReservationService.queryPetBase();
		model.addAttribute("user", pojo);
		return "showBase";
	}

	
	
	
	
	
}
