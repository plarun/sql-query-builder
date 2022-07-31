package com.builder.mysql.clause.join;

public class LeftJoin extends RequiredConditionJoin {
    public LeftJoin(String tableName, String tableAlias, String joinSpecification) {
        super(tableName, tableAlias, joinSpecification);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.LEFT_JOIN;
    }
}
