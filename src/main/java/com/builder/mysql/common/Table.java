package com.builder.mysql.common;

public class Table {
    private String schema;
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

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (schema != null)
            sb.append(schema).append(".");
        sb.append(name);
        if (alias != null)
            sb.append(" ").append(alias);
        return sb.toString();
    }
}
