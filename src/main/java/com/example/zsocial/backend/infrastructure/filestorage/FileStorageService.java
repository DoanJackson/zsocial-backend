package com.example.zsocial.backend.infrastructure.filestorage;

import com.example.zsocial.backend.infrastructure.filestorage.dto.UploadFileResult;
import com.example.zsocial.backend.media.model.enums.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    UploadFileResult uploadFile(MultipartFile file, String destinationPath, MediaType mediaType);

    void deleteFile(String cloudName);

    void deleteFiles(List<String> cloudNames);

}
