package pl.sda.sda_biuro_podrozy.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/post/admin/**")
                .hasRole("ADMIN")//bedzie miało dodany przez Springa prefix ROLE_do roli -> lub: .hasAthority("ROLE_ADMIN")
               /* .antMatchers("/order")
                .hasRole("USER")*/
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login") //to co w GetMappingu
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/loginBySpring") //action w html
                .defaultSuccessUrl("/homepage")
                .failureUrl("/login?status=error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin@localhost.pl")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, 1 FROM users WHERE email=?")
                .authoritiesByUsernameQuery("SELECT email, role \n" +
                        "FROM users WHERE email=?")
                .passwordEncoder(passwordEncoder);
    }

}
