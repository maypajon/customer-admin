package com.challenge.customerAdmin.repository;

import com.challenge.customerAdmin.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(nativeQuery = true, value ="select * from payments p where p.customer_id = :customerId")
    List<Payment> getPaymentByCustomerId(@Param("customerId") Integer customerId);

}
