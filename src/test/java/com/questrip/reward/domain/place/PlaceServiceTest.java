package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PlaceServiceTest {

    @Autowired
    PlaceService placeService;

    @Autowired
    PlaceMongoRepository placeMongoRepository;

    @AfterEach
    void tearDown() {
        placeMongoRepository.deleteAll();
    }

    @DisplayName("역지오코딩 결과를 반환한다.")
    @Test
    void reverseGeocode() {
        // given
        LatLng 잠실동 = new LatLng(37.5068006, 127.0830179);

        // when
        String address = placeService.reverseGeocode(잠실동);

        // then
        assertThat(address).isEqualTo("226-19 Jamsil-dong, Songpa District, Seoul, South Korea");
    }

    @DisplayName("메뉴 그룹을 추가한다.")
    @Test
    void addMenuGroup() {
        // given
        Place init = PlaceFixture.get();
        Place place = placeMongoRepository.save(PlaceEntity.from(init)).toPlace();
        MenuGroup menuGroup = new MenuGroup("testGroup", Set.of(new Menu("testMenu", 9000, "testMenu")));
        MenuGroup menuGroup2 = new MenuGroup("testGroup", Set.of(new Menu("testMenu", 9000, "testMenu")));

        // when
        Place expect = placeService.addMenuGroups(place.getId(), List.of(menuGroup));

        // then
        assertThat(expect.getMenuGroups()).contains(menuGroup);
    }

    @DisplayName("메뉴 그룹을 추가한다.")
    @Test
    void addMenuGroup2() {
        // given
        Place init = PlaceFixture.get();
        Place place = placeMongoRepository.save(PlaceEntity.from(init)).toPlace();
        MenuGroup menuGroup = new MenuGroup("testGroup", Set.of(new Menu("testMenu", 9000, "testMenu"), new Menu("testMenu1", 9000, "testMenu")));
        MenuGroup menuGroup2 = new MenuGroup("testGroup2", Set.of(new Menu("testMenu2", 10000, "testMenu2")));

        // when
        Place expect = placeService.addMenuGroups(place.getId(), List.of(menuGroup, menuGroup2));

        // then
        assertThat(expect.getMenuGroups().size()).isEqualTo(4);
        assertThat(findMenuGroup("testGroup", expect).getMenus().size()).isEqualTo(2);
    }

    @DisplayName("메뉴그룹에 동일한 메뉴 추가 요청이 들어올 경우 메뉴가 추가되지 않는다.")
    @Test
    void addMenuGroup3() {
        // given
        Place init = PlaceFixture.get();
        Place place = placeMongoRepository.save(PlaceEntity.from(init)).toPlace();
        MenuGroup menuGroup = new MenuGroup("testGroup", Set.of(new Menu("testMenu", 9000, "testMenu"), new Menu("testMenu1", 9000, "testMenu")));
        placeService.addMenuGroups(place.getId(), List.of(menuGroup));
        MenuGroup menuGroup2 = new MenuGroup("testGroup2", Set.of(new Menu("testMenu2", 10000, "testMenu2")));

        // when
        Place expect = placeService.addMenuGroups(place.getId(), List.of(menuGroup, menuGroup2));

        // then
        assertThat(expect.getMenuGroups().size()).isEqualTo(4);
        assertThat(findMenuGroup("testGroup", expect).getMenus().size()).isEqualTo(2);
    }

    @DisplayName("동일한 메뉴그룹에 메뉴 추가 요청이 들어올 경우 메뉴만 추가된다.")
    @Test
    void addMenuGroup4() {
        // given
        Place init = PlaceFixture.get();
        Place place = placeMongoRepository.save(PlaceEntity.from(init)).toPlace();
        MenuGroup menuGroup = new MenuGroup("testGroup", Set.of(new Menu("testMenu", 9000, "testMenu"), new Menu("testMenu1", 8000, "testMenu1")));
        placeService.addMenuGroups(place.getId(), List.of(menuGroup));

        MenuGroup menuGroup2 = new MenuGroup("testGroup", Set.of(new Menu("testMenu2", 10000, "testMenu2")));

        // when
        Place expect = placeService.addMenuGroups(place.getId(), List.of(menuGroup2));

        // then
        MenuGroup findGroup = findMenuGroup("testGroup", expect);
        assertThat(expect.getMenuGroups().size()).isEqualTo(3);
        assertThat(findGroup.getMenus())
                .extracting("name", "price", "description")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("testMenu", 9000, "testMenu"),
                        Tuple.tuple("testMenu1", 8000, "testMenu1"),
                        Tuple.tuple("testMenu2", 10000, "testMenu2")
                );
    }

    private MenuGroup findMenuGroup(String groupName, Place place) {
        return place.getMenuGroups()
                .stream()
                .filter(menuGroup -> menuGroup.getGroupName().equals(groupName))
                .findFirst()
                .get();
    }
}