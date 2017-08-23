package com.diamondboss.petsHotel.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamondboss.order.vo.CancelOrderVo;
import com.diamondboss.petsHotel.service.HotelService;
import com.diamondboss.petsHotel.vo.HotelInfoVo;
import com.diamondboss.petsHotel.vo.HotelLoginVo;
import com.diamondboss.petsHotel.vo.HotelWalletDetail;
import com.diamondboss.petsHotel.vo.LoginVo;
import com.diamondboss.util.pojo.SmsReturnInfo;
import com.diamondboss.util.push.rongyun.service.ISendMsgService;
import com.diamondboss.util.vo.APPResponseBody;

/**
 * 酒店服务类
 * 
 * @author John
 * @since 2017-08-16
 *  
 */
@Controller
@RequestMapping("/hotelService")
public class HotelServiceController {

	private static final Logger log =  
			Logger.getLogger(HotelServiceController.class);
	
	@Autowired
	private ISendMsgService sendMsgService; 
	
	@Autowired
	private HotelService hotelService;
	
	/**
	 * 酒店端登录
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public APPResponseBody login(LoginVo vo){

		log.info("酒店合伙人登录");
		
		APPResponseBody app = new APPResponseBody();
		
		if(StringUtils.equals(vo.getPhone(), "15026842350")){
			//系统白名单用户，直接跳过验证码！
		}else{
			// 短信验证码
			SmsReturnInfo info = sendMsgService.verifyCode(
					vo.getSessionId(), vo.getCode());
			
			if (false == info.getSuccess()) {
				app.setData(vo);
				app.setRetnCode(1);
				app.setRetnDesc("验证码校验错误");
				log.info("短信验证码校验错误，手机号：" + vo.getPhone());
				return app;
			}
		}
		
		HotelLoginVo reslut = hotelService.queryHotel(vo.getPhone());
		
		if(reslut == null){
			app.setData("");
			app.setRetnCode(1);
			app.setRetnDesc("该账户未开通酒店服务");
		}
		
		app.setData(reslut);
		app.setRetnCode(0);
		app.setRetnDesc("登录成功");
		
		return app;
	}
	
	/**
	 * 获取酒店信息
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInfo", method = RequestMethod.POST)
	public APPResponseBody queryHotelInfo(String hotelId){
	
		APPResponseBody app = new APPResponseBody();
		
		HotelInfoVo vo = hotelService.queryHotelInfo(hotelId);
		
		app.setData(vo);
		app.setRetnCode(0);
		app.setRetnDesc("");
		
		return app;
		
	}
	
	/**
	 * 获取酒店信息
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryWallet", method = RequestMethod.POST)
	public APPResponseBody queryHotelWallet(String hotelId){
	
		APPResponseBody app = new APPResponseBody();
		
		String amt =  hotelService.queryHotelWallet(hotelId);
		
		app.setData(amt);
		app.setRetnCode(0);
		app.setRetnDesc("");
		
		return app;
		
	}
	
	/**
	 * 获取酒店信息
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryWalletDetail", method = RequestMethod.POST)
	public APPResponseBody queryHotelWalletDetail(String hotelId){
	
		APPResponseBody app = new APPResponseBody();
		
		List<HotelWalletDetail> resultList = hotelService.queryHotelWalletDetail(hotelId);
		
		if(null == resultList || resultList.size() ==0){
			
			app.setData("");
		}else{
			app.setData(resultList);
		}
		
		app.setRetnCode(0);
		app.setRetnDesc("");
		
		return app;
		
	}
	
	/**
	 * 订单查询
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public APPResponseBody queryOrderList(CancelOrderVo vo){

		return null;
	}
}
