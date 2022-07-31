package com.builder.mysql.common;

import com.builder.mysql.clause.OnClause;
import com.builder.mysql.clause.UsingClause;
import com.builder.mysql.clause.join.*;

import java.util.function.Function;

public class TableReference {
    private final StringBuilder query;

    public TableReference() {
        query = new StringBuilder();
    }

    private void add(Table ref) {
        if (query.length() != 0)
            query.append(", ");
        query.append(ref);
    }

    public TableReference tbl(String name) {
        query.append(new Table(name));
        return this;
    }

    public TableReference tbl(String name, String alias) {
        add(new Table(name, alias));
        return this;
    }

    public TableReference stbl(String schema, String name) {
        Table table = new Table(name);
        table.setSchema(schema);
        add(table);
        return this;
    }

    public TableReference stbl(String schema, String name, String alias) {
        Table table = new Table(name, alias);
        table.setSchema(schema);
        add(table);
        return this;
    }

    public TableReference innerJoin(String name, String alias) {
        InnerJoin join = new InnerJoin(name, alias);
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference innerJoinOn(String name, String alias, Function<OnClause, OnClause> on) {
        InnerJoin join = new InnerJoin(name, alias, on.apply(new OnClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference innerJoinUsing(String name, String alias, Function<UsingClause, UsingClause> using) {
        InnerJoin join = new InnerJoin(name, alias, using.apply(new UsingClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference crossJoin(String name, String alias) {
        CrossJoin join = new CrossJoin(name, alias);
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference crossJoinOn(String name, String alias, Function<OnClause, OnClause> on) {
        CrossJoin join = new CrossJoin(name, alias, on.apply(new OnClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference crossJoinUsing(String name, String alias, Function<UsingClause, UsingClause> using) {
        CrossJoin join = new CrossJoin(name, alias, using.apply(new UsingClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference straightJoin(String name, String alias) {
        StraightJoin join = new StraightJoin(name, alias);
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference straightJoinOn(String name, String alias, Function<OnClause, OnClause> on) {
        StraightJoin join = new StraightJoin(name, alias, on.apply(new OnClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference straightJoinUsing(String name, String alias, Function<UsingClause, UsingClause> using) {
        StraightJoin join = new StraightJoin(name, alias, using.apply(new UsingClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference leftJoinOn(String name, String alias, Function<OnClause, OnClause> on)  {
        LeftJoin join = new LeftJoin(name, alias, on.apply(new OnClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference leftJoinUsing(String name, String alias, Function<UsingClause, UsingClause> using) {
        LeftJoin join = new LeftJoin(name, alias, using.apply(new UsingClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference rightJoinOn(String name, String alias, Function<OnClause, OnClause> on) {
        RightJoin join = new RightJoin(name, alias, on.apply(new OnClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference rightJoinUsing(String name, String alias, Function<UsingClause, UsingClause> using) {
        RightJoin join = new RightJoin(name, alias, using.apply(new UsingClause()).getClause());
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference naturalInnerJoin(String name, String alias) {
        NaturalInnerJoin join = new NaturalInnerJoin(name, alias);
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference naturalLeftJoin(String name, String alias) {
        NaturalLeftJoin join = new NaturalLeftJoin(name, alias);
        query.append(" ").append(join.getJoin());
        return this;
    }

    public TableReference naturalRightJoin(String name, String alias) {
        NaturalRightJoin join = new NaturalRightJoin(name, alias);
        query.append(" ").append(join.getJoin());
        return this;
    }

    public String getQuery() {
        return query.toString();
    }
}
