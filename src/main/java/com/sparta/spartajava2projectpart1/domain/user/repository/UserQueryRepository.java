package com.sparta.spartajava2projectpart1.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartajava2projectpart1.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.spartajava2projectpart1.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.spartajava2projectpart1.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<User> findUser(String name, String email, Pageable pageable) {
        List<User> users = queryFactory.select(user)
                .from(user)
                .join(purchase).on(user.eq(purchase.user))
                .where(
                        nameContains(name),
                        emailContains(email)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory.select(user.count())
                .from(user)
                .join(purchase).on(user.eq(purchase.user))
                .where(
                        nameContains(name),
                        emailContains(email)
                ).fetchOne();

        return new PageImpl<>(users, pageable, totalCount);
    }

    private BooleanExpression nameContains(String name) {
        return StringUtils.hasText(name) ? user.name.contains(name) : null;
    }

    private BooleanExpression emailContains(String email) {
        return StringUtils.hasText(email) ? user.email.contains(email) : null;
    }
}
