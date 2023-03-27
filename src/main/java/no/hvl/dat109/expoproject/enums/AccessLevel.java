package no.hvl.dat109.expoproject.enums;

public enum AccessLevel {
    ADMIN(0),
    JURY(1),
    EXIBITOR(2);

    private final int level;
    AccessLevel(int level){
        this.level = level;
    }
}
