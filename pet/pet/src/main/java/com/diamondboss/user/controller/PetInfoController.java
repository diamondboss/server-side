package com.diamondboss.user.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.diamondboss.user.pojo.PetInfoPojo;
import com.diamondboss.user.service.PetInfoService;
import com.diamondboss.user.vo.InputPetInfoVo;
import com.diamondboss.user.vo.PetInfoRequstVo;
import com.diamondboss.util.vo.APPResponseBody;


@Controller
@RequestMapping("/petInfo")
public class PetInfoController {
	
	@Autowired
	private PetInfoService petInfoService;

	private static final Logger log = Logger.getLogger(LoginController.class);
	
	/**
	 * 更新宠物信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePetInfo" ,method = RequestMethod.POST)
	public APPResponseBody updatePetInfo(PetInfoPojo pojo, HttpServletRequest request) {
		APPResponseBody app = new APPResponseBody();
		
		if(pojo == null){
			log.info("请求参数非法，userId为空");
			app.setRetnCode(1);
			app.setRetnDesc("请求参数非法");
			return app;
		}
		
		int result = petInfoService.updatePetInfo(pojo);
		
		if(result < 1){
			log.info("更新宠物信息失败，petInfo：" + pojo.getId() + " , " + pojo.getName());
			app.setData("");
			app.setRetnCode(1);
			return app;
		}
		
		PetInfoPojo petInfo = petInfoService.queryPetInfo(pojo.getUserId());
		app.setData(petInfo);
		app.setRetnCode(0);
		return app;
		
	}
	
	/**
	 * 根据用户ID查询宠物信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPetInfo" ,method = RequestMethod.POST)
	public APPResponseBody queryPetInfo(PetInfoRequstVo vo,HttpServletRequest request) {
		APPResponseBody app = new APPResponseBody();
		
		if(vo.getUserId() == null){
			log.info("请求参数非法，userId为空");
			app.setRetnCode(1);
			app.setRetnDesc("请求参数非法");
			return app;
		}
		
		PetInfoPojo petInfo = petInfoService.queryPetInfo(vo.getUserId());
		
		if(petInfo != null){
			log.info("查询成功，petInfo：" + petInfo);
			app.setData(petInfo);
			app.setRetnCode(0);
			return app;
		}
		
		//默认一条宠物记录，除了用户id，其他都为空
		InputPetInfoVo inputVo =  new InputPetInfoVo();
		inputVo.setUserId(vo.getUserId());
		inputVo.setName("");
		inputVo.setAge("空");
		inputVo.setSex(9); //9为默认，新纪录没性别
		inputVo.setVarieties("");
		
		int result = petInfoService.inputPetInfo(inputVo);
		
		if(result < 1){
			log.info("插入宠物信息失败，petInfo：" + vo.getUserId());
			app.setRetnCode(1);
			app.setRetnDesc("处理异常");
			return app;
		}
		
		log.info("处理成功，用户Id: " + vo.getUserId());
		app.setRetnCode(0);
		return app;
	}
	
	/**
	 * 插入宠物信息（没用）
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/inputPetInfo" ,method = RequestMethod.POST)
	public APPResponseBody inputPetInfo(InputPetInfoVo vo, HttpServletRequest request) {
		APPResponseBody app = new APPResponseBody();
		
		if(vo == null){
			log.info("请求参数非法，userId为空");
			app.setRetnCode(1);	
			app.setRetnDesc("请求参数非法");
			return app;
		}
		
		int result = petInfoService.inputPetInfo(vo);
		
		if(result < 1){
			log.info("插入宠物信息失败，petInfo：" + vo.getUserId() + " , " + vo.getName());
			app.setData("");
			app.setRetnCode(1);
			return app;
		}
		
		//PetInfoPojo petInfo = petInfoService.queryPetInfo(vo.getUserId());
		app.setData("插入宠物信息成功");
		app.setRetnCode(0);
		return app;
		
	}
	
}
