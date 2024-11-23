package com.snzn.project.product.controller;

import com.snzn.project.product.controller.model.DefinitionCreateRequest;
import com.snzn.project.product.controller.model.DefinitionListResponse;
import com.snzn.project.product.service.DefinitionService;
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
@RequestMapping("/definition")
@RestController
public class DefinitionController {

    private final DefinitionService service;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid DefinitionCreateRequest request) {
        service.create(request);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/list-all")
    public ResponseEntity<DefinitionListResponse> listAll() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

}
