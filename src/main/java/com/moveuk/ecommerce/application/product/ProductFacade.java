    package com.moveuk.ecommerce.application.product;

    import com.moveuk.ecommerce.application.product.request.ProductInfo;
    import com.moveuk.ecommerce.application.product.response.ProductResult;
    import com.moveuk.ecommerce.domain.product.Product;
    import com.moveuk.ecommerce.domain.product.ProductInventory;
    import com.moveuk.ecommerce.domain.product.ProductService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.*;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class ProductFacade {

        private final ProductService productService;

        public Page<ProductResult.ProductRegisterV1> listProducts(ProductInfo.ProductRegisterV1 productRegisterV1) {
            Pageable pageable = PageRequest.of(productRegisterV1.page(), productRegisterV1.size(), Sort.by(Sort.Direction.fromString(productRegisterV1.sortBy().split(",")[1]), productRegisterV1.sortBy().split(",")[0]));
            Page<Product> products = productService.searchProducts(productRegisterV1.categoryId(), productRegisterV1.minPrice(),productRegisterV1.maxPrice(), pageable);
            List<ProductResult.ProductRegisterV1> productsList = products.getContent().stream()
                    .map(product -> {
                        long stock = productService.getProductInventory(product.getId()).getStock();
                        return ProductResult.ProductRegisterV1.from(product, stock);
                    })
                    .toList();
            
            return new PageImpl<>(productsList, pageable, products.getTotalElements());
        }

        public ProductResult.ProductRegisterV1 findByProductsId(long productId){
            Product product = productService.getProductById(productId);
            ProductInventory productInventory = productService.getProductInventory(product.getId());
            return ProductResult.ProductRegisterV1.from(product, productInventory.getStock());
        }



    }
