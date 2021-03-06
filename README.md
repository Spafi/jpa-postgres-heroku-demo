## A simple demo SpringBoot application deployment process to Heroku using Hibernate JPA Implementation.
### It's here mostly for me to have it at hand, but I thought it might help somebody else as well.

Navigate to folder containing Spring Application. Tip: Create it with https://start.spring.io/. Set Java version to 11
and check if you have the same version of java with ```java -version``` in terminal.

Also, check that `JAVA_HOME` environment variable is set to the root folder of Java 11 JDK and you have it added to the Path.

Linux shell     
```shell 
> echo $JAVA_PATH # to check if it's set correctly. For setting it, consult Google as I'm using Windows
```
Windows

Start Menu > Search for "Edit the system environment variables > Environment variables > 
  In the System variables list (NOT THE USER VARIABLES), check if `JAVA_HOME` is set to your JDK 11 root folder (if you don't have a JDK 11, download one) and point this variable to the root folder of the downloaded JDK.
  Then, click on `Path` variable (ALSO IN SYSTEM VARIABLES LIST) > Edit, and check if you have this exact line `%JAVA_HOME%\bin` (this points to the bin folder of the above set environment variable). If not, add it to the Path.

You must use following dependencies: Spring Web, PostgreSQL Driver, Spring Data JPA. (Those can be added
from https://start.spring.io/)

You should use Spring Boot DevTools dependency for LiveReload and Fast restart and Lombok for boilerplate code reduction (It is used throughout this project and you can check it out here: https://projectlombok.org/).

Create a  ```system.properties``` file in root folder of the project with ```java.runtime.version=11``` inside it. Java
version must match java version in pom.xml

Add the following lines in src/main/resources/application.properties:

```
spring.jpa.hibernate.ddl-auto=create-drop     // create-drop used in development, consult documentation for proper value
spring.datasource.url=postgres://username:password@host:port/database 
spring.datasource.username=username
spring.datasource.password=password
```
You can get all the variables (username, password and url) using the command that gets the full database url from the bottom of this page
Or by going to your app on Heroku -> Resources -> Heroku Postgres -> Settings -> View Credentials

In terminal:

```shell
> heroku login
> git init
> git add .
> git commit -m "Initial commit"
> heroku create
```

If you want to rename your app, you can use:

```shell
> heroku apps:rename <new-name> # (works only if in current app repo, else you must supply the app name -check Heroku documentation) 
```

Go to Heroku site, and add postgres addon to your app from resources tab of your app, or do it from the console. You can
check if the addon is added with:

```shell
> heroku addons
```

Add Controller, Model, Repository and Service classes.

IMPORTANT!
Check the ones provided in this project to see how Heroku interacts with them.

If you want to push your project on Github:

```shell
> git remote add origin <your-git-repo>  
> git add .
> git commit -m "Your commit message"
> git push origin <branch-name>
```

To deploy your app to Heroku:

```shell
> git add .
> git commit -m "Your commit message"
> git push heroku master 
```

If you're locally on another branch than master, use:

```shell
> git push heroku <local-branch-name>:master
```

You can check your endpoints with https://www.postman.com/ \
When making an HTTP Request, use the Java variable name set in the Model class. (In my case is 'name') Ex.: for a POST
Request:

```https://<your-app-name>.herokuapp.com/api/<path-name>```   NOTE: /api/<path-name> is our path from
the ```ControllerClass```

```json
{
  "name": "The new name"
}
```

Useful commands:

```shell
> heroku logs --tail # continually stream logs
> heroku logs --num=num # number of lines to display
> heroku addons # see enabled heroku addons, in our app we should have heroku postgres
> heroku pg:psql # connect to psql shell
```


To save your full database url locally in a file:
(You can name it whatever you want, I named it HEROKU_POSTGRESQL)
```shell
#---Windows Powershell
> heroku config > HEROKU_POSTGRESQL 

#---Linux shell
> heroku config | grep HEROKU_POSTGRESQL
```
