package app.sergeikonash.classifier_service.controllers;

import app.sergeikonash.classifier_service.dao.entity.Country;
import app.sergeikonash.classifier_service.dto.CountryCreateDto;
import app.sergeikonash.classifier_service.service.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IService<CountryCreateDto, Country> countryService;

    public CountryController(IService<CountryCreateDto, Country> countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CountryCreateDto dto) {
        countryService.create(dto);
    }
}
