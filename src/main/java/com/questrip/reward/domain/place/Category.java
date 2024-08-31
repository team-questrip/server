package com.questrip.reward.domain.place;

import java.util.Map;

public enum Category {
    // Food & Drinks
    RESTAURANT(Map.ofEntries(
            Map.entry("EN", "Restaurant"),
            Map.entry("KO", "음식점"),
            Map.entry("JA", "レストラン"),
            Map.entry("ZH", "餐厅"),
            Map.entry("DE", "Restaurant"),
            Map.entry("ES", "Restaurante"),
            Map.entry("FR", "Restaurant"),
            Map.entry("IT", "Ristorante"),
            Map.entry("NL", "Restaurant"),
            Map.entry("PT", "Restaurante"),
            Map.entry("RU", "Ресторан")
    )),
    CAFE_AND_DESSERT(Map.ofEntries(
            Map.entry("EN", "Cafe & Dessert"),
            Map.entry("KO", "카페 & 디저트"),
            Map.entry("JA", "カフェ＆デザート"),
            Map.entry("ZH", "咖啡和甜点"),
            Map.entry("DE", "Café & Dessert"),
            Map.entry("ES", "Café y Postre"),
            Map.entry("FR", "Café et Dessert"),
            Map.entry("IT", "Caffè e Dessert"),
            Map.entry("NL", "Café en Dessert"),
            Map.entry("PT", "Café e Sobremesa"),
            Map.entry("RU", "Кафе и Десерты")
    )),
    BAR(Map.ofEntries(
            Map.entry("EN", "Bar"),
            Map.entry("KO", "술집"),
            Map.entry("JA", "バー"),
            Map.entry("ZH", "酒吧"),
            Map.entry("DE", "Bar"),
            Map.entry("ES", "Bar"),
            Map.entry("FR", "Bar"),
            Map.entry("IT", "Bar"),
            Map.entry("NL", "Bar"),
            Map.entry("PT", "Bar"),
            Map.entry("RU", "Бар")
    )),
    STREET_FOOD(Map.ofEntries(
            Map.entry("EN", "Street Food"),
            Map.entry("KO", "길거리 음식"),
            Map.entry("JA", "屋台料理"),
            Map.entry("ZH", "街头小吃"),
            Map.entry("DE", "Straßenessen"),
            Map.entry("ES", "Comida Callejera"),
            Map.entry("FR", "Cuisine de Rue"),
            Map.entry("IT", "Cibo di Strada"),
            Map.entry("NL", "Straatvoedsel"),
            Map.entry("PT", "Comida de Rua"),
            Map.entry("RU", "Уличная еда")
    )),
    TRADITIONAL_FOOD(Map.ofEntries(
            Map.entry("EN", "Traditional Food"),
            Map.entry("KO", "전통 음식"),
            Map.entry("JA", "伝統料理"),
            Map.entry("ZH", "传统美食"),
            Map.entry("DE", "Traditionelles Essen"),
            Map.entry("ES", "Comida Tradicional"),
            Map.entry("FR", "Cuisine Traditionnelle"),
            Map.entry("IT", "Cibo Tradizionale"),
            Map.entry("NL", "Traditioneel Eten"),
            Map.entry("PT", "Comida Tradicional"),
            Map.entry("RU", "Традиционная кухня")
    )),

