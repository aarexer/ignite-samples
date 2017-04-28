package com.github.aarexer.dispatcher;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@RequiredArgsConstructor
public class Event implements Serializable {
    private final String name;
}
