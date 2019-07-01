package com.example.SpringBootJPAMultiDataSource.dao;

import com.example.SpringBootJPAMultiDataSource.configuration.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Repository
public class EmployeeDao {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_3)
    private EntityManager entityManager;

    public Double listEmployee(){
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("sp_whileloop");
        return ((Double) query.getSingleResult());
    }
}
