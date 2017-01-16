package fi.evident.gradle.beanstalk;

import org.junit.Test;

import static fi.evident.gradle.beanstalk.StringUtils.*;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void baseNames() {
        assertEquals("", baseName(""));
        assertEquals("", baseName(".bar"));

        assertEquals("foo", baseName("foo"));
        assertEquals("foo", baseName("foo.bar"));
        assertEquals("foo.bar", baseName("foo.bar.baz"));
    }

    @Test
    public void extensions() {
        assertEquals("", extension(""));
        assertEquals(".bar", extension(".bar"));

        assertEquals("", extension("foo"));
        assertEquals(".bar", extension("foo.bar"));
        assertEquals(".baz", extension("foo.bar.baz"));
    }

    @Test
    public void randomStrings() {
        assertEquals(0, randomString(0).length());
        assertEquals(8, randomString(8).length());
        assertEquals(16, randomString(16).length());
    }
}