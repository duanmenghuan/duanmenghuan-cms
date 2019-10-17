package com.dmh.service;


import java.util.List;

import com.dmh.bean.Channel;

/**
 * 
 * @author zhuzg
 *
 */
public interface ChannelService {

	/**
	 *  获取所有的频道（栏目）
	 * @return
	 */
	List<Channel> getAllChnls();

}
