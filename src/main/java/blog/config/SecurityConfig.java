package blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import blog.config.auth.PrincipalDetailService;

@Configuration // 빈 등록
@EnableWebSecurity // Security Filter 등록, 설정은 아래 Class에서 진행
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	// 회원가입 시 비밀번호가 어떤 해시로 바뀌었는지 알려주어야 같은 해시로 암호화해서 DB에 있는 비밀번호와 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf 토큰 비활성화, csfr 토큰없이 ajax를 통해 요청하기 때문
				.authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // Spring Security가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인
				.defaultSuccessUrl("/");
	}

}
