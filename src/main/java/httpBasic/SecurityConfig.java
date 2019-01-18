package httpBasic;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	static final String adminRole = "ADMIN";
	static final String standardRole = "STANDARD";
	
	// creates some users
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		  .withUser("user").password("{noop}pass").roles(standardRole)
		  .and()
		  .withUser("admin").password("{noop}admin").roles(adminRole, adminRole);
	}
	
	
	// configure security through API URL patterns
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		  .authorizeRequests()
            .antMatchers("/seriousInfo").hasRole(adminRole) // APIs with /seriousInfo should only allow users with adminRole
            .anyRequest().authenticated()	                // all other APIs should at least have an user authenticated
          .and().httpBasic();
	}

}
