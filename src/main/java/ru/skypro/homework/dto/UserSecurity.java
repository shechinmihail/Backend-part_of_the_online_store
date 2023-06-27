package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Класс UserSecurity, представляет побочный класс пользователя
 */
@Data
public class UserSecurity {

    /**
     * id пользователя
     */
    private Integer id;

    /**
     * Логин пользователя
     */
    private String email;

    /**
     * Пароль пользователя
     */
    private String password;

    /**
     * Роль пользователя
     */
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
