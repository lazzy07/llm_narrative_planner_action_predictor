package file_handler;

import domain.AvailableDomains;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import planner_data.VisitorData;

import java.io.FileReader;
import java.util.ArrayList;

public class FileHandler {
    private final AvailableDomains domain;
    private final String csvFolderPath;

    public FileHandler(AvailableDomains domain, String csvFolderPath){
        this.domain = domain;
        this.csvFolderPath = csvFolderPath;
    }

    public String getFileName(){
        return this.domain.getDomainName().toLowerCase() + ".csv";
    }

    /**
     * Returns the full path to the CSV file with generated data by using sabre_generator
     * @return corresponding full path to CSV file of the domain selected
     */
    public String getFullPath(){
        String fullPath = this.csvFolderPath + this.getFileName();
        System.out.println("Current folder path: " + fullPath + " for the domain: " + this.domain);
        return fullPath;
    }

    public ArrayList<VisitorData> readCSVFile(){
        String csvFilePath = getFullPath();
        System.out.println("Reading CSV file: " + csvFilePath + " started");

        try (CSVParser parser = new CSVParser(
                new FileReader(csvFilePath),
                CSVFormat.Builder
                        .create()
                        .setHeader()               // autodetect header row
                        .setSkipHeaderRecord(true)
                        .setIgnoreSurroundingSpaces(true)
                        .setTrim(true)
                        .get()
        )){
            ArrayList<VisitorData> records = new ArrayList<>();

            long i = 0;
            for(CSVRecord record: parser){
                records.add(new VisitorData(i, record.get("epistemic"), record.get("plan"), record.get("state"), Integer.parseInt(record.get("distance"))));
                i++;
            }

            return records;
        } catch (Exception e){
            System.out.printf("ERROR: at the CSV parser, CSV File: %s%n", csvFilePath);
            return null;
        }
    }
}
