package com.challenge.customerAdmin.controller;

import com.challenge.customerAdmin.entity.Customer;
import com.challenge.customerAdmin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/customer-admin/api/customer")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @PostMapping
    public String saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/addedLastMonth")
    public List<Customer> getCustomerAddedLastMonth() {
        return customerService.getCustomerAddedLastMonth();
    }

    @GetMapping("/transferredMoneyLastMonth")
    public List<Customer> getCustomerTransferredMoneyLastMonth() {
        return customerService.getCustomerTransferredMoneyLastMonth();
    }

}
