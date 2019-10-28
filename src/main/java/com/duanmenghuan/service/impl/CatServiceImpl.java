package com.duanmenghuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duanmenghuan.bean.Cat;
import com.duanmenghuan.dao.CatMapper;
import com.duanmenghuan.service.CatService;

/**
 * @author it
 */
@Service
public class CatServiceImpl implements CatService {

    @Autowired
    CatMapper catMapper;

    @Override
    public List<Cat> getListByChnlId(Integer id) {
        // TODO Auto-generated method stub
        return catMapper.selectByChnlId(id);
    }


}
