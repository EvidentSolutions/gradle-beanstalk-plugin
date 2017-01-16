package fi.evident.gradle.beanstalk;

import java.util.Random;

final class StringUtils {

    private static final String RANDOM_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    static String baseName(String filename) {
        int index = filename.lastIndexOf('.');
        if (index != -1)
            return filename.substring(0, index);
        else
            return filename;
    }

    static String extension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index != -1)
            return filename.substring(index);
        else
            return "";
    }

    static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++)
            sb.append(RANDOM_ALPHABET.charAt(random.nextInt(RANDOM_ALPHABET.length())));

        return sb.toString();
    }
}
