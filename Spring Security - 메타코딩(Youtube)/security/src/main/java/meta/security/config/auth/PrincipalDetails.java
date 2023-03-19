package meta.security.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어줍니다 (Security Context Holder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 됨.
// User 오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import meta.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

// 이렇게 UserDetails를 implements하면 Authentication안에 PrincipalDetails를 넣을 수 있음
@Getter
// Controller에서 Oauth로 로그인한 경우와 일반 로그인을 한 경우 모두를 커버하기 위해서는
// PrincipalDetails가 UserDetails와 OAuth2User 모두를 implements 하는 것이 좋다
public class PrincipalDetails implements UserDetails, OAuth2User {

	private User user; // 콤포지션

	private Map<String, Object> attributes;

	public PrincipalDetails(User user) {
		this.user = user;
	}

	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return null;
	}

	// 해당 유저의 권한을 return 해야함
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList();
		collect.add((GrantedAuthority) () -> user.getRole().getValue());
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
