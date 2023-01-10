package com.coredev.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class BaseRepository<T> {
    private static EntityManagerFactory factory;
    protected Class<T> clazz;
    public BaseRepository(Class<T> clazz){
        this.clazz=clazz;
    }

    protected static EntityManagerFactory getFactory() {
        if(factory==null){
            factory=Persistence.createEntityManagerFactory("MyPersistenceUnit");
            System.out.println("Factory Created");
        }
        return factory;
    }

    public EntityManager newManager(){
        return getFactory().createEntityManager();
    }

    public void insert(T entity){
        EntityManager entityManager=newManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(T entity){
        EntityManager entityManager=newManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(T entity){
        EntityManager entityManager=newManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public T find(long id){
        EntityManager entityManager=newManager();
        T entity=entityManager.find(clazz, id);
        entityManager.close();
        return entity;
    }

    private static final String SELECT="select e from %s e";
    public List<T> list(){
        EntityManager entityManager=newManager();
        String jpql=String.format(SELECT, clazz.getSimpleName());
        TypedQuery<T> query=entityManager.createQuery(jpql,clazz);
        List<T> entities=query.getResultList();
        entityManager.close();
        return entities;
    }

}
