package com.diamondboss.personal.service.impl;

import com.diamondboss.personal.repository.PetInfoMapper;
import com.diamondboss.personal.service.IPetInfoService;
import com.diamondboss.util.pojo.PetInfoPojo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by focus.liu on 2017/4/27.
 */
@Service(value = "petInfoService")
public class PetInfoServiceImpl implements IPetInfoService {

    @Resource
    PetInfoMapper petInfoMapper;

    @Override
    public boolean inputPetInfo(PetInfoPojo petInfoPojo) {
        petInfoPojo.setEffective(true);

        int result = petInfoMapper.insert(petInfoPojo);

        if (result >0 ){
            return true;
        } else {
            return false;
        }
    }
}
