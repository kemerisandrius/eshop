package lt.codeacademy.springmvc.services;

import org.springframework.stereotype.Service;

import lt.codeacademy.springmvc.entities.Customer;
import lt.codeacademy.springmvc.repositories.CustomerDao;

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
