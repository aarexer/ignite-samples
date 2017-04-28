package com.github.aarexer.dispatcher;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Subscriber {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }
}
