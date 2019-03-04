package com.jxy.springMvc.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;


public class BaseRepository<E> {
    private SessionFactory sessionFactory;

    public BaseRepository() {

    }
    @Inject
    public BaseRepository(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(E e){
        getCurrentSession().save(e);
    }
}
