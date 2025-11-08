package com.hcl.lloyds.ewallet.service;

import com.hcl.lloyds.ewallet.dto.UserRequestDto;
import com.hcl.lloyds.ewallet.dto.UserResponseDto;
import com.hcl.lloyds.ewallet.entity.UserEntity;
import com.hcl.lloyds.ewallet.exception.CustomException;
import com.hcl.lloyds.ewallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto dto) {

        validateUniqueField("email", dto.getEmail());
        validateUniqueField("phone", dto.getPhone());
        validateUniqueField("name", dto.getName());

        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        UserEntity savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getUserId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPhone()
        );
    }

    // Reusable helper for unique field checks
    private void validateUniqueField(String field, String value) {
        boolean exists = switch (field) {
            case "email" -> userRepository.findByEmail(value).isPresent();
            case "phone" -> userRepository.findByPhone(value).isPresent();
            case "name"  -> userRepository.findByName(value).isPresent();
            default      -> false;
        };
        if (exists) {
            throw new CustomException(field.substring(0, 1).toUpperCase() + field.substring(1) + " already exists.");
        }
    }
}
