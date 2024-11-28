package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.CategoryListResponse;
import com.snzn.project.product.controller.model.CategoryResponseModel;
import com.snzn.project.product.repository.CategoryRepository;
import com.snzn.project.product.repository.entity.Category;
import com.snzn.project.product.service.exception.DuplicateRecordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public void create(String name) {
        Optional<Category> optCategory = repository.findByNameAndDeletedFalse(name);
        if (optCategory.isPresent()) {
            throw new DuplicateRecordException();
        }
        repository.save(new Category(name));
    }

    public void softDelete(Long id) {
        Optional<Category> optCategory = repository.findById(id);
        if (optCategory.isPresent()) {
            Category category = optCategory.get();
            category.softDelete();
            repository.save(category);
        }
    }

    public CategoryListResponse listAll() {
        List<Category> categoryList = repository.findByDeletedFalse();
        List<CategoryResponseModel> categoryModelList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryModelList.add(new CategoryResponseModel(category.getId(), category.getName()));
        }

        return new CategoryListResponse(categoryModelList);
    }

}
