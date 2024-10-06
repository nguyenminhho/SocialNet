package com.example.DACN.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.example.DACN.Entity.Image;
import com.example.DACN.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;



    public List<Image> uploadImages(List<MultipartFile> files) throws IOException {


        List<Image> uploadResults = new ArrayList<>();

            for (MultipartFile file : files) {

                Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String publicId = (String) uploadResult.get("public_id");
                String url = (String) uploadResult.get("url");
                String secureUrl = (String) uploadResult.get("secure_url");


                Image image = new Image();
                image.setPublicId(publicId);
                image.setUrl(url);
                image.setSecureUrl(secureUrl);


                uploadResults.add(image);

                  imageRepository.save(image);

            }

            return uploadResults;


    }

    public Image uploadImage(MultipartFile file) throws IOException {

        Image uploadResults = new Image();
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String publicId = (String) uploadResult.get("public_id");
            String url = (String) uploadResult.get("url");
            String secureUrl = (String) uploadResult.get("secure_url");

            Image image = new Image();
            image.setPublicId(publicId);
            image.setUrl(url);
            image.setSecureUrl(secureUrl);

        return imageRepository.save(image);


    }

    public Image uploadVideo(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "video"));
        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");
        String secureUrl = (String) uploadResult.get("secure_url");

        Image video = new Image();
        video.setPublicId(publicId);
        video.setUrl(url);
        video.setSecureUrl(secureUrl);

        return imageRepository.save(video);
    }
}
