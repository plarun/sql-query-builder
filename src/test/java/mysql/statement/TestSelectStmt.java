package mysql.statement;

import com.builder.mysql.MysqlBuilder;
import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSelectStmt {
    private MysqlBuilder mysql;

    @BeforeEach
    void setMysql() {
        mysql = new MysqlBuilder();
    }

    @Test
    @DisplayName("Select statement")
    public void test1() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.select()
                .columns("cust.name", "cust.id", "cust.age", "cust.mail", "addr.city", "addr.pincode")
                .from(clause -> clause.tbl("customer", "cust")
                        .innerJoin("address", "addr"))
                .where(clause -> clause.eq("cust.addr_id", "addr.id"))
                .order("cust.name Asc", "cust.id Desc")
                .limit(2)
                .getQuery();
        String expected = "Select cust.name, cust.id, cust.age, cust.mail, addr.city, addr.pincode " +
                "From customer cust Inner Join address addr " +
                "Where cust.addr_id = addr.id " +
                "Order By cust.name Asc, cust.id Desc " +
                "Limit 2";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Select statement with group and having clause")
    public void test2() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.select()
                .columns("city", "pincode", "Count(*)")
                .from(clause -> clause.tbl("address"))
                .where(clause -> clause.isNotNull("pincode"))
                .group("city", "pincode")
                .having(clause -> clause.gtEq("Count(*)"))
                .limit(2, 1)
                .getQuery();
        String expected = "Select city, pincode, Count(*) " +
                "From address " +
                "Where pincode Is Not NULL " +
                "Group By city, pincode " +
                "Having Count(*) >= ? " +
                "Limit 2, 1";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Select statement with distinct")
    public void test3() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.select()
                .distinct()
                .columns("city")
                .from("address")
                .getQuery();
        String expected = "Select Distinct city From address";

        assertEquals(expected, actual);
    }
}
