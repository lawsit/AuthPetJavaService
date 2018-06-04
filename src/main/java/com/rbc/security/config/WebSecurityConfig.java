package com.rbc.security.config;
 

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.rbc.security.jwt.JwtAuthenticationEntryPoint;
import com.rbc.security.jwt.JwtAuthorizationTokenFilter;
import com.rbc.security.jwt.JwtTokenUtil;
import com.rbc.security.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("authorization")
    private String tokenHeader;

    @Value("/auth")
    private String authenticationPath;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtUserDetailsService)
            .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

 
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

     
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
  
    
    
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity 
        
             
            //.csrf()
            //.and()
            
            .csrf().disable()
            
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"/persons").permitAll()
            .antMatchers(HttpMethod.OPTIONS,"/search/**").permitAll()
            //.antMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()
            
            // The following one is required for getByID
            .antMatchers(HttpMethod.OPTIONS,"/api/pets/**").permitAll()
            
            .antMatchers(HttpMethod.OPTIONS,"/auth/**").permitAll()
            //.antMatchers(HttpMethod.PUT,"/api/pets/**").permitAll()
           // .antMatchers(HttpMethod.POST,"/api/pets").permitAll()
            
            .antMatchers("/auth/**").permitAll()
            .anyRequest().fullyAuthenticated();
  
              
        JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);
        httpSecurity
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
 
            
            
    }

    //https://github.com/spring-projects/spring-security-javaconfig
    //http://blog.florian-hopf.de/2017/08/spring-security.html
    // exclude some parts of the application from authentication by configuring the WebSecurity
    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
            .ignoring() 
            .antMatchers(
            	    HttpMethod.POST,
                    authenticationPath
                );
 
    }
}