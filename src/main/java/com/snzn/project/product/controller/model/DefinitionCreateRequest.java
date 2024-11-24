package com.snzn.project.product.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long categoryId;

    @NotEmpty
    private List<Long> propertyIdList;

}
