package com.example.demo.model;

import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;


@Entity
public class JwtAuth implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jwt;


    private java.sql.Date dataJwt;



    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setData(java.sql.Date data) {
        this.dataJwt = data;
    }

    public java.sql.Date getData() {
        return dataJwt;
    }
}