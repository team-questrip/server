package com.questrip.reward.storage.mysql;

import com.questrip.reward.domain.user.UserPreference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_preference")
public class UserPreferenceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int groupSize;
    @Column(nullable = false)
    private boolean travelingWithKid;
    @Column(nullable = false)
    private boolean vegetarian;
    @Column(nullable = false)
    private boolean nonAlcoholic;
    @Column(nullable = false)
    private boolean seafoodRestrictions;
    @Column(nullable = false)
    private boolean noSpicyFood;
    @Column(nullable = false)
    private int distance;
    private Long userId;

    public static UserPreferenceEntity from(UserPreference userPreference) {
        return UserPreferenceEntity.builder()
                .userId(userPreference.getUserId())
                .groupSize(userPreference.getGroupSize())
                .travelingWithKid(userPreference.isTravelingWithKid())
                .vegetarian(userPreference.getDietary().isVegetarian())
                .nonAlcoholic(userPreference.getDietary().isNonAlcoholic())
                .seafoodRestrictions(userPreference.getDietary().isSeafoodRestrictions())
                .noSpicyFood(userPreference.getDietary().isNoSpicyFood())
                .distance(userPreference.getDistance())
                .build();
    }

    public UserPreference toUserPreference() {
        return UserPreference.builder()
                .userId(userId)
                .groupSize(groupSize)
                .travelingWithKid(travelingWithKid)
                .vegetarian(vegetarian)
                .nonAlcoholic(nonAlcoholic)
                .seafoodRestrictions(seafoodRestrictions)
                .noSpicyFood(noSpicyFood)
                .distance(distance)
                .build();
    }

    @Builder
    public UserPreferenceEntity(Long id, int groupSize, boolean travelingWithKid, boolean vegetarian, boolean nonAlcoholic, boolean seafoodRestrictions, boolean noSpicyFood, int distance, Long userId) {
        this.id = id;
        this.groupSize = groupSize;
        this.travelingWithKid = travelingWithKid;
        this.vegetarian = vegetarian;
        this.nonAlcoholic = nonAlcoholic;
        this.seafoodRestrictions = seafoodRestrictions;
        this.noSpicyFood = noSpicyFood;
        this.distance = distance;
        this.userId = userId;
    }
}
