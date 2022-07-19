package app.sergeikonash.classifier_service.dto;

import java.util.UUID;

public class CategoryCreateDto {

    private String title;

    public CategoryCreateDto() {
    }

    public CategoryCreateDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
