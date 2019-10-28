package com.duanmenghuan.service;

import com.duanmenghuan.bean.Blogroll;

import java.util.List;

public interface BolgrollService {
    List<Blogroll> list();

    int add(Blogroll blogroll);

}
