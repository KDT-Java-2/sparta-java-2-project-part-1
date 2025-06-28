package com.sparta.java_02.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1974455653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final ListPath<com.sparta.java_02.domain.cart.Cart, com.sparta.java_02.domain.cart.QCart> carts = this.<com.sparta.java_02.domain.cart.Cart, com.sparta.java_02.domain.cart.QCart>createList("carts", com.sparta.java_02.domain.cart.Cart.class, com.sparta.java_02.domain.cart.QCart.class, PathInits.DIRECT2);

    public final com.sparta.java_02.domain.category.QCategory category;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final ListPath<com.sparta.java_02.domain.purchase.Purchase, com.sparta.java_02.domain.purchase.QPurchase> purchases = this.<com.sparta.java_02.domain.purchase.Purchase, com.sparta.java_02.domain.purchase.QPurchase>createList("purchases", com.sparta.java_02.domain.purchase.Purchase.class, com.sparta.java_02.domain.purchase.QPurchase.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.sparta.java_02.domain.category.QCategory(forProperty("category"), inits.get("category")) : null;
    }

}

