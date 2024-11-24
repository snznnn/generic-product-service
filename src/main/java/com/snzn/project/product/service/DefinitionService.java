package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.DefinitionCreateRequest;
import com.snzn.project.product.controller.model.DefinitionListResponse;
import com.snzn.project.product.controller.model.DefinitionResponseModel;
import com.snzn.project.product.repository.CategoryRepository;
import com.snzn.project.product.repository.DefinitionRepository;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.entity.Definition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;
    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;

    public void create(DefinitionCreateRequest request) {
        var categoryReference = categoryRepository.getReferenceById(request.getCategoryId());
        var propertyReferenceList = request.getPropertyIdList().stream().map(propertyRepository::getReferenceById).toList();

        var definition = new Definition(
                categoryReference,
                propertyReferenceList,
                request.getName()
        );
        definitionRepository.save(definition);
    }

    public DefinitionListResponse listAll() {
        List<Definition> definitionList = definitionRepository.findAll();
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
