package app.sergeikonash.events_service.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "concerts", schema = "afisha_schema")
public class Concert {

    @Id
    @Column(name = "uuid", nullable = false, updatable = false)
    private UUID uuid;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "dt_event")
    long dt_event;

    @Column(name = "dt_end_of_sale")
    long dt_end_of_sale;

    @Column(name = "type")
    String type;

    @Column(name = "status")
    String status;

    @Column(name = "category")
    UUID category;

    @Column(name = "date_create")
    private LocalDateTime dtCreate;

    @Column(name = "date_update")
    @Version
    private LocalDateTime dtUpdate;

    public Concert() {
    }

    public Concert(UUID uuid,
                   String title,
                   String description,
                   long dt_event,
                   long dt_end_of_sale,
                   String type,
                   String status,
                   UUID category,
                   LocalDateTime dtCreate,
                   LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.dt_event = dt_event;
        this.dt_end_of_sale = dt_end_of_sale;
        this.type = type;
        this.status = status;
        this.category = category;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID id) {
        this.uuid = id;
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

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
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
