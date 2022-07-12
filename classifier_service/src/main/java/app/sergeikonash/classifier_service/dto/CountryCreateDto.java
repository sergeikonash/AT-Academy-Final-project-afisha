package app.sergeikonash.classifier_service.dto;

import java.util.UUID;

public class CountryCreateDto {

    private UUID uuid;
    private String title;
    private String description;

    public CountryCreateDto() {
    }

    public CountryCreateDto(UUID uuid, String title, String description) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
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
}
