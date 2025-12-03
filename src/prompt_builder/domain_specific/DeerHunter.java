package prompt_builder.domain_specific;

public class DeerHunter extends DomainSpecificPrompt {
    public DeerHunter(){
        super();

        this.domainDescription = """
                There are three locations in this story: the house, the bank, and the forest. There are three items in this story: a rifle, some ammunition, and some money. There are three characters in this story: Bubba is a human. 
                The bank clerk is a human. Bambi is a deer. Bambi wants to be alive. There are eight kinds of actions characters can take in the story. 
                A character can become greedy, and then they will want to have money. A character can become hungry, and then they will want to eat. A character can pick up an item at their location. 
                A character can load ammunition into a rifle. A character can go from one location to another. 
                One character can steal an item from a second character if the first character has a loaded gun. 
                One character can shoot and kill another character if the first character has a loaded gun. 
                One character can eat an animal if the animal is dead.
                """;
    }

    @Override
    public String getAvailableActions() {
        return """
                decide_to_get_money(character : human),
                decide_to_eat(character : human),
                pickup(character : human, item : item, place : place),
                load(character : human, gun : gun, ammo : ammo),
                go(character : character, from : place, to : place),
                steal(thief : human, victim : human, gun : gun, place : place),
                shoot(shooter : human, target : character, gun : gun, place : place),
                eat(character : human, food : animal, place : place)
                """;
    }
}
