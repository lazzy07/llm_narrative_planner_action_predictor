package planner_data;

import java.util.ArrayList;

/**
 * All the data came from visiting the CSV file.
 */
public class VisitorData {
    public final long id;
    public final String epistemic;
    public String[] plan;
    public String[] state;
    public String[] availableActions;
    public final int distance;

    public VisitorData(long id, String epistemic, String planStr, String stateStr, String availableActions, int distance) {
        this.id = id;
        this.epistemic = epistemic;
        this.processPlan(planStr);
        this.processState(stateStr);
        this.distance = distance;
        this.processAvailableActions(availableActions);
    }

    /**
     * Creates the plan as an array of action strings
     * @param planStr Plan as a compound string (e.g: "action1();action2(param1)")
     */
    private void processPlan(String planStr){
        if(planStr.isEmpty()){
            this.plan = new String[0];
            return;
        }

        String[] unsanitizedActionArr = planStr.split(";");
        ArrayList<String> sanitizedActionArrList = this.sanitizeStrArray(unsanitizedActionArr);
        this.plan = ToArray(sanitizedActionArrList, String.class);
    }

    private void processAvailableActions(String planStr){
        if(planStr.isEmpty()){
            this.plan = new String[0];
            return;
        }

        String[] unsanitizedActionArr = planStr.split(";");
        ArrayList<String> sanitizedActionArrList = this.sanitizeStrArray(unsanitizedActionArr);
        this.availableActions = ToArray(sanitizedActionArrList, String.class);
    }

    /**
     * Creates the state as an array of states as strings
     * @param stateStr State as a compound string (e.g: "fluent() = True; fluent2(param1) = False")
     */
    private void processState(String stateStr){
        if(stateStr.isEmpty()){
            System.out.println("ERROR:: An Invalid state found!");
        }

        String[] unsanitizedStateArr = stateStr.split(";");
        ArrayList<String> sanitizedStateArrList = this.sanitizeStrArray(unsanitizedStateArr);
        this.state = ToArray(sanitizedStateArrList, String.class);
    }

    public String getCurrentAction(){
        if(this.plan.length == 0){
            System.out.println("WARNING: VisitorData's plan is empty.");
            return "";
        }

        return this.plan[this.plan.length - 1];
    }

    public String getPreviousActions(){
        if(this.plan.length < 2){
            System.out.println("WARNING: Trying to get previous action stack from a plan < 2 ID:" + this.id);
            return "";
        }

        StringBuilder strBuilder = new StringBuilder();

        for(int i = 0; i< this.plan.length - 1; i++){
            strBuilder.append(this.plan[i]);
        }

        return strBuilder.toString();
    }

    public String getAllActions(){
        if(this.plan.length < 1){
            System.out.println("WARNING: Trying to get previous action stack from a plan < 1 ID:" + this.id);
            return "";
        }

        StringBuilder strBuilder = new StringBuilder();

        for (String s : this.plan) {
            strBuilder.append(s);
        }

        return strBuilder.toString();
    }

    public String getAllState(){
        StringBuilder strBuilder = new StringBuilder();

        for (String s : this.state) {
            strBuilder.append(s);
        }

        return strBuilder.toString();
    }

    public String getAllAvailableActions(){
        StringBuilder strBuilder = new StringBuilder();

        for (String s : this.availableActions) {
            strBuilder.append(s).append("\n");
        }

        return strBuilder.toString();
    }

    private ArrayList<String> sanitizeStrArray(String[] strArray) {
        ArrayList<String> result = new ArrayList<>(strArray.length);

        for (String s : strArray) {
            if (s == null) {
                result.add(null);  // or skip it—your choice
            } else {
                result.add(s.trim());
            }
        }

        return result;
    }

    public static <T> T[] ToArray(ArrayList<T> list, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, list.size());
        return list.toArray(array);
    }
}
