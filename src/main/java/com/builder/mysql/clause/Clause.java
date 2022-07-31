package com.builder.mysql.clause;

import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;

public interface Clause {
    String getClause() throws MissingClauseException, EmptyColumnException;
}
