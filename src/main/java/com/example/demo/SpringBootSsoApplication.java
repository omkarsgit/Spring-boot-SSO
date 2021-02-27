package com.example.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//Passing OAuth2User object and returning user attributes stored in a Map.

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SpringBootSsoApplication extends WebSecurityConfigurerAdapter{
	
	@GetMapping("/user")
		public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal){
			
			//return Collections.singletonMap("name", principal.getAttribute("name"));
			Map<String, Object> returnValues = new HashMap<String, Object>();
			Map<String, Object> attributes = principal.getAttributes();
			returnValues.put("name", principal.getAttribute("name"));
			//returnValues.put("username", principal.getAttribute("username"));
			System.out.println(attributes);
			return attributes;
			
	}
	
	//Routing user requests to different pages. If there is an error, it will redirect to error page. 
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests(a->a
				.antMatchers("/","/errors").permitAll().anyRequest().authenticated())
		.exceptionHandling(e->e
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				).oauth2Login();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSsoApplication.class, args);
	}

}
