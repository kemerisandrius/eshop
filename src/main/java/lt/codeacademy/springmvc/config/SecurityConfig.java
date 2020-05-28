package lt.codeacademy.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/h2/**", "/prisijungimas/**", "/public/**", "/").permitAll()
                    .antMatchers("/private/**").authenticated()
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

        http.csrf()
                .ignoringAntMatchers("/h2/**");
        http.headers()
                .frameOptions()
                .sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /**
         * Custom User Store
         */
        auth.userDetailsService(userDetailsService);

        /**
         * JDBC User Store
         */
//        auth
//                .jdbcAuthentication()
//                    .dataSource(dataSource)
//                    .usersByUsernameQuery("SELECT username, password, TRUE as enabled FROM Users WHERE username=?")
//                    .authoritiesByUsernameQuery("SELECT username, authority FROM Authorities WHERE username=?");

        /**
         * In Memory User Store
         */
//        auth
//                .inMemoryAuthentication()
//                    .withUser("user")
//                    .password(encoder().encode("pass"))
//                    .roles("USER")
//                .and()
//                    .withUser("admin")
//                    .password(encoder().encode("admin"))
//                    .roles("ADMIN", "USER");
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
