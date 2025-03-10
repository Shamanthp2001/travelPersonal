package com.shami.travel_advisor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Autowired
//    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        //disable csrf control. Lambda expression - AbstractHttpConfigurer::disable
//        http.csrf(customise-> customise.disable());
//        http.authorizeHttpRequests(request->request.anyRequest().authenticated());
//
//        /*
//        check with property file username,password
//        It will work only in Browser {Ok status but shows form code in postman }
//         */
////        http.formLogin(Customizer.withDefaults());
//
//        //for getting proper response which can be readable eg:In postMan
//        http.httpBasic(Customizer.withDefaults());
//
//        //making HTTP request stateless because no need to worry about sessionID and we are desabled CSRF
//        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();

        //simplified code
        return http
                .csrf(customise-> customise.disable())
                .authorizeHttpRequests(request->request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * UserDetailService for customizing user security <username,password,access>
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1=User.withDefaultPasswordEncoder().
                username("sham")
                .password("sham")
                .roles("USER")
                .build();
        UserDetails user2=User.withDefaultPasswordEncoder().
                username("nec")
                .password("nec")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

    /**
     * to customize authentication ,to access Database
     * @return AuthenticationProvider
     */
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }
}
