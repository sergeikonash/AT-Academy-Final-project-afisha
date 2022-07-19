package app.sergeikonash.classifier_service.service;

import app.sergeikonash.classifier_service.dao.api.ICategoryDao;
import app.sergeikonash.classifier_service.dao.entity.Category;
import app.sergeikonash.classifier_service.dto.CategoryCreateDto;
import app.sergeikonash.classifier_service.dto.CategoryReadDto;
import app.sergeikonash.classifier_service.dto.PageDto;
import app.sergeikonash.classifier_service.service.api.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private ICategoryDao categoryDao;
    private ModelMapper mapper;

    @Autowired
    public CategoryService(ICategoryDao categoryDao, ModelMapper mapper) {
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    @Override
    public CategoryCreateDto create(CategoryCreateDto categoryCreateDto) {

        if (categoryCreateDto.getTitle() == null) {
            throw new IllegalArgumentException("This field cannot be empty");
        }

        Category category = mapper.map(categoryCreateDto, Category.class);
        category.setUuid(UUID.randomUUID());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtCreate());
        category.setTitle(categoryCreateDto.getTitle());
        categoryDao.save(category);

        return categoryCreateDto;
    }

    @Override
    public PageDto<CategoryReadDto> getAll(Integer page, Integer size) {
        List<Category> listEntity = categoryDao.findAll();
        List<CategoryReadDto> listDto = listEntity.stream()
                .map(element -> mapper.map(element, CategoryReadDto.class))
                .collect(Collectors.toList());
        Pageable paging = PageRequest.of(--page, size);
        Page<Category> pagedResult = categoryDao.findAll(paging);
        Page<CategoryReadDto> pageDtoR = new PageImpl<>(listDto, paging, pagedResult.getTotalElements());
        PageDto<CategoryReadDto> pageDto = mapper.map(pageDtoR, PageDto.class);
        return pageDto;
    }
}
