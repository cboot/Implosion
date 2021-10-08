package com.implosion.user.services.utils;

import com.implosion.user.dto.UserDTO;
import com.implosion.user.entities.User;

public class Mapper {

	public static UserDTO toDTO(User user) {
		UserDTO output = new UserDTO();
		output.setEmail(user.getEmail());
		output.setId(user.getId());
		output.setRegisterDate(user.getRegisterDate());
		output.setUsername(user.getName());
		return output;
	}
}
