package app.sergeikonash.events_service.service;

import app.sergeikonash.events_service.dao.api.IConcertDao;
import app.sergeikonash.events_service.dao.entity.Concert;
import app.sergeikonash.events_service.dto.ConcertDto;
import app.sergeikonash.events_service.service.api.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConcertService implements IEventService<Concert, ConcertDto> {

    private final IConcertDao concertDao;

    public ConcertService(IConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    @Override
    public Concert createEvent(ConcertDto concertDto) {

        if (concertDto.getTitle() == null || concertDto.getType() == null) {
            throw new IllegalArgumentException("This field cannot be empty");
        }

        Concert concert = new Concert();
        concert.setTitle(concertDto.getTitle());
        concert.setDescription(concertDto.getDescription());
        concert.setDt_event(concertDto.getDt_event());
        concert.setDt_end_of_sale(concertDto.getDt_end_of_sale());
        concert.setType(concertDto.getType());
        concert.setStatus(concertDto.getStatus());
        concert.setCategory(concertDto.getCategory());
        concert.setDtCreate(LocalDateTime.now());
        concert.setDtUpdate(LocalDateTime.now());
        return this.concertDao.save(concert);
    }

    @Override
    public Concert findByUuid(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        return this.concertDao
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Не нашли такого события");
                });
    }

    @Override
    public Concert editByUuid(ConcertDto toEdit, UUID uuid, LocalDateTime dtUpdate) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        Concert concert = this.findByUuid(uuid);

        if(concert.getDtUpdate().equals(dtUpdate)){
            throw new IllegalArgumentException("Событие уже было обновлено кем-то ранее");
        }

        concert.setTitle(toEdit.getTitle());
        concert.setDescription(toEdit.getDescription());
        concert.setDt_event(toEdit.getDt_event());
        concert.setDt_end_of_sale(toEdit.getDt_end_of_sale());
        concert.setType(toEdit.getType());
        concert.setStatus(toEdit.getStatus());
        concert.setCategory(toEdit.getCategory());
        concert.setDtUpdate(LocalDateTime.now());
        this.concertDao.save(concert);

        return this.findByUuid(uuid);
    }

    @Override
    public Page<Concert> findPageOfEvents(Pageable pageable) {
        return this.concertDao.findAll(pageable);
    }
}
