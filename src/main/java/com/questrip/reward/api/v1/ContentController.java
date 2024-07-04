package com.questrip.reward.api.v1;

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
        var result = contentService.findAllTranslatedContent(language)
                .stream()
                .map(ContentResponse::new)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }

//    @GetMapping("/{pageId}")
//    public ApiResponse<List<BlockResponse>> getBlocks(@PathVariable String pageId) {
//        List<BlockResponse> result = contentService.getBlocks(pageId)
//                .stream()
//                .map(BlockResponse::fromBlock)
//                .collect(Collectors.toList());
//
//        return ApiResponse.success(result);
//    }

    @PostMapping("/{pageId}/translate")
    public void translateAll(@PathVariable String pageId) {
        contentService.translateAll(pageId);
    }

    @PostMapping("/{pageId}")
    public void postDefaultBlock(@PathVariable String pageId) {

    }
}
