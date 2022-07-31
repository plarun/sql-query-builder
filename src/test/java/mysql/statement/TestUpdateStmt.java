package mysql.statement;

import com.builder.mysql.MysqlBuilder;
import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import com.builder.mysql.exception.MissingTableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUpdateStmt {
    private MysqlBuilder mysql;

    @BeforeEach
    void setMysql() {
        mysql = new MysqlBuilder();
    }

    @Test
    @DisplayName("Update statement")
    public void test1() throws MissingTableException, MissingClauseException, EmptyColumnException {
        String actual = mysql.update()
                .table("customer")
                .set("age", "mail")
                .where(cond -> cond.eq("name").and().eq("id"))
                .getQuery();
        String expected = "Update customer Set age = ?, mail = ? Where name = ? And id = ?";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update statement with table reference")
    public void test2() throws MissingTableException, MissingClauseException, EmptyColumnException {
        String actual = mysql.update()
                .table("customer")
                .set("age", "mail")
                .where(cond -> cond.eq("name").and().in("role", 2)
                        .orWrap()
                        .eq("mail").and().ltEq("age"))
                .limit(10, 2)
                .getQuery();
        String expected = "Update customer Set age = ?, mail = ? " +
                "Where (name = ? And role In (?,?)) Or (mail = ? And age <= ?) Limit 10, 2";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update statement with multiple table reference")
    public void test4() throws MissingTableException, MissingClauseException, EmptyColumnException {
        String actual = mysql.update()
                .table(ref -> ref.tbl("customer", "cust")
                        .leftJoinUsing("temp1", "t1", using -> using.columns("c1"))
                        .leftJoinUsing("temp2", "t2", using -> using.columns("c1"))
                ).set("cust.age")
                .order("cust.name")
                .limit(5)
                .getQuery();
        String expected = "Update customer cust Left Join temp1 t1 Using (c1) Left Join temp2 t2 Using (c1) " +
                "Set cust.age = ? Order By cust.name Limit 5";

        assertEquals(expected, actual);
    }
}
