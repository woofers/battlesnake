<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-0">Battle Snake 2018</a>
<ul>
<li><a href="#sec-1">Strategy</a></li>
<ul>
<li><a href="#sec-1-1">Drawbacks</a></li>
</ul>
<li><a href="#sec-2">Battle History</a></li>
<li><a href="#sec-3">Screenshots</a></li>
<li><a href="#sec-4">Acknowledgments</a></li>
</ul>
</li>
</ul>
</div>
</div>


# Battle Snake 2018<a id="sec-0" name="sec-0"></a>

<img height="120" width="120" src="https://github.com/woofers/battle-snake-2018/blob/master/screenshots/intermediate.png?raw=true" />

A simple [BattleSnake](https://www.battlesnake.io) written in Java. Entered in the 2018 Intermediate competition as **Solid Snake**.

Deployed to `https://battlesnake-solid.herokuapp.com/`

## Strategy<a id="sec-1" name="sec-1"></a>

Our snake's strategy was aggresive. For each turn, if the snake wasn't the largest on the board, or if the health was below a specific threshold, the snake would navigate towards the closest food. Otherwise, it would try to target the other snakes' heads to either eliminate the opponent in a head-on collision or trap them by cutting off their path. The snake also included an unused passive state, where she would try to loop back around to her tail to avoid collisions.

### Drawbacks<a id="sec-1-1" name="sec-1-1"></a>

The snake's biggest drawback was trapping itself in its own tail. We didn't implement an effective algorithm to calculate dangerous regions of the board, and ultimately the snake died by going for takedowns and boxing itself in.

## Battle History<a id="sec-2" name="sec-2"></a>
**Bounty Snakes:**
* Beat two of [Bambora](https://www.bambora.com/en/ca/)'s bounty snakes
* Beat [Rooof](https://www.rooof.com/)'s bounty snakes
* Beat [Giftbit](https://www.giftbit.com/)'s bounty snakes
* Beat [GitHub](https://github.com)'s two snakes in only 67 moves
* Beat [Sendwithus](https://www.sendwithus.com/)' three bounty snakes
* Beat [Semaphore](https://semaphoresolutions.com/)'s zombie snakes and hungry snake
* Lost to one of [Bambora](https://www.bambora.com/en/ca/)'s bounty snakes
* Lost to [Accio](https://myaccio.com/)'s defensive snake
* Lost to [AppColony](http://www.appcolony.ca/)'s tron snake
* Lost to [Redbrick](https://rdbrck.com/)'s champion snake
* Lost to [Workday](https://www.workday.com/)'s bounty snake
* Lost to [CheckFront](https://www.checkfront.com/)'s two bounty snakes

**Tournament:**

The snake came third in both our rounds of the battle. There were 57 competitors in the intermediate tier.

Timestamp on [Sendwithus' Twitch stream](https://www.twitch.tv/videos/234961139) is [1:56:44-1:58:24](https://www.twitch.tv/videos/234961139?t=01h56m44s).

**[Game 1](https://clips.twitch.tv/SplendidNiceKoalaTwitchRPG)**

**[Game 2](https://clips.twitch.tv/GentleCrispyReubenCorgiDerp)**

## Screenshots<a id="sec-3" name="sec-3"></a>
![Screenshot #1](./screenshots/snake-0.png "Round Start")
![Screenshot #2](./screenshots/snake-1.png "Head-on Collision")
![Screenshot #3](./screenshots/snake-2.png "Hungry State")
![Screenshot #4](./screenshots/snake-3.png "Attack State")

## Acknowledgments<a id="sec-4" name="sec-4"></a>
* __Built in by__ [Ben Austin](https://github.com/austinben), [Jaxson Van Doorn](https://github.com/woofers), and [Zak White](https://github.com/zakwht), March 3, 2018
* __Template Forked from__ [here](https://github.com/tflinz/BasicBattleSnake2018)
* __Designed for__ [Sendwithus](https://github.com/sendwithus)' BattleSnake competition, March 3, 2018
<img align="left" height="120" width="120" src="https://github.com/woofers/battle-snake-2018/blob/master/screenshots/intermediate.png?raw=true" />
