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

import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
            throw new IllegalArgumentException("This field can not be empty");
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
    public UserCreateDto editUser(UserCreateDto userCreateDto, UUID uuid, Long dtUpdate) {

        LocalDateTime dateUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        User user = userDao.findById(uuid).orElseThrow(()-> {
            throw new IllegalArgumentException("Нет такого пользователя");
        });
        if (user.getDtUpdate().equals(dateUpdate)) {
            user.setDtUpdate(LocalDateTime.now());
            user.setMail(userCreateDto.getMail());
            user.setNick(userCreateDto.getNick());
            user.setRole(userCreateDto.getRole());
            user.setStatus(userCreateDto.getStatus());
            this.userDao.save(user);
        } else {
            throw new OptimisticLockException("Entity already updated");
        }
        return userCreateDto;
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
    public UserReadDto getUser(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("This field cannot be null");
        }

        User user = userDao.findById(uuid).
                orElseThrow(()-> {
                    throw new IllegalArgumentException("Нет такого пользователя");
                });
        return mapper.map(user, UserReadDto.class);
    }
}
