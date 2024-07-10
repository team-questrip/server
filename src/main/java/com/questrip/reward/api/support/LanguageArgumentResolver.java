package com.questrip.reward.api.support;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LanguageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Language.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String language = webRequest.getParameter("language");
        if (StringUtils.isBlank(language)) {
            Language annotation = parameter.getParameterAnnotation(Language.class);
            language = annotation.defaultValue();
        }

        return normalizeLanguage(language);
    }

    private String normalizeLanguage(String language) {
        if (language == null) {
            return "EN";
        }

        language = language.trim().toUpperCase();

        if (language.startsWith("ZH-CHS") || language.startsWith("ZH")) {
            return "ZH";
        }

        return language;
    }
}