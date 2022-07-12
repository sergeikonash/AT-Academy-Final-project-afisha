package app.sergeikonash.events_service.service;

import app.sergeikonash.events_service.dao.api.IFilmDao;
import app.sergeikonash.events_service.dao.entity.Film;
import app.sergeikonash.events_service.dto.FilmDto;
import app.sergeikonash.events_service.service.api.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FilmService implements IEventService<Film, FilmDto> {

    private final IFilmDao filmDao;

    public FilmService(IFilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @Override
    public Film createEvent(FilmDto filmDto) {
        if (filmDto.getTitle() == null || filmDto.getType() == null) {
            throw new IllegalArgumentException("This field cannot be empty");
        }

        Film film = new Film();
        film.setTitle(filmDto.getTitle());
        film.setDescription(filmDto.getDescription());
        film.setDt_event(filmDto.getDt_event());
        film.setDt_end_of_sale(filmDto.getDt_end_of_sale());
        film.setType(filmDto.getType());
        film.setStatus(filmDto.getStatus());
        film.setCountry(filmDto.getCountry());
        film.setRelease_year(filmDto.getRelease_year());
        film.setRelease_date(filmDto.getRelease_date());
        film.setDuration(filmDto.getDuration());
        film.setDtCreate(LocalDateTime.now());
        film.setDtUpdate(LocalDateTime.now());
        return this.filmDao.save(film);
    }

    @Override
    public Film findByUuid(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        return this.filmDao
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Не нашли такого события");
                });
    }

    @Override
    public Film editByUuid(FilmDto toEdit, UUID uuid, LocalDateTime dtUpdate) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        Film film = this.findByUuid(uuid);

        if(!film.getDtUpdate().equals(dtUpdate)){
            throw new IllegalArgumentException("Событие уже было обновлено кем-то ранее");
        }

        film.setTitle(toEdit.getTitle());
        film.setDescription(toEdit.getDescription());
        film.setDt_event(toEdit.getDt_event());
        film.setDt_end_of_sale(toEdit.getDt_end_of_sale());
        film.setType(toEdit.getType());
        film.setStatus(toEdit.getStatus());
        film.setCountry(toEdit.getCountry());
        film.setRelease_year(toEdit.getRelease_year());
        film.setRelease_date(toEdit.getRelease_date());
        film.setDuration(toEdit.getDuration());
        film.setDtUpdate(LocalDateTime.now());
        this.filmDao.save(film);

        return this.findByUuid(uuid);
    }

    @Override
    public Page<Film> findPageOfEvents(Pageable pageable) {
        return this.filmDao.findAll(pageable);
    }
}
