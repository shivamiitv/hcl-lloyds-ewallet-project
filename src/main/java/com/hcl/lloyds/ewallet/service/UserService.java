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
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new CustomException("Email already exists.");
        }
        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        UserEntity savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getUserId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPhone());
    }
}
