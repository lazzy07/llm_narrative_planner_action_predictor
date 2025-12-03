package file_handler;

import domain.AvailableDomains;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.Locale;

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

    public void readCSVFile(){
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
            for(CSVRecord row : parser){
                String epistemic = row.get("epistemic");
                System.out.println("Epistemic: " + epistemic);
            }
        } catch (Exception e){
            System.out.println("ERROR: at the CSV parser, CSV File: " + csvFilePath);
            e.printStackTrace();
        }
    }
}
