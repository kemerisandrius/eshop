package lt.codeacademy.springmvc.services;

import lt.codeacademy.springmvc.entities.DeliveryInfo;
import org.springframework.stereotype.Service;

import lt.codeacademy.springmvc.repositories.CustomerDao;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public DeliveryInfo saveOrUpdateCustomer(DeliveryInfo deliveryInfo){
        return customerDao.save(deliveryInfo);
    }
}
