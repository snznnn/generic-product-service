package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.ProductCreateRequest;
import com.snzn.project.product.controller.model.ProductIdValueModel;
import com.snzn.project.product.controller.model.ProductListResponse;
import com.snzn.project.product.controller.model.ProductResponseModel;
import com.snzn.project.product.controller.model.ProductUpdateRequest;
import com.snzn.project.product.repository.DefinitionRepository;
import com.snzn.project.product.repository.ProductRepository;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.PropertyValueRepository;
import com.snzn.project.product.repository.entity.Definition;
import com.snzn.project.product.repository.entity.Product;
import com.snzn.project.product.repository.entity.Property;
import com.snzn.project.product.repository.entity.PropertyValue;
import com.snzn.project.product.service.exception.DuplicateRecordException;
import com.snzn.project.product.service.exception.RecordNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DefinitionRepository definitionRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyValueRepository propertyValueRepository;

    @Transactional
    public void create(ProductCreateRequest request) {
        Optional<Definition> optDefinition = definitionRepository.findById(request.getDefinitionId());
        if (optDefinition.isEmpty()) {
            throw new RecordNotFoundException();
        }
        Definition definition = optDefinition.get();

        Optional<Product> optCategory = productRepository.findByDefinitionAndBrandAndModel(definition, request.getBrand(), request.getModel());
        if (optCategory.isPresent()) {
            throw new DuplicateRecordException();
        }

        var product = new Product(
                definition,
                request.getBrand(),
                request.getModel());
        productRepository.save(product);

        List<PropertyValue> propertyValueList = convertPropertyValueModelToEntity(definition, product, request.getPropertyList());
        propertyValueRepository.saveAll(propertyValueList);
    }

    @Transactional
    public void update(ProductUpdateRequest request) {
        Optional<Product> optProduct = productRepository.findByIdAndDeletedFalse(request.getProductId());
        if (optProduct.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            Product product = optProduct.get();
            propertyValueRepository.deleteAllByProduct(product);

            List<PropertyValue> propertyValueList = convertPropertyValueModelToEntity(product.getDefinition(), product, request.getPropertyList());
            propertyValueRepository.saveAll(propertyValueList);
        }
    }

    private List<PropertyValue> convertPropertyValueModelToEntity(Definition definition, Product product, List<ProductIdValueModel> propertyValueModelList) {
        List<PropertyValue> propertyValueList = new ArrayList<>();
        List<Long> propertyIdList = definition.getPropertyList().stream().map(Property::getId).toList();

        for (ProductIdValueModel propertyValueModel : propertyValueModelList) {
            if (propertyIdList.contains(propertyValueModel.getId())) {
                propertyValueList.add(
                        new PropertyValue(
                                product,
                                propertyRepository.getReferenceById(propertyValueModel.getId()),
                                propertyValueModel.getValue()
                        )
                );
            }
        }

        return propertyValueList;
    }

    public void softDelete(Long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            product.softDelete();
            productRepository.save(product);
        }
    }

    public ProductListResponse list(Long definitionId) {
        List<Product> productList;

        if (isNull(definitionId)) {
            productList = productRepository.findByDeletedFalse();
        } else {
            productList = productRepository.findByDefinitionIdAndDeletedFalse(definitionId);
        }

        List<ProductResponseModel> productModelList = new ArrayList<>();

        for (Product product : productList) {
            Definition definition = product.getDefinition();
            List<PropertyValue> propertyValueList = propertyValueRepository.findByProduct(product);

            ProductResponseModel productModel = new ProductResponseModel(
                    product.getId(),
                    definition.getCategory().getName(),
                    definition.getName(),
                    product.getBrand(),
                    product.getModel(),
                    PropertyService.convertPropertyValueEntityToModel(propertyValueList)
            );
            productModelList.add(productModel);
        }

        return new ProductListResponse(productModelList);
    }

}
