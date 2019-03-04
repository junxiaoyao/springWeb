package com.jxy.springMvc.repository;

import com.jxy.springMvc.entity.RoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class RoleRepository  {
    private SessionFactory sessionFactory;
    @Inject
    public RoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(RoleEntity e){
        getCurrentSession().save(e);
    }
}
