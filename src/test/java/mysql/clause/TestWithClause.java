package mysql.clause;

import com.builder.mysql.MysqlBuilder;
import com.builder.mysql.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWithClause {
    private MysqlBuilder mysql;

    @BeforeEach
    public void setMysql() {
        mysql = new MysqlBuilder();
    }

    @Test
    @DisplayName("With clause")
    public void test1() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.with().as(
                "active_customer",
                select -> select.columns("name", "age")
                        .from(clause -> clause.tbl("customer", "c"))
                        .where(cond -> cond.eq("c.status"))
                        .order("c.name"),
                "name", "age"
        ).getClause();
        String expected = "With active_customer (name, age) As " +
                "(Select name, age From customer c Where c.status = ? Order By c.name)";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("With recursive clause")
    public void test2() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.with().
                recursive()
                .as(
                "active_customer",
                select -> select.columns("name", "age")
                        .from(clause -> clause.tbl("customer", "c"))
                        .where(cond -> cond.eq("c.status"))
                        .order("c.name"),
                "name", "age"
        ).getClause();
        String expected = "With Recursive active_customer (name, age) As " +
                "(Select name, age From customer c Where c.status = ? Order By c.name)";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("With clause followed by Select stmt")
    public void test3() throws MissingClauseException, EmptyColumnException {
        String actual = mysql.with().as(
                "active_customer",
                select -> select.columns("name", "age")
                        .from(clause -> clause.tbl("customer", "c"))
                        .where(cond -> cond.eq("c.status"))
                        .order("c.name"),
                "name", "age"
        ).select()
                .columns("*")
                .from(clause -> clause.tbl("active_customer"))
                .where(cond -> cond.gtEq("age"))
                .getQuery();
        String expected = "With active_customer (name, age) As " +
                "(Select name, age From customer c Where c.status = ? Order By c.name) " +
                "Select * From active_customer Where age >= ?";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("With clause followed by Update stmt")
    public void test4() throws MissingClauseException, EmptyColumnException, MissingTableException {
        String actual = mysql.with()
                .as(
                        "temp1",
                        select -> select.columns("*")
                                .from("customer")
                                .where(cond -> cond.gt("c.id"))
                ).as(
                        "temp2",
                        select -> select.columns("*")
                                .from("customer")
                                .where(cond -> cond.gt("c.id"))
                ).update()
                .table(ref -> ref.tbl("t1", "a")
                        .leftJoinUsing("temp1", "t1", using -> using.columns("c1"))
                        .leftJoinUsing("temp2", "t2", using -> using.columns("c1"))
                ).set("age")
                .getQuery();
        String expected = "With temp1 As (Select * From customer Where c.id > ?), " +
                "temp2 As (Select * From customer Where c.id > ?) " +
                "Update t1 a Left Join temp1 t1 Using (c1) Left Join temp2 t2 Using (c1) " +
                "Set age = ?";

        assertEquals(expected, actual);
    }
}
