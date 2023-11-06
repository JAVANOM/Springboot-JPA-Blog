package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

//@EnableWebSecurity
@Configuration //IoC 등록
public class SecurityConfig {
    
	
	@Bean BCryptPasswordEncoder encode() { return new BCryptPasswordEncoder(); }
	 
	 @Bean
	 SecurityFilterChain configure(HttpSecurity http) throws Exception {
	  http
	    .authorizeRequests()
	    .antMatchers("/auth/**")
	    .permitAll()
	    .anyRequest()
	    .authenticated()
	    .and()
	    .formLogin()
	    .loginPage("/auth/loginForm");
	  return http.build();
	 }
	
	
	
	
	/*
	 * @Bean SecurityFilterChain configure(HttpSecurity http,
	 * HandlerMappingIntrospector introspector) throws Exception {
	 * 
	 * MvcRequestMatcher.Builder mvcRequestMatcher = new
	 * MvcRequestMatcher.Builder(introspector).servletPath("/");
	 * 
	 * http.authorizeHttpRequests(authorize -> {
	 * authorize.requestMatchers(mvcRequestMatcher.pattern("/auth/**")).permitAll()
	 * .anyRequest().authenticated(); }) .formLogin(login ->
	 * login.loginPage("/auth/loginForm").permitAll());
	 * 
	 * return http.build();
	 * 
	 * }
	 */
	
	
	
}
