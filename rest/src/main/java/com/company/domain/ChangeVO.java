package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangeVO {
	private String userid;
	private String password;
	private String new_password;
	private String confirm_password;
	
	public boolean newPasswordEqualsConfirmPassword() {
		return new_password.equals(confirm_password);
	}
}
