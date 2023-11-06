package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

@EnableWebSecurity //시큐리티 활성화 -> 기본 스프링 필터체인에 등
@Configuration //IoC 등록
public class SecurityConfig {
    
	/*
	 * @Autowired //DI private PrincipalDetail principalDetail;
	 */

	//시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교 할 수 있음
	
	
	
	 @Bean
	 SecurityFilterChain configure(HttpSecurity http) throws Exception {
	  http
	    .csrf().disable() //csrf 토큰 비활성화, csrf 토큰을 가지고 있지 않으면 시큐리티에서 막아버림
	    .authorizeRequests()
	    .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
	    .permitAll()
	    .anyRequest()
	    .authenticated()
	    .and()
	    .formLogin()
	    .loginPage("/auth/loginForm")
	    .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 함
	    .defaultSuccessUrl("/"); 
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
