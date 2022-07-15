package app.sergeikonash.user_service.service.api;

import app.sergeikonash.user_service.dao.entity.User;
import app.sergeikonash.user_service.dto.PageDto;
import app.sergeikonash.user_service.dto.UserCreateDto;
import app.sergeikonash.user_service.dto.UserReadDto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    UserCreateDto createUser(UserCreateDto userCreateDto);

    User editUser(UserCreateDto userCreate, UUID uuid, LocalDateTime dtUpdate);

    PageDto<UserReadDto> getAllUsers(Integer page, Integer size);

    User getUser(UUID uuid);
}
