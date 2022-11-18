package com.jaddy.calendarresourceoauth.utils;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

public class PostgreSQL94CustomDialect extends PostgreSQL94Dialect {
    public PostgreSQL94CustomDialect() {
        super();
        registerColumnType(Types.JAVA_OBJECT, "java json object");
        registerColumnType(Types.ARRAY, "authorities object");
        registerColumnType(Types.TIMESTAMP_WITH_TIMEZONE, "timestamp with time zone");
    }
}
