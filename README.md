
# Table of Contents

-   [Battlesnake 2019](#org3d791e4)
    -   [Strategy](#org2052e43)
        -   [Drawbacks](#orgf766e5f)
    -   [Battle History](#org70a6d39)
    -   [Game Snippets](#orgf8a57a7)
    -   [Usage](#org6d63f24)
        -   [Prerequisites](#org459b44d)
        -   [Test Board](#org78797e0)
        -   [Run Locally](#orgc05cc85)
        -   [Deployment](#org76eb5b8)
    -   [Acknowledgments](#org0c262be)
        -   [Notable Moments](#org4e55375)



<a id="org3d791e4"></a>

# Battlesnake 2019

<img height="120" width="120" src="screenshots/advanced.png" />

A simple [Battlesnake](https://www.battlesnake.io) written in Java.

Was entered in the expert divison of 2019's tournament as ****🐍 ‏‏‎ 𝙎𝙐𝙋𝙀𝙍 𝙎𝙇𝙄𝙈𝙀𝙔 ‏‏‎ 🐍****.

Deployed to [https://battlesnake-liquid.herokuapp.com/](https://battlesnake-liquid.herokuapp.com/)

[![img](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)


<a id="org2052e43"></a>

## Strategy

Our snake's strategy was aggresive. For each turn, if the snake wasn't
the largest on the board, or if the health was below a specific
threshold, the snake would navigate towards the closest food. Otherwise,
it would try to target the other snakes' heads to either eliminate the
opponent in a head-on collision or trap them by cutting off their path.


<a id="orgf766e5f"></a>

### Drawbacks

Despite efforts to improve the issue from 2018's entry, the snake's biggest
drawback was trapping itself in its own tail. Wedidn't implement an effective
algorithm to calculate dangerous regions of the board, and ultimately the
snake died by going for a food point and boxing itself in.


<a id="org70a6d39"></a>

## Battle History

**Bounty Snakes:**

-   Beat all 3 of [Bambora](https://www.bambora.com/en/ca/)'s bounty snakes
-   Beat all 3 of [Freshworks](https://freshworks.io/)' bounty snake levels
-   Beat both of [Semaphore](https://semaphoresolutions.com/)'s bounty snakes
-   Beat [Workday](https://www.workday.com/)'s bounty snake
-   Beat [Giftbit](https://www.giftbit.com/)'s 3v1 bounty snake match
-   Beat two of four of [Rooof](https://www.rooof.com/)'s bounty snakes
-   Beat one of two of [Redbrick](https://rdbrck.com/)'s bounty snakes
-   Beat both of [Pixel Union](https://www.pixelunion.net/)'s bounty snakes
-   Beat one of [Schneider Electric](https://www.schneider-electric.ca/en/)'s bounty snakes
-   Beat one of [Telmediq](https://www.telmediq.com/)'s bounty snakes
-   Made it to round 6 with [Sendwithus](https://www.sendwithus.com/)' zombie wave style bounty snakes
-   Lost to [CheckFront](https://www.checkfront.com/)'s bounty snake
-   Lost to [GitHub](https://github.com)'s bounty snake

**Tournament:**

The snake came 3rd in the first round of battle, and came 2nd in the second round. There were 29
competitors in the expert tier.  Our games were played on a 11x11 board with 5 food spawned at start.

Timestamp on the [Battlesnake.io Twitch stream](https://www.twitch.tv/videos/389395340) is [2:52:53-2:55:30](https://www.twitch.tv/videos/389395340?t=02h52m53s).

**[Game 1](https://clips.twitch.tv/SoftDepressedWebDAESuppy)**

**[Game 2](https://clips.twitch.tv/CoyRelentlessFiddleheadsSoBayed)**

At the end of the tournament competition, our snake
was listed #13 of 139 snakes in the **[Arena](https://play.battlesnake.io/leaderboard/)**.


<a id="orgf8a57a7"></a>

## Game Snippets

![img](./screenshots/snake-win-1.gif) ![img](./screenshots/snake-win-2.gif)
![img](./screenshots/snake-win-7.gif) ![img](./screenshots/snake-win-6.gif)


<a id="org6d63f24"></a>

## Usage


<a id="org459b44d"></a>

### Prerequisites

1.  Install JDK 11 or higher
2.  Install Docker + Docker compose (required for [Docker container method](#org1006309))
3.  Install Heroku CLI (required for [Heroku CLI method](#org42a8e2d))
4.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy` ) (required for [Heroku CLI method](#org42a8e2d))
5.  Create a Heroku App online or using the Heroku CLI with `heroku create <name>`

    (alternatively any other hosting service can be used)


<a id="org78797e0"></a>

### Test Board

-   Online

    Goto [play.battlesnake.io](https://play.battlesnake.io)


<a id="orgc05cc85"></a>

### Run Locally

-   Gradle

    1.  Run the project using either `./gradle run` or `gradlew run` for UNIX and Windows platforms respectively.  This will build and run the project as a \`JAR\` using [Webapp Runner](https://github.com/jsimone/webapp-runner).
    2.  Use `http://localhost:8080` as the snake URL.

-   Docker

    <a id="org1006309"></a>

    1.  Build the project using either `./gradle build` or `gradlew build` for UNIX and Windows platforms respectively.
    2.  Run the Docker Tomcat image in a container with `docker-compose up`.
    3.  Use `http://localhost:8080/battlesnake` as the snake URL.


<a id="org76eb5b8"></a>

### Deployment

-   Heroku-GitHub Integration

    1.  Go the dashboard for the Heroku app.
    2.  Click on the `deploy` tab.
    3.  Scroll down to the `Deployment method` and select `GitHub`.
    4.  Enter the repository name and click `Connect`.

-   Heroku CLI

    <a id="org42a8e2d"></a>

    1.  Build .war file `./gradlew build`
    2.  Deploy to Heroku `heroku war:deploy build/libs/battlesnake.war --app <name>`
    3.  Use `https://[name].herokuapp.com/` as the snake URL.


<a id="org0c262be"></a>

## Acknowledgments

-   **Built by** [Ben Austin](https://github.com/austinben) and [Jaxson Van Doorn](https://github.com/woofers)
-   **Adapted from** [tflinz's snake](https://github.com/tflinz/BasicBattleSnake2018)
-   **Designed for** [Battlesnake.io](https://github.com/battlesnakeio) competition


<a id="org4e55375"></a>

### Notable Moments

"Is that a Young Thug reference? You guys are awesome."

"Im just a white guy at a coding convention talking about
Young Thug, don't mind me!"

"Ohhh, *you're* woofers. Your emojis are breaking my logs!"

<img align="left" height="120" width="120" src="screenshots/advanced.png" />
