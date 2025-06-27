package com.sparta.java2.project.part1.commerce.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java2.project.part1.commerce.domain.user.entity.QUser;
import com.sparta.java2.project.part1.commerce.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<User> findByNameAndEmail(String name, String email, Pageable pageable) {
        List<User> users = jpaQueryFactory
                .selectFrom(QUser.user)
                .where(
                        nameContains(name),
                        emailContains(email)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = jpaQueryFactory
                .select(QUser.user.count())
                .from(QUser.user)
                .where(
                        nameContains(name),
                        emailContains(email)
                )
                .fetchOne();

        return new PageImpl<>(users, pageable, totalCount);
    }

    private BooleanExpression nameContains(String name) {
        return name == null ? null : QUser.user.name.contains(name);
    }

    private BooleanExpression emailContains(String email) {
        return email == null ? null : QUser.user.email.contains(email);
    }
}
