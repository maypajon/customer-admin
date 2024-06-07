package com.challenge.customerAdmin.controller;

import com.challenge.customerAdmin.entity.Payment;
import com.challenge.customerAdmin.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/customer-admin/api/payment")
public class PaymentController {

    @Autowired
    public PaymentService paymentService;

    @PostMapping
    public String savePayment(@RequestBody Payment payment){
        return paymentService.savePayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayment(){
        return paymentService.getAllPayment();
    }

    @GetMapping("/getByCustomerId/{customerId}")
    public List<Payment> getPaymentByCustomerId(@PathVariable Integer customerId) {
        return paymentService.getPaymentByCustomerId(customerId);
    }

}
