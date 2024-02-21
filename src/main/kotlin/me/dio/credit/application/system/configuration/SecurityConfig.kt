package me.dio.credit.application.system.configuration

import me.dio.credit.application.system.enummeration.Roles
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.security.AuthenticationFilter
import me.dio.credit.application.system.security.AuthorizationFilter
import me.dio.credit.application.system.security.JwtUtil
import me.dio.credit.application.system.service.impl.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val customerRepository: CustomerRepository,
    private val userDetails: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
) {

    private val publicMatchers = arrayOf(
        "/api/customers",
        "/h2-console/**",
        "/swagger-ui.html",
        "/v3/api-docs/**",
        "/swagger-ui/**"
    )

    private val adminMatchers = arrayOf(
        "/api/admin/**"
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
                    .requestMatchers(*publicMatchers.map { AntPathRequestMatcher(it) }.toTypedArray()).permitAll()
                    .requestMatchers(*adminMatchers.map { AntPathRequestMatcher(it) }.toTypedArray())
                    .hasAuthority(Roles.ADMIN.description)
                    .anyRequest().authenticated()
            }
            .addFilter(AuthenticationFilter(authenticationManager(), customerRepository, jwtUtil))
            .addFilter(AuthorizationFilter(authenticationManager(), jwtUtil, userDetails))
            .build()
    }


}