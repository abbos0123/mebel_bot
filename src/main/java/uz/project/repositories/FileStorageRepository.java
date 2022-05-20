package uz.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.models.FileStorage;
import uz.project.models.FileStorageStatus;

import java.util.List;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {

    FileStorage findFileStorageByHashId(String hashId);

    List<FileStorage> findFileStorageByStorageStatus(FileStorageStatus fileStorageStatus);

    boolean existsFileStorageByHashId(String hashId);

    FileStorage findByHashId(String hashId);

}
