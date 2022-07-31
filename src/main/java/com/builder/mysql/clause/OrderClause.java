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
        StringBuilder sb = new StringBuilder();
        sb.append(clauseName).append(" ");
        boolean flag = false;
        for (String order : orderList) {
            if (!flag) {
                sb.append(order);
                flag = true;
            } else {
                sb.append(", ").append(order);
            }
        }
        return sb.toString();
    }
}
