package blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blog.model.Roletype;
import blog.model.User;
import blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired // DI
	private UserRepository userRepository;

	@DeleteMapping("dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "해당 아이디는 존재하지 않습니다.";
		}
		return "삭제되었습니다.";
	}

	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
//		userRepository.save(user); // id값이 있으면 insert, id값이 없으면 insert + update
		return user;
	}

	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	@GetMapping("/dummy/user")
	public Page<User> pagelist(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<User> pagingUsers = userRepository.findAll(pageable);
		List<User> users = pagingUsers.getContent();
		return pagingUsers;
	}

	// id값으로 파라미터를 전달할 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.id: " + id);
			}
		});
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("Id: " + user.getId());

		user.setRole(Roletype.User);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}
