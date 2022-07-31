package mysql.clause;

import com.builder.mysql.clause.OnClause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOnClause {
    private OnClause onClause;

    @BeforeEach
    public void setOnClause() {
        onClause = new OnClause();
    }

    @Test
    public void test1() {
        String actual = onClause.eq("t1.c1", "t2.c2")
                .and().eq("t1.c3", "t2.c3")
                .getClause();
        String expected = "On (t1.c1 = t2.c2 And t1.c3 = t2.c3)";

        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        String actual = onClause.eq("t1.c1", "t2.c2")
                .or().notEq("t1.c3", "t2.c3")
                .getClause();
        String expected = "On (t1.c1 = t2.c2 Or t1.c3 <> t2.c3)";

        assertEquals(expected, actual);
    }
}
