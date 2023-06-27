package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService manager;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private UserMapper userMapper;

    public AuthServiceImpl(UserDetailsService manager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName); // информация о пользователе
        return encoder.matches(password, userDetails.getPassword()); // проверка совпадения паролей
    }

    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (userRepository.findByEmailIgnoreCase(registerReq.getUsername()).isPresent()) { // если пользователь уже есть, то фолс
            return false;
        }
        UserEntity userEntity = userMapper.toEntityFromReq(registerReq);
        userEntity.setRole(role);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity); // создается новый пользователь
        return true;
    }
}
