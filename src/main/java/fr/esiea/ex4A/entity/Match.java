/* (C)2021 */
package fr.esiea.ex4A.entity;


public class Match {
    private final String userName;
    private final int userAge;
    private final int userCount;

    public Match(String userName, int userAge, int userCount) {
        this.userName = userName;
        this.userAge = userAge;
        this.userCount = userCount;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public int getUserCount() {
        return userCount;
    }
}
