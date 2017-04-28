package com.github.aarexer.examples.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class EntityKey {
    private final String name;
    private final int orderTo;
    private final int orderFrom;
}
