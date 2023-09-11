package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.impl;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Video;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.VideoRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void uploadVideo(MultipartFile file, String title, String description) throws IOException {
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);

        // Guardar el archivo en el sistema de archivos o almacenarlo en una base de datos BLOB, dependiendo de tus necesidades.
        // Ejemplo de almacenamiento en el sistema de archivos:
        // Files.write(Paths.get("directorio_de_almacenamiento", file.getOriginalFilename()), file.getBytes());

        // Guardar los detalles del video en la base de datos
        videoRepository.save(video);
    }
}
