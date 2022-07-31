package mysql.statement;

import com.builder.mysql.MysqlBuilder;
import com.builder.mysql.exception.MissingClauseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeleteStmt {
    private MysqlBuilder mysql;

    @BeforeEach
    void setMysql() {
        mysql = new MysqlBuilder();
    }

    @Test
    @DisplayName("Delete statement")
    public void test1() throws MissingClauseException {
        String actual = mysql.delete()
                .from(clause -> clause.tbl("customer"))
                .where(clause -> clause.eq("name").and().eq("id"))
                .getQuery();
        String expected = "Delete From customer Where name = ? And id = ?";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Delete statement with order and limit")
    public void test2() throws MissingClauseException {
        String actual = mysql.delete()
                .from(clause -> clause.tbl("customer"))
                .where(clause -> clause.eq("name").and().eq("id"))
                .order("id", "name")
                .limit(4)
                .getQuery();
        String expected = "Delete From customer Where name = ? And id = ? Order By id, name Limit 4";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Delete statement with order and limit")
    public void test3() throws MissingClauseException {
        String actual = mysql.delete()
                .ref("cust", "addr")
                .from(clause -> clause.tbl("customer", "cust")
                        .innerJoin("cust_address", "addr"))
                .where(clause -> clause.eq("cust.addr_id", "addr.id").and().eq("cust.id"))
                .getQuery();
        String expected = "Delete cust, addr From customer cust Inner Join cust_address addr " +
                "Where cust.addr_id = addr.id And cust.id = ?";

        assertEquals(expected, actual);
    }
}
