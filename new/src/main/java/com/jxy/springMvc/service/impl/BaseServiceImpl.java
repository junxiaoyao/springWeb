package com.jxy.springMvc.service.impl;

import com.jxy.springMvc.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public int count() {
        return (int)(Math.random()*100);
    }
}
