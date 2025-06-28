package com.sparta.java_02.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1318512583L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.sparta.java_02.domain.cart.Cart, com.sparta.java_02.domain.cart.QCart> carts = this.<com.sparta.java_02.domain.cart.Cart, com.sparta.java_02.domain.cart.QCart>createList("carts", com.sparta.java_02.domain.cart.Cart.class, com.sparta.java_02.domain.cart.QCart.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<com.sparta.java_02.domain.purchase.Purchase, com.sparta.java_02.domain.purchase.QPurchase> purchases = this.<com.sparta.java_02.domain.purchase.Purchase, com.sparta.java_02.domain.purchase.QPurchase>createList("purchases", com.sparta.java_02.domain.purchase.Purchase.class, com.sparta.java_02.domain.purchase.QPurchase.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

