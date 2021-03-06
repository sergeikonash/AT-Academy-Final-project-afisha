package app.sergeikonash.events_service.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "films", schema = "afisha_schema")
public class Film implements Serializable{

    @Id
    @Column(name = "uuid", nullable = false, updatable = false)
    private UUID uuid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "dt_event")
    private long dt_event;

    @Column(name = "dt_end_of_sale")
    private long dt_end_of_sale;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "country")
    private UUID country;

    @Column(name = "release_year")
    private int release_year;

    @Column(name = "release_date")
    private LocalDate release_date;

    @Column(name = "duration")
    private int duration;

    @Column(name = "date_create")
    private LocalDateTime dtCreate;

    @Column(name = "date_update")
    @Version
    private LocalDateTime dtUpdate;

    public Film() {
    }

    public Film(UUID uuid,
                String title,
                String description,
                long dt_event,
                long dt_end_of_sale,
                String type,
                String status,
                UUID country,
                int release_year,
                LocalDate release_date,
                int duration,
                LocalDateTime dtCreate,
                LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.dt_event = dt_event;
        this.dt_end_of_sale = dt_end_of_sale;
        this.type = type;
        this.status = status;
        this.country = country;
        this.release_year = release_year;
        this.release_date = release_date;
        this.duration = duration;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID id) {
        this.uuid = uuid;
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
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
}
