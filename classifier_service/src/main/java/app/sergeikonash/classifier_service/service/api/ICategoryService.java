package app.sergeikonash.classifier_service.service.api;

import app.sergeikonash.classifier_service.dto.CategoryCreateDto;
import app.sergeikonash.classifier_service.dto.CategoryReadDto;
import app.sergeikonash.classifier_service.dto.PageDto;

public interface ICategoryService extends IService<CategoryCreateDto, CategoryReadDto>{

    @Override
    CategoryCreateDto create(CategoryCreateDto categoryCreateDto);

    @Override
    PageDto<CategoryReadDto> getAll(Integer page, Integer size);
}
