package com.eazybytes.eazyschool.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
//@MappedSuperclass is a JPA annotation used to define superclasses that provide common fields and behavior to multiple entity classes
// without being mapped to a database table itself.

//When multiple entities share common attributes (e.g., createdAt, updatedAt, id), instead of duplicating those fields in every entity,
// you can define them once in a superclass using @MappedSuperclass.

@EntityListeners(AuditingEntityListener.class) // Treat Base Entity class as an Entity Which supports Auditing
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)

    private LocalDateTime createdAt;


    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;


@LastModifiedBy
@Column(insertable = false)
    private String updatedBy;


}
