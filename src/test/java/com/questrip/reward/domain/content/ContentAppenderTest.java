package com.questrip.reward.domain.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questrip.reward.storage.mongo.ContentEntity;
import com.questrip.reward.storage.mongo.ContentMongoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ContentAppenderTest {

    @Autowired
    ContentAppender contentAppender;

    @Autowired
    ContentMongoRepository contentMongoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void tearDown() {
        contentMongoRepository.deleteAll();
    }

    @DisplayName("컨텐츠를 추가한다.")
    @Test
    void append() throws JsonProcessingException {
        String json = "{\n" +
                "    \"title\": \"Enduring the Hot Summer in Korea: Boknal and Samgyetang\",\n" +
                "    \"tags\": [\"Boknal\", \"Korea\", \"Summer\", \"Samgyetang\"],\n" +
                "    \"category\" : \"FOOD_CULTURE\",\n" +
                "    \"images\": \"imagePath\",\n" +
                "    \"sections\": [\n" +
                "      {\n" +
                "        \"title\": \"Introduction\",\n" +
                "        \"content\": \"Boknal refers to the three hottest days in Korea, between mid-July and mid-August. Traditionally, people have designated these days to consume nutritious foods to withstand the intense heat. This tradition has continued to the present day, where people eat special foods to pray for health on Boknal.\",\n" +
                "        \"image\": \"imagePath\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Nutrient-rich Foods to Overcome the Heat\",\n" +
                "        \"content\": \"For people in the past, summer was not just about enduring the heat but also overcoming threats like food poisoning and infectious diseases. They didn't just avoid the heat; they fortified their health by consuming nutritious foods. The tradition of eating health-boosting foods on Boknal is a reflection of their efforts.\",\n" +
                "        \"image\": \"imagePath\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Modern Boknal: Unchanged Values\",\n" +
                "        \"content\": \"In modern times, while nutritional concerns have decreased, Boknal still holds special significance. A bowl of carefully prepared nutritious food brings back traditional flavors and provides comfort amidst the busy daily life. Sharing Boknal meals with family, friends, and colleagues strengthens bonds and recharges positive energy.\",\n" +
                "        \"image\": \"imagePath\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"The Representative Nutrient-rich Food: Samgyetang\",\n" +
                "        \"content\": \"Among the various nutritious foods, Samgyetang is indispensable for Boknal. This dish, made by boiling a chicken stuffed with ginseng, jujube, garlic, and glutinous rice, is rich in protein and nutrients, revitalizing the weary body.\",\n" +
                "        \"image\": \"imagePath\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Pronunciation\",\n" +
                "        \"content\": \"Samgyetang is written in Hanja as '蔘鷄湯', where '蔘' means ginseng, '鷄' means chicken, and '湯' means soup or broth. Thus, Samgyetang means 'a soup made with ginseng and chicken'. It's spelled 'Samgyetang' in Roman letters and pronounced as /sam.gje.tʰaŋ/. It’s similar to pronouncing 'sum-get-tongue' in English, so you can easily follow.\",\n" +
                "        \"image\": \"imagePath\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Samgyetang Plus\",\n" +
                "        \"bulletedList\": [\n" +
                "          {\n" +
                "            \"title\": \"Abalone Samgyetang\",\n" +
                "            \"items\": [\n" +
                "              \"Chewy abalone adds flavor and nutrition!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Neungi Samgyetang\",\n" +
                "            \"items\": [\n" +
                "              \"The unique aroma and taste of neungi mushrooms!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Perilla Seed Samgyetang\",\n" +
                "            \"items\": [\n" +
                "              \"Rich and savory flavor with plenty of perilla seeds!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Mung Bean Samgyetang\",\n" +
                "            \"items\": [\n" +
                "              \"Savory mung beans add more richness and softness!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Astragalus Samgyetang\",\n" +
                "            \"items\": [\n" +
                "              \"The subtle aroma and bitter taste of astragalus!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Black Garlic Samgyetang\",\n" +
                "            \"items\": [\n" +
                "              \"Deep taste and aroma of black garlic for added health benefits!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Oak Samgyetang\",\n" +
                "            \"items\": [\n" +
                "              \"Rich and unique flavor from oak tree infusion (caution for oak allergy!)\"\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Relatives of Samgyetang\",\n" +
                "        \"bulletedList\": [\n" +
                "          {\n" +
                "            \"title\": \"Dakbaeksuk\",\n" +
                "            \"items\": [\n" +
                "              \"Lighter in glutinous rice compared to Samgyetang, with garlic, jujube, and astragalus for a clean and mild taste!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Nurungji Dakbaeksuk\",\n" +
                "            \"items\": [\n" +
                "              \"Added nurungji for a richer and more satisfying taste!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Jjimdak\",\n" +
                "            \"items\": [\n" +
                "              \"Chicken, potatoes, and noodles simmered in soy sauce for a sweet and savory flavor!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Dakbokkeumtang\",\n" +
                "            \"items\": [\n" +
                "              \"Spicy braised chicken, a beloved Korean dish!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Ori Baeksuk\",\n" +
                "            \"items\": [\n" +
                "              \"Duck meat instead of chicken for a chewy texture and rich broth!\"\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Tips for Enjoying Samgyetang Like a Korean!\",\n" +
                "        \"bulletedList\": [\n" +
                "          {\n" +
                "            \"title\": \"Be Careful of the Heat! Take Your Time to Cool It Down!\",\n" +
                "            \"items\": [\n" +
                "              \"Samgyetang is served boiling hot in an earthenware pot. Koreans are used to cooling down hot food slowly, but it can be unfamiliar to foreigners. Be careful not to burn your mouth by cooling it down patiently.\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Salt, Pepper, and Radish Kimchi Broth? Adjust to Your Taste!\",\n" +
                "            \"items\": [\n" +
                "              \"Samgyetang is seasoned to taste. Use the salt and pepper provided at the table to season it. Koreans sometimes add radish kimchi broth for a unique flavor. Try it if you're feeling adventurous!\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Be Careful with the Chicken Bones!\",\n" +
                "            \"items\": [\n" +
                "              \"The chicken is cooked so tender that the meat easily separates from the bones, but be cautious as not all bones are completely removed.\"\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Don't Miss the Glutinous Rice Porridge!\",\n" +
                "            \"items\": [\n" +
                "              \"The real highlight of Samgyetang is the glutinous rice porridge hidden inside the chicken! After eating some of the chicken, enjoy the rich and savory porridge mixed with the remaining broth.\"\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Samgyetang for All Seasons!\",\n" +
                "        \"content\": \"Samgyetang is a beloved dish in Korea, enjoyed not only in the hot summer but throughout the year. A warm bowl on a cold day warms the body and soul. While there are long lines at Samgyetang restaurants during Boknal, you can enjoy it more leisurely on other days.\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Conclusion\",\n" +
                "        \"content\": \"If you visit Korea this summer, why not experience Korean tradition, taste, and health all in one bowl of Samgyetang?\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
        // given
        var content = objectMapper.readValue(json, Content.class);

        // when
        Content append = contentAppender.append(content);

        // then
        List<ContentEntity> list = contentMongoRepository.findAll();

        assertThat(list.size()).isOne();
        assertThat(list.get(0)).extracting("title", "category", "tags", "images", "sections")
                .containsExactly(content.getTitle(), content.getCategory(), content.getTags(), content.getImages(), content.getSections());
    }
}