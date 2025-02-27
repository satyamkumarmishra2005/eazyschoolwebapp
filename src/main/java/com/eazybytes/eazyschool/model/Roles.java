package com.eazybytes.eazyschool.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Roles extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int roleId;

    private String roleName;


}