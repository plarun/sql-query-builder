package com.builder.mysql.clause.join;

public class InnerJoin extends OptionalConditionJoin {
    public InnerJoin(String tableName, String tableAlias) {
        super(tableName, tableAlias);
        setJoinName();
    }

    public InnerJoin(String tableName, String tableAlias, String joinSpecification) {
        super(tableName, tableAlias, joinSpecification);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.INNER_JOIN;
    }
}
