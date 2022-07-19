package app.sergeikonash.events_service.controller;

import app.sergeikonash.events_service.dto.FilmCreateDto;
import app.sergeikonash.events_service.dto.FilmReadDto;
import app.sergeikonash.events_service.dto.PageDto;
import app.sergeikonash.events_service.service.api.IFilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/films")
public class FilmController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IFilmService filmService;

    public FilmController(IFilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseEntity<FilmCreateDto> createEvent(@RequestBody FilmCreateDto filmCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(filmService.createEvent(filmCreateDto));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<FilmReadDto> getEventByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(filmService.findByUuid(uuid));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<FilmCreateDto> editEvent(@RequestBody FilmCreateDto filmCreateDto,
                          @PathVariable UUID uuid,
                          @PathVariable ("dt_update") Long dtUpdate){
        return ResponseEntity.ok(filmService.editByUuid(filmCreateDto, uuid, dtUpdate));
    }

    @GetMapping
    public ResponseEntity<PageDto> getPageOfEvents(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return ResponseEntity.ok(filmService.getAll(page, size));
    }
}
