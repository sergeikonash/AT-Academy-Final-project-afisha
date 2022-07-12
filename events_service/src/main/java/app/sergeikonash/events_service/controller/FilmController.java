package app.sergeikonash.events_service.controller;

import app.sergeikonash.events_service.dao.entity.Film;
import app.sergeikonash.events_service.dto.FilmDto;
import app.sergeikonash.events_service.dto.enums.Type;
import app.sergeikonash.events_service.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/{type}")
    public ResponseEntity<Film> createEvent(@RequestBody FilmDto dto, @PathVariable Type type){
        if (!type.equals(dto.getType())) {
            throw new IllegalArgumentException("Неверный тип");
        }
        return new ResponseEntity<>(this.filmService.createEvent(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{type}/{uuid}")
    public Film getEventByUuid(@RequestParam UUID uuid) {
        return this.filmService.findByUuid(uuid);
    }

    @PutMapping("/{type}/{uuid}/dt_update/{dt_update}")
    public Film editEvent(@RequestBody FilmDto dto,
                          @PathVariable Type type,
                          @PathVariable UUID uuid,
                          @PathVariable ("dt_update")Long dtUpdate){
        return null;
    }

    @GetMapping("/{type}")
    public ResponseEntity<Film> getPageOfEvents(@PathVariable Type type,
                                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return null;
    }
}
