package com.builder.mysql.clause.join;

public class NaturalInnerJoin extends NoConditionJoin {
    public NaturalInnerJoin(String tableName, String tableAlias) {
        super(tableName, tableAlias);
        setJoinName();
    }

    @Override
    public void setJoinName() {
        this.joinName = JoinName.NATURAL_INNER_JOIN;
    }
}
