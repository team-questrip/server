package com.questrip.reward.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class MenuBatchProcessor {

    private JobLauncher jobLauncher;
    private Job job;

    public MenuBatchProcessor(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public BatchStatus run(MultipartFile file) {
        log.info("Menu Batch Executed");

        try {
            Path tempFile = Files.createTempFile("insert_menu", ".csv");

            file.transferTo(tempFile.toFile());

            String filePath = tempFile.toAbsolutePath().toString();

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("filePath", filePath)
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(job, jobParameters);
            Files.delete(tempFile);

            return execution.getStatus();
        } catch (Exception e) {
            log.error("batch processor error : {}", e.getMessage());
            return BatchStatus.FAILED;
        }
    }
}
