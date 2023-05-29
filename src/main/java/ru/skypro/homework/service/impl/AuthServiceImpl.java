package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserDetailsManager manager;

  private final PasswordEncoder encoder;

  public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder passwordEncoder) {
    this.manager = manager;
    this.encoder = passwordEncoder;
  }

  @Override
  public boolean login(String userName, String password) {
    if (!manager.userExists(userName)) { // если пользователя с таким именем нет, то фолс
      return false;
    }
    UserDetails userDetails = manager.loadUserByUsername(userName); // иноформация о пользователе
    return encoder.matches(password, userDetails.getPassword()); // проверка совпадения паролей
  }

  @Override
  public boolean register(RegisterReq registerReq, Role role) {
    if (manager.userExists(registerReq.getUsername())) { // если пользователь уже есть, то фолс
      return false;
    }
    manager.createUser(
        User.builder()
            .passwordEncoder(this.encoder::encode)
            .password(registerReq.getPassword())
            .username(registerReq.getUsername())
            .roles(role.name())
            .build()); // создается новый пользователь
    return true;
  }
}
