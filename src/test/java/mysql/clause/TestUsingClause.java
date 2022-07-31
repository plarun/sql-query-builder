package mysql.clause;

import com.builder.mysql.clause.UsingClause;
import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import com.builder.mysql.statement.SelectStmt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUsingClause {
    private UsingClause usingClause;
    private SelectStmt selectStmt;

    @BeforeEach
    public void setUsingClause() {
        usingClause = new UsingClause();
        selectStmt = new SelectStmt();
    }

    @Test
    public void test1() {
        String actual = usingClause
                .columns("c1", "c2", "c3")
                .getClause();
        String expected = "Using (c1, c2, c3)";

        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws MissingClauseException, EmptyColumnException {
        String actual = selectStmt.columns("t1.c1", "t2.c2")
                .from(clause -> clause.tbl("table1", "t1")
                        .innerJoinUsing("table2", "t2", using -> using.columns("c1")))
                .getQuery();
        String expected = "Select t1.c1, t2.c2 From table1 t1 Inner Join table2 t2 Using (c1)";

        assertEquals(expected, actual);
    }
}
