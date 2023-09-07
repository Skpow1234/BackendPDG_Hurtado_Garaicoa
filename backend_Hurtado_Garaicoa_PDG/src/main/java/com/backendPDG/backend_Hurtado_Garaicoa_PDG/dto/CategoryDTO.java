package com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long Id;
    private String name;
    private String description;
}

