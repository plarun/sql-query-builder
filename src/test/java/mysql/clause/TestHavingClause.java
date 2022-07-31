package mysql.clause;

import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import com.builder.mysql.statement.SelectStmt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHavingClause {
    private SelectStmt selectStmt;

    @BeforeEach
    public void setSelectStmt() {
        selectStmt = new SelectStmt();
    }

    @Test
    public void test1() throws MissingClauseException, EmptyColumnException {
        String actual = selectStmt
                .columns("c1", "c2", "Count(*)")
                .from("t1")
                .group("c1", "c2")
                .having(cond -> cond.gt("Count(*)"))
                .getQuery();
        String expected = "Select c1, c2, Count(*) From t1 Group By c1, c2 Having Count(*) > ?";

        assertEquals(expected, actual);
    }
}
