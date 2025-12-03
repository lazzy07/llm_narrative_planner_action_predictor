import domain.AvailableDomains;
import file_handler.FileHandler;
import planner_data.PlannerDomain;
import planner_data.VisitorData;
import planner_graph.PlannerGraph;
import prompt_builder.SabrePromptBuilder;

import java.util.ArrayList;

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
        ArrayList<VisitorData> records = fileHandler.readCSVFile();
        if(records == null){
            System.out.println("CSV File parsing is INCOMPLETE");
            return;
        }

        System.out.println("CSV File parsing is DONE");
        PlannerDomain plannerDomain = null;
        try {
            plannerDomain = new PlannerDomain(records);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PlannerGraph plannerGraph = new PlannerGraph(plannerDomain);
        SabrePromptBuilder promptBuilder = new SabrePromptBuilder(plannerDomain, plannerGraph);
        promptBuilder.generatePromptFromGraph();

        System.out.println("\n\n ***************************** End ********************************************");
    }
}