<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">Battle Snake 2018</a>
<ul>
<li><a href="#sec-1-1">Prerequisites</a></li>
<li><a href="#sec-1-2">Deployment</a></li>
</ul>
</li>
</ul>
</div>
</div>


# Battle Snake 2018<a id="sec-1" name="sec-1"></a>

A simple Java Battle Snake.

## Prerequisites<a id="sec-1-1" name="sec-1-1"></a>

1.  Install Maven
2.  Install Jetty
3.  Install Heroku
4.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy`
5.  Create a Heroku App `heroku create <name>`

## Deployment<a id="sec-1-2" name="sec-1-2"></a>

1.  Build .war file `mvn install`
2.  Deploy to Heroku `heroku war:deploy target/BattleSnake.war --app <name>`
