package com.example.SpringBootJPAMultiDataSource.dao;

import com.example.SpringBootJPAMultiDataSource.configuration.Constants;
import com.example.SpringBootJPAMultiDataSource.entity1.PublishersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class PublisherDAO {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public List<PublishersEntity> listPublishers(){
//        String sql = "select e from " + PublishersEntity.class.getName() + " e ";
//        Query query = entityManager.createQuery(sql,PublishersEntity.class);
//        return query.getResultList();
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("sp_GetPublisher");
        return query.getResultList();
    }

    public PublishersEntity findById(Long id){
        return this.entityManager.find(PublishersEntity.class,id);
    }


}
