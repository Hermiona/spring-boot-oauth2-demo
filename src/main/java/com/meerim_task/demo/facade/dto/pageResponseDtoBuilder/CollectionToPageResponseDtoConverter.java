package com.meerim_task.demo.facade.dto.pageResponseDtoBuilder;

import com.meerim_task.demo.facade.dto.PageInfoDto;
import com.meerim_task.demo.facade.dto.PageResponseDto;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionToPageResponseDtoConverter<T, R> implements PageResponseDtoBuilder {
    private Collection<T> items;
    private Function<T, R> converter;

    public CollectionToPageResponseDtoConverter(Collection<T> items, Function<T, R> converter) {
        this.items = items;
        this.converter = converter;
    }

    @Override
    public PageResponseDto<R> build() {
        return new PageResponseDto<R>(
                (long) items.size(),
                items.stream().map(i -> converter.apply(i)).collect(Collectors.toList()),
                new PageInfoDto(
                        0,
                        items.size()
                ));
    }
}
