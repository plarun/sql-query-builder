package mysql.clause;

import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import com.builder.mysql.statement.SelectStmt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrderClause {
    private SelectStmt selectStmt;

    @BeforeEach
    public void setSelectStmt() {
        selectStmt = new SelectStmt();
    }

    @Test
    public void test1() throws MissingClauseException, EmptyColumnException {
        String actual = selectStmt.columns("*").from(clause -> clause.tbl("t1")).order("c1", "c2 Asc").getQuery();
        String expected = "Select * From t1 Order By c1, c2 Asc";

        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws MissingClauseException, EmptyColumnException {
        String actual = selectStmt.columns("c1", "c2").from(clause -> clause.tbl("t1")).order("1", "2").getQuery();
        String expected = "Select c1, c2 From t1 Order By 1, 2";

        assertEquals(expected, actual);
    }
}
