package app.sergeikonash.user_service.controllers;

import app.sergeikonash.user_service.dto.PageDto;
import app.sergeikonash.user_service.dto.UserCreateDto;
import app.sergeikonash.user_service.dto.UserReadDto;
import app.sergeikonash.user_service.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserCreateDto> create(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserReadDto> get(@PathVariable UUID uuid){
        return ResponseEntity.ok(userService.getUser(uuid));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserCreateDto> edit(@RequestBody UserCreateDto userCreateDto,
                                              @PathVariable UUID uuid,
                                              @PathVariable(name = "dt_update") Long dtUpdate) {
        return ResponseEntity.ok(userService.editUser(userCreateDto, uuid, dtUpdate));
    }

    @GetMapping
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }
}
