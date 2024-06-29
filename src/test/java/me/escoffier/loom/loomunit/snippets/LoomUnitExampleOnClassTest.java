package me.escoffier.loom.loomunit.snippets;
// @start region="example"
import java.time.temporal.ChronoUnit;
import me.escoffier.loom.loomunit.LoomUnitExtension;
import me.escoffier.loom.loomunit.ThreadPinnedEvents;
import me.escoffier.loom.loomunit.ShouldNotPin;
import me.escoffier.loom.loomunit.ShouldPin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.awaitility.Awaitility.await;


@ExtendWith(LoomUnitExtension.class) // Use the extension
@ShouldNotPin(threshHold = 10, unit = ChronoUnit.MILLIS) // You can use @ShouldNotPin or @ShouldPin on the class itself, it's applied to each method.
public class LoomUnitExampleOnClassTest {

	CodeUnderTest codeUnderTest = new CodeUnderTest();

	@Test
	public void testThatShouldNotPin() throws InterruptedException {
		// ...
		Thread start = Thread.ofVirtual().start(() -> {
			synchronized (this) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ignored) {
//                    throw new RuntimeException(e);
                }
            }
		});
		start.join();
	}

	@Test
	@ShouldPin(atMost = 1) // Method annotation overrides the class annotation
	public void testThatShouldPinAtMostOnce() {
		codeUnderTest.pin();
	}

}
// @end