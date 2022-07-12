package app.sergeikonash.events_service.controller;

import app.sergeikonash.events_service.dao.entity.Concert;
import app.sergeikonash.events_service.dto.ConcertDto;
import app.sergeikonash.events_service.dto.enums.Type;
import app.sergeikonash.events_service.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event")
public class ConcertController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @PostMapping(value = "/{type}")
    public ResponseEntity<Concert> createEvent(@RequestBody ConcertDto dto, @PathVariable Type type){
        if (!type.equals(dto.getType())) {
            throw new IllegalArgumentException("Неверный тип");
        }
        return new ResponseEntity<>(this.concertService.createEvent(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{type}/{uuid}")
    public Concert getEventByUuid(@RequestParam Type type, @RequestParam UUID uuid) {
        return this.concertService.findByUuid(uuid);
    }

    @PutMapping("/{type}/{uuid}/dt_update/{dt_update}")
    public Concert editEvent(@RequestBody ConcertDto dto,
                             @PathVariable Type type,
                             @PathVariable UUID uuid,
                             @PathVariable ("dt_update")Long dtUpdate){
        return null;
    }

    @GetMapping("/{type}")
    public ResponseEntity<Object> getPageOfEvents(@PathVariable Type type,
                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("uuid"));
//
//        ResponseEntity<Object> responseEntity;
//
//        Page<Concert> pageConcert = concertService.getPageOfEvents(pageRequest);
        return null;
    }
}
