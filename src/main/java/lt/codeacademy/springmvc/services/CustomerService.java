package lt.codeacademy.springmvc.services;

import lt.codeacademy.springmvc.controller.Customer;
import lt.codeacademy.springmvc.repositories.CustomerDao;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer saveOrUpdateCustomer(Customer customer){
        return customerDao.save(customer);
    }
}
