package com.builder.mysql.clause.join;

public abstract class NoConditionJoin implements Joinable {
    protected JoinName joinName;
    private final String tableName;
    private final String tableAlias;

    public NoConditionJoin(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    abstract void setJoinName();

    @Override
    public String getJoin() {
        StringBuilder sb = new StringBuilder();
        sb.append(joinName).append(" ").append(tableName).append(" ").append(tableAlias);
        return sb.toString();
    }
}
