package com.questrip.reward.domain.place;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void 카테고리_번역_내용을_제공한다() {
        Category kdrama = Category.KDRAMA;
        assertThat(kdrama.getTranslation("KO")).isEqualTo("K-드라마");
    }

    @Test
    void 번역_언어가_없을_경우_영어로_제공한다() {
        Category kdrama = Category.KDRAMA;
        assertThat(kdrama.getTranslation("WRONG")).isEqualTo("K-drama");
    }

    @Test
    void 소문자로_요청이_들어올_경우_대문자로_변환해서_반환한다() {
        Category kdrama = Category.KDRAMA;
        assertThat(kdrama.getTranslation("ko")).isEqualTo("K-드라마");
    }

    @Test
    void eports는_SHOPPING_AND_ENTERTAINMENT_그룹에_포함된다() {
        Category esports = Category.ESPORTS;
        assertThat(CategoryGroup.findGroup(esports)).isEqualTo(CategoryGroup.SHOPPING_AND_ENTERTAINMENT);
    }
}