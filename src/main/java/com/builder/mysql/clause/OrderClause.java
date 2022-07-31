package com.builder.mysql.clause;

import java.util.List;

public class OrderClause implements Clause {
    private final List<String> orderList;
    private static final String clauseName = "Order By";

    public OrderClause(String... orderList) {
        this.orderList = List.of(orderList);
    }

    @Override
    public String getClause() {
        StringBuilder query = new StringBuilder();
        query.append(clauseName).append(" ");

        String csv = String.join(", ", orderList);
        query.append(csv);

        return query.toString();
    }
}
