package com.builder.mysql.clause.join;

public class NaturalLeftJoin extends NoConditionJoin {
    public NaturalLeftJoin(String tableName, String tableAlias) {
        super(tableName, tableAlias);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.NATURAL_LEFT_JOIN;
    }
}
