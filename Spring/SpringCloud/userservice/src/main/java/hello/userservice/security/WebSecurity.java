package hello.userservice.security;

import hello.userservice.service.UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final Environment env;
    private final UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 로컬 및 링크 로컬 IP 허용
                        .requestMatchers(request -> {
                            String remoteAddr = request.getRemoteAddr();
                            return "127.0.0.1".equals(remoteAddr) || "localhost".equals(request.getRemoteHost()) ||
                                    remoteAddr.startsWith("169.254.");
                        }).permitAll()
                        // 특정 경로 허용
                        .requestMatchers("/user-service/users/**", "/user-service/h2-console/**", "/user-service/health_check/**").permitAll()
                        // 기타 요청 인증 필요
                        .anyRequest().authenticated()
                )
                .addFilter(authenticationFilter(http.getSharedObject(AuthenticationManager.class)));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private AuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, userService, env);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        return authenticationFilter;
    }
}
