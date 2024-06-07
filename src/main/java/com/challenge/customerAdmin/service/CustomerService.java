package com.challenge.customerAdmin.service;

import com.challenge.customerAdmin.entity.Customer;
import com.challenge.customerAdmin.entity.PaymentMethod;
import com.challenge.customerAdmin.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private static LocalDateTime getLocalDateTime() {
        Date now = new Date();
        return LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
    }

    private static LocalDateTime getLocalDateTimeMinusOneMonth() {
        Date now = new Date();
        return LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()).minusMonths(1);
    }

    public String saveCustomer(Customer customer){
        log.info("CustomerService - Save New Customer: {}", customer.getName());
        try {
            if(null == customer.getCreationDate()){
                customer.setCreationDate(getLocalDateTime());
            }
            customerRepository.save(customer);
            return "New Customer: " + customer.getName() + " successfully saved.";
        } catch (Exception e){
            return "Error saving new Customer: " + customer.getName() + ". Error: " + e.getMessage();
        }
    }

    public List<Customer> getAllCustomer(){
        log.info("CustomerService - Get All Customer");
        return customerRepository.findAll();
    }

    public List<Customer> getCustomerAddedLastMonth() {
        log.info("CustomerService - Get Customer Added Last Month");
        return customerRepository.getCustomerAddedLastMonth(getLocalDateTimeMinusOneMonth());
    }

    public List<Customer> getCustomerTransferredMoneyLastMonth() {
        log.info("CustomerService - Get Customer Transferred Money Last Month");
        return customerRepository.getCustomerTransferredMoneyLastMonth(getLocalDateTimeMinusOneMonth(), PaymentMethod.TRANSFER.toString());
    }

}
