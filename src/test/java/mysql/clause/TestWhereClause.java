package mysql.clause;

import com.builder.mysql.clause.WhereClause;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWhereClause {
    @Test
    @DisplayName("single condition")
    public void singleCondition() {
        String actual = new WhereClause(cond -> cond
                .eq("col")
                .or().notEq("col")
                .or().lt("col")
                .or().ltEq("col")
                .or().gt("col")
                .or().gtEq("col")
                .or().in("col", 5)
                .or().notIn("col", 1)
                .or().is("col", true)
                .or().is("col", false)
                .or().is("col", null)
                .or().like("col")
                .or().between("col")
        ).getClause();
        String expected = "Where col = ? Or col <> ? Or col < ? Or col <= ? Or col > ? Or col >= ? Or col In (?,?,?,?,?) Or col Not In (?) Or col Is TRUE Or col Is FALSE Or col Is NULL Or col Like ? Or col Between ? And ?";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("multiple condition with andCondition")
    public void multipleConditionWithAndCondition() {
        String actual = new WhereClause(cond -> cond
                .eq("col")
                .and().in("col", 2)
                .andWrap()
                .eq("col")
                .and().notIn("col", 3)
                .andWrap()
                .notEq("col", "val")
        ).getClause();
        String expected = "Where (col = ? And col In (?,?)) And (col = ? And col Not In (?,?,?)) And (col <> val)";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("multiple condition with orCondition")
    public void multipleConditionWithOrCondition() {
        String actual = new WhereClause(cond -> cond
                .eq("col")
                .and().in("col", 2)
                .orWrap()
                .eq("col")
                .and().notIn("col", 3)
        ).getClause();
        String expected = "Where (col = ? And col In (?,?)) Or (col = ? And col Not In (?,?,?))";

        assertEquals(expected, actual);
    }
}
