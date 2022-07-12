package app.sergeikonash.classifier_service.dto;

import java.util.UUID;

public class CategoryCreateDto {

    private UUID uuid;
    private String title;

    public CategoryCreateDto() {
    }

    public CategoryCreateDto(UUID uuid, String title) {
        this.uuid = uuid;
        this.title = title;
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
}
