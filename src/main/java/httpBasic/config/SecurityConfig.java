package httpBasic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	static final String service2Auth = "SERVICE2";

	@Autowired
	CustomConfig config;
	
	@Bean
	@Override //used to avoid "Using generated security password: 5f2511ac-66c6-4538-a777-8fb6aa7a0b59" being printed
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> memoryAuth = auth.inMemoryAuthentication();

		for (User u : config.getUsers()) {
			memoryAuth = memoryAuth.withUser(u.getName())
					               .password("{noop}" + u.getPass())
					               .authorities(u.getServices().toArray(new String[0]))
					               .and();
		}
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()                                            // disable csrf, allowing delete, post and other http verbs
		.authorizeRequests()                                         
	    	.antMatchers("/seriousInfo1").hasAuthority(service2Auth) // APIs with '/seriousInfo1' should only allow users with SERVICE2
	        .anyRequest().authenticated()	                         // all APIs should at least have an user authenticated
	    .and().httpBasic()
	    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //real stateless!
		}
}
