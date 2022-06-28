package com.binar.kelompok3.secondhand.service.cloudinary;

import com.binar.kelompok3.secondhand.model.entity.ImageProduct;
import com.binar.kelompok3.secondhand.model.entity.Products;
import com.binar.kelompok3.secondhand.model.entity.Users;
import com.binar.kelompok3.secondhand.repository.ImageProductRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryServiceImpl implements ICloudinaryService {

    private Cloudinary cloudinary;


    @Override
    public String uploadFile(MultipartFile image) {
        try {
            File uploadedFile = convertMultiPartToFile(image);
            Map params = ObjectUtils.asMap(
                    "folder", "secondhand/"
            );
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, params);
            boolean isDeleted = uploadedFile.delete();
            if (isDeleted) {
                System.out.println("File is successfully deleted");
            } else
                System.out.println("File doesn't exist");
            return uploadResult.get("url").toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
