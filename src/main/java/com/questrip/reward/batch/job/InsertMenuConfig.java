package com.questrip.reward.batch.job;

import com.questrip.reward.batch.domain.MenuDto;
import com.questrip.reward.batch.validator.FileResourceParameterValidator;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import static com.questrip.reward.batch.job.MenuDtoFieldSetMapper.*;

@Configuration
@RequiredArgsConstructor
public class InsertMenuConfig extends DefaultBatchConfiguration {

    private final MenuItemWriter menuItemWriter;
    private final MenuJobParameter menuJobParameter;

    @Bean
    public Job insertPlaceJob(JobRepository jobRepository, Step insertMenuStep) {
        return new JobBuilder("insertPlaceJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .validator(new FileResourceParameterValidator())
                .start(insertMenuStep)
                .build();
    }

    @JobScope
    @Bean
    public Step insertMenuStep(JobRepository jobRepository,
                                 PlatformTransactionManager transactionManager,
                                 FlatFileItemReader<MenuDto> menuItemReader,
                                 ItemWriter<MenuDto> menuItemWriter
    ) {
        return new StepBuilder("insertMenuStep", jobRepository)
                .<MenuDto, MenuDto>chunk(100, transactionManager)
                .reader(menuItemReader)
                .writer(menuItemWriter)
                .allowStartIfComplete(true)
                .faultTolerant()
                .skip(GlobalException.class)
                .skipLimit(3)
                .build();
    }

    @StepScope
    @Bean
    public FlatFileItemReader<MenuDto> menuItemReader() {
        return new FlatFileItemReaderBuilder<MenuDto>()
                .name("menuItemReader")
                .resource(new FileSystemResource(menuJobParameter.getFilePath()))
                .encoding("UTF-8")
                .delimited()
                .delimiter(",")
                .names(PLACE_ID, MENU_GROUP, MENU_NAME, PRICE, DESCRIPTION)
                .linesToSkip(1)
                .fieldSetMapper(new MenuDtoFieldSetMapper())
                .build();
    }
}
