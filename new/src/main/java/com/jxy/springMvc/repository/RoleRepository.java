package com.jxy.springMvc.repository;

import com.jxy.springMvc.hEntity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public RoleRepository() {

    }


    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Role e) {
        getCurrentSession().saveOrUpdate(e);
    }
}
