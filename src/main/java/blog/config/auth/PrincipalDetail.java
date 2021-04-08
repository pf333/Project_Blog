package blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import blog.model.User;
import lombok.Getter;

// UserDetails 타입의 PrinciaplDetail을 Spring Security의 세션 저장소에 저장한다.
@Getter
public class PrincipalDetail implements UserDetails {

	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정의 만료 여부(true: 만료 되지 않음)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정의 잠김 여부(true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호의 만료 여부(true: 만료 되지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 여부(true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정이 가지고 있는 권한 목록
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});
		return collectors;
	}

}
