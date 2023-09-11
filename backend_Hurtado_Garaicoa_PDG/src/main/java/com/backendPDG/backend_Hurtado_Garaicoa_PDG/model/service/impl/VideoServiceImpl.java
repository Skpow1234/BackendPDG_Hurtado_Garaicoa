package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.impl;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Video;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.VideoRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.VideoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    @Transactional
    public void uploadVideo(MultipartFile file, String title, String description) throws IOException {
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);

        // Convierte el archivo MultipartFile en bytes y almacénalo en la entidad Video.
        video.setVideoData(file.getBytes());

        // Guarda los detalles del video en la base de datos.
        videoRepository.save(video);
    }
}
