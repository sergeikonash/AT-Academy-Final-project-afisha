package app.sergeikonash.user_service.controllers;

import app.sergeikonash.user_service.dao.entity.User;
import app.sergeikonash.user_service.dto.PageDto;
import app.sergeikonash.user_service.dto.UserCreateDto;
import app.sergeikonash.user_service.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreateDto> create(@RequestBody UserCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @GetMapping("/{id}")
    public User get(@PathVariable UUID uuid){
        return this.userService.getUser(uuid);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public User edit(@PathVariable UUID uuid, @PathVariable Long version, @RequestBody UserCreateDto dto){
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(version), ZoneId.systemDefault());
        return this.userService.editUser(dto, uuid, lastKnowDtUpdate);
    }

    @GetMapping
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }
}
