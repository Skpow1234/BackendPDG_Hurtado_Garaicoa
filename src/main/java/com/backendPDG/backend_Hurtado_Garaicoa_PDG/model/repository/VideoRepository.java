package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}

