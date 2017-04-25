package com.diamondboss.dog.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.dog.service.IPetReservationService;
import com.diamondboss.util.pojo.PetBasePojo;
import com.diamondboss.util.vo.APPResponseBody;
import com.diamondboss.util.vo.PetBaseVo;
import com.diamondboss.util.vo.PetDetailedVo;

@Controller
@RequestMapping("/reservation")
public class PetReservationController {

	@Resource
	private IPetReservationService petReservationService;
	
	public static Logger log = LoggerFactory.getLogger(PetReservationController.class);

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
		
		log.info("查看宠物详情");
		
		Integer id = Integer.parseInt(request.getParameter("id"));
	
		PetDetailedVo result = this.petReservationService.queryPetDetailed(id);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(result);
		app.setRetnCode(0);
		return app;
	}
	
	
	
	
}
