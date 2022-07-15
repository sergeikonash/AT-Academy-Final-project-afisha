package app.sergeikonash.user_service.dto;

import app.sergeikonash.user_service.dto.enums.Role;
import app.sergeikonash.user_service.dto.enums.Status;

import java.util.UUID;

public class UserCreateDto {

    private UUID uuid;
    private String mail;
    private String nick;
    private Role role;
    private Status status;
    private String password;

    public UserCreateDto() {
    }

    public UserCreateDto(UUID uuid,
                         String mail,
                         String nick,
                         Role role,
                         Status status,
                         String password) {
        this.uuid = uuid;
        this.mail = mail;
        this.nick = nick;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
