package blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

	private static final String TAG = "HTTPControllerTest : ";

	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("Blog").password("1234").email("email").build();
		System.out.println(TAG + "getter : " + m.getUsername());
		m.setUsername("Blog");
		System.out.println(TAG + "setter : " + m.getUsername());
		return "lombok test 완료";
	}

	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청: " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}

	@PostMapping("/http/post")
	public String postTest(@RequestBody String text) {
		return "post 요청: " + text;
	}

	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}

	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
