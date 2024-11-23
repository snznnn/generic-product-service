package com.snzn.project.product.service;

import com.snzn.project.product.controller.model.PropertyListResponse;
import com.snzn.project.product.controller.model.PropertyResponseModel;
import com.snzn.project.product.repository.PropertyRepository;
import com.snzn.project.product.repository.entity.Property;
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
        List<PropertyResponseModel> propertyModelList = new ArrayList<>();


        for (Property property : propertyList) {
            propertyModelList.add(new PropertyResponseModel(property.getId(), property.getName()));
        }

        return new PropertyListResponse(propertyModelList);
    }

}
