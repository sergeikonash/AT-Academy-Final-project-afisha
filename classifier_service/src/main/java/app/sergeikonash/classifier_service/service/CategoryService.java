package app.sergeikonash.classifier_service.service;

import app.sergeikonash.classifier_service.dao.api.ICategoryDao;
import app.sergeikonash.classifier_service.dao.entity.Category;
import app.sergeikonash.classifier_service.dto.CategoryCreateDto;
import app.sergeikonash.classifier_service.service.api.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CategoryService implements IService<CategoryCreateDto, Category> {

    private ICategoryDao categoryDao;

    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(LocalDateTime.now());
        category.setTitle(categoryCreateDto.getTitle());
        categoryDao.save(category);
    }

    @Override
    public Page<Category> getAll(Pageable page) {
        return categoryDao.findAll(page);
    }

    @Override
    public Category get(UUID uuid) {
        return categoryDao.getReferenceById(uuid);
    }
}
