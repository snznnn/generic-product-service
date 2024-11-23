package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.CategoryListResponse;
import com.snzn.project.product.controller.model.CategoryResponseModel;
import com.snzn.project.product.repository.CategoryRepository;
import com.snzn.project.product.repository.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public void create(String name) {
        repository.save(new Category(name));
    }

    public CategoryListResponse listAll() {
        List<Category> categoryList = repository.findAll();
        List<CategoryResponseModel> categoryModelList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryModelList.add(new CategoryResponseModel(category.getId(), category.getName()));
        }

        return new CategoryListResponse(categoryModelList);
    }

}
