# BasicBattleSnake
a basic java battle snake

## Base Technologies
- Java 8 SDK - [Link](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- Maven 3.3 - [Link](https://maven.apache.org/download.cgi)
- Servlet 3.0 - 
- Gson 2.8.0 - [Link](https://github.com/google/gson)

## Minimum Requirements to run
1. Install Java 8 SDK - [Link](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
2. Install Maven 3.3+ - [Link](https://maven.apache.org/install.html)
3. Checkout project from git - [Link](https://guides.github.com/activities/hello-world/)
4. From project directory that contains the pom.xml, run `mvn jetty:run`

### Verify running
- [http://localhost:8080/BasicBattleSnake/](http://localhost:8080/BasicBattleSnake/) -> should produce static web page: `Hello Static World!`
- [http://localhost:8080/BasicBattleSnake/start](http://localhost:8080/BasicBattleSnake/start)
- [http://localhost:8080/BasicBattleSnake/move](http://localhost:8080/BasicBattleSnake/move)

#### Expected Response times
- Tomcat 4-5 ms
- Jetty 6-7 ms


---

## More Advanced Requirements to run
1. Install Java 8 SDK
2. Install Eclipse JEE Edition (I used Neon)
3. Install Maven 3.3+ (optional since Eclipse comes with maven)
4. Import project into Eclipse via git
5. Alt-F5 to ensure Maven is synched  
**At this point, you could use step #4 from the minimum requirements**  
*OR*  
6. Install Tomcat 8.0+ (I used 8.5 when I made this) 
7. Install above could be done through eclipse, but if done separately, need to add the tomcat server installation to eclipse.
8. Run-As -> Run on Server  
*Verifications steps should be the same*

---

## Other Handy Tools
- Postman chrome plugin, for testing web api POST requests
