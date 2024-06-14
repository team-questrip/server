package com.questrip.reward.batch.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@JobScope
@Component
public class MenuJobParameter {

    private String filePath;

    @Value("#{jobParameters[filePath]}")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
