package com.snzn.project.product.controller.model;

import jakarta.validation.Valid;
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
public class ProductCreateRequest {

    @NotNull
    private Long definitionId;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Valid
    @NotEmpty
    private List<ProductIdValueModel> propertyList;

}
