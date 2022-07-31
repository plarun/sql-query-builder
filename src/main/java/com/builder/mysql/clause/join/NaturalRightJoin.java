package com.builder.mysql.clause.join;

public class NaturalRightJoin extends NoConditionJoin {
    public NaturalRightJoin(String tableName, String tableAlias) {
        super(tableName, tableAlias);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.NATURAL_RIGHT_JOIN;
    }
}
