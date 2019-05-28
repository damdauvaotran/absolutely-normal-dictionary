package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.*;
/**
 * This is a minitool to convert from text to sqlite database
 * @author MyPC
 *
 */
public class CreateNewDb {
	public static void main(String[] args) {

		try {
			File fileDir = new File("D:\\OOP\\kap\\resources\\V_E.txt");
			SQLiteJDBCDriverConnection driverConnection = new SQLiteJDBCDriverConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));

			driverConnection.connect();
			String str;
			String wordRegex = "^.*(?=\\<html\\>)";
			String meaningRegex = "\\<html\\>.*\\<\\/html\\>";
			int counter = 0;
			while ((str = in.readLine()) != null) {

				Pattern wordPattern = Pattern.compile(wordRegex);
				Pattern meaningPattern = Pattern.compile(meaningRegex);

				Matcher wordMatcher = wordPattern.matcher(str);
				Matcher meaningMatcher = meaningPattern.matcher(str);
				StringBuilder sql = new StringBuilder();
				if (wordMatcher.find() && meaningMatcher.find()) {
					sql.append("INSERT INTO dictionary_v_e (WORD_TARGET,WORD_MEANING) ").append("VALUES ('")
							.append(wordMatcher.group().replaceAll("\'", "\'\'")).append("','")
							.append(meaningMatcher.group().replaceAll("\'", "\'\'")).append("' );");
				}
				counter++;
				if (counter % 1000 == 0) {
					System.out.println(counter);
				}
				driverConnection.executeSQL(sql.toString());
			}
			driverConnection.commit();
			driverConnection.disconnect();
			in.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

