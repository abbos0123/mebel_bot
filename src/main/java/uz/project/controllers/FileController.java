package uz.project.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.project.models.FileStorage;
import uz.project.services.FileService;
import uz.project.utilds.CustomResponse;

import javax.ws.rs.NotFoundException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.InputMismatchException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    @Value("${upload.folder}")
    private String uploadFolder;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadAnyFile(@RequestParam("file") MultipartFile multipartFile) throws Exception{

        if (multipartFile.isEmpty())
            throw new Exception("You dont select any file. Please select one!");

        var file = fileService.save(multipartFile);
        return ResponseEntity.ok(file);
    }


    @PostMapping("/upload/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartFile) throws InputMismatchException, Exception{
        if (multipartFile.isEmpty())
            throw new Exception("You dont select any file. Please select one!");

        if (!"image/png".equals(multipartFile.getContentType()) && !"image/jpeg".equals(multipartFile.getContentType())) {
          throw new InputMismatchException("Please enter only png and jpeg images!");
        }

        var file = fileService.save(multipartFile);
        return ResponseEntity.ok(file);
    }


    @GetMapping("/preview/{hashId}")
    public ResponseEntity<?> previewFile(@PathVariable String hashId) throws MalformedURLException, NotFoundException {
        FileStorage fileStorage = fileService.findByHashId(hashId);

        if (fileStorage == null)
            throw new NotFoundException("File could not be found!");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"" + URLEncoder.encode(fileStorage.getName()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath())));
    }


    @GetMapping("/download/{hashId}")
    public ResponseEntity<?> downloadFile( @PathVariable String hashId) throws NotFoundException {

        try {
            FileStorage fileStorage = fileService.findByHashId(hashId);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + URLEncoder.encode(fileStorage.getName()))
                    .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                    .body(new FileUrlResource(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath())));

        } catch (Exception e) {
           throw new NotFoundException("File could not be found!");
        }
    }


    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity<?> deleteFile(@PathVariable String hashId) throws Exception{
        try {
            fileService.deleteFile(hashId);
            return ResponseEntity.ok(new CustomResponse(HttpStatus.OK.value(), "File is deleted."));
        } catch (Exception e) {
             throw new Exception(e.getMessage());
        }
    }
}
