package com.challenge.customerAdmin.service;

import com.challenge.customerAdmin.entity.Payment;
import com.challenge.customerAdmin.entity.PaymentMethod;
import com.challenge.customerAdmin.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public String savePayment(Payment payment){
        log.info("PaymentService - Add New Payment");
        try {
            if(null == payment.getDate()){
                Date now = new Date();
                payment.setDate(LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()));
            }
            if(!Arrays.stream(PaymentMethod.values()).toList().contains(PaymentMethod.valueOf(payment.getMethod()))){
                throw new IllegalArgumentException("Method is not valid.");
            }
            paymentRepository.save(payment);
            return "New Payment from Customer: " + payment.getCustomer().getId() + " successfully saved.";
        } catch (Exception e){
            return "Error saving new Payment. Error: " + e.getMessage();
        }
    }

    public List<Payment> getAllPayment(){
        log.info("PaymentService - Get All Payment");
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentByCustomerId(Integer customerId) {
        log.info("PaymentService - Get Payment By CustomerId");
        return paymentRepository.getPaymentByCustomerId(customerId);
    }
}
