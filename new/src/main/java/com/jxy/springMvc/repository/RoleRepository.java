package com.jxy.springMvc.repository;

import com.jxy.springMvc.hEntity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public RoleRepository() {

    }


    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Role e) {
      /*  Role role=getCurrentSession().get(Role.class,1l);
        getCurrentSession().saveOrUpdate(e);*/
    }
}
