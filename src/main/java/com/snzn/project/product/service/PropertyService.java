package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.PropertyIdNameModel;
import com.snzn.project.product.controller.model.PropertyListResponse;
import com.snzn.project.product.controller.model.PropertyNameValueModel;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.entity.Category;
import com.snzn.project.product.repository.entity.Property;
import com.snzn.project.product.repository.entity.PropertyValue;
import com.snzn.project.product.service.exception.DuplicateRecordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository repository;

    public void create(String name) {
        Optional<Property> optProperty = repository.findByNameAndDeletedFalse(name);
        if (optProperty.isPresent()) {
            throw new DuplicateRecordException();
        }
        repository.save(new Property(name));
    }

    public void softDelete(Long id) {
        Optional<Property> optProperty = repository.findById(id);
        if (optProperty.isPresent()) {
            Property property = optProperty.get();
            property.softDelete();
            repository.save(property);
        }
    }

    public PropertyListResponse listAll() {
        List<Property> propertyList = repository.findByDeletedFalse();
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
            Property property = propertyValue.getProperty();
            propertyValueModelList.add(new PropertyNameValueModel(
                    property.getId(),
                    property.getName(),
                    propertyValue.getValue()
            ));
        }

        return propertyValueModelList;
    }

}
