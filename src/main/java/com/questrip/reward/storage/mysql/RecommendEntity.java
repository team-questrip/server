package com.questrip.reward.storage.mysql;

import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recommend")
public class RecommendEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placeId;
    private Long userId;
    @Enumerated(value = EnumType.STRING)
    private Recommend.Status status;

    public static RecommendEntity from(Recommend recommend) {
        return RecommendEntity.builder()
                .placeId(recommend.getPlace().getId())
                .userId(recommend.getUserId())
                .status(recommend.getStatus())
                .build();
    }

    public Recommend toRecommend() {
        return Recommend.builder()
                .id(id)
                .userId(userId)
                .placeId(placeId)
                .status(status)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

    public void updateStatus(Recommend.Status status) {
        validateStatus();
        this.status = status;
    }

    private void validateStatus() {
        if(this.status != Recommend.Status.ACCEPTED) {
            throw new GlobalException(ErrorCode.CAN_NOT_UPDATE_STATUS);
        }
    }

    @Builder
    private RecommendEntity(Long id, String placeId, Long userId, Recommend.Status status) {
        this.id = id;
        this.placeId = placeId;
        this.userId = userId;
        this.status = status;
    }
}
