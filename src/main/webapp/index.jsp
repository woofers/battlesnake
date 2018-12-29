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
        <tr>
          <th>Head Type</th>
          <td class="lower"><%=SNAKE.head()%></td>
        </tr>
        <tr>
          <th>Tail Type</th>
          <td class="lower"><%=SNAKE.tail()%></td>
        </tr>
        <tr>
          <th>Start Taunt</th>
          <td><%=SNAKE.taunt()%></td>
        </tr>
        <tr>
          <th>Color</th>
          <td class="color"><%=SNAKE.color()%></td>
        </tr>
      </tbody>
    </table>
    <img src="<%=SNAKE.profile()%>"/>
  </body>
</html>
