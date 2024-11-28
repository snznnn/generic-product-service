package com.snzn.project.product.controller;

import com.snzn.project.product.controller.model.ProductCreateRequest;
import com.snzn.project.product.controller.model.ProductDeleteRequest;
import com.snzn.project.product.controller.model.ProductListResponse;
import com.snzn.project.product.controller.model.ProductUpdateRequest;
import com.snzn.project.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

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

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid ProductUpdateRequest request) {
        service.update(request);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid ProductDeleteRequest request) {
        service.softDelete(request.getId());
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<ProductListResponse> list(@RequestParam(required = false) Long definitionId) {
        return new ResponseEntity<>(service.list(definitionId), OK);
    }

}
