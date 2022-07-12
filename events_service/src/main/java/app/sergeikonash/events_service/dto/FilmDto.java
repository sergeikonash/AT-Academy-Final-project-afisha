package app.sergeikonash.events_service.dto;

import app.sergeikonash.events_service.dto.enums.Status;
import app.sergeikonash.events_service.dto.enums.Type;

import java.time.LocalDate;
import java.util.UUID;

public class FilmDto {

    private long id;
    private String title;
    private String description;
    private long dt_event;
    private long dt_end_of_sale;
    private Type type;
    private Status status;
    private UUID country;
    private int release_year;
    private LocalDate release_date;
    private int duration;

    public FilmDto() {
    }

    public FilmDto(long id,
                   String title,
                   String description,
                   long dt_event,
                   long dt_end_of_sale,
                   String type,
                   String status,
                   UUID country,
                   int release_year,
                   LocalDate release_date,
                   int duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dt_event = dt_event;
        this.dt_end_of_sale = dt_end_of_sale;
        this.type = Type.valueOf(type);
        this.status = Status.valueOf(status);
        this.country = country;
        this.release_year = release_year;
        this.release_date = release_date;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDt_event() {
        return dt_event;
    }

    public void setDt_event(long dt_event) {
        this.dt_event = dt_event;
    }

    public long getDt_end_of_sale() {
        return dt_end_of_sale;
    }

    public void setDt_end_of_sale(long dt_end_of_sale) {
        this.dt_end_of_sale = dt_end_of_sale;
    }

    public String getType() {
        return String.valueOf(type);
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public String getStatus() {
        return String.valueOf(status);
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
