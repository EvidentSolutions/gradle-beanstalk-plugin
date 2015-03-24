
package fi.evident.gradle.beanstalk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

final class EncodingUtils {

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
