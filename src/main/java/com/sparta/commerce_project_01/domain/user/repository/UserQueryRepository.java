package com.sparta.commerce_project_01.domain.user.repository;

// static  선언하면 QUser  생략 가능

import static com.sparta.commerce_project_01.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.commerce_project_01.domain.user.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce_project_01.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Page<User> findUsers(String name, String email, Pageable pageable) {
    List<User> users = queryFactory.select(user)
        .from(user)
//        .join(purchase).on(user.id.eq(purchase.user.id))
//                .join(purchase).on(user.eq(purchase.user), user.createdAt.after(LocalDateTime.now()))
        .join(purchase).on(user.eq(purchase.user), user.createdAt.after(LocalDateTime.now()))
        .where(
//            user.name.eq(name).or(user.email.eq(email)),
//            user.name.contains(name),
//            user.email.eq(email)
            nameContains(name),
            emailContains(email)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = queryFactory.select(user.count())
        .from(user)
        .join(purchase).on(user.eq(purchase.user), user.createdAt.after(LocalDateTime.now()))
        .where(
//            user.name.eq(name).or(user.email.eq(email)),
//            user.name.contains(name),
//            user.email.eq(email)
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
