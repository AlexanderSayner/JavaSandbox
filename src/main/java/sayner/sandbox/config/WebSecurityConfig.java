package sayner.sandbox.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sayner.sandbox.authorization.handlers.CustomAccessDeniedHandler;
import sayner.sandbox.exceptions.handler.AuthenticationExceptionHandler;
import sayner.sandbox.models.User;
import sayner.sandbox.repositories.UserDetailsRepo;

import java.time.LocalDateTime;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationExceptionHandler authenticationExceptionHandler;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("awesomeUser").password(new BCryptPasswordEncoder().encode("1234")).roles("a_mere_mortals")
                .and()
                .withUser("niceUser").password(new BCryptPasswordEncoder().encode("123")).roles("a_mere_mortals")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("12345")).roles("GODLiKE")
        ;
    }

    //     HTTP Basic authorization :
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/articles*/**").hasRole("GODLiKE")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationExceptionHandler)
                .accessDeniedHandler(new CustomAccessDeniedHandler())
        ;
    }

//    // OAuth2 authorization :
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .mvcMatchers("/hello").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//        ;
//    }
//
//    @Bean
//    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
//
//        return map -> {
//            String stringId = (String) map.get("sub");
//            User user = userDetailsRepo.findById(stringId).orElseGet(() -> {
//                User newUser=new User();
//
//                newUser.setId(stringId);
//                newUser.setName((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
//                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
//
//                return newUser;
//            });
//
//            user.setLastVisit(LocalDateTime.now());
//
//            return  userDetailsRepo.save(user);
//        };
//    }

}
