package com.snzn.project.product.controller;

import com.snzn.project.product.controller.model.PropertyCreateRequest;
import com.snzn.project.product.controller.model.PropertyDeleteRequest;
import com.snzn.project.product.controller.model.PropertyListResponse;
import com.snzn.project.product.service.PropertyService;
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
@RequestMapping("/property")
@RestController
public class PropertyController {

    private final PropertyService service;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid PropertyCreateRequest request) {
        service.create(request.getName());
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid PropertyDeleteRequest request) {
        service.softDelete(request.getId());
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/list-all")
    public ResponseEntity<PropertyListResponse> listAll() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

}
