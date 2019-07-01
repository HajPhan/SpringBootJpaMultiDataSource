package com.example.SpringBootJPAMultiDataSource.dao;

import com.example.SpringBootJPAMultiDataSource.configuration.Constants;
import com.example.SpringBootJPAMultiDataSource.entity2.AdvertisersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.sql.CallableStatement;
import java.util.List;

@Repository
public class AdvertiserDAO {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_2)
    private EntityManager entityManager;

    public List<AdvertisersEntity> listAdvertisers() {
//        String sql = "Select e from " + AdvertisersEntity.class.getName() + " e ";
//        String sql = "call sp_GetAdvertiser";
//        Query query = entityManager.createQuery(sql, AdvertisersEntity.class);
        StoredProcedureQuery query1 = this.entityManager.createNamedStoredProcedureQuery("sp_GetAdvertiser");
        return query1.getResultList();
//        return query.getResultList();
    }

    public AdvertisersEntity findById(Long id) {
        return this.entityManager.find(AdvertisersEntity.class, id);
    }
}
