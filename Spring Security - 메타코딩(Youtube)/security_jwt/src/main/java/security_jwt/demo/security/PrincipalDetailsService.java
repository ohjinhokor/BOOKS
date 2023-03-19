package security_jwt.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security_jwt.demo.model.User;
import security_jwt.demo.repository.UserRepository;

// http://localhost:8080/login으로 요청이 들어왔을 때 동작하게 되어있음.
// formLogin().disabled()라고 되어있으므로 현재는 자동으로 해당 메서드를 호출하는 것이 사라진 상태임
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("계정 찾기 시작");
		User user = userRepository.findByUsername(username);
		System.out.println("계정 찾아 옴");
		return new PrincipalDetails(user);
	}
}
