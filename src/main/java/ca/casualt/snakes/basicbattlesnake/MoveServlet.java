package ca.casualt.snakes.basicbattlesnake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;
import ca.casualt.snakes.basicbattlesnake.types.MoveResponse;
import ca.casualt.snakes.basicbattlesnake.types.serialization.TwentyEighteenJsonHelper;
import ca.casualt.snakes.basicbattlesnake.utilities.movers.Mover;
import ca.casualt.snakes.basicbattlesnake.utilities.movers.RandomMover;

/**
 * This is the servlet that is hit when triggering the /move endpoint.
 *
 * @author Tony
 *
 */
@SuppressWarnings("serial")
@WebServlet("/move")
public class MoveServlet extends HttpServlet {

	/**
	 * Used for json serialization/deserialization.
	 */
	private final Gson gson = new Gson();
	/**
	 * For returning a response.
	 */
	private final Mover mover = new RandomMover();

	/**
	 * This handles the stnadard post request, converts the json request body
	 * into a java object, and creates a random response.
	 *
	 * @param req
	 *            The http request.
	 * @param resp
	 *            The http response.
	 */
	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		final String requestBody = new BufferedReader(new InputStreamReader(req.getInputStream())).lines()
				.collect(Collectors.joining("\n"));
		System.out.println("Move Request body: [" + requestBody + "]");
		final MoveRequest moveRequest = parseToMoveRequest(requestBody);

		final MoveResponse moveResponse = new MoveResponse();
		moveResponse.setMove(mover.getMove(moveRequest));
		System.out.println("Move to do: " + moveResponse.getMove());
		moveResponse.setTaunt("Walk the plank you scallywag!");

		final String responseBody = gson.toJson(moveResponse);
		resp.getWriter().println(responseBody);
	}

	public MoveRequest parseToMoveRequest(final String requestBody) {
		return gson.fromJson(TwentyEighteenJsonHelper.cleanJson(requestBody), MoveRequest.class);
	}

}
