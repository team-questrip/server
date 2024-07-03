package com.questrip.reward.domain.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OpenPeriodsTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("영업시간 내에는 영업중으로 보인다.")
    @Test
    void isOpenInOpeningHours() throws JsonProcessingException {
        // given
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
        List<Period> list = objectMapper.readValue(periods, new TypeReference<List<Period>>() {
        });

        OpenPeriods openPeriods = new OpenPeriods(list);
        LocalDateTime openTime = LocalDateTime.of(2024, 3, 27, 17, 0, 0);
        LocalDateTime currentTime = LocalDateTime.of(2024, 3, 27, 19, 0, 0);
        LocalDateTime closeTime = LocalDateTime.of(2024, 3, 27, 2, 0, 0);

        // when
        OpenStatus open = openPeriods.isOpen(openTime);
        OpenStatus current = openPeriods.isOpen(currentTime);
        OpenStatus close = openPeriods.isOpen(closeTime);

        // then
        assertThat(open).isEqualTo(OpenStatus.OPEN);
        assertThat(current).isEqualTo(OpenStatus.OPEN);
        assertThat(close).isEqualTo(OpenStatus.OPEN);
    }

    @DisplayName("영업시간 외에는 영업시간이 아닌 것으로 보인다.")
    @Test
    void isOpenNoOpeningHours() throws JsonProcessingException {
        // given
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
        List<Period> list = objectMapper.readValue(periods, new TypeReference<List<Period>>() {
        });

        OpenPeriods openPeriods = new OpenPeriods(list);

        LocalDateTime currentTime = LocalDateTime.of(2024, 3, 27, 14, 0, 0);
        LocalDateTime openTime = LocalDateTime.of(2024, 3, 27, 16, 59, 59);
        LocalDateTime closeTime = LocalDateTime.of(2024, 3, 27, 2, 0, 1);

        // when
        OpenStatus current = openPeriods.isOpen(currentTime);
        OpenStatus open = openPeriods.isOpen(openTime);
        OpenStatus close = openPeriods.isOpen(closeTime);

        // then
        assertThat(open).isEqualTo(OpenStatus.CLOSE);
        assertThat(current).isEqualTo(OpenStatus.CLOSE);
        assertThat(close).isEqualTo(OpenStatus.CLOSE);
    }

    @DisplayName("영업시간 외에는 영업시간이 아닌 것으로 보인다.")
    @Test
    void isOpenNoOpeningHours2() throws JsonProcessingException {
        // given
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
                "              \"hour\": 3,\n" +
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
         * 월화수목금일 17:00 ~ 02:00
         * 토 03:00 ~ 02:00
         */
        List<Period> list = objectMapper.readValue(periods, new TypeReference<List<Period>>() {
        });

        OpenPeriods openPeriods = new OpenPeriods(list);

        LocalDateTime 토요일새벽두시오십분 = LocalDateTime.of(2024, 4, 27, 2, 50, 59);

        // when
        OpenStatus 새벽두시오십분 = openPeriods.isOpen(토요일새벽두시오십분);

        // then
        assertThat(새벽두시오십분).isEqualTo(OpenStatus.CLOSE);
    }

    @DisplayName("브레이크 타임이 포함된 영업시간 테스트")
    @Test
    void isOpenWithBreakTime() throws JsonProcessingException {
        // given
        String periods = "[\n" +
                "          {\n" +
                "            \"open\": {\n" +
                "              \"day\": 1,\n" +
                "              \"hour\": 11,\n" +
                "              \"minute\": 30\n" +
                "            },\n" +
                "            \"close\": {\n" +
                "              \"day\": 1,\n" +
                "              \"hour\": 15,\n" +
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
                "          }\n" +
                "        ]";

        /**
         * 월요일 11:30 ~ 15:00, 17:00 ~ 02:00 (다음날)
         */
        List<Period> list = objectMapper.readValue(periods, new TypeReference<List<Period>>() {});

        OpenPeriods openPeriods = new OpenPeriods(list);

        LocalDateTime beforeOpen = LocalDateTime.of(2024, 3, 25, 11, 29, 0);  // 월요일 11:29
        LocalDateTime beforeOpen2 = LocalDateTime.of(2024, 3, 25, 11, 30, 0);  // 월요일 11:30
        LocalDateTime duringFirstPeriod = LocalDateTime.of(2024, 3, 25, 13, 0, 0);  // 월요일 13:00
        LocalDateTime duringBreakTime = LocalDateTime.of(2024, 3, 25, 16, 0, 0);  // 월요일 16:00
        LocalDateTime duringSecondPeriod = LocalDateTime.of(2024, 3, 25, 20, 0, 0);  // 월요일 20:00
        LocalDateTime afterMidnight = LocalDateTime.of(2024, 3, 26, 1, 0, 0);  // 화요일 01:00
        LocalDateTime afterClose = LocalDateTime.of(2024, 3, 26, 3, 0, 0);  // 화요일 03:00

        // when
        OpenStatus beforeOpenStatus = openPeriods.isOpen(beforeOpen);
        OpenStatus beforeOpenStatus2 = openPeriods.isOpen(beforeOpen2);
        OpenStatus duringFirstPeriodStatus = openPeriods.isOpen(duringFirstPeriod);
        OpenStatus duringBreakTimeStatus = openPeriods.isOpen(duringBreakTime);
        OpenStatus duringSecondPeriodStatus = openPeriods.isOpen(duringSecondPeriod);
        OpenStatus afterMidnightStatus = openPeriods.isOpen(afterMidnight);
        OpenStatus afterCloseStatus = openPeriods.isOpen(afterClose);

        // then
        assertThat(beforeOpenStatus).isEqualTo(OpenStatus.CLOSE);
        assertThat(beforeOpenStatus2).isEqualTo(OpenStatus.OPEN);
        assertThat(duringFirstPeriodStatus).isEqualTo(OpenStatus.OPEN);
        assertThat(duringBreakTimeStatus).isEqualTo(OpenStatus.CLOSE);
        assertThat(duringSecondPeriodStatus).isEqualTo(OpenStatus.OPEN);
        assertThat(afterMidnightStatus).isEqualTo(OpenStatus.OPEN);
        assertThat(afterCloseStatus).isEqualTo(OpenStatus.CLOSE);
    }
}