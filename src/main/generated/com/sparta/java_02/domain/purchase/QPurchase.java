package com.sparta.java_02.domain.purchase;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchase is a Querydsl query type for Purchase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchase extends EntityPathBase<Purchase> {

    private static final long serialVersionUID = -1717636999L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchase purchase = new QPurchase("purchase");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath deliveryAddress = createString("deliveryAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orderNotes = createString("orderNotes");

    public final StringPath paymentMethod = createString("paymentMethod");

    public final com.sparta.java_02.domain.product.QProduct product;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final com.sparta.java_02.domain.refund.QRefund refund;

    public final EnumPath<PurchaseStatus> status = createEnum("status", PurchaseStatus.class);

    public final NumberPath<java.math.BigDecimal> totalAmount = createNumber("totalAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> unitPrice = createNumber("unitPrice", java.math.BigDecimal.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.sparta.java_02.domain.user.QUser user;

    public QPurchase(String variable) {
        this(Purchase.class, forVariable(variable), INITS);
    }

    public QPurchase(Path<? extends Purchase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchase(PathMetadata metadata, PathInits inits) {
        this(Purchase.class, metadata, inits);
    }

    public QPurchase(Class<? extends Purchase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.sparta.java_02.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.refund = inits.isInitialized("refund") ? new com.sparta.java_02.domain.refund.QRefund(forProperty("refund"), inits.get("refund")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.java_02.domain.user.QUser(forProperty("user")) : null;
    }

}

