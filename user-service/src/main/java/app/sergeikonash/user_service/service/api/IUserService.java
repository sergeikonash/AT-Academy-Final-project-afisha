package app.sergeikonash.user_service.service.api;

import app.sergeikonash.user_service.dto.PageDto;
import app.sergeikonash.user_service.dto.UserCreateDto;
import app.sergeikonash.user_service.dto.UserReadDto;

import java.util.UUID;

public interface IUserService {
    UserCreateDto createUser(UserCreateDto userCreateDto);

    UserCreateDto editUser(UserCreateDto userCreate, UUID uuid, Long dtUpdate);

    PageDto<UserReadDto> getAllUsers(Integer page, Integer size);

    UserReadDto getUser(UUID uuid);
}
