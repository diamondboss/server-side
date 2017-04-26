package com.diamondboss.personal.service;

import com.diamondboss.util.pojo.PetInfoPojo;
import org.springframework.stereotype.Service;

/**
 * Created by focus.liu on 2017/4/27.
 */
public interface IPetInfoService {
    /**
     * 录入宠物信息
     * @param petInfoPojo
     * @return
     */
    public boolean inputPetInfo(PetInfoPojo petInfoPojo);
}
