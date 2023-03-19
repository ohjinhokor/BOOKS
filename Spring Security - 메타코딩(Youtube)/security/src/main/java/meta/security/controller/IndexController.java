package meta.security.controller;

import lombok.RequiredArgsConstructor;
import meta.security.config.auth.PrincipalDetails;
import meta.security.entity.Role;
import meta.security.entity.User;
import meta.security.repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController {

	private final BCryptPasswordEncoder passwordEncoder;

	private final UserRepository userRepository;


	/**
	 * 기존에는 Oauth와 form login을 따로 관리했기에 별도로 메서드가 필요했지만, 이제는 PrincipalDetails를 통해 한 번에 관리할 수 있으므로 같은 역할을 하는 메서드 하나만 만들어주면 됨
	 * <p>
	 * 위의 내용은 '/user'를 받아주는 메서드를 통해 확인해보자
	 */
//	@GetMapping("/test/login")
//	public @ResponseBody String testLogin(
//		Authentication authentication,
//		@AuthenticationPrincipal PrincipalDetails principalDetails2) {
//		System.out.println("test/login");
//		System.out.println("authentication.getPrincipal() = " + authentication.getPrincipal());
//
//		// OAUTH로 로그인 한 경우 아래 코드에서 CastException이 발생함
//		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//		System.out.println("principalDetails.getUser() = " + principalDetails.getUser());
//
//		System.out.println(principalDetails2.getUser());
//		return "세션 정보 확인하기";
//	}
//
//	@GetMapping("/test/oauth/login")
//	public @ResponseBody String testOauthLogin(
//		Authentication authentication) {
//		System.out.println("test/oauth/login");
//		System.out.println("authentication.getPrincipal() = " + authentication.getPrincipal());
//
//		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//		System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());
//
//		return "세션 정보 확인하기";
//	}
	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}

	@GetMapping("user")
	@ResponseBody
	public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("principalDetails = " + principalDetails);
		System.out.println("principalDetails.getUser() = " + principalDetails.getUser());
		System.out.println("principalDetails.getUser().getUsername() = " + principalDetails.getUser().getUsername());
		return "user";
	}

	@GetMapping("admin")
	@ResponseBody
	public String admin() {
		return "admin";
	}

	@GetMapping("manager")
	@ResponseBody
	public String manager() {
		return "manager";
	}

	@GetMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@PostMapping("join")
	//Entity를 그대로 받는 것은 좋지 않지만 예제이므로 그냥 진행
	public String join(User user) {
		user.setRole(Role.ROLE_USER);
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return "redirect:/loginForm";
	}

	@GetMapping("joinForm")
	public String joinForm() {
		return "joinForm";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//	@PostAuthorize() : 함수가 실행된 뒤에 확인
	@GetMapping("/info2")
	public @ResponseBody String secretData() {
		return "비밀 데이터";
	}
}
