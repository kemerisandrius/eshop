package lt.codeacademy.springmvc.services;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lt.codeacademy.springmvc.controller.Customer;

/**
 * @author tsimonavicius
 */
public class MyUser implements UserDetails {

    private String username;
    private String password;
    private String name;
    private String lastName;

    public MyUser(Customer customer) {
        this.username = customer.getUserName();
        this.password = customer.getPassword();
        this.name = customer.getName();
        this.lastName = customer.getLastName();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
