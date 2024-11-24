package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.ProductCreateRequest;
import com.snzn.project.product.controller.model.PropertyRequestModel;
import com.snzn.project.product.repository.DefinitionRepository;
import com.snzn.project.product.repository.ProductRepository;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.PropertyValueRepository;
import com.snzn.project.product.repository.entity.Product;
import com.snzn.project.product.repository.entity.PropertyValue;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DefinitionRepository definitionRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyValueRepository propertyValueRepository;

    @Transactional
    public void create(ProductCreateRequest request) {
        var definitionReference = definitionRepository.getReferenceById(request.getDefinitionId());

        var product = new Product(
                definitionReference,
                request.getBrand(),
                request.getModel());
        productRepository.save(product);

        List<PropertyValue> propertyValueList = convertPropertyValueModelToEntity(product, request.getPropertyValueList());
        propertyValueRepository.saveAll(propertyValueList);
    }

    private List<PropertyValue> convertPropertyValueModelToEntity(Product product, List<PropertyRequestModel> propertyValueModelList) {
        List<PropertyValue> propertyValueList = new ArrayList<>();

        for (PropertyRequestModel propertyValueModel : propertyValueModelList) {
            propertyValueList.add(
                    new PropertyValue(
                            product,
                            propertyRepository.getReferenceById(propertyValueModel.getId()),
                            propertyValueModel.getValue()
                    )
            );
        }

        return propertyValueList;
    }

}
