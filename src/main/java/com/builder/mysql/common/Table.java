package com.builder.mysql.common;

public class Table {
    private final String name;
    private final String alias;

    public Table(String name) {
        this.name = name;
        this.alias = null;
    }

    public Table(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (alias != null)
            sb.append(" ").append(alias);
        return sb.toString();
    }
}
