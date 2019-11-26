package mfq.com.refooddelivery2;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Assert;
import org.junit.Test;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginInstrumentedTest {

    @Test
    public void testUserLoginTaskWithExistCredentials() throws InterruptedException {

        String email = "test@test.ru";
        String password = "Qwerty123";
        Task<AuthResult> authResultTask = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
        do {
            Thread.sleep(100);
        } while (!authResultTask.isComplete());

        Assert.assertTrue(authResultTask.isSuccessful());
    }

    @Test
    public void testUserLoginTaskWithFakeCredentials() throws InterruptedException {

        String email = "test@test.ru";
        String password = "qwerty123";
        Task<AuthResult> authResultTask = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
        do {
            Thread.sleep(100);
        } while (!authResultTask.isComplete());

        Assert.assertTrue(!authResultTask.isSuccessful());
    }
}
