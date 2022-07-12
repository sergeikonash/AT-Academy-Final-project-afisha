package app.sergeikonash.events_service.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "concerts", schema = "events")
public class Concert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;

    @Column(name = "date_update")
    @Version
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;

    public Concert() {
    }

    public Concert(long id,
                   String title,
                   String description,
                   long dt_event,
                   long dt_end_of_sale,
                   String type,
                   String status,
                   UUID category,
                   LocalDateTime dtCreate,
                   LocalDateTime dtUpdate) {
        this.id = id;
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
