package prompt_builder;

public class PromptSnippet {
    public static final String generalDescription = """
            I will describe a story setting and the beginning of a narrative. Your task is to extract and list only the actions that all involved characters explicitly consent to. Do not continue the story. Do not infer or assume consent. Only include actions that are clearly, explicitly agreed upon by every character participating in that action.
            """;

    public static final String beforePlanDescription = """
            These events have already happened in the story:
            """;

    public static final String beforeCurrentStateDescription = """
            This is the current situation after those events:
            """;

    public static final String allActionDescription = """
            These are all the actions available in the domain:
            """;

    public static final String finalInstruction = """
            Explain why you chose each action. After the explanation of the whole story, give a JSON object with only the actions that all involved characters explicitly consent to. The JSON should include an array called 'chosenActions' Example format:
            {
              "chosenActions" : [action1, action2]
            }
            """;
}
