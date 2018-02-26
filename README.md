<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">Battle Snake 2018</a>
<ul>
<li><a href="#sec-1-1">Prerequisites</a></li>
<li><a href="#sec-1-2">Test Server</a></li>
<li><a href="#sec-1-3">Run Locally</a></li>
<li><a href="#sec-1-4">Deployment</a></li>
</ul>
</li>
</ul>
</div>
</div>


# Battle Snake 2018<a id="sec-1" name="sec-1"></a>

A simple Java Battle Snake.

## Prerequisites<a id="sec-1-1" name="sec-1-1"></a>

1.  Install Maven
2.  Install Heroku
3.  Install Jetty (To Run Locally)
4.  Install Docker (To Run Test Server)
5.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy`
6.  Create a Heroku App `heroku create <name>`

## Test Server<a id="sec-1-2" name="sec-1-2"></a>

1.  Run `docker run -it --rm -p 3000:3000 sendwithus/battlesnake-server`
2.  Goto `localhost:3000` in a browser.

## Run Locally<a id="sec-1-3" name="sec-1-3"></a>

1.  Run `mvn jetty:run`
2.  Use `http://[local-ip]:8080` as snake URL.

## Deployment<a id="sec-1-4" name="sec-1-4"></a>

1.  Build .war file `mvn install`
2.  Deploy to Heroku `heroku war:deploy target/BattleSnake.war --app <name>`
3.  Use `https://[name].herokuapp.com/` as snake URL.
