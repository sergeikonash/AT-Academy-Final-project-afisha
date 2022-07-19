package app.sergeikonash.events_service.controller;

import app.sergeikonash.events_service.dto.*;
import app.sergeikonash.events_service.service.api.IConcertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/concert")
public class ConcertController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IConcertService concertService;

    public ConcertController(IConcertService concertService) {
        this.concertService = concertService;
    }

    @PostMapping
    public ResponseEntity<ConcertCreateDto> createEvent(@RequestBody ConcertCreateDto concertCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(concertService.createEvent(concertCreateDto));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ConcertReadDto> getEventByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(concertService.findByUuid(uuid));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<ConcertCreateDto> editEvent(@RequestBody ConcertCreateDto concertCreateDto,
                                                   @PathVariable UUID uuid,
                                                   @PathVariable ("dt_update")Long dtUpdate){
        return ResponseEntity.ok(concertService.editByUuid(concertCreateDto, uuid, dtUpdate));
    }

    @GetMapping
    public ResponseEntity<PageDto> getPageOfEvents(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return ResponseEntity.ok(concertService.getAll(page, size));
    }
}
