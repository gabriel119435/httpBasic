package httpBasic.config;

import httpBasic.config.dto.CustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    static final String SERVICE_2 = "SERVICE2";

    @Autowired
    CustomConfig config;

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        List<UserDetails> users = new ArrayList<>();
        for (httpBasic.config.dto.User u : config.users()) {
            users.add(User.withUsername(u.name())
                    .password("{noop}" + u.pass())
                    .authorities(u.services().toArray(new String[0]))
                    .build());
        }
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disable csrf, allowing delete, post and other http verbs
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // apis with '/seriousInfo1' should only allow users with SERVICE2
                        .requestMatchers("/seriousInfo1").hasAuthority(SERVICE_2)
                        // all apis should at least have an user authenticated
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
