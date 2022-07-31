package com.builder.mysql.statement;

import com.builder.mysql.exception.*;

public interface QueryStmt {
    String getQuery() throws QueryBuildException, MissingClauseException, EmptyColumnException, MissingTableException;
}
