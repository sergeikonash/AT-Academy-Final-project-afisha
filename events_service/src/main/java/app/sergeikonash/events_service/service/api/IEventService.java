package app.sergeikonash.events_service.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IEventService<T1, T2> {

    T1 createEvent(T2 toCreateDto);

    T1 findByUuid(UUID uuid);

    T1 editByUuid(T2 toEdit, UUID uuid, LocalDateTime dtUpdate);

    Page<T1> findPageOfEvents(Pageable pageable);
}
