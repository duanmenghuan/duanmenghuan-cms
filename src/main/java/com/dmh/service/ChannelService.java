package com.dmh.service;


import java.util.List;

import com.dmh.bean.Article;
import com.dmh.bean.Channel;

/**
 * 
 * @author duanmenghuan
 *
 */
public interface ChannelService {

	/**
	 *  获取所有的频道（栏目）
	 * @param article 
	 * @return
	 */
	List<Channel> getAllChnls();

}