    // Culture & History
    CULTURAL_SITE(Map.ofEntries(
            Map.entry("EN", "Cultural Site"),
            Map.entry("KO", "문화유적지"),
            Map.entry("JA", "文化遺産"),
            Map.entry("ZH", "文化遗址"),
            Map.entry("DE", "Kulturstätte"),
            Map.entry("ES", "Sitio Cultural"),
            Map.entry("FR", "Site Culturel"),
            Map.entry("IT", "Sito Culturale"),
            Map.entry("NL", "Culturele Plaats"),
            Map.entry("PT", "Sítio Cultural"),
            Map.entry("RU", "Культурный объект")
    )),
    HISTORICAL_SITE(Map.ofEntries(
            Map.entry("EN", "Historical Site"),
            Map.entry("KO", "역사유적지"),
            Map.entry("JA", "歴史的遺跡"),
            Map.entry("ZH", "历史遗址"),
            Map.entry("DE", "Historische Stätte"),
            Map.entry("ES", "Sitio Histórico"),
            Map.entry("FR", "Site Historique"),
            Map.entry("IT", "Sito Storico"),
            Map.entry("NL", "Historische Plaats"),
            Map.entry("PT", "Sítio Histórico"),
            Map.entry("RU", "Исторический объект")
    )),
    LOCAL_FESTIVAL_AND_EVENT(Map.ofEntries(
            Map.entry("EN", "Local Festival & Event"),
            Map.entry("KO", "지역 축제 및 이벤트"),
            Map.entry("JA", "地域の祭りとイベント"),
            Map.entry("ZH", "本地节日和活动"),
            Map.entry("DE", "Lokales Festival & Event"),
            Map.entry("ES", "Festival y Evento Local"),
            Map.entry("FR", "Festival et Événement Local"),
            Map.entry("IT", "Festival ed Eventi Locali"),
            Map.entry("NL", "Lokaal Festival en Evenement"),
            Map.entry("PT", "Festival e Evento Local"),
            Map.entry("RU", "Местный фестиваль и мероприятие")
    )),

    // Nature & Landmarks
    MOUNTAIN(Map.ofEntries(
            Map.entry("EN", "Mountain"),
            Map.entry("KO", "산"),
            Map.entry("JA", "山"),
            Map.entry("ZH", "山"),
            Map.entry("DE", "Berg"),
            Map.entry("ES", "Montaña"),
            Map.entry("FR", "Montagne"),
            Map.entry("IT", "Montagna"),
            Map.entry("NL", "Berg"),
            Map.entry("PT", "Montanha"),
            Map.entry("RU", "Гора")
    )),
    RIVER(Map.ofEntries(
            Map.entry("EN", "River"),
            Map.entry("KO", "강"),
            Map.entry("JA", "川"),
            Map.entry("ZH", "河流"),
            Map.entry("DE", "Fluss"),
            Map.entry("ES", "Río"),
            Map.entry("FR", "Rivière"),
            Map.entry("IT", "Fiume"),
            Map.entry("NL", "Rivier"),
            Map.entry("PT", "Rio"),
            Map.entry("RU", "Река")
    )),
    SEA(Map.ofEntries(
            Map.entry("EN", "Sea"),
            Map.entry("KO", "바다"),
            Map.entry("JA", "海"),
            Map.entry("ZH", "海"),
            Map.entry("DE", "Meer"),
            Map.entry("ES", "Mar"),
            Map.entry("FR", "Mer"),
            Map.entry("IT", "Mare"),
            Map.entry("NL", "Zee"),
            Map.entry("PT", "Mar"),
            Map.entry("RU", "Море")
    )),
    WATERFALL(Map.ofEntries(
            Map.entry("EN", "Waterfall"),
            Map.entry("KO", "폭포"),
            Map.entry("JA", "滝"),
            Map.entry("ZH", "瀑布"),
            Map.entry("DE", "Wasserfall"),
            Map.entry("ES", "Cascada"),
            Map.entry("FR", "Cascade"),
            Map.entry("IT", "Cascata"),
            Map.entry("NL", "Waterval"),
            Map.entry("PT", "Cachoeira"),
            Map.entry("RU", "Водопад")
    )),
    PARK(Map.ofEntries(
            Map.entry("EN", "Park"),
            Map.entry("KO", "공원"),
            Map.entry("JA", "公園"),
            Map.entry("ZH", "公园"),
            Map.entry("DE", "Park"),
            Map.entry("ES", "Parque"),
            Map.entry("FR", "Parc"),
            Map.entry("IT", "Parco"),
            Map.entry("NL", "Park"),
            Map.entry("PT", "Parque"),
            Map.entry("RU", "Парк")
    )),
    NIGHT_VIEW(Map.ofEntries(
            Map.entry("EN", "Night View"),
            Map.entry("KO", "야경"),
            Map.entry("JA", "夜景"),
            Map.entry("ZH", "夜景"),
            Map.entry("DE", "Nachtansicht"),
            Map.entry("ES", "Vista Nocturna"),
            Map.entry("FR", "Vue de Nuit"),
            Map.entry("IT", "Vista Notturna"),
            Map.entry("NL", "Nachtelijk Uitzicht"),
            Map.entry("PT", "Vista Noturna"),
            Map.entry("RU", "Ночной вид")
    )),

