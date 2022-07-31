package com.builder.mysql.clause.join;

public class StraightJoin extends OptionalConditionJoin {
    public StraightJoin(String tableName, String tableAlias) {
        super(tableName, tableAlias);
        setJoinName();
    }

    public StraightJoin(String tableName, String tableAlias, String joinSpecification) {
        super(tableName, tableAlias, joinSpecification);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.STRAIGHT_JOIN;
    }
}
