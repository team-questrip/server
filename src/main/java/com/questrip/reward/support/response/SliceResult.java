package com.questrip.reward.support.response;

import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class SliceResult<T> {
    private List<T> content;
    private int page;
    private int size;
    private int numberOfElements;
    private boolean hasNext;

    public SliceResult(Slice<T> slice) {
        this.content = slice.getContent();
        this.page = slice.getNumber();
        this.size = slice.getSize();
        this.numberOfElements = slice.getNumberOfElements();
        this.hasNext = slice.hasNext();
    }

    public SliceResult(List<T> content, int page, int size, int numberOfElements, boolean hasNext) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.numberOfElements = numberOfElements;
        this.hasNext = hasNext;
    }

    public <U> SliceResult<U> map(Function<? super T, ? extends U> converter) {
        return new SliceResult<>(getConvertedContent(converter), page, size, numberOfElements, hasNext);
    }

    private <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
        return this.content.stream().map(converter::apply).collect(Collectors.toList());
    }
}