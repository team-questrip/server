package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.request.ContentRequest;
import com.questrip.reward.api.v1.response.ContentResponse;
import com.questrip.reward.domain.content.Content;
import com.questrip.reward.domain.content.ContentService;
import com.questrip.reward.support.response.ApiResponse;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/content")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ApiResponse publish(@RequestBody ContentRequest request) {
        contentService.publish(request.toContent());

        return ApiResponse.success(null);
    }

    @GetMapping("/{contentId}")
    public ApiResponse<ContentResponse> findContent(@PathVariable String contentId) {
        Content content = contentService.findContent(contentId);

        return ApiResponse.success(new ContentResponse(content));
    }

    @GetMapping
    public ApiResponse<SliceResult<ContentResponse>> findContents(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "5") int size){
        var result = contentService.findContentsBy(page, size)
                .map(ContentResponse::new);

        return ApiResponse.success(result);
    }
}
