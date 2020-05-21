package lt.codeacademy.springmvc.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lt.codeacademy.springmvc.repositories.CustomerDao;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private CustomerDao customerDao;

    public MyUserDetailsService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao.findCustomerByUserName(username)
                .map(MyUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found by name: " + username));
    }
}
