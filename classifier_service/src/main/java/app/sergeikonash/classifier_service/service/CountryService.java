package app.sergeikonash.classifier_service.service;

import app.sergeikonash.classifier_service.dao.api.ICountryDao;
import app.sergeikonash.classifier_service.dao.entity.Country;
import app.sergeikonash.classifier_service.dto.CountryCreateDto;
import app.sergeikonash.classifier_service.dto.CountryReadDto;
import app.sergeikonash.classifier_service.dto.PageDto;
import app.sergeikonash.classifier_service.service.api.ICountryService;
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
public class CountryService implements ICountryService {

    private ICountryDao countryDao;
    private ModelMapper mapper;

    @Autowired
    public CountryService(ICountryDao countryDao, ModelMapper mapper) {
        this.countryDao = countryDao;
        this.mapper = mapper;
    }

    @Override
    public CountryCreateDto create(CountryCreateDto countryCreateDto) {

        if (countryCreateDto.getTitle() == null) {
            throw new IllegalArgumentException("This field cannot be empty");
        }

        Country country = mapper.map(countryCreateDto, Country.class);
        country.setUuid(UUID.randomUUID());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtCreate());
        country.setTitle(countryCreateDto.getTitle());
        country.setDescription(countryCreateDto.getDescription());
        countryDao.save(country);

        return countryCreateDto;
    }

    @Override
    public PageDto<CountryReadDto> getAll(Integer page, Integer size) {
        List<Country> listEntity = countryDao.findAll();
        List<CountryReadDto> listDto = listEntity.stream()
                .map(element -> mapper.map(element, CountryReadDto.class))
                .collect(Collectors.toList());
        Pageable paging = PageRequest.of(--page, size);
        Page<Country> pagedResult = countryDao.findAll(paging);
        Page<CountryReadDto> pageDtoR = new PageImpl<>(listDto, paging, pagedResult.getTotalElements());
        PageDto<CountryReadDto> pageDto = mapper.map(pageDtoR, PageDto.class);
        return pageDto;
    }
}
