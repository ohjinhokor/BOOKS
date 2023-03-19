package security_jwt.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security_jwt.demo.model.User;
import security_jwt.demo.security.PrincipalDetails;

// 스프링 시큐리티에 있는  UsernamePasswordAuthenticationFilter를 extends 함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	// login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		System.out.println("로그인 시도 중");

		// 1. username, password를 받아서

		// 2. 정상인지 로그인 시도를 해보자!
		// authenticationManager로 로그인 시도를 하면, PrincipalDetailsService.loadUserByUsername가 호출된다.

		// 3. PrincipalDetails를 세션에 담고(권한 관리를 위해서)

		// 4. JWT 토큰을 만들어서 응답해주면 됨.

		ObjectMapper om = new ObjectMapper();
		try {
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

			// PrincipalDetailsService.loadUserByUsername가 호출된다
			// loadUserByUsername()이 실행된 후 정상이면 authentication이 리턴됨
			// 아래 코드가 잘 동작한다는 말은 DB에 있는 username과 password가 일치하는 것임
			Authentication authentication = authenticationManager.authenticate(token);

			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println(principalDetails.getUser().getUsername()); // 여기서 값이 잘 출력되면 로그인이 정상적으로 된 것임을 알 수 있다.

			//여기서 authentication객체를 return하면 authentication객체가 session 영역에 저장
			// JWT를 사용하므로 세션을 만들 이유가 없지만, 권한 처리를 편하게 하기 위해 session에 넣어줌
			return authentication;
		} catch (IOException e) {
		}
		return null;
	}

	// attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨
	// JWT 토큰을 만들어서 request를 요청한 사용자에게 JWT토큰을 response해주면 됨
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		System.out.println("login success");

		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

		// RSA 방식은 아니구 Hash 암호 방식
		String token = JWT.create()
			.withSubject(principalDetails.getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 10)))
			.withClaim("id", principalDetails.getUser().getId())
			.withClaim("username", principalDetails.getUser().getUsername())
			.sign(Algorithm.HMAC512("cos"));

		response.addHeader("Authorization", "Bearer " + token);

		System.out.println("token 발급 됨");
	}
}
