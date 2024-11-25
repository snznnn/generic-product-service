package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.DefinitionCreateRequest;
import com.snzn.project.product.controller.model.DefinitionListResponse;
import com.snzn.project.product.controller.model.DefinitionResponseModel;
import com.snzn.project.product.repository.CategoryRepository;
import com.snzn.project.product.repository.DefinitionRepository;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.entity.Definition;
import com.snzn.project.product.service.exception.DuplicateRecordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;
    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;

    public void create(DefinitionCreateRequest request) {
        var categoryReference = categoryRepository.getReferenceById(request.getCategoryId());
        var propertyReferenceList = request.getPropertyIdList().stream().map(propertyRepository::getReferenceById).toList();

        Optional<Definition> optDefinition = definitionRepository.findByNameAndDeletedFalse(request.getName());
        if (optDefinition.isPresent()) {
            throw new DuplicateRecordException();
        }

        var definition = new Definition(
                categoryReference,
                propertyReferenceList,
                request.getName()
        );
        definitionRepository.save(definition);
    }

    public void softDelete(Long id) {
        Optional<Definition> optDefinition = definitionRepository.findById(id);
        if (optDefinition.isPresent()) {
            Definition definition = optDefinition.get();
            definition.softDelete();
            definitionRepository.save(definition);
        }
    }

    public DefinitionListResponse list(Long categoryId) {
        List<Definition> definitionList = new ArrayList<>();

        if (isNull(categoryId)) {
            definitionList = definitionRepository.findByDeletedFalse();
        } else {
            definitionList = definitionRepository.findByCategoryIdAndDeletedFalse(categoryId);
        }

        List<DefinitionResponseModel> definitionModelList = new ArrayList<>();

        for (Definition definition : definitionList) {
            var definitionModel = new DefinitionResponseModel(
                    definition.getId(),
                    definition.getName(),
                    definition.getCategory().getName(),
                    PropertyService.convertPropertyEntityToModel(definition.getPropertyList())
            );
            definitionModelList.add(definitionModel);
        }

        return new DefinitionListResponse(definitionModelList);
    }

}
