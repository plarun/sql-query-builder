package com.builder.mysql;

import com.builder.SqlBuilder;
import com.builder.mysql.statement.*;

public class MysqlBuilder implements SqlBuilder {
    private static final String sqlType = "My-SQL";

    @Override
    public String sqlType() {
        return sqlType;
    }

    public InsertStmt insert() {
        return new InsertStmt();
    }

    public SelectStmt select() {
        return new SelectStmt();
    }

    public UpdateStmt update() {
        return new UpdateStmt();
    }

    public DeleteStmt delete() {
        return new DeleteStmt();
    }
}
