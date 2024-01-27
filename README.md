
<h1 align="center">
  <br>
Discord BH 38030 repro example
  <br>
</h1>
<h4 align="center">

<p align="center">
  <a href="#how-to-use">How to use</a> •
  <a href="#variables">Variables</a> •
  <a href="#licence">Licence</a> 
</p>Reproduction steps for the issue : Copy/Paste image into slash commands are not working.

## How to use
### Use with docker-compose

#### Dev 
1. Change <a href="#variables">variables</a> in [application.properties](https://github.com/brandonfl/discord-bh-38030/blob/master/src/main/resources/application.properties) file
2. Use command `docker-compose up`

#### Production

1. Create a docker-compose like https://github.com/brandonfl/discord-bh-38030/blob/master/misc/docker-compose-example.yml
2. Use command `docker-compose up -d`

### Use with docker run
Command 
`docker run IMAGE -e BOT_TOKEN=TOKEN ...` 

with `-e` the <a href="#variables">variables</a>

### Use with java
1. Compile `mvn clean package`
2. Run `java -jar target/bot.war` with <a href="#variables">variables</a>

### Use with tomcat
1. Compile `mvn clean package` and get the war file in `target`folder
2. Config the config file of your bot `CATALINA-HOME/conf/Catalina/localhost/bot.xml` with <a href="#variables">variables</a>
3. Deploy the war `CATALINA-HOME/webapps/bot.war`

## Variables

| Key | Description | Default |
|--|--|--|
| LOG_FILE | Location of log file | ./log/bot.log |
| BOT_TOKEN | Token of the Discord bot | None - **required** |

## Licence

Project under [MIT](https://github.com/brandonfl/discord-bh-38030/blob/master/LICENSE) licence
