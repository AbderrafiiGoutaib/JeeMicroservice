package sid.authentidicationservice.security.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurer {
    private UserAccount userDetailServiceImplementation;

    public SecurityConfig(UserDetailServiceImplementation userDetailServiceImplementation) {
        this.userDetailServiceImplementation = userDetailServiceImplementation;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailServiceImplementation);

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/h2-console/**","/refreshToken/**","/login/**").permitAll();
        //http.formLogin();
        // http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("ADMIN");
        // http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new Jwt( authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}