package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.UserSecurity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

/**
 * MyUserDetailsService
 * Сервис для данных пользователя
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    /**
     * Поле репозитория пользователя
     */
    private final UserRepository userRepository;

    /**
     * Поле маппинга пользователя
     */
    private final UserMapper userMapper;

    /**
     * Поле данных пользователя
     */
    private final MyUserDetails myUserDetails;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity userSecurity = userRepository.findByEmailIgnoreCase(username)
                .map(userMapper::toSecurityDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        myUserDetails.setUserSecurity(userSecurity);
        return myUserDetails;
    }
}
