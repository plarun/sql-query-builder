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
                .onTable("customer")
                .set("age", "mail")
                .where(clause -> clause.eq("name").and().eq("id"))
                .getQuery();
        String expected = "Update customer Set age = ?, mail = ? Where name = ? And id = ?";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update statement with table reference")
    public void test2() throws MissingTableException, MissingClauseException, EmptyColumnException {
        String actual = mysql.update()
                .onTable("schema", "customer")
                .set("age", "mail")
                .where(clause -> clause.eq("name").and().in("role", 2)
                        .orWrap()
                        .eq("mail").and().ltEq("age"))
                .getQuery();
        String expected = "Update schema.customer Set age = ?, mail = ? " +
                "Where (name = ? And role In (?,?)) Or (mail = ? And age <= ?)";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update statement with multiple table reference")
    public void test4() throws MissingTableException, MissingClauseException, EmptyColumnException {
        String actual = mysql.update()
                .onTable(ref -> ref.tbl("t1", "a")
                        .leftJoinUsing("temp1", "t1", using -> using.columns("c1"))
                        .leftJoinUsing("temp2", "t2", using -> using.columns("c1"))
                ).set("age")
                .getQuery();
        String expected = "Update t1 a Left Join temp1 t1 Using (c1) Left Join temp2 t2 Using (c1) " +
                "Set age = ?";

        assertEquals(expected, actual);
    }
}
