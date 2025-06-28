package com.sparta.part_1.domain.user.repository;

import static com.sparta.part_1.domain.user.entity.QUser.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Boolean hasSameUserEmail(String email) {
    int size = queryFactory.select(user)
        .from(user)
        .where(user.email.eq(email))
        .fetch().size();

    return size > 0;

  }
}
