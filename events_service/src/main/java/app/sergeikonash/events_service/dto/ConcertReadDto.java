package app.sergeikonash.events_service.dto;

import app.sergeikonash.events_service.dto.enums.Status;
import app.sergeikonash.events_service.dto.enums.Type;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConcertReadDto {

    private UUID uuid;
    private String title;
    private String description;
    private long dt_event;
    private long dt_end_of_sale;
    private Type type;
    private Status status;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

    public ConcertReadDto() {
    }

    public ConcertReadDto(UUID uuid,
                          String title,
                          String description,
                          long dt_event,
                          long dt_end_of_sale,
                          Type type,
                          Status status,
                          LocalDateTime dtCreate,
                          LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.dt_event = dt_event;
        this.dt_end_of_sale = dt_end_of_sale;
        this.type = type;
        this.status = status;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
