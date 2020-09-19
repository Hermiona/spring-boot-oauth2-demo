package com.meerim_task.demo.mapper.pageResponseDtoBuilder;

import com.meerim_task.demo.facade.dto.PageInfoDto;
import com.meerim_task.demo.facade.dto.PageResponseDto;
import org.springframework.data.domain.Page;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PageToPageResponseDtoConverter<T, R> implements PageResponseDtoBuilder {
    Page<T> page;
    Function<T, R> converter;

    public PageToPageResponseDtoConverter(Page<T> page, Function<T, R> converter) {
        this.page = page;
        this.converter = converter;
    }

    @Override
    public PageResponseDto<R> build() {
        return new PageResponseDto<R>(
                page.getTotalElements(),
                page.getContent().stream().map(i -> converter.apply(i)).collect(Collectors.toList()),
                new PageInfoDto(
                        page.getPageable().getPageNumber(),
                        page.getNumberOfElements()
                ));
    }
}
