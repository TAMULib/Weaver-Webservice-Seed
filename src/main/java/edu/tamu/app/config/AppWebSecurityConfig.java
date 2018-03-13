package edu.tamu.app.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import edu.tamu.app.auth.service.UserDetailsService;
import edu.tamu.app.enums.AppRole;
import edu.tamu.app.model.User;
import edu.tamu.app.model.repo.UserRepo;
import edu.tamu.weaver.auth.config.AuthWebSecurityConfig;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AppWebSecurityConfig extends AuthWebSecurityConfig<User, UserRepo, UserDetailsService> {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .sessionManagement()
                .sessionCreationPolicy(STATELESS)
            .and()
                .authorizeRequests()
                    .expressionHandler(webExpressionHandler())
                    .antMatchers("/**/*")
                        .permitAll()
            .and()
                .headers()
                    .frameOptions()
                    .disable()
            .and()
                .csrf()
                    .disable()
            .addFilter(tokenAuthorizationFilter());
        // @formatter:on
    }

    @Override
    protected String buildRoleHierarchy() {
        StringBuilder roleHeirarchy = new StringBuilder();
        AppRole[] roles = AppRole.values();
        for (int i = 0; i <= roles.length - 2; i++) {
            roleHeirarchy.append(roles[i] + " > " + roles[i + 1]);
            if (i < roles.length - 2) {
                roleHeirarchy.append(" ");
            }
        }
        return roleHeirarchy.toString();
    }

}
