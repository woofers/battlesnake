package ca.casualt.snakes.basicbattlesnake.types.serialization;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.google.gson.JsonParser;

public class TwentyEighteenJsonHelperTest {

	public static String getTestMoveRequest() throws IOException, URISyntaxException {
		URL resource = TwentyEighteenJsonHelper.class.getResource("exampleMoveRequest.json");
		System.out.println("File path: " + resource);
		final String dirty = new String(Files.readAllBytes(Paths.get(resource.toURI())));
		return dirty;
	}

	@Test
	public void testCleanJson() throws IOException, URISyntaxException {
		final String dirty = getTestMoveRequest();
		System.out.println("Dirty: " + dirty);
		final String clean = TwentyEighteenJsonHelper.cleanJson(dirty);
		System.out.println("Clean: " + clean);

		// Ensure no longer contains "data" keys
		assertFalse(clean.contains("data"));

		// Ensure valid json
		assertNotNull(new JsonParser().parse(clean));
	}

}
