package com.duanmenghuan.service;


import java.util.List;

import com.duanmenghuan.bean.Channel;

/**
 * @author duanmenghuan
 */
public interface ChannelService {

    /**
     * 获取所有的频道（栏目）
     *
     * @param article
     * @return
     */
    List<Channel> getAllChnls();

}
