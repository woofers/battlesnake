<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">Battle Snake 2019</a>
<ul>
<li><a href="#sec-1-1">Strategy</a>
<ul>
<li><a href="#sec-1-1-1">Drawbacks</a></li>
</ul>
</li>
<li><a href="#sec-1-2">Battle History</a></li>
<li><a href="#sec-1-3">Screenshots</a></li>
<li><a href="#sec-1-4">Usage</a>
<ul>
<li><a href="#sec-1-4-1">Prerequisites</a></li>
<li><a href="#sec-1-4-2">Test Server</a></li>
<li><a href="#sec-1-4-3">Run Locally</a></li>
<li><a href="#sec-1-4-4">Deployment</a></li>
</ul>
</li>
<li><a href="#sec-1-5">Acknowledgments</a></li>
</ul>
</li>
</ul>
</div>
</div>


# Battle Snake 2019<a id="sec-1" name="sec-1"></a>

<img height="120" width="120" src="https://github.com/woofers/battle-snake-2019/blob/master/screenshots/intermediate.png?raw=true" />

A simple [BattleSnake](https://www.battlesnake.io) written in Java.
Will be entered in the 2019 Intermediate competition as **Liquid Snake**.

Deployed to `https://battlesnake-liquid.herokuapp.com/`

## Strategy<a id="sec-1-1" name="sec-1-1"></a>

Our snake's strategy was aggresive. For each turn, if the snake wasn't
the largest on the board, or if the health was below a specific
threshold, the snake would navigate towards the closest food. Otherwise,
it would try to target the other snakes' heads to either eliminate the
opponent in a head-on collision or trap them by cutting off their path.
The snake also included an unused passive state, where she would try to
loop back around to her tail to avoid collisions.

### Drawbacks<a id="sec-1-1-1" name="sec-1-1-1"></a>

The snake's biggest drawback was trapping itself in its own tail. We
didn't implement an effective algorithm to calculate dangerous regions
of the board, and ultimately the snake died by going for takedowns and
boxing itself in.

## Battle History<a id="sec-1-2" name="sec-1-2"></a>

**Bounty Snakes:**
-   Beat two of [Bambora](https://www.bambora.com/en/ca/)'s bounty snakes
-   Beat [Rooof](https://www.rooof.com/)'s bounty snakes
-   Beat [Giftbit](https://www.giftbit.com/)'s bounty snakes
-   Beat [GitHub](https://github.com)'s two snakes in only 67 moves
-   Beat [Sendwithus](https://www.sendwithus.com/)' three bounty snakes
-   Beat [Semaphore](https://semaphoresolutions.com/)'s zombie snakes and hungry snake
-   Lost to one of [Bambora](https://www.bambora.com/en/ca/)'s bounty snakes
-   Lost to [Accio](https://myaccio.com/)'s defensive snake
-   Lost to [AppColony](http://www.appcolony.ca/)'s tron snake
-   Lost to [Redbrick](https://rdbrck.com/)'s champion snake
-   Lost to [Workday](https://www.workday.com/)'s bounty snake
-   Lost to [CheckFront](https://www.checkfront.com/)'s two bounty snakes

**Tournament:**

The snake came third in both our rounds of the battle. There were 57
competitors in the intermediate tier.  Our games were played on a 15x15 board with 10 food.

Timestamp on [Sendwithus'
Twitch stream](https://www.twitch.tv/videos/234961139) is
[1:56:44-1:58:24](https://www.twitch.tv/videos/234961139?t=01h56m44s).

**[Game 1](https://clips.twitch.tv/SplendidNiceKoalaTwitchRPG)**

**[Game 2](https://clips.twitch.tv/GentleCrispyReubenCorgiDerp)**

## Screenshots<a id="sec-1-3" name="sec-1-3"></a>

![img](./screenshots/snake-0.png) ![img](./screenshots/snake-1.png)
![img](./screenshots/snake-2.png) ![img](./screenshots/snake-3.png)

## Usage<a id="sec-1-4" name="sec-1-4"></a>

### Prerequisites<a id="sec-1-4-1" name="sec-1-4-1"></a>

1.  Install Maven
2.  Install Heroku
3.  Install Jetty (To Run Locally)
4.  Install Docker (To Run Test Server)
5.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy`
6.  Create a Heroku App `heroku create <name>`

### Test Server<a id="sec-1-4-2" name="sec-1-4-2"></a>

1.  Run `docker run -it --rm -p 3000:3000 sendwithus/battlesnake-server`
2.  Goto `localhost:3000` in a browser.

### Run Locally<a id="sec-1-4-3" name="sec-1-4-3"></a>

1.  Run `mvn jetty:run`
2.  Use `http://[local-ip]:8080` as snake URL.

### Deployment<a id="sec-1-4-4" name="sec-1-4-4"></a>

1.  Build .war file `mvn install`
2.  Deploy to Heroku `heroku war:deploy target/BattleSnake.war --app <name>`
3.  Use `https://[name].herokuapp.com/` as snake URL.

## Acknowledgments<a id="sec-1-5" name="sec-1-5"></a>

-   **Built by** [Ben Austin](https://github.com/austinben),
    [Jaxson Van Doorn](https://github.com/woofers), and
    [Zak White](https://github.com/zakwht), March 3, 2018
-   **Template Forked from**
       [here](https://github.com/tflinz/BasicBattleSnake2018)
-   **Designed for** [Sendwithus](https://github.com/sendwithus)'
    BattleSnake competition, March 3, 2018

<img align="left" height="120" width="120" src="https://github.com/woofers/battle-snake-2019/blob/master/screenshots/intermediate.png?raw=true" />
