<%@ page import="java.util.jar.Manifest" %>
<html>
  <head>
    <title>Battlesnake</title>
  </head>

  <body>
    <h2>
      Battle Snake
      <%
        Manifest manifest = new Manifest(getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF"));
        String version = manifest.getMainAttributes().getValue("Implementation-Version");
        out.println(version);
      %>
    </h2>
  </body>
</html>
