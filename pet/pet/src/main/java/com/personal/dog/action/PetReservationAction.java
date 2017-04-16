package com.personal.dog.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.dog.service.IPetReservationService;
import com.personal.util.pojo.PetBasePojo;
import com.personal.util.vo.APPResponseBody;
import com.personal.util.vo.PetBaseVo;
import com.personal.util.vo.PetDetailedVo;

@Controller
@RequestMapping("/reservation")
public class PetReservationAction {

	@Resource
	private IPetReservationService petReservationService;

	@ResponseBody
	@RequestMapping(value = "/showBase" ,method = RequestMethod.POST)
	public APPResponseBody showBase(HttpServletRequest request) {
		
		List<PetBasePojo> pojoList = this.petReservationService.queryPetBase();
		
		PetBaseVo vo = new PetBaseVo();
		List<PetBaseVo> resultList = vo.getPetBaseVo(pojoList);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(resultList);
		app.setRetnCode(0);
		return app;
	}

	@ResponseBody
	@RequestMapping(value = "/showDetailed" ,method = RequestMethod.POST)
	public APPResponseBody showDetailed(HttpServletRequest request) {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		PetDetailedVo result = this.petReservationService.queryPetDetailed(id);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(result);
		app.setRetnCode(0);
		return app;
	}
	
	
	
	
}
