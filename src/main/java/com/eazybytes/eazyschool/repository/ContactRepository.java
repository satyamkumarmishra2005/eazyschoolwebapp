package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    //findBy<FieldName><Operator>(Parameters)

    List<Contact> findByStatus(String status); // Here we are creating a custom query where we are fetching the data based on the status of the message
    // we have defined this custom query because by defualt springDataJpa will fetch the data based on the primary key
    //but when you need more complex queries (like fetching based on multiple conditions, joins, aggregations, or specific filtering), you should use Custom Queries

// @Query("SELECT c FROM Contact c WHERE c.status= :status") JPQL query

    @Query(value = "SELECT * FROM contact_msg c WHERE c.status= :status",nativeQuery = true)  // Native Sql query

    Page<Contact> findByStatus(String status , Pageable pageable);

    @Transactional  // This annoation tells the spring data jpa that The method should execute fully if any run time error occurs then it will roll back the transaction the method should not partially occur
    @Modifying // This annotation is used to tell Spring Data JPA that this method is modifying the database.
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String staus , int id);

}