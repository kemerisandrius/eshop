package lt.codeacademy.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/prisijungimas")
                .loginProcessingUrl("/prisijungimas-submit")
                .usernameParameter("user")
                .passwordParameter("pass")
                .failureUrl("/prisijungimas?error")
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/atsijungimas")
                .deleteCookies();
    }

}
