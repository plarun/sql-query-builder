package mysql.statement;

import com.builder.mysql.MysqlBuilder;
import com.builder.mysql.exception.MissingTableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInsertStmt {
    private MysqlBuilder mysql;

    @BeforeEach
    void setMysql() {
        mysql = new MysqlBuilder();
    }

    @Test
    @DisplayName("Insert stmt without columns")
    public void insertWithoutColumns() throws MissingTableException {
        String query = mysql.insert()
                .intoTable("schema", "customer")
                .columns(5)
                .setRows(3)
                .getQuery();
        String expected = "Insert Into schema.customer Values (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";

        assertEquals(expected, query);
    }

    @Test
    @DisplayName("Insert stmt with columns")
    public void insertWithColumns() throws MissingTableException {
        String query = mysql.insert()
                .intoTable("schema", "customer")
                .columns("id", "name", "age", "email", "gender")
                .setRows(3)
                .getQuery();
        String expected = "Insert Into schema.customer (id, name, age, email, gender) Values (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";

        assertEquals(expected, query);
    }

    @Test
    @DisplayName("Table without schema")
    public void tableWithoutSchema() throws MissingTableException {
        String query = mysql.insert()
                .intoTable("customer")
                .columns(5)
                .setRows(3)
                .getQuery();
        String expected = "Insert Into customer Values (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";

        assertEquals(expected, query);
    }
}
