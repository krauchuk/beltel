package by.grsu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/index", "/notice/**", "/get_pdf/**").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers("/admin/**", "/all_notices", "/new_notices", "/edit_db", "/change_status/**", "/delete/**", "/pdf_settings").access("hasRole('ADMIN')")
                .antMatchers("/user/**", "/user_notices", "/create_notice").access("hasRole('USER')")
                .and().formLogin()
                .loginPage("/login_page").loginProcessingUrl("/login").usernameParameter("username")
                .passwordParameter("password").and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().csrf().disable();
    }
}
