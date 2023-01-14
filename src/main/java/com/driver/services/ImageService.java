package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image(description,dimensions);

        image.setBlog(blog);

        List<Image> imageList = blog.getImageList();

        imageList.add(image);

        blog.setImageList(imageList);

        blogRepository2.save(blog);
        imageRepository2.save(image);

        return image;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        if(screenDimensions.split("X").length==2 && image!=null) {
            String imageLength = "";
            imageLength = imageLength + image.getDimensions().charAt(0) + image.getDimensions().charAt(1);

            String screenLength = "";
            screenLength = screenLength + screenDimensions.charAt(0) + screenDimensions.charAt(1);

            int length = Integer.parseInt(screenLength) / Integer.parseInt(imageLength);

            String imagebreadth = "";
            imagebreadth = imagebreadth + image.getDimensions().charAt(3) + image.getDimensions().charAt(4);

            String screenbreadth = "";
            screenbreadth = screenbreadth + screenDimensions.charAt(3) + screenDimensions.charAt(4);

            int breadth = Integer.parseInt(screenbreadth) / Integer.parseInt(imagebreadth);
            return length * breadth;

        }
       return 0;
    }
}
