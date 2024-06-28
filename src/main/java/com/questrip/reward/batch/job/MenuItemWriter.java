package com.questrip.reward.batch.job;

import com.questrip.reward.batch.domain.MenuDto;
import com.questrip.reward.domain.place.MenuGroup;
import com.questrip.reward.domain.place.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@StepScope
@Component
@Slf4j
@RequiredArgsConstructor
public class MenuItemWriter implements ItemWriter<MenuDto> {

    private final PlaceService placeService;
    @Override
    public void write(Chunk<? extends MenuDto> chunk) {
        Map<String, List<MenuGroup>> menuGroupMap = getMap(chunk);
        Set<String> keySet = menuGroupMap.keySet();

        for(String placeId : keySet) {
            try {
                placeService.addMenuGroups(placeId, menuGroupMap.get(placeId));
            } catch (Exception e) {
                log.error("batch write error : {}", e.getMessage());
                throw e;
            }
        }
    }

    private static Map<String, List<MenuGroup>> getMap(Chunk<? extends MenuDto> chunk) {
        return chunk.getItems()
                .stream()
                .collect(Collectors.groupingBy(
                        MenuDto::getPlaceId,
                        Collectors.mapping(MenuDto::toGroups, Collectors.toList()))
                );
    }
}
