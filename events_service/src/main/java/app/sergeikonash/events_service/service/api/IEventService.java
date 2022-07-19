package app.sergeikonash.events_service.service.api;

import app.sergeikonash.events_service.dto.PageDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IEventService<T1, T2> {

    T1 createEvent(T1 toCreateDto);

    T2 findByUuid(UUID uuid);

    T1 editByUuid(T1 toEdit, UUID uuid, Long dtUpdate);

    PageDto<T2> getAll(int page, int size);
}
