package com.authenticationgateway;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
/*
 * 
 * */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class AuthenticationGatewayLoginSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	//*************************** 1. Login Authentication****************************
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.usersByUsernameQuery("select username, password,'true' as enabled from login where username=?")
		.authoritiesByUsernameQuery("select username, authority from authorities where username=?");
	}
	//***************************** 2. Session of Login**********************************
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.defaultSuccessUrl("/index", true).permitAll()
		.and()
		.logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutSuccessUrl("/login").permitAll();
	}
}
