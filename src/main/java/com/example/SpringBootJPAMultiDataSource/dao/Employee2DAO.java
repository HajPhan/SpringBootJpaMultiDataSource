package com.example.SpringBootJPAMultiDataSource.dao;

import com.example.SpringBootJPAMultiDataSource.configuration.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Repository
public class Employee2DAO {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_4)
    private EntityManager entityManager;

    public Double listEmployee(Double n) {
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("sp_whileloop");
        query.registerStoredProcedureParameter("time_total",Double.class, ParameterMode.OUT);
        query.setParameter("number", n);
        query.execute();
        Double rs = (Double) query.getOutputParameterValue("time_total");
        return rs;
    }

}
