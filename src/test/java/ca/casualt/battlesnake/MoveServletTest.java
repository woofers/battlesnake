package ca.casualt.battlesnake;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;

import ca.casualt.battlesnake.http.request.MoveRequest;
import org.junit.Test;

public class MoveServletTest {

	@Test
	public void testDeserialization() throws IOException, URISyntaxException {
		final String dirty = TwentyEighteenJsonHelperTest.getTestMoveRequest();

		// Just want to make sure it can do it without errors.
		MoveRequest result = new MoveServlet().parseToMoveRequest(dirty);
		System.out.println(result);
		assertNotNull(result);
	}

}
