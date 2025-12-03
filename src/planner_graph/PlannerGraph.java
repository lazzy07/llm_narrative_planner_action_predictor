package planner_graph;

import planner_data.PlannerDomain;
import planner_data.VisitorData;

import java.util.ArrayList;
import java.util.HashMap;

public class PlannerGraph {
    public final PlannerNode root;
    public long noOfprocessedNodes = 0;
    private final PlannerDomain domain;
    public final HashMap<Integer, ArrayList<PlannerNode>> nodesByDepth = new HashMap<>();
    public final ArrayList<PlannerNode> allNodes = new ArrayList<>();

    public PlannerGraph(PlannerDomain domain){
        this.domain = domain;

        VisitorData data = domain.getRootNodeData();
        this.root = new PlannerNode(data.id, 0,null, data);
        this.addToNodesByDepth(this.root);
        this.allNodes.add(this.root);
        noOfprocessedNodes++;

        System.out.println("Root node added to the Planner Graph");

        processChildrenNodes();

        treeInitializationCheck();
    }

    public void processChildrenNodes(){
        int currentDepth = 1;
        System.out.println("Started: Initializing other tree nodes");

        // First add depth=1 nodes, parent will be the root node
        ArrayList<VisitorData> depth1DataArr = domain.recordsByPlanLength.get(currentDepth);

        System.out.println("Started: Adding Depth 1 Nodes");
        for(VisitorData data : depth1DataArr){
            PlannerNode newNode = new PlannerNode(data.id, currentDepth, this.root, data);
            this.addToNodesByDepth(newNode);
            this.allNodes.add(newNode);
            this.noOfprocessedNodes++;
        }
        System.out.println("Finished: Adding Depth 1 Nodes");

        // Now the currentDepth will be 2
        currentDepth = 2;

        for(int i = currentDepth; i <= domain.maxPlanLength; i++){
            ArrayList<VisitorData> currentDataArr = domain.recordsByPlanLength.get(i);
            System.out.println("Visiting depth: " + i);
            for(VisitorData currentNodeData : currentDataArr){
                // First, find the correct parent (The node with current action set as the previous action set of the current node)
                ArrayList<PlannerNode> prevNodeArr = this.nodesByDepth.get(i - 1);

                PlannerNode parentNode = null;
                for(PlannerNode prevNodeInQuestion : prevNodeArr){
                    if(prevNodeInQuestion.data.getAllActions().equals(currentNodeData.getPreviousActions())){
                        // If the code access here, that means the prevNodeInQuestion is the parent of the node created using current data
                        PlannerNode newNode = new PlannerNode(currentNodeData.id, i, prevNodeInQuestion, currentNodeData);
                        this.addToNodesByDepth(newNode);
                        this.allNodes.add(newNode);
                        this.noOfprocessedNodes++;
                    }
                }

            }
        }

        System.out.println("Finished: Initializing other tree nodes");
    }

    private void addToNodesByDepth(PlannerNode node){
        int depth = node.depth;
        ArrayList<PlannerNode> depthData = this.nodesByDepth.get(depth);

        if(depthData == null){
            depthData = new ArrayList<>();
        }

        depthData.add(node);
        this.nodesByDepth.put(depth, depthData);
    }

    private void treeInitializationCheck(){
        if(domain.mainRecords.size() == this.noOfprocessedNodes){
            System.out.println("Tree initialization test is SUCCESS");
            return;
        }

        System.out.println("ERROR: Domain main record size: " + domain.mainRecords.size() + " not equal to # of tree nodes processed: " + this.noOfprocessedNodes);
    }
}
