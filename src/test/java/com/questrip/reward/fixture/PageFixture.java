package com.questrip.reward.fixture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questrip.reward.domain.content.Page;
import org.junit.jupiter.api.BeforeEach;

public class PageFixture {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Page get() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String str = "{\n" +
                "            \"object\": \"page\",\n" +
                "            \"id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\",\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-28T03:06:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"cover\": null,\n" +
                "            \"icon\": null,\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"database_id\",\n" +
                "                \"database_id\": \"183385c0-793a-4985-9c61-806b09d08cbc\"\n" +
                "            },\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"properties\": {\n" +
                "                \"tags\": {\n" +
                "                    \"id\": \"XLDT\",\n" +
                "                    \"type\": \"multi_select\",\n" +
                "                    \"multi_select\": [\n" +
                "                        {\n" +
                "                            \"id\": \"051b9ae3-6c74-45c8-87a9-5de1c996c54a\",\n" +
                "                            \"name\": \"summer\",\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"1efe4507-23c6-4b60-8d65-4bf327beb48a\",\n" +
                "                            \"name\": \"nourishing\",\n" +
                "                            \"color\": \"orange\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"c35bc89e-2025-4a67-98ec-26d6358738e8\",\n" +
                "                            \"name\": \"chicken\",\n" +
                "                            \"color\": \"green\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"88507441-436d-4240-a163-281bc97c40e0\",\n" +
                "                            \"name\": \"soup/broth/stew\",\n" +
                "                            \"color\": \"gray\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"title\": {\n" +
                "                    \"id\": \"bQo~\",\n" +
                "                    \"type\": \"rich_text\",\n" +
                "                    \"rich_text\": [\n" +
                "                        {\n" +
                "                            \"type\": \"text\",\n" +
                "                            \"text\": {\n" +
                "                                \"content\": \"Korea's Hot Summer Remedy: Boknal and Samgyetang\",\n" +
                "                                \"link\": null\n" +
                "                            },\n" +
                "                            \"annotations\": {\n" +
                "                                \"bold\": false,\n" +
                "                                \"italic\": false,\n" +
                "                                \"strikethrough\": false,\n" +
                "                                \"underline\": false,\n" +
                "                                \"code\": false,\n" +
                "                                \"color\": \"default\"\n" +
                "                            },\n" +
                "                            \"plain_text\": \"Korea's Hot Summer Remedy: Boknal and Samgyetang\",\n" +
                "                            \"href\": null\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"category\": {\n" +
                "                    \"id\": \"c%3BYd\",\n" +
                "                    \"type\": \"multi_select\",\n" +
                "                    \"multi_select\": [\n" +
                "                        {\n" +
                "                            \"id\": \"58f45995-19e8-4334-91c6-d628fe000eda\",\n" +
                "                            \"name\": \"Korean food culture\",\n" +
                "                            \"color\": \"orange\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"4d39800a-6fed-4e27-bbd5-a5ddb43bc1de\",\n" +
                "                            \"name\": \"menu introduction\",\n" +
                "                            \"color\": \"orange\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"thumbnailImage\": {\n" +
                "                    \"id\": \"fAjE\",\n" +
                "                    \"type\": \"files\",\n" +
                "                    \"files\": [\n" +
                "                        {\n" +
                "                            \"name\": \"한국관광공사 프레임스튜디오 - 한국관광공사 (1).jpg\",\n" +
                "                            \"type\": \"file\",\n" +
                "                            \"file\": {\n" +
                "                                \"url\": \"https://prod-files-secure.s3.us-west-2.amazonaws.com/82f83567-d6c2-48cc-9a5b-f222d4c78ff0/6e0894d0-a3f0-4da3-964e-a0aeb373d598/%E1%84%92%E1%85%A1%E1%86%AB%E1%84%80%E1%85%AE%E1%86%A8%E1%84%80%E1%85%AA%E1%86%AB%E1%84%80%E1%85%AA%E1%86%BC%E1%84%80%E1%85%A9%E1%86%BC%E1%84%89%E1%85%A1_%E1%84%91%E1%85%B3%E1%84%85%E1%85%A6%E1%84%8B%E1%85%B5%E1%86%B7%E1%84%89%E1%85%B3%E1%84%90%E1%85%B2%E1%84%83%E1%85%B5%E1%84%8B%E1%85%A9_-_%E1%84%92%E1%85%A1%E1%86%AB%E1%84%80%E1%85%AE%E1%86%A8%E1%84%80%E1%85%AA%E1%86%AB%E1%84%80%E1%85%AA%E1%86%BC%E1%84%80%E1%85%A9%E1%86%BC%E1%84%89%E1%85%A1_%281%29.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45HZZMZUHI%2F20240628%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20240628T065504Z&X-Amz-Expires=3600&X-Amz-Signature=db6b166c848d1fb0c567f999130ec8eaa415107148e4134b490248bf7026a8fd&X-Amz-SignedHeaders=host&x-id=GetObject\",\n" +
                "                                \"expiry_time\": \"2024-06-28T07:55:04.447Z\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"menuItems\": {\n" +
                "                    \"id\": \"mdJw\",\n" +
                "                    \"type\": \"multi_select\",\n" +
                "                    \"multi_select\": [\n" +
                "                        {\n" +
                "                            \"id\": \"911487fd-dafb-4e2f-8124-10849f0e45f1\",\n" +
                "                            \"name\": \"삼계탕\",\n" +
                "                            \"color\": \"purple\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"5dc7ac22-ceb3-4a72-a74a-f560b1c02fb2\",\n" +
                "                            \"name\": \"닭백숙\",\n" +
                "                            \"color\": \"purple\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"2d46f552-bbb3-4f0c-9423-272ad35fc7c3\",\n" +
                "                            \"name\": \"닭곰탕\",\n" +
                "                            \"color\": \"gray\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"452ad678-702b-4bab-a2a6-5f299eadf399\",\n" +
                "                            \"name\": \"닭한마리\",\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"f6ef133c-bdfc-458f-b939-0a18e3326163\",\n" +
                "                            \"name\": \"찜닭\",\n" +
                "                            \"color\": \"brown\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"7bb60bfa-7332-4a80-999c-3c394f3dcde3\",\n" +
                "                            \"name\": \"닭볶음탕\",\n" +
                "                            \"color\": \"red\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": \"4c5ec09b-e2cf-4a32-bd51-57b8c78faf2e\",\n" +
                "                            \"name\": \"오리백숙\",\n" +
                "                            \"color\": \"pink\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"id\": {\n" +
                "                    \"id\": \"title\",\n" +
                "                    \"type\": \"title\",\n" +
                "                    \"title\": [\n" +
                "                        {\n" +
                "                            \"type\": \"text\",\n" +
                "                            \"text\": {\n" +
                "                                \"content\": \"1\",\n" +
                "                                \"link\": null\n" +
                "                            },\n" +
                "                            \"annotations\": {\n" +
                "                                \"bold\": false,\n" +
                "                                \"italic\": false,\n" +
                "                                \"strikethrough\": false,\n" +
                "                                \"underline\": false,\n" +
                "                                \"code\": false,\n" +
                "                                \"color\": \"default\"\n" +
                "                            },\n" +
                "                            \"plain_text\": \"1\",\n" +
                "                            \"href\": null\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            },\n" +
                "            \"url\": \"https://www.notion.so/1-8860f178e89f488cb68ba0ac414e25de\",\n" +
                "            \"public_url\": null\n" +
                "        }";

        return objectMapper.readValue(str, Page.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(PageFixture.get());
    }
}
