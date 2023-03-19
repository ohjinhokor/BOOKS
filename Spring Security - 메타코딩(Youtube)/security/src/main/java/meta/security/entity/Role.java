package meta.security.entity;

import lombok.Getter;

@Getter
public enum Role {
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_MANAGER("ROLE_MANAGER"),
	ROLE_USER("ROLE_USER"),
	;

	private String value;

	Role(String value) {
		this.value = value;
	}
}