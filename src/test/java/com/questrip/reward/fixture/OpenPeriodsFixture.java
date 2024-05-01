package com.questrip.reward.fixture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questrip.reward.domain.place.Period;

import java.util.List;

public class OpenPeriodsFixture {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static List<Period> get() {
        String periods = "[\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 0,\n" +
                "              \"hour\": 17,\n" +
                "              \"minute\": 0\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 1,\n" +
                "              \"hour\": 2,\n" +
                "              \"minute\": 0\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 1,\n" +
                "              \"hour\": 17,\n" +
                "              \"minute\": 0\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 2,\n" +
                "              \"hour\": 2,\n" +
                "              \"minute\": 0\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 2,\n" +
                "              \"hour\": 17,\n" +
                "              \"minute\": 0\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 3,\n" +
                "              \"hour\": 2,\n" +
                "              \"minute\": 0\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 3,\n" +
                "              \"hour\": 17,\n" +
                "              \"minute\": 0\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 4,\n" +
                "              \"hour\": 2,\n" +
                "              \"minute\": 0\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 4,\n" +
                "              \"hour\": 17,\n" +
                "              \"minute\": 0\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 5,\n" +
                "              \"hour\": 2,\n" +
                "              \"minute\": 0\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 5,\n" +
                "              \"hour\": 17,\n" +
                "              \"minute\": 0\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 6,\n" +
                "              \"hour\": 2,\n" +
                "              \"minute\": 0\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 6,\n" +
                "              \"hour\": 17,\n" +
                "              \"minute\": 0\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 0,\n" +
                "              \"hour\": 2,\n" +
                "              \"minute\": 0\n" +
                "            }\n" +
                "          }\n" +
                "        ]";

        /**
         * 매일 17:00 ~ 02:00
         */

        try {
            return objectMapper.readValue(periods, new TypeReference<List<Period>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}