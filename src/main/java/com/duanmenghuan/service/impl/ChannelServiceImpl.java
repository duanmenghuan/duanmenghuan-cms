package com.duanmenghuan.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duanmenghuan.bean.Channel;
import com.duanmenghuan.dao.ChannelMapper;
import com.duanmenghuan.service.ChannelService;

/**
 * @author zhuzg
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    @Override
    public List<Channel> getAllChnls() {
        // TODO Auto-generated method stub
        return channelMapper.listAll();

    }

}
