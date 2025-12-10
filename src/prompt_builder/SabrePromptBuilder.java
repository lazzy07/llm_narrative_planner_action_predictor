package prompt_builder;

import domain.AvailableDomains;
import domain.DomainData;
import file_handler.SavePromptToFile;
import planner_data.PlannerDomain;
import planner_graph.PlannerGraph;
import planner_graph.PlannerNode;
import prompt_builder.domain_specific.DeerHunter;

public class SabrePromptBuilder extends PromptBuilder {
    private PlannerDomain currentDomain;
    private PlannerGraph graph;

    public SabrePromptBuilder(PlannerDomain currentDomain, PlannerGraph graph){
        super();

        this.currentDomain = currentDomain;
        this.graph = graph;
    }

    public void generatePromptFromGraph(){
        for(int i=0; i < graph.allNodes.size(); i++){
            String prompt = generatePromptForTheNode(graph.allNodes.get(i));
            SavePromptToFile.savePromptOrganized("DeerHunter", "sabre", String.valueOf(graph.allNodes.get(i).id), prompt);
        }
    }

    public String generatePromptForTheNode(PlannerNode node){
        DeerHunter dh = new DeerHunter();
        return """
                %s
                
                %s
                
                %s
                
                %s
                
                %s
                
                %s
                
                %s
                
                %s
                
                %s
                
                """.formatted(PromptSnippet.generalDescription, dh.domainDescription, PromptSnippet.allActionDescription, node.data.getAllAvailableActions(),PromptSnippet.beforePlanDescription, node.data.getAllActions(), PromptSnippet.beforeCurrentStateDescription, node.data.getAllState(), PromptSnippet.finalInstruction);
    }
}
