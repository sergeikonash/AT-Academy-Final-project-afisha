package app.sergeikonash.classifier_service.controllers;

import app.sergeikonash.classifier_service.dao.entity.Category;
import app.sergeikonash.classifier_service.dao.entity.Country;
import app.sergeikonash.classifier_service.dto.*;
import app.sergeikonash.classifier_service.service.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CategoryController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IService<CategoryCreateDto, Category, CategoryReadDto> categoryService;

    public CategoryController(IService<CategoryCreateDto, Category, CategoryReadDto> categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryCreateDto> create(@RequestBody CategoryCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(dto));
    }

    @GetMapping
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return ResponseEntity.ok(categoryService.getAll(page, size));
    }
}
