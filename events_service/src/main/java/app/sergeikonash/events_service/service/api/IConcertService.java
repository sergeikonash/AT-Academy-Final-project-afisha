package app.sergeikonash.events_service.service.api;

import app.sergeikonash.events_service.dto.ConcertCreateDto;
import app.sergeikonash.events_service.dto.ConcertReadDto;
import app.sergeikonash.events_service.dto.PageDto;

import java.util.UUID;

public interface IConcertService extends IEventService<ConcertCreateDto, ConcertReadDto>{
    @Override
    ConcertCreateDto createEvent(ConcertCreateDto toCreateDto);

    @Override
    ConcertReadDto findByUuid(UUID uuid);

    @Override
    ConcertCreateDto editByUuid(ConcertCreateDto toEdit, UUID uuid, Long dtUpdate);

    @Override
    PageDto<ConcertReadDto> getAll(int page, int size);
}