    // Shopping & Entertainment
    SHOPPING_MALL(Map.ofEntries(
            Map.entry("EN", "Shopping Mall"),
            Map.entry("KO", "쇼핑몰"),
            Map.entry("JA", "ショッピングモール"),
            Map.entry("ZH", "购物中心"),
            Map.entry("DE", "Einkaufszentrum"),
            Map.entry("ES", "Centro Comercial"),
            Map.entry("FR", "Centre Commercial"),
            Map.entry("IT", "Centro Commerciale"),
            Map.entry("NL", "Winkelcentrum"),
            Map.entry("PT", "Shopping"),
            Map.entry("RU", "Торговый центр")
    )),
    MART(Map.ofEntries(
            Map.entry("EN", "Mart"),
            Map.entry("KO", "마트"),
            Map.entry("JA", "マート"),
            Map.entry("ZH", "超市"),
            Map.entry("DE", "Supermarkt"),
            Map.entry("ES", "Supermercado"),
            Map.entry("FR", "Supermarché"),
            Map.entry("IT", "Supermercato"),
            Map.entry("NL", "Supermarkt"),
            Map.entry("PT", "Supermercado"),
            Map.entry("RU", "Супермаркет")
    )),
    DEPARTMENT_STORE(Map.ofEntries(
            Map.entry("EN", "Department Store"),
            Map.entry("KO", "백화점"),
            Map.entry("JA", "デパート"),
            Map.entry("ZH", "百货商店"),
            Map.entry("DE", "Kaufhaus"),
            Map.entry("ES", "Grandes Almacenes"),
            Map.entry("FR", "Grand Magasin"),
            Map.entry("IT", "Grande Magazzino"),
            Map.entry("NL", "Warenhuis"),
            Map.entry("PT", "Loja de Departamentos"),
            Map.entry("RU", "Универмаг")
    )),
    KPOP(Map.ofEntries(
            Map.entry("EN", "K-pop"),
            Map.entry("KO", "K-pop"),
            Map.entry("JA", "K-POP"),
            Map.entry("ZH", "韩流音乐"),
            Map.entry("DE", "K-Pop"),
            Map.entry("ES", "K-pop"),
            Map.entry("FR", "K-pop"),
            Map.entry("IT", "K-pop"),
            Map.entry("NL", "K-pop"),
            Map.entry("PT", "K-pop"),
            Map.entry("RU", "K-pop")
    )),
    KDRAMA(Map.ofEntries(
            Map.entry("EN", "K-drama"),
            Map.entry("KO", "K-드라마"),
            Map.entry("JA", "韓国ドラマ"),
            Map.entry("ZH", "韩剧"),
            Map.entry("DE", "K-Drama"),
            Map.entry("ES", "Drama Coreano"),
            Map.entry("FR", "Drama Coréen"),
            Map.entry("IT", "Drama Coreano"),
            Map.entry("NL", "Koreaanse Drama"),
            Map.entry("PT", "Dorama"),
            Map.entry("RU", "Корейская драма")
    )),
    ART_AND_EXHIBITION(Map.ofEntries(
            Map.entry("EN", "Art & Exhibition"),
            Map.entry("KO", "예술 & 전시"),
            Map.entry("JA", "アート＆展示"),
            Map.entry("ZH", "艺术与展览"),
            Map.entry("DE", "Kunst & Ausstellung"),
            Map.entry("ES", "Arte y Exposición"),
            Map.entry("FR", "Art et Exposition"),
            Map.entry("IT", "Arte e Mostra"),
            Map.entry("NL", "Kunst en Tentoonstelling"),
            Map.entry("PT", "Arte e Exposição"),
            Map.entry("RU", "Искусство и выставки")
    )),
    ESPORTS(Map.ofEntries(
            Map.entry("EN", "E-sports"),
            Map.entry("KO", "E-스포츠"),
            Map.entry("JA", "eスポーツ"),
            Map.entry("ZH", "电子竞技"),
            Map.entry("DE", "E-Sport"),
            Map.entry("ES", "Deportes Electrónicos"),
            Map.entry("FR", "E-sport"),
            Map.entry("IT", "E-sport"),
            Map.entry("NL", "E-sports"),
            Map.entry("PT", "E-sports"),
            Map.entry("RU", "Киберспорт")
    )),

