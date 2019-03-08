package com.jxy.springMvc.repositoryService;

import com.jxy.springMvc.hEntity.Role;
import com.jxy.springMvc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleServiceRe {
    @Autowired
    private RoleRepository roleRepository;
    public void testSave(){
        Role roleEntity=new Role();
        roleEntity.setUserId(9);
        roleEntity.setRoleName("测试插入");
        roleEntity.setCreateTime(new Date());
        roleEntity.setRemark("测试");
        roleEntity.setUserName("rss");
        roleRepository.save(roleEntity);
    }
}
