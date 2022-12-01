package com.grupo3.androiddsa.domain.to;

import java.io.Serializable;

public class ObjectRecycler implements Serializable {

    String name;
    String description;

    public ObjectRecycler(){}

    public ObjectRecycler(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