    // Wellness & Relaxation
    JJIMJILBANG(Map.ofEntries(
            Map.entry("EN", "Jjimjilbang"),
            Map.entry("KO", "찜질방"),
            Map.entry("JA", "チムジルバン"),
            Map.entry("ZH", "汗蒸房"),
            Map.entry("DE", "Jjimjilbang"),
            Map.entry("ES", "Jjimjilbang"),
            Map.entry("FR", "Jjimjilbang"),
            Map.entry("IT", "Jjimjilbang"),
            Map.entry("NL", "Jjimjilbang"),
            Map.entry("PT", "Jjimjilbang"),
            Map.entry("RU", "Чимчильбан")
    )),
    MASSAGE(Map.ofEntries(
            Map.entry("EN", "Massage"),
            Map.entry("KO", "마사지"),
            Map.entry("JA", "マッサージ"),
            Map.entry("ZH", "按摩"),
            Map.entry("DE", "Massage"),
            Map.entry("ES", "Masaje"),
            Map.entry("FR", "Massage"),
            Map.entry("IT", "Massaggio"),
            Map.entry("NL", "Massage"),
            Map.entry("PT", "Massagem"),
            Map.entry("RU", "Массаж")
    )),
    HAIR_SALON(Map.ofEntries(
            Map.entry("EN", "Hair Salon"),
            Map.entry("KO", "미용실"),
            Map.entry("JA", "美容室"),
            Map.entry("ZH", "美发沙龙"),
            Map.entry("DE", "Friseursalon"),
            Map.entry("ES", "Peluquería"),
            Map.entry("FR", "Salon de Coiffure"),
            Map.entry("IT", "Salone di Bellezza"),
            Map.entry("NL", "Kapsalon"),
            Map.entry("PT", "Salão de Beleza"),
            Map.entry("RU", "Парикмахерская")
    )),
    MAKEUP(Map.ofEntries(
            Map.entry("EN", "Makeup"),
            Map.entry("KO", "메이크업"),
            Map.entry("JA", "メイクアップ"),
            Map.entry("ZH", "化妆"),
            Map.entry("DE", "Make-up"),
            Map.entry("ES", "Maquillaje"),
            Map.entry("FR", "Maquillage"),
            Map.entry("IT", "Trucco"),
            Map.entry("NL", "Make-up"),
            Map.entry("PT", "Maquiagem"),
            Map.entry("RU", "Макияж")
    )),

    // Day Tours & Activities
    ONE_DAY_CLASS(Map.ofEntries(
            Map.entry("EN", "One Day Class"),
            Map.entry("KO", "원데이 클래스"),
            Map.entry("JA", "ワンデイクラス"),
            Map.entry("ZH", "一日课程"),
            Map.entry("DE", "Eintägiger Kurs"),
            Map.entry("ES", "Clase de Un Día"),
            Map.entry("FR", "Cours d'Une Journée"),
            Map.entry("IT", "Corso di Un Giorno"),
            Map.entry("NL", "Eendaagse Cursus"),
            Map.entry("PT", "Aula de Um Dia"),
            Map.entry("RU", "Однодневный класс")
    )),
    DAY_TOUR(Map.ofEntries(
            Map.entry("EN", "Day Tour"),
            Map.entry("KO", "데이투어"),
            Map.entry("JA", "日帰りツアー"),
            Map.entry("ZH", "一日游"),
            Map.entry("DE", "Tagesausflug"),
            Map.entry("ES", "Tour de Un Día"),
            Map.entry("FR", "Excursion d'Une Journée"),
            Map.entry("IT", "Tour Giornaliero"),
            Map.entry("NL", "Dagexcursie"),
            Map.entry("PT", "Passeio de Um Dia"),
            Map.entry("RU", "Однодневный тур")
    ));

    private final Map<String, String> translations;

    Category(Map<String, String> translations) {
        this.translations = translations;
    }

    public String getTranslation(String languageCode) {
        return translations.getOrDefault(languageCode.toUpperCase(), translations.get("EN"));
    }

    public CategoryGroup getCategoryGroup() {
        return CategoryGroup.findGroup(this);
    }
}