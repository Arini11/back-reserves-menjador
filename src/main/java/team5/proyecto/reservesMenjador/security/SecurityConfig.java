package team5.proyecto.reservesMenjador.security;


import static team5.proyecto.reservesMenjador.security.Constants.LOGIN_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	
	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * 1. Se desactiva el uso de cookies
	 * 2. Se activa la configuración CORS con los valores por defecto
	 * 3. Se desactiva el filtro CSRF
	 * 4. Se indica que el login no requiere autenticación
	 * 5. Se indica que el resto de URLs esten securizadas
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        	.cors().and().csrf().disable()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll() //permitimos el acceso a /login a cualquiera
        	.antMatchers(HttpMethod.POST, "/api/users/add").permitAll()
        	.antMatchers("/api/categories/**").hasRole("Administrador")
        	.antMatchers(
        			"/v2/api-docs",           // swagger
                    "/webjars/**",            // swagger-ui webjars
                    "/swagger-resources/**",  // swagger-ui resources
                    "/swagger-ui/**",  // swagger-ui
                    "/configuration/**"      // swagger configuration
        	).permitAll() //permetre swagger
        	.anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
            .and()
            // Las peticiones /login pasaran previamente por este filtro
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))

            // Las demás peticiones pasarán por este filtro para validar el token
            .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}

