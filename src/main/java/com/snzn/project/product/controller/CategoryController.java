package com.snzn.project.product.controller;

import com.snzn.project.product.controller.model.CategoryCreateRequest;
import com.snzn.project.product.controller.model.CategoryDeleteRequest;
import com.snzn.project.product.controller.model.CategoryListResponse;
import com.snzn.project.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid CategoryCreateRequest request) {
        service.create(request.getName());
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid CategoryDeleteRequest request) {
        service.softDelete(request.getId());
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/list-all")
    public ResponseEntity<CategoryListResponse> listAll() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

}
