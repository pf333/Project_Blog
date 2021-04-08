package blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.model.User;

//JapRepository는 User Table이 관리하는 Repository, User Table의 Primary Key는 Integer
public interface UserRepository extends JpaRepository<User, Integer> {
	// SELECT * FROM user WHERE username=1?;
	Optional<User> findByUsername(String username);
}

// JPA naming 쿼리, SELECT * FROM user WHERE username = ?1 AND password = ?2
//User findByUsernameAndPassword(String username, String password);