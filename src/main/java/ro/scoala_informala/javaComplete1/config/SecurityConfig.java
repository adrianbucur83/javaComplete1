package ro.scoala_informala.javaComplete1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

//    @Bean
//    @ConditionalOnMissingBean(UserDetailsService.class)
//    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails user = User.withUsername("test")
//                .password("$2a$12$IBocCKu7Ur1rVCdlfYtApO/YfBf7GAs3YJiIDBZrWFWUSt23PIN1a").roles("USER").build();
//        UserDetails admin = User.withUsername("admin")
//                .password("$2a$12$IBocCKu7Ur1rVCdlfYtApO/YfBf7GAs3YJiIDBZrWFWUSt23PIN1a").roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(List.of(user, admin));
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/mvc/customers").permitAll()
                        .requestMatchers("/mvc/customers/delete").hasRole("ADMIN")
                        .requestMatchers("/mvc/customers/update").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
