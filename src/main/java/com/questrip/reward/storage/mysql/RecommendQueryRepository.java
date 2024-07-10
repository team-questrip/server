package com.questrip.reward.storage.mysql;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.questrip.reward.domain.recommend.Recommend;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.questrip.reward.storage.mysql.QRecommendEntity.recommendEntity;

@Repository
@RequiredArgsConstructor
public class RecommendQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<String> getExcludePlaceId(Long userId, LocalDateTime start, LocalDateTime end) {
        return jpaQueryFactory.select(recommendEntity.placeId).distinct()
                .from(recommendEntity)
                .where(cond(userId, start, end))
                .fetch();
    }

    private BooleanExpression cond(Long userId, LocalDateTime start, LocalDateTime end) {
        return recommendEntity.userId.eq(userId)
                .and(recommendEntity.status.eq(Recommend.Status.DENIED))
                .or(recommendEntity.placeId.in(getTodayCreatedRecommendPlace(userId, start, end)));
    }

    private List<String> getTodayCreatedRecommendPlace(Long userId, LocalDateTime start, LocalDateTime end) {
        return jpaQueryFactory.select(recommendEntity.placeId)
                .from(recommendEntity)
                .where(recommendEntity.userId.eq(userId))
                .where(recommendEntity.createdAt.between(start, end))
                .fetch();
    }

    public Slice<RecommendEntity> findAllRecommendStatus(Long userId, Pageable pageable, List<Recommend.Status> status) {
        List<RecommendEntity> content = jpaQueryFactory.select(recommendEntity)
                .from(recommendEntity)
                .where(recommendEntity.userId.eq(userId).and(recommendEntity.status.in(status)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .orderBy(recommendEntity.createdAt.desc())
                .fetch();

        return new SliceImpl<>(content, pageable, hasNextPage(content, pageable.getPageSize()));
    }

    private boolean hasNextPage(List<RecommendEntity> contents, int pageSize) {
        if (contents.size() > pageSize) {
            contents.remove(pageSize);
            return true;
        }
        return false;
    }
}
