package com.example.SpringBootJPAMultiDataSource.entity4;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employee", schema = "ttcntt", catalog = "")
@NamedStoredProcedureQuery(name = "sp_whileloop", procedureName = "sp_whileloop", resultClasses = EmployeeEntity4.class, parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN,type = Double.class,name = "number")
})
public class EmployeeEntity4 {
    private int id;
    private String fullname;
    private String sex;
    private Timestamp birthday;
    private String address;
    private String phone;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birthday")
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

