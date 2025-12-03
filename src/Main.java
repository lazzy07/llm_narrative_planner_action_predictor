import domain.AvailableDomains;
import file_handler.FileHandler;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Currently selected domain to be processed.
    public final static AvailableDomains DOMAIN_NAME = AvailableDomains.DEER_HUNTER;

    // Folder path for the .csv data files
    public final static String CSV_FOLDER_PATH = "sabre_generator/output/";

    public static void main(String[] args) {
        System.out.println("\n\n ********************* LLM_Next_Action_Predictor **************************\n\n");
        FileHandler fileHandler = new FileHandler(DOMAIN_NAME, CSV_FOLDER_PATH);
        fileHandler.readCSVFile();
        System.out.println("\n\n ***************************** End ********************************************");
    }
}