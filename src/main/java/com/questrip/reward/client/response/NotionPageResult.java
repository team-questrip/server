package com.questrip.reward.client.response;

import com.questrip.reward.domain.content.Page;
import lombok.Getter;

import java.util.List;

@Getter
public class NotionPageResult {
    List<Page> results;
}
