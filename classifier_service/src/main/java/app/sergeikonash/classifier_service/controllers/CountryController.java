package app.sergeikonash.classifier_service.controllers;

import app.sergeikonash.classifier_service.dto.*;
import app.sergeikonash.classifier_service.service.api.ICountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final ICountryService countryService;

    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<CountryCreateDto> create(@RequestBody CountryCreateDto countryCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.create(countryCreateDto));
    }

    @GetMapping
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return ResponseEntity.ok(countryService.getAll(page, size));
    }
}
