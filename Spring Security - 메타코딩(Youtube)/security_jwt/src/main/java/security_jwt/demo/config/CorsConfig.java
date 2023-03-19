package security_jwt.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true); // 내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 결정하는 것.
		config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용함
		config.addAllowedHeader("*"); // 모든 header에 응답을 허용함
		config.addAllowedMethod("*"); // 모든 'http method'에 응답을 허용함
		source.registerCorsConfiguration("/api/**", config);

		return new CorsFilter(source);
	}
}
