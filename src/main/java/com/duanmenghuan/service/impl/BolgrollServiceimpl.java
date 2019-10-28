package com.duanmenghuan.service.impl;


import com.bw.utility.StringUtility;
import com.duanmenghuan.bean.Blogroll;
import com.duanmenghuan.dao.BolgrollMapper;
import com.duanmenghuan.service.BolgrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BolgrollServiceimpl implements BolgrollService {

    @Autowired
    BolgrollMapper bolgrollMapper;

    @Override
    public List<Blogroll> list() {

        return  bolgrollMapper.list();


    }

    @Override
    public int add(Blogroll blogroll) {
        if(StringUtility.isUrl(blogroll.getUrl())){
            return bolgrollMapper.add(blogroll);
        }else {
            return 0;
        }
    }
}
