package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {
    void uploadVideo(MultipartFile file, String title, String description) throws IOException;
}

