package com.questrip.reward.batch.job;

import com.questrip.reward.batch.domain.MenuDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class MenuDtoFieldSetMapper implements FieldSetMapper<MenuDto> {

    public static final String PLACE_ID = "placeId";
    public static final String MENU_GROUP = "menuGroup";
    public static final String MENU_NAME = "menuName";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";


    @Override
    public MenuDto mapFieldSet(FieldSet fieldSet) throws BindException {
        return new MenuDto(
                fieldSet.readString(PLACE_ID),
                fieldSet.readString(MENU_GROUP),
                fieldSet.readString(MENU_NAME),
                fieldSet.readInt(PRICE),
                fieldSet.readString(DESCRIPTION)
        );
    }
}
