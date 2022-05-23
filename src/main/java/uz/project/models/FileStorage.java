package uz.project.models;

import javax.persistence.*;

@Entity
@Table(name = "all_files")
public class FileStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "hash_id")
    private String hashId;

    @Column(name = "upload_path")
    private String uploadPath;

    @Column(name = "content_type")
    private String contentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "storage_status")
    private FileStorageStatus storageStatus;

    public FileStorage() {
    }

    public FileStorage(String name, String extension, Long fileSize, String hashId, String uploadPath, String contentType, FileStorageStatus storageStatus) {
        this.name = name;
        this.extension = extension;
        this.fileSize = fileSize;
        this.hashId = hashId;
        this.uploadPath = uploadPath;
        this.contentType = contentType;
        this.storageStatus = storageStatus;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getHashId() {
        return hashId;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public String getContentType() {
        return contentType;
    }

    public FileStorageStatus getStorageStatus() {
        return storageStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setStorageStatus(FileStorageStatus storageStatus) {
        this.storageStatus = storageStatus;
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", fileSize=" + fileSize +
                ", hashId='" + hashId + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                ", contentType='" + contentType + '\'' +
                ", storageStatus=" + storageStatus +
                '}';
    }
}
