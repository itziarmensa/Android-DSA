package com.itziar.androiddsa.domain.vo;

import java.util.UUID;

public class RandomId {
    public static String getId() {
        return UUID.randomUUID().toString();
    }
}

