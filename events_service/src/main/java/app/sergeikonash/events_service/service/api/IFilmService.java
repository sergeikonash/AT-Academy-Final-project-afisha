package app.sergeikonash.events_service.service.api;

import app.sergeikonash.events_service.dto.FilmCreateDto;
import app.sergeikonash.events_service.dto.FilmReadDto;
import app.sergeikonash.events_service.dto.PageDto;

import java.util.UUID;

public interface IFilmService extends IEventService<FilmCreateDto, FilmReadDto> {
    @Override
    FilmCreateDto createEvent(FilmCreateDto toCreateDto);

    @Override
    FilmReadDto findByUuid(UUID uuid);

    @Override
    FilmCreateDto editByUuid(FilmCreateDto toEdit, UUID uuid, Long dtUpdate);

    @Override
    PageDto<FilmReadDto> getAll(int page, int size);
}
