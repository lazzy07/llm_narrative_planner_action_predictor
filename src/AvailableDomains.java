/**
 * Holds all the available domains.
 * @implNote  If you are adding a new domain, please update this list.
 */
public enum AvailableDomains {
    DEER_HUNTER("deerhunter");

    private final String domainName; // Field that holds the domain name

    AvailableDomains(String domainName){
        this.domainName = domainName;
    }

    /**
     * Returns the name of the domain selected
     * @return Domain name that is currently selected
     */
    public String getDomainName(){
        return this.domainName;
    }

    @Override
    public String toString() {
        return domainName;
    }
}
