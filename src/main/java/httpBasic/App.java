package httpBasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@ComponentScan
@EnableAutoConfiguration
//allows @PreAuthorize("hasAuthority('someService')") to be used
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}