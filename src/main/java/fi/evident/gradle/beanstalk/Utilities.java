package fi.evident.gradle.beanstalk;

public class Utilities {
    public static String coalesce(String... items) {
        for (String i : items) {
            if (i != null) {
                return i;
            }
        }
        return null;
    }
}
