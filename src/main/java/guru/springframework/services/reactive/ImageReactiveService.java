package guru.springframework.services.reactive;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by jt on 7/3/17.
 */
public interface ImageReactiveService {

    void saveImageFile(String recipeId, MultipartFile file);
}
