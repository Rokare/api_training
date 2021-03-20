/* (C)2021 */
package fr.esiea.ex4A.entity;


public class Match {
    private final String name;
    private final String twitter;

    public Match(String userName, String twitter) {
        this.name = userName;
        this.twitter = twitter;
    }

    public String getName() {
        return name;
    }

    public String getTwitter() {
        return twitter;
    }

    @Override
    public String toString() {
        return "Match{" +
                "name='" + name + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
