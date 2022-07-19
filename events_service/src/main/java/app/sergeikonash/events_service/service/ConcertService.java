package app.sergeikonash.events_service.service;

import app.sergeikonash.events_service.dao.api.IConcertDao;
import app.sergeikonash.events_service.dao.entity.Concert;
import app.sergeikonash.events_service.dto.ConcertCreateDto;
import app.sergeikonash.events_service.dto.ConcertReadDto;
import app.sergeikonash.events_service.dto.PageDto;
import app.sergeikonash.events_service.service.api.IConcertService;
import app.sergeikonash.events_service.service.api.IEventService;
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
public class ConcertService implements IConcertService {

    private final IConcertDao concertDao;
    private final ModelMapper mapper;

    @Autowired
    public ConcertService(IConcertDao concertDao, ModelMapper mapper) {
        this.concertDao = concertDao;
        this.mapper = mapper;
    }

    @Override
    public ConcertCreateDto createEvent(ConcertCreateDto concertCreateDto) {

        if (concertCreateDto.getTitle() == null || concertCreateDto.getType() == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        Concert concert = mapper.map(concertCreateDto, Concert.class);
        concert.setUuid(UUID.randomUUID());
        concert.setDtCreate(LocalDateTime.now());
        concert.setDtUpdate(concert.getDtCreate());
        this.concertDao.save(concert);
        return concertCreateDto;
    }

    @Override
    public ConcertReadDto findByUuid(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть пустым");
        }

        Concert concert = concertDao.findById(uuid).
                orElseThrow(()-> {
                    throw new IllegalArgumentException("Нет такого концерта");
                });
        return mapper.map(concert, ConcertReadDto.class);
    }

    @Override
    public ConcertCreateDto editByUuid(ConcertCreateDto toEdit, UUID uuid, Long dtUpdate) {
        LocalDateTime dateUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        Concert concert = concertDao.findById(uuid).orElseThrow(()-> {
            throw new IllegalArgumentException("Нет такого концерта");
        });

        if (concert.getDtUpdate().equals(dateUpdate)) {
        concert.setTitle(toEdit.getTitle());
        concert.setDescription(toEdit.getDescription());
        concert.setDt_event(toEdit.getDt_event());
        concert.setDt_end_of_sale(toEdit.getDt_end_of_sale());
        concert.setType(toEdit.getType());
        concert.setStatus(toEdit.getStatus());
        concert.setDtUpdate(LocalDateTime.now());
        this.concertDao.save(concert);
        } else {
            throw new OptimisticLockException("Событие уже было обновлено");
        }
        return toEdit;
    }

    @Override
    public PageDto<ConcertReadDto> getAll(int page, int size) {
        List<Concert> listEntity = concertDao.findAll();
        List<ConcertReadDto> listDto = listEntity.stream()
                .map(element -> mapper.map(element, ConcertReadDto.class))
                .collect(Collectors.toList());
        Pageable paging = PageRequest.of(--page,size);
        Page<Concert> pagedResult = concertDao.findAll(paging);
        Page<ConcertReadDto> pageConcert = new PageImpl<>(listDto, paging, pagedResult.getTotalElements());
        PageDto<ConcertReadDto> pageDto = mapper.map(pageConcert, PageDto.class);
        return pageDto;
    }
}
