<%@ page import="java.util.jar.Manifest, com.battlesnake.game.snake.SnakeConfig" %>
<%
  final SnakeConfig SNAKE = new SnakeConfig();
  final String VERSION = new Manifest(
      getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF")
  ).getMainAttributes().getValue("Implementation-Version");
%>
<html>
  <head>
    <title><%=SNAKE.name()%></title>

  </head>
  <body>
    <h1><%=SNAKE.name()%></h1>
    <table>
      <tbody>
        <tr>
          <th>Version</th>
          <td><%=VERSION%></td>
        </tr>
      </tbody>
    </table>
  </body>
</html>
