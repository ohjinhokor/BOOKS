package security_jwt.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import security_jwt.demo.model.User;
import security_jwt.demo.repository.UserRepository;

@RestController
@RequiredArgsConstructor
public class RestApiController {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/home")
	public String home() {
		return "<h1>home<h1>";
	}

	@PostMapping("/join")
	public String join(@RequestBody User user) {
		String password = passwordEncoder.encode(user.getPassword());
		userRepository.save(new User(user.getUsername(), password));
		return "회원 가입 완료";
	}
}