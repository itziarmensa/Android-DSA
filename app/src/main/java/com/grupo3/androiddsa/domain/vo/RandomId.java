package com.grupo3.androiddsa.domain.vo;

import java.io.Serializable;
import java.util.UUID;

public class RandomId implements Serializable {
    public static String getId() {
        return UUID.randomUUID().toString();
    }
}

