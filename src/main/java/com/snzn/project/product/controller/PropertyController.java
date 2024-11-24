package com.snzn.project.product.controller;

import com.snzn.project.product.controller.model.PropertyCreateRequest;
import com.snzn.project.product.controller.model.PropertyListResponse;
import com.snzn.project.product.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/property")
@RestController
public class PropertyController {

    private final PropertyService service;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid PropertyCreateRequest request) {
        service.create(request.getName());
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/list-all")
    public ResponseEntity<PropertyListResponse> listAll() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

}
