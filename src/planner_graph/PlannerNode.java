package planner_graph;

import planner_data.VisitorData;

import java.util.ArrayList;

public class PlannerNode {
    public final long id;
    public final int depth;
    public final PlannerNode parent;
    public final ArrayList<PlannerNode> children = new ArrayList<>(10);

    public final VisitorData data;

    PlannerNode(long id, int depth, PlannerNode parent, VisitorData data){
        this.id = id;
        this.parent = parent;
        this.data = data;
        this.depth = depth;

        /// Add 'this' to the parent node as a child
        if(parent != null){
            parent.children.add(this);
        }
    }

    /**
     * Get the action associated with the Node
     * @return Current tail action (Previous action that connects with the previous node)
     */
    public String getCurrentAction(){
        if(this.data.plan.length == 0){
            System.out.printf("Node %d does not have an action%n", this.id);
            return "";
        }

        return this.data.getCurrentAction();
    }

    /**
     * Get the state associated with the current node
     * @return Current state
     */
    public String[] getState(){
        return this.data.state;
    }

    @Override
    public String toString() {
        return """
                %n************** Node Data ************
                 NodeID: %d
                 Depth: %d
                 ParentID: %d
                 Tail-Action: %s
                *************************************%n
                """.formatted(this.id, this.depth, this.parent.id, this.getCurrentAction());
    }
}
