package ru.javawebinar.topjava;

/**
 * GKislin
 * 06.03.2015.
 */
public class LoggedUser {

    private static int id = 1;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        LoggedUser.id = id;
    }
}
