
# Table of Contents

-   [Battle Snake 2019](#orgd6bce11)
    -   [Strategy](#org24d40c3)
        -   [Drawbacks](#org829ec48)
    -   [Battle History](#org21ee592)
    -   [Screenshots](#org724c68d)
    -   [Usage](#org0af2685)
        -   [Prerequisites](#orgd35566e)
        -   [Test Server](#orgbff479e)
        -   [Run Locally](#org23ed799)
        -   [Deployment](#orge0f762a)
    -   [Acknowledgments](#orge2f2566)



<a id="orgd6bce11"></a>

# Battle Snake 2019

<img height="120" width="120" src="https://github.com/woofers/battle-snake-2019/blob/master/screenshots/intermediate.png?raw=true" />

A simple [BattleSnake](https://www.battlesnake.io) written in Java.
Will be entered in the 2019 Intermediate competition as **Liquid Snake**.

Deployed to `https://battlesnake-liquid.herokuapp.com/`


<a id="org24d40c3"></a>

## Strategy

Our snake's strategy was aggresive. For each turn, if the snake wasn't
the largest on the board, or if the health was below a specific
threshold, the snake would navigate towards the closest food. Otherwise,
it would try to target the other snakes' heads to either eliminate the
opponent in a head-on collision or trap them by cutting off their path.
The snake also included an unused passive state, where she would try to
loop back around to her tail to avoid collisions.


<a id="org829ec48"></a>

### Drawbacks

The snake's biggest drawback was trapping itself in its own tail. We
didn't implement an effective algorithm to calculate dangerous regions
of the board, and ultimately the snake died by going for takedowns and
boxing itself in.


<a id="org21ee592"></a>

## Battle History

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


<a id="org724c68d"></a>

## Screenshots

![img](./screenshots/snake-0.png) ![img](./screenshots/snake-1.png)
![img](./screenshots/snake-2.png) ![img](./screenshots/snake-3.png)


<a id="org0af2685"></a>

## Usage


<a id="orgd35566e"></a>

### Prerequisites

1.  Install Java 8 or higher
2.  Install Docker + Docker compose
3.  Install Heroku
4.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy`
5.  Create a Heroku App `heroku create <name>`


<a id="orgbff479e"></a>

### Test Server

1.  Run `docker run -it --rm -p 3000:3000 sendwithus/battlesnake-server`
2.  Goto `localhost:3000` in a browser.


<a id="org23ed799"></a>

### Run Locally

-   Gradle

    1.  Run the project using either `./gradle run` or `gradlew run` for UNIX and Windows platforms respectively.  This will build and run the project as a \`JAR\` using [Webapp Runner](https://github.com/jsimone/webapp-runner).
    2.  Use `http://localhost:8080` as the snake URL.

-   Docker

    1.  Build the project using either `./gradle build` or `gradlew build` for UNIX and Windows platforms respectively.
    2.  Run the Docker Tomcat image in a container with `docker-compose up`.
    3.  Use `http://localhost:8080/battlesnake` as the snake URL.


<a id="orge0f762a"></a>

### Deployment

1.  Build .war file `./gradlew build`
2.  Deploy to Heroku `heroku war:deploy build/libs/battlesnake.war --app <name>`
3.  Use `https://[name].herokuapp.com/` as the snake URL.


<a id="orge2f2566"></a>

## Acknowledgments

-   **Built by** [Ben Austin](https://github.com/austinben),
    [Jaxson Van Doorn](https://github.com/woofers), and
    [Zak White](https://github.com/zakwht), March 3, 2018
-   **Template Forked from**
    [here](https://github.com/tflinz/BasicBattleSnake2018)
-   **Designed for** [Sendwithus](https://github.com/sendwithus)'
    BattleSnake competition, March 3, 2018

<img align="left" height="120" width="120" src="https://github.com/woofers/battle-snake-2019/blob/master/screenshots/intermediate.png?raw=true" />
