package com.personal.dog.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.dog.service.IPetReservationService;
import com.personal.util.pojo.PetBasePojo;
import com.personal.util.vo.APPResponseBody;
import com.personal.util.vo.PetBaseVo;

@Controller
@RequestMapping("/reservation")
public class PetReservationAction {

	@Resource
	private IPetReservationService petReservationService;

	@ResponseBody
	@RequestMapping(value = "/showBase" ,method = RequestMethod.POST)
	public APPResponseBody toIndex(HttpServletRequest request) {
		
		List<PetBasePojo> pojoList = this.petReservationService.queryPetBase();
		
		PetBaseVo vo = new PetBaseVo();
		List<PetBaseVo> resultList = vo.getPetBaseVo(pojoList);
		
		APPResponseBody app = new APPResponseBody();
		app.setData(resultList);
		app.setRetnCode(0);
		return app;
	}

	
	
	
	
	
}
