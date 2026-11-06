package com.desafio.model;

/** Representa um parceiro da empresa. */
public class Partner {
    private final String id;
    private final String name;

    public Partner(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
