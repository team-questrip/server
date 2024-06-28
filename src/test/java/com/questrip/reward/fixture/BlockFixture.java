package com.questrip.reward.fixture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questrip.reward.domain.content.Block;

import java.util.List;

public class BlockFixture {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Block> get() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String str = "[\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"2fa682cb-2e81-4c13-9b21-8c14709d087a\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:50:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"image\",\n" +
                "            \"image\": {\n" +
                "                \"caption\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Photo provided by Korea Tourism Organization Frames Studio - Korea Tourism Organization\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Photo provided by Korea Tourism Organization Frames Studio - Korea Tourism Organization\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"type\": \"file\",\n" +
                "                \"file\": {\n" +
                "                    \"url\": \"https://prod-files-secure.s3.us-west-2.amazonaws.com/82f83567-d6c2-48cc-9a5b-f222d4c78ff0/4ad7d06d-8a94-4cc4-9230-b3326a1add88/%ED%95%9C%EA%B5%AD%EA%B4%80%EA%B4%91%EA%B3%B5%EC%82%AC_%ED%94%84%EB%A0%88%EC%9E%84%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4_-_%ED%95%9C%EA%B5%AD%EA%B4%80%EA%B4%91%EA%B3%B5%EC%82%AC_%281%29.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45HZZMZUHI%2F20240628%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20240628T054332Z&X-Amz-Expires=3600&X-Amz-Signature=4a2a2b86cec33377e0aae2f763de930dcf0275c0b73d9861f41a2075b9660116&X-Amz-SignedHeaders=host&x-id=GetObject\",\n" +
                "                    \"expiry_time\": \"2024-06-28T06:43:32.453Z\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"cf7c6ccd-178a-4b07-966e-a61c3b44c64c\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:51:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:51:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"heading_2\",\n" +
                "            \"heading_2\": {\n" +
                "                \"rich_text\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Boknal,\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Boknal,\",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" Korea's Special Summer Days\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" Korea's Special Summer Days\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"is_toggleable\": false,\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"69bae836-1805-42ca-b1e4-429d5b1e995f\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:51:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:51:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"paragraph\",\n" +
                "            \"paragraph\": {\n" +
                "                \"rich_text\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Boknal\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Boknal\",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" refers to three specific days during the hottest period of summer in Korea, falling between mid-July and mid-August.  To overcome the scorching heat, ancient Koreans designated these special days and developed a custom of consuming nutritious food to boost their energy.  This tradition continues today, with people enjoying special dishes on \",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" refers to three specific days during the hottest period of summer in Korea, falling between mid-July and mid-August.  To overcome the scorching heat, ancient Koreans designated these special days and developed a custom of consuming nutritious food to boost their energy.  This tradition continues today, with people enjoying special dishes on \",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Boknal\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Boknal\",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" to promote good health.\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" to promote good health.\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"ce959355-8e7a-455e-989a-ff92f1786290\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T09:14:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T09:14:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"paragraph\",\n" +
                "            \"paragraph\": {\n" +
                "                \"rich_text\": [],\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"df4d4278-e230-4e89-9ee6-032ca7b32f19\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:52:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"heading_2\",\n" +
                "            \"heading_2\": {\n" +
                "                \"rich_text\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Invigorating Foods, The Power to Beat the Heat\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Invigorating Foods, The Power to Beat the Heat\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"is_toggleable\": false,\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"f207275a-fed1-4f1d-b7b4-18fbc5287bb2\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:53:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"image\",\n" +
                "            \"image\": {\n" +
                "                \"caption\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"\\\"The Joy of Fishing\\\" (釣魚樂圖) by Yi Hyeong-rok (李亨祿)\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"\\\"The Joy of Fishing\\\" (釣魚樂圖) by Yi Hyeong-rok (李亨祿)\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"type\": \"file\",\n" +
                "                \"file\": {\n" +
                "                    \"url\": \"https://prod-files-secure.s3.us-west-2.amazonaws.com/82f83567-d6c2-48cc-9a5b-f222d4c78ff0/2d2a8571-9e9e-4201-b479-43dccdbaf900/SSC_20240424111743_O2_%281%29.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45HZZMZUHI%2F20240628%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20240628T054332Z&X-Amz-Expires=3600&X-Amz-Signature=ee2ea362ab04e88623bb12547fb732801b3e63f757f6efb914da19f0a711d613&X-Amz-SignedHeaders=host&x-id=GetObject\",\n" +
                "                    \"expiry_time\": \"2024-06-28T06:43:32.472Z\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"8792b82a-b60b-42b3-bee7-6d7b864e9d19\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:56:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"paragraph\",\n" +
                "            \"paragraph\": {\n" +
                "                \"rich_text\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" For our ancestors, summer wasn't just about enduring the heat; it was also a time to guard against the threats of food poisoning and infectious diseases. So, they didn't just try to avoid the heat, but actively took care of their health by consuming nourishing foods to boost their strength. The \",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" For our ancestors, summer wasn't just about enduring the heat; it was also a time to guard against the threats of food poisoning and infectious diseases. So, they didn't just try to avoid the heat, but actively took care of their health by consuming nourishing foods to boost their strength. The \",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Boknal\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Boknal\",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" tradition of enjoying these invigorating dishes is a testament to their efforts.\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" tradition of enjoying these invigorating dishes is a testament to their efforts.\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"242dfb52-9003-4843-b7e8-0864d0bd319d\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T09:14:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T09:14:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"paragraph\",\n" +
                "            \"paragraph\": {\n" +
                "                \"rich_text\": [],\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"b222fe4a-a1dd-44ca-806b-9a7f19630886\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:53:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"heading_2\",\n" +
                "            \"heading_2\": {\n" +
                "                \"rich_text\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Modern Boknal, Timeless Value\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Modern Boknal, Timeless Value\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"is_toggleable\": false,\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"32e96823-0984-4eda-a59d-d8a36c00cb9a\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:54:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"image\",\n" +
                "            \"image\": {\n" +
                "                \"caption\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Photo provided by Alexbundo - Korea Tourism Organization\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Photo provided by Alexbundo - Korea Tourism Organization\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"type\": \"file\",\n" +
                "                \"file\": {\n" +
                "                    \"url\": \"https://prod-files-secure.s3.us-west-2.amazonaws.com/82f83567-d6c2-48cc-9a5b-f222d4c78ff0/2ee9c074-7f75-49b0-92c8-83c1cdd84a6c/%EC%95%8C%EB%A0%89%EC%8A%A4_%EB%B6%84%EB%8F%84_-_%ED%95%9C%EA%B5%AD%EA%B4%80%EA%B4%91%EA%B3%B5%EC%82%AC.jpg.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45HZZMZUHI%2F20240628%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20240628T054332Z&X-Amz-Expires=3600&X-Amz-Signature=9980b8940080299296f537b40130a56745850a79ec675f7a1d4b97dfff14fe1a&X-Amz-SignedHeaders=host&x-id=GetObject\",\n" +
                "                    \"expiry_time\": \"2024-06-28T06:43:32.478Z\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"6faefeb6-b486-4bfe-8a7d-5e0bb3b499dd\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:55:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"paragraph\",\n" +
                "            \"paragraph\": {\n" +
                "                \"rich_text\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" In today's affluent society, while worries about nutrition have lessened, \",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" In today's affluent society, while worries about nutrition have lessened, \",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Boknal\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Boknal\",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" remains a special occasion for modern Koreans. A bowl of carefully prepared \",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" remains a special occasion for modern Koreans. A bowl of carefully prepared \",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"boyangshik\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"boyangshik\",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" (nourishing food) revives the forgotten flavors of tradition amidst busy schedules, providing a moment of respite and a sense of peace for both body and mind. Sharing these special dishes with family, friends, and colleagues fosters stronger bonds and becomes a valuable way to recharge with positive energy.\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" (nourishing food) revives the forgotten flavors of tradition amidst busy schedules, providing a moment of respite and a sense of peace for both body and mind. Sharing these special dishes with family, friends, and colleagues fosters stronger bonds and becomes a valuable way to recharge with positive energy.\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"fc74b2dd-69fc-4c0e-8fce-7274cad8ec00\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T09:14:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T09:14:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"paragraph\",\n" +
                "            \"paragraph\": {\n" +
                "                \"rich_text\": [],\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"ddf6fa0b-8b2d-48da-b951-5607bdfcd157\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:55:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"heading_2\",\n" +
                "            \"heading_2\": {\n" +
                "                \"rich_text\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"A \",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": true,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"A \",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Boyangshik\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": true,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Boyangshik\",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \" Icon: \",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": true,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \" Icon: \",\n" +
                "                        \"href\": null\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Samgyetang\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": true,\n" +
                "                            \"italic\": true,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Samgyetang\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"is_toggleable\": false,\n" +
                "                \"color\": \"default\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"object\": \"block\",\n" +
                "            \"id\": \"93f6c19c-57c3-4f2b-9998-3fc427b0f7e2\",\n" +
                "            \"parent\": {\n" +
                "                \"type\": \"page_id\",\n" +
                "                \"page_id\": \"8860f178-e89f-488c-b68b-a0ac414e25de\"\n" +
                "            },\n" +
                "            \"created_time\": \"2024-06-27T08:46:00.000Z\",\n" +
                "            \"last_edited_time\": \"2024-06-27T08:56:00.000Z\",\n" +
                "            \"created_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"last_edited_by\": {\n" +
                "                \"object\": \"user\",\n" +
                "                \"id\": \"1e211f19-4e2e-4bdf-b9b4-db6d0b40d93d\"\n" +
                "            },\n" +
                "            \"has_children\": false,\n" +
                "            \"archived\": false,\n" +
                "            \"in_trash\": false,\n" +
                "            \"type\": \"image\",\n" +
                "            \"image\": {\n" +
                "                \"caption\": [\n" +
                "                    {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"text\": {\n" +
                "                            \"content\": \"Photo provided by Korea Tourism Organization Frames Studio - Korea Tourism Organization\",\n" +
                "                            \"link\": null\n" +
                "                        },\n" +
                "                        \"annotations\": {\n" +
                "                            \"bold\": false,\n" +
                "                            \"italic\": false,\n" +
                "                            \"strikethrough\": false,\n" +
                "                            \"underline\": false,\n" +
                "                            \"code\": false,\n" +
                "                            \"color\": \"default\"\n" +
                "                        },\n" +
                "                        \"plain_text\": \"Photo provided by Korea Tourism Organization Frames Studio - Korea Tourism Organization\",\n" +
                "                        \"href\": null\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"type\": \"file\",\n" +
                "                \"file\": {\n" +
                "                    \"url\": \"https://prod-files-secure.s3.us-west-2.amazonaws.com/82f83567-d6c2-48cc-9a5b-f222d4c78ff0/ae27eb54-5d9d-4f2e-b87f-0cb2783f9b99/%ED%95%9C%EA%B5%AD%EA%B4%80%EA%B4%91%EA%B3%B5%EC%82%AC_%ED%94%84%EB%A0%88%EC%9E%84%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4_-_%ED%95%9C%EA%B5%AD%EA%B4%80%EA%B4%91%EA%B3%B5%EC%82%AC_%282%29.jpg.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45HZZMZUHI%2F20240628%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20240628T054332Z&X-Amz-Expires=3600&X-Amz-Signature=5d4e760953720ca5adec170f6c1a2d45702d185a17055534f65d94404465edd6&X-Amz-SignedHeaders=host&x-id=GetObject\",\n" +
                "                    \"expiry_time\": \"2024-06-28T06:43:32.465Z\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ]";

        return objectMapper.readValue(str, new TypeReference<>() {});
    }
}
