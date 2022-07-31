package mysql.statement;

import com.builder.mysql.MysqlBuilder;
import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestSelectStmt {
    private MysqlBuilder mysql;

    @BeforeEach
    void setMysql() {
        mysql = new MysqlBuilder();
    }

    @Test
    @DisplayName("Test 1")
    public void test1() throws MissingClauseException, EmptyColumnException {
        String query = mysql.select()
                .columns("cust.name", "cust.id", "cust.age", "cust.mail", "addr.city", "addr.pincode")
                .from(clause -> clause.stbl("customer", "cust")
                        .innerJoin("cust_address", "addr"))
                .where(clause -> clause.eq("cust.addr_id", "addr.id"))
                .order("cust.name Asc", "cust.id Desc")
                .limit(2)
                .getQuery();

        System.out.println(query);
    }

    @Test
    @DisplayName("Test 2")
    public void test2() throws MissingClauseException, EmptyColumnException {
        String query = mysql.select()
                .columns("addr.city", "addr.pincode", "Count(*)")
                .from(clause -> clause.stbl("customer", "cust"))
                .where(clause -> clause.eq("cust.status"))
                .group("addr.city", "addr.pincode")
                .having(clause -> clause.gtEq("Count(*)"))
                .order("Count(*) Desc")
                .getQuery();

        System.out.println(query);
    }
}
