package com.sparta.java_02.domain.refund;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefund is a Querydsl query type for Refund
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefund extends EntityPathBase<Refund> {

    private static final long serialVersionUID = 688750937L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefund refund = new QRefund("refund");

    public final StringPath adminNotes = createString("adminNotes");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> processedAt = createDateTime("processedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> processedBy = createNumber("processedBy", Long.class);

    public final com.sparta.java_02.domain.purchase.QPurchase purchase;

    public final StringPath reason = createString("reason");

    public final NumberPath<java.math.BigDecimal> refundAmount = createNumber("refundAmount", java.math.BigDecimal.class);

    public final DateTimePath<java.time.LocalDateTime> requestedAt = createDateTime("requestedAt", java.time.LocalDateTime.class);

    public final EnumPath<RefundStatus> status = createEnum("status", RefundStatus.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QRefund(String variable) {
        this(Refund.class, forVariable(variable), INITS);
    }

    public QRefund(Path<? extends Refund> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefund(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefund(PathMetadata metadata, PathInits inits) {
        this(Refund.class, metadata, inits);
    }

    public QRefund(Class<? extends Refund> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.purchase = inits.isInitialized("purchase") ? new com.sparta.java_02.domain.purchase.QPurchase(forProperty("purchase"), inits.get("purchase")) : null;
    }

}

