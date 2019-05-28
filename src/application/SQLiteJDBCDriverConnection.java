package application;

import java.sql.*;
import java.util.ArrayList;
import radixTree.RadixTree;

public class SQLiteJDBCDriverConnection {
    Connection connection = null;

    public SQLiteJDBCDriverConnection(Connection conn) {
        this.connection = conn;

    }

    public SQLiteJDBCDriverConnection() {

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Connect to the database, must call first
     */
    public void connect() {
        try {
            String url = "jdbc:sqlite:resources/dict.sqlite";
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Disconnect to the data base, call lastest
     */
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }

    }

    /**
     * Execute the sql query, suitable for INSERT and DELETE query
     *
     * @param sql
     */
    public void executeSQL(String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Execute the sql query, suitable for SELECT query
     *
     * @param sql
     * @return An ArrayList of word arcoding to
     */
    public RadixTree<Word> executeSQLSelectQuery(String sql) {
        RadixTree<Word> dictionary = new RadixTree<Word>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String wordTarget = resultSet.getString("WORD_TARGET");
                String wordMeaning = resultSet.getString("WORD_MEANING");
                Word tempWord = new Word(id, wordTarget, wordMeaning);
                dictionary.put(wordTarget,  tempWord);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return dictionary;
    }

    public void addWordDictionary(String wordTarget, String wordMeaning) {
        this.executeSQL("INSERT INTO dictionary_e_v (WORD_TARGET, WORD_MEANING) VALUES ('" + wordTarget + "', '" + wordMeaning + "');");
    }

    public void editWordDictionary(int id, String wordTarget, String wordMeaning) {
        this.executeSQL("UPDATE dictionary_e_v SET WORD_TARGET = '"+wordTarget+"', WORD_MEANING = '"+ wordMeaning+ "' WHERE ID = " + id + " ;");
    }

    public void deleteWordDictionary(int id) {
        this.executeSQL("DELETE FROM dictionary_e_v WHERE ID = " + id + ";");
    }

    public RadixTree<Word> initialDictionary(){
        return this.executeSQLSelectQuery("SELECT * FROM dictionary_e_v ");
    }


    public static void main(String[] args) {
        SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
        sqLiteJDBCDriverConnection.connect();
        sqLiteJDBCDriverConnection.disconnect();
    }
}
