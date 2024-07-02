package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.response.BlockResponse;
import com.questrip.reward.api.v1.response.PageResponse;
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
    public ApiResponse<List<PageResponse>> getPages() {
        List<PageResponse> result = contentService.getPages()
                .stream()
                .map(PageResponse::new)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }

    @GetMapping("/{pageId}")
    public ApiResponse<List<BlockResponse>> getBlocks(@PathVariable String pageId) {
        List<BlockResponse> result = contentService.getBlocks(pageId)
                .stream()
                .map(BlockResponse::fromBlock)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }

    @GetMapping("/translate")
    public ApiResponse<List<PageResponse>> getTranslatedPages(@RequestParam String sourceLang, @RequestParam String targetLang) {
        List<PageResponse> result = contentService.getTranslatedPages(sourceLang, targetLang)
                .stream()
                .map(PageResponse::new)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }

    @GetMapping("/translate/{pageId}")
    public ApiResponse<List<BlockResponse>> getTranslatedBlocks(@PathVariable String pageId,
                                                                @RequestParam String sourceLang,
                                                                @RequestParam String targetLang) {
        List<BlockResponse> result = contentService.getTranslatedBlocks(pageId, sourceLang, targetLang)
                .stream()
                .map(BlockResponse::fromTranslatedBlcok)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }
}
