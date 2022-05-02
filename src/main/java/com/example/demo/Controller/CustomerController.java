package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    public CustomerRepository customerRepository;
    @PostMapping("/AddCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
    {
        String message = "";
        try{

            if(customerRepository.findById(customer.getId()).isPresent())
            {
                message = "Already Exist";
            }
            else {
                customerService.addData(customer);
                message = "Saved";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
    @PostMapping("/dummydata")
    public ResponseEntity<?> getDummyData(@RequestBody String usrdata)
    {
        String name=null;

        try {
            HashMap mapData=new ObjectMapper().readValue(usrdata, HashMap.class);
            if(mapData.get("un")!=null){
                name=mapData.get("un").toString();
                System.out.println(name);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(usrdata);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

}
