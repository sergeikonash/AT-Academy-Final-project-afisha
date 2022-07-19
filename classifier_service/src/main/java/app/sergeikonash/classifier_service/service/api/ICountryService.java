package app.sergeikonash.classifier_service.service.api;

import app.sergeikonash.classifier_service.dto.CountryCreateDto;
import app.sergeikonash.classifier_service.dto.CountryReadDto;
import app.sergeikonash.classifier_service.dto.PageDto;

public interface ICountryService extends IService<CountryCreateDto, CountryReadDto>{

    @Override
    CountryCreateDto create(CountryCreateDto countryCreateDto);

    @Override
    PageDto<CountryReadDto> getAll(Integer page, Integer size);
}
