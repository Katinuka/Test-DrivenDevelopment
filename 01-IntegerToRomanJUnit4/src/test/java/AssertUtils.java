/**
 * This code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
import org.junit.Assert;

import java.util.concurrent.Callable;

public class AssertUtils {

    /**
     * Asserts that the given method either throws the expected exception or returns null.
     *
     * @param callable the method to test, which may throw an exception and/or return a result
     * @param expectedException the type of exception expected, or null if no exception is expected
     */
    public static void assertThrowsOrReturnsNull(Callable<Object> callable, Class<? extends Throwable> expectedException) {
        try {
            // Execute the callable
            Object result = callable.call();

            // If no exception is thrown, assert that the result is null
            Assert.assertNull("Expected result to be null", result);
        } catch (Throwable ex) {
            // Check if the exception is of the expected type
            if (!expectedException.isInstance(ex)) {
                Assert.fail("Expected exception of type " + expectedException.getName() + " but got " + ex.getClass().getName());
            }
        }
    }
}