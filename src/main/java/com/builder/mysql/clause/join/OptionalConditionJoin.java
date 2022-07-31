package com.builder.mysql.clause.join;

public abstract class OptionalConditionJoin implements Joinable {
    protected JoinName joinName;
    private final String tableName;
    private final String tableAlias;
    private final String  joinSpecification;

    public OptionalConditionJoin(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.joinSpecification = null;
    }

    public OptionalConditionJoin(String tableName, String tableAlias, String joinSpecification) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.joinSpecification = joinSpecification;
    }

    abstract void setJoinName();

    @Override
    public String getJoin() {
        StringBuilder sb = new StringBuilder();
        sb.append(joinName).append(" ").append(tableName).append(" ").append(tableAlias);
        if (joinSpecification != null) {
            sb.append(" ").append(joinSpecification);
        }
        return sb.toString();
    }
}
