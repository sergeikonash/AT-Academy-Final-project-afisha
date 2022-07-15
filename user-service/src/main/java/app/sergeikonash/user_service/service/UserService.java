package app.sergeikonash.user_service.service;

import app.sergeikonash.user_service.dao.api.IUserDao;
import app.sergeikonash.user_service.dao.entity.User;
import app.sergeikonash.user_service.dto.PageDto;
import app.sergeikonash.user_service.dto.UserCreateDto;
import app.sergeikonash.user_service.dto.UserReadDto;
import app.sergeikonash.user_service.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private IUserDao userDao;
    private ModelMapper mapper;

    @Autowired
    public UserService(IUserDao userDao, ModelMapper mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }

    @Override
    public UserCreateDto createUser(UserCreateDto userCreateDto) {

        if (userCreateDto.getMail() == null || userCreateDto.getNick() == null) {
            throw new IllegalArgumentException("This field cannot be empty");
        }

        User user = mapper.map(userCreateDto, User.class);
        user.setUuid(UUID.randomUUID());
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtCreate());
        user.setMail(userCreateDto.getMail());
        user.setNick(userCreateDto.getNick());
        user.setRole(userCreateDto.getRole());
        user.setStatus(userCreateDto.getStatus());
        user.setPassword(userCreateDto.getPassword());
        userDao.save(user);

        return userCreateDto;
    }

    @Override
    public User editUser(UserCreateDto userCreateDto, UUID uuid, LocalDateTime dtUpdate) {

        if (uuid == null) {
            throw new IllegalArgumentException("This field cannot be null");
        }

        User user = this.getUser(uuid);

        if(!user.getDtUpdate().equals(dtUpdate)){
            throw new IllegalArgumentException("Информация о пользователе уже была обновлена кем-то ранее");
        }

        user.setDtUpdate(LocalDateTime.now());
        user.setMail(userCreateDto.getMail());
        user.setNick(userCreateDto.getNick());
        user.setRole(userCreateDto.getRole());
        user.setStatus(userCreateDto.getStatus());
        user.setPassword(userCreateDto.getPassword());

        this.userDao.save(user);

        return this.getUser(uuid);
    }

    @Override
    public PageDto<UserReadDto> getAllUsers(Integer page, Integer size) {
        List<User> listEntity = userDao.findAll();
        List<UserReadDto> listDto = listEntity.stream()
                .map(element -> mapper.map(element, UserReadDto.class))
                .collect(Collectors.toList());
        Pageable paging = PageRequest.of(--page, size);
        Page<User> pagedResult = userDao.findAll(paging);
        Page<UserReadDto> pageDtoR = new PageImpl<>(listDto, paging, pagedResult.getTotalElements());
        PageDto<UserReadDto> pageDto = mapper.map(pageDtoR, PageDto.class);
        return pageDto;
    }

    @Override
    public User getUser(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("This field cannot be null");
        }

        return this.userDao
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Не нашли такого пользователя");
                });
    }
}
