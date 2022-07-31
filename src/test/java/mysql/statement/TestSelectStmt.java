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
    @DisplayName("Test 1")
    public void test1() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.select()
                .columns("cust.name", "cust.id", "cust.age", "cust.mail", "addr.city", "addr.pincode")
                .from(clause -> clause.tbl("customer", "cust")
                        .innerJoin("cust_address", "addr"))
                .where(clause -> clause.eq("cust.addr_id", "addr.id"))
                .order("cust.name Asc", "cust.id Desc")
                .limit(2)
                .getQuery();
        String expected = "Select cust.name, cust.id, cust.age, cust.mail, addr.city, addr.pincode " +
                "From customer cust Inner Join cust_address addr " +
                "Where cust.addr_id = addr.id " +
                "Order By cust.name Asc, cust.id Desc " +
                "Limit 2";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test 2")
    public void test2() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.select()
                .columns("city", "pincode", "Count(*)")
                .from(clause -> clause.tbl("addr_customer"))
                .where(clause -> clause.isNotNull("pincode"))
                .group("city", "pincode")
                .having(clause -> clause.gtEq("Count(*)"))
                .limit(2, 1)
                .getQuery();
        String expected = "Select city, pincode, Count(*) " +
                "From addr_customer " +
                "Where pincode Is Not NULL " +
                "Group By city, pincode " +
                "Having Count(*) >= ? " +
                "Limit 2, 1";

        assertEquals(expected, actual);
    }
}
