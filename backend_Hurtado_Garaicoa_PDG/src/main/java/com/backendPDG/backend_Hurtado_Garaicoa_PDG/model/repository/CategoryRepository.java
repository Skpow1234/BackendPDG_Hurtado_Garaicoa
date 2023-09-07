package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
