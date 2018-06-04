package com.battlesnake;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;
import com.battlesnake.http.request.MoveRequest;

public class MoveServletTest
{

    @Test
    public void testDeserialization() throws IOException, URISyntaxException
    {
        final String dirty = CleanJsonTest.getTestMoveRequest();

        // Just want to make sure it can do it without errors.
        MoveRequest result = new MoveServlet().parseToMoveRequest(dirty);
        System.out.println(result);
        assertNotNull(result);
    }

}
