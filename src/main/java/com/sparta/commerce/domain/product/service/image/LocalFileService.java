package com.sparta.commerce.domain.product.service.image;

import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.entity.ProductImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Profile("local")
@RequiredArgsConstructor
public class LocalFileService implements FileService{

  @Override
  public ProductImage uploadFile(Product product, MultipartFile file, boolean isThumbnail, String basePath) {
    createDirIfNotExists(basePath);

    String originalName = file.getOriginalFilename();
    String savedName = generateFileName(file);
    String filePath = basePath + "/" + savedName;

    try {
      file.transferTo(new File(getFileUrl(filePath)));
      ProductImage productImage = ProductImage.builder()
          .savedName(savedName)
          .originalName(originalName)
          .imageUrl(filePath)
          .isThumbnail(isThumbnail)
          .product(product)
          .build();
      return productImage;
    } catch (Exception e) {
      throw new RuntimeException("파일 저장 실패 : " + e);
    }
  }

  @Override
  public String getFileUrl(String filePath) {
    return filePath;
  }

  @Override
  public void deleteFile(String filePath) {
    Path path = Paths.get(filePath);
    try {
      Files.walk(path)
          .sorted(Comparator.reverseOrder()) // path에 속한 이미지들부터 삭제하고 난 다음에 디렉토리 삭제하도록
          .map(Path::toFile)
          .forEach(File::delete);
    } catch (IOException e) {
      throw new RuntimeException("파일 삭제 중 오류 발생: " + e.getMessage());
    }
  }

  private String generateFileName(MultipartFile file) {
    String originalName = file.getOriginalFilename();
    return UUID.randomUUID() + "-" + originalName;
  }

  private void createDirIfNotExists(String dirPath) {
    File file = new File(dirPath);

    if (!file.exists()) {
      boolean isCreated = file.mkdirs();
      if (!isCreated) {
        throw new RuntimeException(dirPath + " : 디렉토리 생성 실패");
      }
    }
  }
}
