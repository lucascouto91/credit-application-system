package me.dio.credit.application.system.configuration

import me.dio.credit.application.system.enummeration.Roles
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.security.AuthenticationFilter
import me.dio.credit.application.system.security.AuthorizationFilter
import me.dio.credit.application.system.security.JwtUtil
import me.dio.credit.application.system.service.impl.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val customerRepository: CustomerRepository,
    private val userDetails: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
) {


    private val h2AndSwagger = arrayOf(
        "/h2-console/**",
        "/swagger-ui.html",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/login"
    )

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors { cors -> cors.disable() }
            .csrf { it.disable() }
            .headers { headers -> headers.frameOptions { frame -> frame.sameOrigin() } }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(*h2AndSwagger.map { AntPathRequestMatcher(it) }.toTypedArray()).permitAll()
                    .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/customers")).permitAll()
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/api/admin/**"))
                    .hasAuthority(Roles.ADMIN.description)
                    .anyRequest().authenticated()
            }
            .addFilter(AuthenticationFilter(authenticationManager(), customerRepository, jwtUtil))
            .addFilter(AuthorizationFilter(authenticationManager(), jwtUtil, userDetails))
            .build()
    }

    @Bean
    fun corsConfig(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

}