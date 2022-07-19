package app.sergeikonash.classifier_service.dto;

import java.util.UUID;

public class CountryCreateDto {

    private String title;
    private String description;

    public CountryCreateDto() {
    }

    public CountryCreateDto(String title, String description) {
        this.title = title;
        this.description = description;
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
