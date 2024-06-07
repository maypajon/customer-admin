package com.challenge.customerAdmin.repository;

import com.challenge.customerAdmin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(nativeQuery = true, value ="select * from customers c where c.creation_date > :date")
    List<Customer> getCustomerAddedLastMonth(@Param("date") LocalDateTime date);

    @Query(nativeQuery = true, value = "select c.* from customers c join " +
            "(select customer_id from payments p where p.method = :transferMethod and p.date > :date " +
            "group by customer_id) p on c.id = p.customer_id")
    List<Customer> getCustomerTransferredMoneyLastMonth(@Param("date") LocalDateTime date, @Param("transferMethod") String transferMethod);

}
