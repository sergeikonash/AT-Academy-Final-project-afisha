package app.sergeikonash.classifier_service.controllers;

import app.sergeikonash.classifier_service.dto.*;
import app.sergeikonash.classifier_service.service.api.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/classifier/category")
public class CategoryController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryCreateDto> create(@RequestBody CategoryCreateDto categoryCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryCreateDto));
    }

    @GetMapping
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return ResponseEntity.ok(categoryService.getAll(page, size));
    }
}
