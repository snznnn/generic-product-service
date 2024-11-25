package com.snzn.project.product.controller;

import com.snzn.project.product.controller.model.DefinitionCreateRequest;
import com.snzn.project.product.controller.model.DefinitionDeleteRequest;
import com.snzn.project.product.controller.model.DefinitionListResponse;
import com.snzn.project.product.service.DefinitionService;
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
@RequestMapping("/definition")
@RestController
public class DefinitionController {

    private final DefinitionService service;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid DefinitionCreateRequest request) {
        service.create(request);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid DefinitionDeleteRequest request) {
        service.softDelete(request.getId());
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/list-all")
    public ResponseEntity<DefinitionListResponse> listAll() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

}
