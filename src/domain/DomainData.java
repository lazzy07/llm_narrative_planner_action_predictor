package domain;

public class DomainData {
    private String domainName;

    DomainData(String domainName){
        this.domainName = domainName;
        System.out.println("Current domain selected: " + domainName);


    }
}
