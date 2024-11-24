package com.snzn.project.product.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseModel {

    private String category;

    private String definition;

    private String brand;

    private String model;

    private List<PropertyNameValueModel> propertyList;

}
