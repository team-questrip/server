package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.response.ContentBlockResponse;
import com.questrip.reward.api.v1.response.ContentResponse;
import com.questrip.reward.domain.content.ContentService;
import com.questrip.reward.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/content")
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public ApiResponse<List<ContentResponse>> getPages(@RequestParam(required = false, defaultValue = "EN") String language) {
        var result = contentService.findAllTranslatedContent(toUppercase(language))
                .stream()
                .map(ContentResponse::new)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }

    @GetMapping("/{pageId}")
    public ApiResponse<ContentBlockResponse> getBlocks(@PathVariable String pageId, @RequestParam(required = false, defaultValue = "EN") String language) {
        var contentBlock = contentService.getBlocks(pageId, toUppercase(language));

        return ApiResponse.success(new ContentBlockResponse(contentBlock));
    }

    private String toUppercase(String str) {
        return str.toUpperCase();
    }

    @PostMapping("/{pageId}/init")
    public void init(@PathVariable String pageId) {
        contentService.init(pageId);
    }
}
