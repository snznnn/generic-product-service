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
public class DefinitionResponseModel {

    private Long id;

    private String name;

    private String categoryName;

    private List<PropertyIdNameModel> propertyList;

}
