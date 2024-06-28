package com.questrip.reward.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class BatchController {

    private final MenuBatchProcessor menuBatchProcessor;

    @PostMapping("/batch")
    public String menuCsvBatch(MultipartFile file) {
        return menuBatchProcessor.run(file).toString();
    }
}
