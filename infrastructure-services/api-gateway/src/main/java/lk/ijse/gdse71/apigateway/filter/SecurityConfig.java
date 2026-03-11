package lk.ijse.gdse71.apigateway.filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable()) // CSRF අක්‍රිය කරන්න
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().permitAll() // හැම request එකකටම Gateway එක ඇතුළට එන්න ඉඩ දෙන්න
                )
                .formLogin(form -> form.disable()) // අර Login Form එක අක්‍රිය කරන්න
                .httpBasic(basic -> basic.disable())
                .build();
    }
}