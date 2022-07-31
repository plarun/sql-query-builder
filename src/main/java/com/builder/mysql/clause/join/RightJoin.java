package com.builder.mysql.clause.join;

public class RightJoin extends RequiredConditionJoin {
    public RightJoin(String tableName, String tableAlias, String joinSpecification) {
        super(tableName, tableAlias, joinSpecification);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.RIGHT_JOIN;
    }
}
