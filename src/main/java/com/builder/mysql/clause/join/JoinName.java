package com.builder.mysql.clause.join;

public enum JoinName {
    LEFT_JOIN("Left Join"),
    RIGHT_JOIN("Right Join"),
    INNER_JOIN("Inner Join"),
    CROSS_JOIN("Cross Join"),
    STRAIGHT_JOIN("Straight_Join"),
    NATURAL_INNER_JOIN("Natural Inner Join"),
    NATURAL_LEFT_JOIN("Natural Left Join"),
    NATURAL_RIGHT_JOIN("Natural Right Join");

    private final String name;

    JoinName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
