package app.sergeikonash.events_service.service;

import app.sergeikonash.events_service.dao.api.IFilmDao;
import app.sergeikonash.events_service.dao.entity.Concert;
import app.sergeikonash.events_service.dao.entity.Film;
import app.sergeikonash.events_service.dto.ConcertReadDto;
import app.sergeikonash.events_service.dto.FilmCreateDto;
import app.sergeikonash.events_service.dto.FilmReadDto;
import app.sergeikonash.events_service.dto.PageDto;
import app.sergeikonash.events_service.service.api.IEventService;
import app.sergeikonash.events_service.service.api.IFilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilmService implements IFilmService {

    private final IFilmDao filmDao;
    private final ModelMapper mapper;

    @Autowired
    public FilmService(IFilmDao filmDao, ModelMapper mapper) {
        this.filmDao = filmDao;
        this.mapper = mapper;
    }

    @Override
    public FilmCreateDto createEvent(FilmCreateDto filmCreateDto) {

        if (filmCreateDto.getTitle() == null || filmCreateDto.getType() == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        Film film = mapper.map(filmCreateDto, Film.class);
        film.setUuid(UUID.randomUUID());
        film.setDtCreate(LocalDateTime.now());
        film.setDtUpdate(film.getDtCreate());
        this.filmDao.save(film);
        return filmCreateDto;
    }

    @Override
    public FilmReadDto findByUuid(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        Film film = filmDao.findById(uuid).
                orElseThrow(()-> {
                    throw new IllegalArgumentException("Нет такого фильма");
                });
        return mapper.map(film, FilmReadDto.class);
    }

    @Override
    public FilmCreateDto editByUuid(FilmCreateDto toEdit, UUID uuid, Long dtUpdate) {
        LocalDateTime dateUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        Film film = filmDao.findById(uuid).orElseThrow(()-> {
            throw new IllegalArgumentException("Нет такого фильма");
        });

        if (film.getDtUpdate().equals(dateUpdate)) {
        film.setTitle(toEdit.getTitle());
        film.setDescription(toEdit.getDescription());
        film.setDt_event(toEdit.getDt_event());
        film.setDt_end_of_sale(toEdit.getDt_end_of_sale());
        film.setType(toEdit.getType());
        film.setStatus(toEdit.getStatus());
        film.setRelease_year(toEdit.getRelease_year());
        film.setRelease_date(toEdit.getRelease_date());
        film.setDuration(toEdit.getDuration());
        film.setDtUpdate(LocalDateTime.now());
        this.filmDao.save(film);
        } else {
            throw new OptimisticLockException("Событие уже было обновлено");
        }
        return toEdit;
    }

    @Override
    public PageDto<FilmReadDto> getAll(int page, int size) {
        List<Film> listEntity = filmDao.findAll();
        List<FilmReadDto> listDto = listEntity.stream()
                .map(element -> mapper.map(element, FilmReadDto.class))
                .collect(Collectors.toList());
        Pageable paging = PageRequest.of(--page,size);
        Page<Film> pagedResult = filmDao.findAll(paging);
        Page<FilmReadDto> pageConcert = new PageImpl<>(listDto, paging, pagedResult.getTotalElements());
        PageDto<FilmReadDto> pageDto = mapper.map(pageConcert, PageDto.class);
        return pageDto;
    }
}
