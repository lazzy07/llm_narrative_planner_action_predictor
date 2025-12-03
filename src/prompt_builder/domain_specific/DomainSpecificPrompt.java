package prompt_builder.domain_specific;

public abstract class DomainSpecificPrompt {
    public String domainDescription;

    public abstract String getAvailableActions();
}
