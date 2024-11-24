package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.PropertyListResponse;
import com.snzn.project.product.controller.model.PropertyIdNameModel;
import com.snzn.project.product.controller.model.PropertyNameValueModel;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.entity.Property;
import com.snzn.project.product.repository.entity.PropertyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository repository;

    public void create(String name) {
        repository.save(new Property(name));
    }

    public PropertyListResponse listAll() {
        List<Property> propertyList = repository.findAll();
        List<PropertyIdNameModel> propertyModelList = convertPropertyEntityToModel(propertyList);

        return new PropertyListResponse(propertyModelList);
    }

    public static List<PropertyIdNameModel> convertPropertyEntityToModel(List<Property> propertyList) {
        List<PropertyIdNameModel> propertyModelList = new ArrayList<>();

        for (Property property : propertyList) {
            propertyModelList.add(new PropertyIdNameModel(property.getId(), property.getName()));
        }

        return propertyModelList;
    }

    public static List<PropertyNameValueModel> convertPropertyValueEntityToModel(List<PropertyValue> propertyValueList) {
        List<PropertyNameValueModel> propertyValueModelList = new ArrayList<>();

        for (PropertyValue propertyValue : propertyValueList) {
            propertyValueModelList.add(new PropertyNameValueModel(propertyValue.getProperty().getName(), propertyValue.getValue()));
        }

        return propertyValueModelList;
    }

}
