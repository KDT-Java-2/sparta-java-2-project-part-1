package com.sparta.commerce.domain.product.service.image;

import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  ProductImage uploadFile(Product product, MultipartFile file, boolean isThumbnail, String basePath);
  String getFileUrl(String filePath);
  void deleteFile(String filePath);
}
