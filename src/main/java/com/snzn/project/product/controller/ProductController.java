package com.snzn.project.product.controller;

import com.snzn.project.product.controller.model.ProductCreateRequest;
import com.snzn.project.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/entity")
@RestController
public class ProductController {

    private final ProductService service;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid ProductCreateRequest request) {
        service.create(request);
        return new ResponseEntity<>(CREATED);
    }

}
