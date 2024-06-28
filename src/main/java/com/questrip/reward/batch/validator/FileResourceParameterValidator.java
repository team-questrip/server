package com.questrip.reward.batch.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class FileResourceParameterValidator implements JobParametersValidator {

    public final static String FILE_PATH = "filePath";

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String filePath = parameters.getString(FILE_PATH);

        if (!StringUtils.hasText(filePath)){
            throw new JobParametersInvalidException("parameter is empty");
        }
    }
}
