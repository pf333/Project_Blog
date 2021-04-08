package blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // User class에 해당하는 테이블이 MySQL에 생성
//@DynamicInsert //Insert 할 때 null인 필드 제외
public class User {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트와 연결된 DB의 넘버링 전략을 따라감
	private int id;

	@Column(nullable = false, length = 100, unique = true) // null을 허용하지 않음, 최대 30자 이내
	private String username; // ID

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	// @ColumnDefault("user")
	@Enumerated(EnumType.STRING) // DB에는 Roletype이라는게 없으므로 알려줌
	private Roletype role; // Enum을 쓰는게 좋다.(admin, manager, user 등)

	private String oauth;

	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
