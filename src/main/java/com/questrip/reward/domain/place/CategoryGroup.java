package com.questrip.reward.domain.place;

import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public enum CategoryGroup {
    FOOD_AND_DRINKS(
            Map.ofEntries(
                    Map.entry("EN", "Food & Drinks"),
                    Map.entry("KO", "음식 & 음료"),
                    Map.entry("JA", "飲食"),
                    Map.entry("ZH", "美食与饮品"),
                    Map.entry("DE", "Essen & Getränke"),
                    Map.entry("ES", "Comida y Bebidas"),
                    Map.entry("FR", "Nourriture et Boissons"),
                    Map.entry("IT", "Cibo e Bevande"),
                    Map.entry("NL", "Eten en Drinken"),
                    Map.entry("PT", "Comida e Bebidas"),
                    Map.entry("RU", "Еда и напитки")
            ),
            Category.RESTAURANT, Category.CAFE_AND_DESSERT, Category.BAR, Category.STREET_FOOD, Category.TRADITIONAL_FOOD
    ),
    CULTURE_AND_HISTORY(
            Map.ofEntries(
                    Map.entry("EN", "Culture & History"),
                    Map.entry("KO", "문화 & 역사"),
                    Map.entry("JA", "文化と歴史"),
                    Map.entry("ZH", "文化与历史"),
                    Map.entry("DE", "Kultur & Geschichte"),
                    Map.entry("ES", "Cultura e Historia"),
                    Map.entry("FR", "Culture et Histoire"),
                    Map.entry("IT", "Cultura e Storia"),
                    Map.entry("NL", "Cultuur en Geschiedenis"),
                    Map.entry("PT", "Cultura e História"),
                    Map.entry("RU", "Культура и история")
            ),
            Category.CULTURAL_SITE, Category.HISTORICAL_SITE, Category.LOCAL_FESTIVAL_AND_EVENT
    ),
    NATURE_AND_LANDMARKS(
            Map.ofEntries(
                    Map.entry("EN", "Nature & Landmarks"),
                    Map.entry("KO", "자연 & 랜드마크"),
                    Map.entry("JA", "自然とランドマーク"),
                    Map.entry("ZH", "自然与地标"),
                    Map.entry("DE", "Natur & Sehenswürdigkeiten"),
                    Map.entry("ES", "Naturaleza y Monumentos"),
                    Map.entry("FR", "Nature et Monuments"),
                    Map.entry("IT", "Natura e Monumenti"),
                    Map.entry("NL", "Natuur en Bezienswaardigheden"),
                    Map.entry("PT", "Natureza e Pontos Turísticos"),
                    Map.entry("RU", "Природа и достопримечательности")
            ),
            Category.MOUNTAIN, Category.RIVER, Category.SEA, Category.WATERFALL, Category.PARK, Category.NIGHT_VIEW
    ),
    SHOPPING_AND_ENTERTAINMENT(
            Map.ofEntries(
                    Map.entry("EN", "Shopping & Entertainment"),
                    Map.entry("KO", "쇼핑 & 엔터테인먼트"),
                    Map.entry("JA", "ショッピングとエンターテイメント"),
                    Map.entry("ZH", "购物与娱乐"),
                    Map.entry("DE", "Einkaufen & Unterhaltung"),
                    Map.entry("ES", "Compras y Entretenimiento"),
                    Map.entry("FR", "Shopping et Divertissement"),
                    Map.entry("IT", "Shopping e Intrattenimento"),
                    Map.entry("NL", "Winkelen en Entertainment"),
                    Map.entry("PT", "Compras e Entretenimento"),
                    Map.entry("RU", "Шоппинг и развлечения")
            ),
            Category.SHOPPING_MALL, Category.MART, Category.DEPARTMENT_STORE, Category.KPOP, Category.KDRAMA, Category.ART_AND_EXHIBITION, Category.ESPORTS
    ),
    WELLNESS_AND_RELAXATION(
            Map.ofEntries(
                    Map.entry("EN", "Wellness & Relaxation"),
                    Map.entry("KO", "웰니스 & 휴식"),
                    Map.entry("JA", "ウェルネスとリラクゼーション"),
                    Map.entry("ZH", "健康与放松"),
                    Map.entry("DE", "Wellness & Entspannung"),
                    Map.entry("ES", "Bienestar y Relajación"),
                    Map.entry("FR", "Bien-être et Relaxation"),
                    Map.entry("IT", "Benessere e Relax"),
                    Map.entry("NL", "Wellness en Ontspanning"),
                    Map.entry("PT", "Bem-estar e Relaxamento"),
                    Map.entry("RU", "Здоровье и релаксация")
            ),
            Category.JJIMJILBANG, Category.MASSAGE, Category.HAIR_SALON, Category.MAKEUP
    ),
    DAY_TOURS_AND_ACTIVITIES(
            Map.ofEntries(
                    Map.entry("EN", "Day Tours & Activities"),
                    Map.entry("KO", "데이투어 & 활동"),
                    Map.entry("JA", "日帰りツアーとアクティビティ"),
                    Map.entry("ZH", "一日游与活动"),
                    Map.entry("DE", "Tagesausflüge & Aktivitäten"),
                    Map.entry("ES", "Tours de Un Día y Actividades"),
                    Map.entry("FR", "Excursions d'Une Journée et Activités"),
                    Map.entry("IT", "Tour Giornalieri e Attività"),
                    Map.entry("NL", "Dagexcursies en Activiteiten"),
                    Map.entry("PT", "Passeios de Um Dia e Atividades"),
                    Map.entry("RU", "Однодневные туры и мероприятия")
            ),
            Category.ONE_DAY_CLASS, Category.DAY_TOUR
    );

    private final Map<String, String> translations;
    private final Set<Category> categories;

    CategoryGroup(Map<String, String> translations, Category... categories) {
        this.translations = translations;
        this.categories = new HashSet<>(Arrays.asList(categories));
    }

    public String getTranslation(String languageCode) {
        return translations.getOrDefault(languageCode, translations.get("EN"));
    }

    public static CategoryGroup findGroup(Category category) {
        return Arrays.stream(CategoryGroup.values())
                .filter(group -> group.categories.contains(category))
                .findFirst()
                .orElseThrow(() -> new GlobalException(ErrorCode.CATEGORY_NOT_FOUND));
    }
}