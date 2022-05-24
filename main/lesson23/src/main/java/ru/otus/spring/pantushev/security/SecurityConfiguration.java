package ru.otus.spring.pantushev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailServiceImpl userDetailService;

    @Autowired
    public SecurityConfiguration(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                    "/",
                    "/index"
                ).permitAll()
                .antMatchers(
                    "/books",
                    "/authors",
                    "/jenres"
                    )
            .hasAuthority(AppAutorities.USER_READER.toString())
                .antMatchers(
                    "/books/edit",
                    "/books/add"
                ).hasAuthority(AppAutorities.USER_WRITER.toString())
                .antMatchers(
                    "/books/delete"
                ).hasAuthority(AppAutorities.ADMIN.toString());
        http.formLogin();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        http.rememberMe().key("fjEyeuMbgky");
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }



    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
        /*
        //Стандартная реализация
        auth.jdbcAuthentication()
            .dataSource(dataSource);

         */
    }
}
