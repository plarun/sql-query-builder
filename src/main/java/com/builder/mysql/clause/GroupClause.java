package com.builder.mysql.clause;

import java.util.List;

public class GroupClause implements Clause {
    private static final String clauseName = "Group By";
    private final List<String> groupList;

    public GroupClause(String... groupList) {
        this.groupList = List.of(groupList);
    }

    @Override
    public String getClause() {
        StringBuilder query = new StringBuilder();
        query.append(clauseName).append(" ");

        String csv = String.join(", ", groupList);
        query.append(csv);

        return query.toString();
    }
}
