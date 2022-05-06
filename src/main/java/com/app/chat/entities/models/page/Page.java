package com.app.chat.entities.models.page;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Getter
@Builder
public class Page<T> {

    int offset;
    int limit;
    long total;
    Collection<T> content;
}
