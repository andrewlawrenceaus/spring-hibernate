package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    private List<Customer> getCustomers() {

        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    private Customer getCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null){
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        return customer;

    }

    @PostMapping("customers")
    private Customer addCustomer(@RequestBody Customer customer){

        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/customers")
    private Customer getCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    private Customer deleteCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null){
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        customerService.deleteCustomer(customerId);

        return customer;
    }
}
