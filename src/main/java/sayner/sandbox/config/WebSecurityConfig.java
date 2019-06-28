package sayner.sandbox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import sayner.sandbox.jsonmuster.ModelResponse;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ModelResponse modelResponse;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.exceptionHandling().accessDeniedHandler((AccessDeniedHandler) new ModelResponse().responseEntity(null, "Success", null, null));
        http.authorizeRequests()
                .antMatchers("/shops").authenticated()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    String json = String.format("{ \"exception\": \"%s\", \"statusCode\": \"%s\", \"message\": \"%s\"}", authException.getMessage(), HttpStatus.UNAUTHORIZED, "без пароля низя");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                });
       /* http.formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll(); */

        /* http.addFilterAfter(new CustomFilter(),
                BasicAuthenticationFilter.class); */
    }


}

/*
@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/articles").authenticated()
                //.mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
/*
    @Bean
    public PrincipalExtractor principalExtractor(UserDetailRepo userDetailsRepo) {
        return map -> {
            return new User();
        };
    }
}
*/
