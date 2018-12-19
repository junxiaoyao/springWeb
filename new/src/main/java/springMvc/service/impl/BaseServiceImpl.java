package springMvc.service.impl;

import org.springframework.stereotype.Service;
import springMvc.service.BaseService;
@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public int count() {
        return (int)(Math.random()*100);
    }
}
