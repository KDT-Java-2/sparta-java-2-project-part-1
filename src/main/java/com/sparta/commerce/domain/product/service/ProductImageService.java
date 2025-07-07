package com.sparta.commerce.domain.product.service;

import com.sparta.commerce.domain.product.dto.image.ProductImageRequest;
import com.sparta.commerce.domain.product.dto.image.ProductImageResponse;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.entity.ProductImage;
import com.sparta.commerce.domain.product.mapper.ProductImageMapper;
import com.sparta.commerce.domain.product.repository.ProductImageRepository;
import com.sparta.commerce.domain.product.service.image.FileService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductImageService {
  private final ProductImageRepository productImageRepository;
  private final FileService fileService;
  private final ProductImageMapper productImageMapper;

  @Value("${local.file.upload-dir}")
  private String baseDir;

  public void saveProductImages(Product product, List<ProductImageRequest> productItemRequests, List<MultipartFile> files){
    uploadAndSaveImages(product, productItemRequests, files);
  }

  public List<ProductImageResponse> updateProductImages(Product product, List<ProductImageRequest> productItemRequests, List<MultipartFile> files) {
    productImageRepository.deleteByProduct(product);
    List<ProductImage> productImages = uploadAndSaveImages(product, productItemRequests, files);
    return productImageMapper.toResponses(productImages);
  }

  private List<ProductImage> uploadAndSaveImages(Product product, List<ProductImageRequest> requests, List<MultipartFile> files) {
    if (requests.size() != files.size()) {
      throw new RuntimeException("요청 수와 파일 수가 일치하지 않습니다.");
    }

    List<ProductImage> images = new ArrayList<>();
    for (int i = 0; i < files.size(); i++) {
      MultipartFile file = files.get(i);
      ProductImageRequest request = requests.get(i);

      ProductImage savedImage = fileService.uploadFile(
          product,
          file,
          request.isThumbnail(),
          generateProductImageUploadPath(product)
      );
      productImageRepository.save(savedImage);
      images.add(savedImage);
    }

    return images;
  }

  public List<ProductImageResponse> getImages(Product product) {
    List<ProductImage> images = productImageRepository.findByProduct(product);
    return productImageMapper.toResponses(images);
  }

  public Map<Long, ProductImageResponse> getThumbnailImages(List<Product> products){
    List<ProductImage> thumbnails = productImageRepository.findByProductInAndIsThumbnailTrue(products);
    List<ProductImageResponse> productImageResponses = productImageMapper.toResponses(thumbnails);

    return productImageResponses.stream().collect(Collectors.toMap(
        productImageResponse -> productImageResponse.getProductId(), Function.identity()));
  }

  public void delete(Product product){
    productImageRepository.deleteByProduct(product);
    fileService.deleteFile(generateProductImageUploadPath(product));
  }

  private String generateProductImageUploadPath(Product product){
    return baseDir + "/" + product.getId();
  }
}
