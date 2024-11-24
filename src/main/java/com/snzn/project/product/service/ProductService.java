package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.ProductCreateRequest;
import com.snzn.project.product.controller.model.ProductIdValueModel;
import com.snzn.project.product.controller.model.ProductListResponse;
import com.snzn.project.product.controller.model.ProductResponseModel;
import com.snzn.project.product.repository.DefinitionRepository;
import com.snzn.project.product.repository.ProductRepository;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.PropertyValueRepository;
import com.snzn.project.product.repository.entity.Definition;
import com.snzn.project.product.repository.entity.Product;
import com.snzn.project.product.repository.entity.Property;
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

        List<PropertyValue> propertyValueList = convertPropertyValueModelToEntity(product, request.getPropertyList());
        propertyValueRepository.saveAll(propertyValueList);
    }

    private List<PropertyValue> convertPropertyValueModelToEntity(Product product, List<ProductIdValueModel> propertyValueModelList) {
        List<PropertyValue> propertyValueList = new ArrayList<>();

        for (ProductIdValueModel propertyValueModel : propertyValueModelList) {
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

    public ProductListResponse listAll() {
        List<Product> productList  = productRepository.findAll();
        List<ProductResponseModel> productModelList = new ArrayList<>();

        for (Product product : productList) {
            Definition definition = product.getDefinition();
            List<PropertyValue> propertyValueList = propertyValueRepository.findByProduct(product);

            ProductResponseModel productModel = new ProductResponseModel(
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
