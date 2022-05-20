package uz.project.services;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.project.models.FileStorage;
import uz.project.models.FileStorageStatus;
import uz.project.repositories.FileStorageRepository;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {


    @Value("${upload.folder}")
    private String uploadFolder;

    private final Hashids hashids;

    private final FileStorageRepository fileStorageRepository;

    public FileService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName(), 6);
    }

    public FileStorage save(MultipartFile multipartFile) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setStorageStatus(FileStorageStatus.ACTIVE);
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorageRepository.save(fileStorage);

        File uploadFolder = new File(String.format("%s/upload_files", this.uploadFolder));

        if (!uploadFolder.exists() && uploadFolder.mkdirs()) {
            System.out.println("Mentioned file is created !");
        }

        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadPath(String.format("upload_files/%s.%s"
                , fileStorage.getHashId()
                , fileStorage.getExtension()));

        fileStorageRepository.save(fileStorage);
        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getExtension()));
        try {
            multipartFile.transferTo(file);
            return fileStorage;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Transactional(readOnly = true)
    public FileStorage findByHashId(String hashID) {
        return fileStorageRepository.findFileStorageByHashId(hashID);
    }

    public void deleteFile(String hashId) throws Exception {
        var fileStorage = findByHashId(hashId);
        var file = new File(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath()));
        if (file.delete()) {
            fileStorageRepository.delete(fileStorage);
        } else {
            throw new Exception("This file can not deleted!");
        }
    }

    private String getExt(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2)
                ext = fileName.substring(dot + 1);
        }
        return ext;
    }


    public boolean existsByHashId(String hashId) {
        return fileStorageRepository.existsFileStorageByHashId(hashId);
    }

    @Scheduled(cron = "00 00 00 * * *")
    public void deleteAllDraft() {
        var list = fileStorageRepository.findFileStorageByStorageStatus(FileStorageStatus.ACTIVE);
        for (FileStorage fileStorage : list) {
            try {
                clearMemory(fileStorage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void clearMemory(FileStorage fileStorage) {
        try {
            var path = fileStorage.getUploadPath();
            fileStorageRepository.delete(fileStorage);
            deleteGarbage(path);
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    public void deleteGarbage(String path) throws Exception {
        var file = new File(String.format("%s/%s", uploadFolder, path));
        file.delete();
    }
}
