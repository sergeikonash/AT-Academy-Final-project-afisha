package app.sergeikonash.classifier_service.service;

import app.sergeikonash.classifier_service.dao.api.ICountryDao;
import app.sergeikonash.classifier_service.dao.entity.Country;
import app.sergeikonash.classifier_service.dto.CountryCreateDto;
import app.sergeikonash.classifier_service.service.api.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CountryService implements IService<CountryCreateDto, Country> {

    private ICountryDao countryDao;

    public CountryService(ICountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public void create(CountryCreateDto countryCreateDto) {
        Country country = new Country();
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(LocalDateTime.now());
        country.setTitle(countryCreateDto.getTitle());
        country.setDescription(countryCreateDto.getDescription());
        countryDao.save(country);
    }

    @Override
    public Page<Country> getAll(Pageable page) {
        return countryDao.findAll(page);
    }

    @Override
    public Country get(UUID uuid) {
        return countryDao.getReferenceById(uuid);
    }

}
