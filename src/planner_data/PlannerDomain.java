package planner_data;

import java.util.ArrayList;
import java.util.HashMap;

public class PlannerDomain {
    /// All the parser records that retrieved from the CSV file.
    private final ArrayList<VisitorData> parserRecords;
    /// Main records: AKA Epistemic records. (All the records with epistemic is empty)
    public final ArrayList<VisitorData> mainRecords = new ArrayList<>();
    public final HashMap<Integer, ArrayList<VisitorData>> recordsByPlanLength = new HashMap<Integer, ArrayList<VisitorData>>();

    /// Maximum plan length seen in a plan
    public int maxPlanLength = 0;

    public PlannerDomain(ArrayList<VisitorData> parserRecords) throws Exception {
        this.parserRecords = parserRecords;
        initializeMainRecords();
        validateData();
    }

    /**
     * This function will filter only the Epistemic records (Where the epistemic cell is empty in CSV)
     */
    private void initializeMainRecords(){
        for(VisitorData record : this.parserRecords){
            if(record == null){
                System.out.println("WARNING: A record with null ptr found!");
            }
            assert record != null;
            if(record.epistemic.isEmpty()){
                mainRecords.add(record);
                int planLength = record.plan.length;
                ArrayList<VisitorData> data = recordsByPlanLength.get(planLength);

                if(data == null){
                    data = new ArrayList<>();

                    if(this.maxPlanLength < planLength){
                        this.maxPlanLength = planLength;
                    }
                }
                // Add the record to the corresponding bucket (Plan length)
                data.add(record);
                recordsByPlanLength.put(planLength, data);
            }
        }

        System.out.printf("# %d of Epistemic records added.%n", mainRecords.size());
    }

    /**
     * Get the root node of the domain (Initial state and other data associated with it)
     * @return The root node of the domain (Initial state)
     */
    public VisitorData getRootNodeData(){
        return this.mainRecords.getFirst();
    }

    /**
     * Validate if the data is correct,
     * I'm using the recordsByPlanLength to check this, since the keys must start at 0 and go up to #maxPlanLength (0, .... 10), without any missing numbers
     */
    private void validateData() throws Exception{
        for(int i=0; i <= maxPlanLength; i++){
            if(recordsByPlanLength.get(i) == null){
                throw new Exception("ERROR: Data validation did not pass");
            }
        }
        System.out.println("Data validation is SUCCESSFUL");

    }
}
