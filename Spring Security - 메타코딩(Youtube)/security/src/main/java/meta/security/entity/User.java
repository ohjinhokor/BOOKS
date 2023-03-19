package meta.security.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter // Entity에는 Setter를 붙이지 않는 것이 더 맞는 방법이라고 생각
@Entity
@NoArgsConstructor
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private String email;

	@Enumerated(EnumType.STRING)
	private Role role; //ROLE_USER, ROLE_ADMIN

	private String provider; // google ..

	private String providerId;

	@CreationTimestamp
	private Timestamp createDate;

	@Builder
	public User(String username, String password, String email, Role role, String provider, String providerId, Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
}