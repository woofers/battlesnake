

# Battlesnake

<img height="120" width="120" src="screenshots/advanced.png" />


A simple [Battlesnake](https://play.battlesnake.com/) written in Java.

Deployed to [https://battlesnake-liquid.herokuapp.com/](https://battlesnake-liquid.herokuapp.com/)

Battlesnake documentation can be found at <https://docs.battlesnake.com/>.

[![img](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Competition History

Variations of this snake have participated in multiple Battlesnake competitions.

* Competed in the veteran division of Battlesnake: Stay Home and Code as  🚚🦴🐶 𝗪𝗔𝗟𝗧𝗘𝗥 👑 🚒

* Competed in the expert division of Battlesnake 2019 & Battlesnake 2019 Winter Classic as  🐍 ‏‏‎𝙎𝙐𝙋𝙀𝙍 𝙎𝙇𝙄𝙈𝙀𝙔 ‏🐍 - [View Snake](https://github.com/woofers/battlesnake-2019/tree/battlesnake-2019)

* Competed in the intermediate division Battlesnake 2018 as **Solid Snake** - [View Snake](https://github.com/woofers/battlesnake-2019/tree/battlesnake-2018)

## Strategy

Our snake's strategy was aggresive. For each turn, if the snake wasn't
the largest on the board, or if the health was below a specific
threshold, the snake would navigate towards the closest food. Otherwise,
it would try to target the other snakes' heads to either eliminate the
opponent in a head-on collision or trap them by cutting off their path.

## Tournament - Stay Home and Code

The snake beat six other competitors the second round of Group B to qualify for the Quaterfinals, where it was eliminated. There were 32
competitors in the veteran division. Our games were played on a 11x11 board with 4 food spawned at start.

Timestamp on the [BattlesnakeOfficial Twitch stream](https://www.twitch.tv/videos/602731416) is [2:16:32-2:25:35](https://www.twitch.tv/videos/602731416?t=02h16m32s) for Group B, and [4:04:08-4:15:18](https://www.twitch.tv/videos/602731416?t=04h04m08s) for Quaterfinals.

**[Group B - Round 1](https://clips.twitch.tv/BeautifulCuteKleeResidentSleeper)**

**[Group B - Round 2](https://clips.twitch.tv/CrackyElegantWaspPartyTime)**

**[Quaterfinals - Round 1](https://clips.twitch.tv/WrongBitterDragonflyRitzMitz)**

**[Quaterfinals - Round 2](https://clips.twitch.tv/PluckyElegantMouseEleGiggle)**

**[Quaterfinals - Round 3](https://clips.twitch.tv/WanderingHonorableCurlewPeteZaroll)**


## Usage


### Prerequisites

1.  Install JDK 11 or higher
2.  Install Docker + Docker compose (required for [Docker container method](#orgfd68ec6))
3.  Install Heroku CLI (required for [Heroku CLI method](#org8843ce7))
4.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy` ) (required for [Heroku CLI method](#org8843ce7))
5.  Create a Heroku App online or using the Heroku CLI with `heroku create <name>`

    (alternatively any other hosting service can be used)


### Test Board

**Online**

Goto [play.battlesnake.io](https://play.battlesnake.io)


### Run Locally

**Gradle**

1.  Run the project using either `./gradle run` or `gradlew run` for UNIX and Windows platforms respectively.  This will build and run the project as a \`JAR\` using [Webapp Runner](https://github.com/jsimone/webapp-runner).
2.  Use `http://localhost:8080` as the snake URL.

**Docker**
<a id="orgfd68ec6"></a>

1.  Build the project using either `./gradle build` or `gradlew build` for UNIX and Windows platforms respectively.
2.  Run the Docker Tomcat image in a container with `docker-compose up`.
3.  Use `http://localhost:8080/battlesnake` as the snake URL.


### Deployment

**Heroku-GitHub Integration**

1.  Go the dashboard for the Heroku app.
2.  Click on the `deploy` tab.
3.  Scroll down to the `Deployment method` and select `GitHub`.
4.  Enter the repository name and click `Connect`.

**Heroku CLI**
<a id="org8843ce7"></a>

1.  Build .war file `./gradlew build`
2.  Deploy to Heroku `heroku war:deploy build/libs/battlesnake.war --app <name>`
3.  Use `https://[name].herokuapp.com/` as the snake URL.


## Acknowledgments

-   **Built by** [Jaxson Van Doorn](https://github.com/woofers) and [Ben Austin](https://github.com/austinben)
-   **Adapted from** [tflinz's snake](https://github.com/tflinz/BasicBattleSnake2018)
-   **Designed for** [Battlesnake](https://github.com/battlesnakeofficial) competition

<img align="left" height="120" width="120" src="screenshots/advanced.png" />
