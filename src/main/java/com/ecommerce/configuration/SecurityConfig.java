package com.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ecommerce.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
@Autowired
googleOAuth2SuccessHandler googleOAuth2successHandler;
@Autowired
CustomUserDetailService customUserDetailService;

@Override
protected void configure(HttpSecurity http) throws Exception {
	// TODO Auto-generated method stub
	http
	.authorizeRequests()
	.antMatchers("/","/shop/**","/register", "/h2-console/**").permitAll()
	.antMatchers("/admin/**").hasRole("ADMIN")
	.anyRequest()
	.authenticated()
	.and()
	.formLogin()
	.loginPage("/login")
	.permitAll()
	.failureUrl("/login?error = true")
	.defaultSuccessUrl("/")
	.usernameParameter("email")
	.and()
			/*
			 * .oauth2Login() .loginPage("/login")
			 * .successHandler(googleOAuth2successHandler) .and()
			 */
	.logout()
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	.logoutSuccessUrl("/login")
	.invalidateHttpSession(true)
	.deleteCookies("JSESSIONID")
	.and()
	.exceptionHandling()
	.and()
	.csrf()
	.disable();
	
	http.headers().frameOptions().disable();
	
}

@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder()
{
	return new BCryptPasswordEncoder();
	
	}

@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customUserDetailService);
	}


@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/**", "/static/**", "/images/**", "/productimages/**","/css/**", "/js/**");
	}

}
