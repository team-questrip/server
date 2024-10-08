package com.questrip.reward.api.v1;

import com.questrip.reward.api.support.Language;
import com.questrip.reward.api.v1.response.ContentBlockResponse;
import com.questrip.reward.api.v1.response.ContentResponse;
import com.questrip.reward.domain.content.ContentService;
import com.questrip.reward.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/content")
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public ApiResponse<List<ContentResponse>> getPages(@Language String language) {
        log.info("content list request, request language is : {}", language);
        var result = contentService.findAllTranslatedContent(language)
                .stream()
                .map(ContentResponse::new)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }

    @GetMapping("/{pageId}")
    public ApiResponse<ContentBlockResponse> getBlocks(@PathVariable String pageId, @Language String language) {
        log.info("content pageId :{} request, request language is : {}", pageId, language);
        var contentBlock = contentService.getBlocks(pageId, language);

        return ApiResponse.success(new ContentBlockResponse(contentBlock));
    }

    @PostMapping("/{pageId}/init")
    public void init(@PathVariable String pageId) {
        contentService.init(pageId);
    }
}
