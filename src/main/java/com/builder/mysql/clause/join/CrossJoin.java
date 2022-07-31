package com.builder.mysql.clause.join;

public class CrossJoin extends OptionalConditionJoin {
    public CrossJoin(String tableName, String tableAlias) {
        super(tableName, tableAlias);
        setJoinName();
    }

    public CrossJoin(String tableName, String tableAlias, String joinSpecification) {
        super(tableName, tableAlias, joinSpecification);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.CROSS_JOIN;
    }
}
