package mfq.com.refooddelivery2;

import org.junit.Test;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    @Parameterized.Parameters
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}