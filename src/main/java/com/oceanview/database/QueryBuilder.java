package com.oceanview.database;

// Utility class to build SQL queries
public class QueryBuilder {

    // Build SELECT query
    public static String buildSelectQuery(String tableName, String columns) {
        return "SELECT " + columns + " FROM " + tableName;
    }

    // Build SELECT query with WHERE clause
    public static String buildSelectQueryWithWhere(String tableName, String columns, String whereClause) {
        return buildSelectQuery(tableName, columns) + " WHERE " + whereClause;
    }

    // Build INSERT query
    public static String buildInsertQuery(String tableName, String columns, String values) {
        return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
    }

    // Build UPDATE query
    public static String buildUpdateQuery(String tableName, String setClause, String whereClause) {
        return "UPDATE " + tableName + " SET " + setClause + " WHERE " + whereClause;
    }

    // Build DELETE query
    public static String buildDeleteQuery(String tableName, String whereClause) {
        return "DELETE FROM " + tableName + " WHERE " + whereClause;
    }
}
