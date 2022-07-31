package mysql.clause;

import com.builder.mysql.clause.FromClause;
import com.builder.mysql.common.TableReference;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class TestFromClause {

    String from(Function<TableReference, TableReference> clause) {
        FromClause fromClause = new FromClause(clause);
        return fromClause.getClause();
    }

    @Test
    @DisplayName("Inner Join without keyword")
    public void innerTest1() {
        String expected = "From ta a, tb b";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .tbl("tb", "b"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Inner Join without join specification")
    public void innerTest2() {
        String expected = "From ta a Inner Join tb b";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .innerJoin("tb", "b"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Inner Join with On")
    public void innerTest3() {
        String expected = "From ta a Inner Join tb b On (a.c1 = b.c1 And a.c2 = b.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .innerJoinOn("tb", "b", on -> on.eq("a.c1", "b.c1").and().eq("a.c2", "b.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Inner Join with Using")
    public void innerTest4() {
        String expected = "From ta a Inner Join tb b Using (c1, c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .innerJoinUsing("tb", "b", using -> using.columns("c1", "c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple Inner Join with On")
    public void innerTest5() {
        String expected = "From ta a Inner Join tb b Inner Join tc c On (a.c1 = c.c1 And b.c2 = c.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .innerJoin("tb", "b")
                .innerJoinOn("tc", "c", on -> on.eq("a.c1", "c.c1").and().eq("b.c2", "c.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple Inner Join with multiple On")
    public void innerTest6() {
        String expected = "From ta a Inner Join tb b On (a.c1 = b.c1) Inner Join tc c On (b.c2 = c.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .innerJoinOn("tb", "b", on -> on.eq("a.c1", "b.c1"))
                .innerJoinOn("tc", "c", on -> on.eq("b.c2", "c.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Cross Join without join specification")
    public void crossTest1() {
        String expected = "From ta a Cross Join tb b";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .crossJoin("tb", "b"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Cross Join with On")
    public void crossTest2() {
        String expected = "From ta a Cross Join tb b On (a.c1 = b.c1 And a.c2 = b.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .crossJoinOn("tb", "b", on -> on.eq("a.c1", "b.c1").and().eq("a.c2", "b.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Cross Join with Using")
    public void crossTest3() {
        String expected = "From ta a Cross Join tb b Using (c1, c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .crossJoinUsing("tb", "b", using -> using.columns("c1", "c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple Cross Join with On")
    public void crossTest4() {
        String expected = "From ta a Cross Join tb b Cross Join tc c On (a.c1 = c.c1 And b.c2 = c.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .crossJoin("tb", "b")
                .crossJoinOn("tc", "c", on -> on.eq("a.c1", "c.c1").and().eq("b.c2", "c.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple Cross Join with multiple On")
    public void crossTest5() {
        String expected = "From ta a Cross Join tb b On (a.c1 = b.c1) Cross Join tc c On (b.c2 = c.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .crossJoinOn("tb", "b", on -> on.eq("a.c1", "b.c1"))
                .crossJoinOn("tc", "c", on -> on.eq("b.c2", "c.c2")));

        assertEquals(expected, actual);
    }

    //
    @Test
    @DisplayName("straight Join without join specification")
    public void straightTest1() {
        String expected = "From ta a Straight_Join tb b";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .straightJoin("tb", "b"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("straight Join with On")
    public void straightTest2() {
        String expected = "From ta a Straight_Join tb b On (a.c1 = b.c1 And a.c2 = b.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .straightJoinOn("tb", "b", on -> on.eq("a.c1", "b.c1").and().eq("a.c2", "b.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("straight Join with Using")
    public void straightTest3() {
        String expected = "From ta a Straight_Join tb b Using (c1, c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .straightJoinUsing("tb", "b", using -> using.columns("c1", "c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple straight Join with On")
    public void straightTest4() {
        String expected = "From ta a Straight_Join tb b Straight_Join tc c On (a.c1 = c.c1 And b.c2 = c.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .straightJoin("tb", "b")
                .straightJoinOn("tc", "c", on -> on.eq("a.c1", "c.c1").and().eq("b.c2", "c.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple straight Join with multiple On")
    public void straightTest5() {
        String expected = "From ta a Straight_Join tb b On (a.c1 = b.c1) Straight_Join tc c On (b.c2 = c.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .straightJoinOn("tb", "b", on -> on.eq("a.c1", "b.c1"))
                .straightJoinOn("tc", "c", on -> on.eq("b.c2", "c.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Left Join with On")
    public void leftTest1() {
        String expected = "From ta a Left Join tb b On (a.c1 = b.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .leftJoinOn("tb", "b", on -> on.eq("a.c1", "b.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Left Join with Using")
    public void leftTest2() {
        String expected = "From ta a Left Join tb b Using (c1)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .leftJoinUsing("tb", "b", using -> using.columns("c1")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple Left Join")
    public void leftTest3() {
        String expected = "From ta a Left Join tb b Using (c1) Left Join tc c On (a.c3 = c.c3)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .leftJoinUsing("tb", "b", using -> using.columns("c1"))
                .leftJoinOn("tc", "c", on -> on.eq("a.c3", "c.c3")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Right Join with On")
    public void rightTest1() {
        String expected = "From ta a Right Join tb b On (a.c1 = b.c2)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .rightJoinOn("tb", "b", on -> on.eq("a.c1", "b.c2")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Right Join with Using")
    public void rightTest2() {
        String expected = "From ta a Right Join tb b Using (c1)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .rightJoinUsing("tb", "b", using -> using.columns("c1")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple Right Join")
    public void rightTest3() {
        String expected = "From ta a Right Join tb b Using (c1) Right Join tc c On (a.c3 = c.c3)";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .rightJoinUsing("tb", "b", using -> using.columns("c1"))
                .rightJoinOn("tc", "c", on -> on.eq("a.c3", "c.c3")));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Natural Inner")
    public void naturalTest1() {
        String expected = "From ta a Natural Inner Join tb b";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .naturalInnerJoin("tb", "b"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Natural Left")
    public void naturalTest2() {
        String expected = "From ta a Natural Left Join tb b";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .naturalLeftJoin("tb", "b"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Natural Right")
    public void naturalTest3() {
        String expected = "From ta a Natural Right Join tb b";
        String actual = from(clause -> clause
                .tbl("ta", "a")
                .naturalRightJoin("tb", "b"));

        assertEquals(expected, actual);
    }
}
