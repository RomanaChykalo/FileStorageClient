package utils.parser;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVParser {

    private static List<Object[]> usersLoginDataList;

    public static List<Object[]> parseCSVFile(File file) {
        usersLoginDataList = new ArrayList<>();
        readDataFromCSVFile(file);
        return usersLoginDataList;
    }

    private static void readDataFromCSVFile(File file) {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            Object[] line;
            while ((line = reader.readNext()) != null) {
                usersLoginDataList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
