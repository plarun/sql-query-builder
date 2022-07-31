package com.builder.mysql.clause.join;

public abstract class RequiredConditionJoin implements Joinable {
    protected JoinName joinName;
    private final String tableName;
    private final String tableAlias;
    private final String joinSpecification;

    public RequiredConditionJoin(String tableName, String tableAlias, String joinSpecification) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.joinSpecification = joinSpecification;
    }

    abstract void setJoinName();

    @Override
    public String getJoin() {
        StringBuilder sb = new StringBuilder();
        sb.append(joinName).append(" ").append(tableName).append(" ").append(tableAlias);
        sb.append(" ").append(joinSpecification);
        return sb.toString();
    }
}
