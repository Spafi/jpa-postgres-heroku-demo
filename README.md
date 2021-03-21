Navigate to folder containing Spring Application. Tip: Create it with https://start.spring.io/. Set Java version to 11 and check if you have the same version of java with ```java -version``` in terminal, otherwise it won't work.

You must use following dependencies: Spring Web, Lombok, PostgreSQL Driver, Spring Data JPA. (Those can be added from https://start.spring.io/)

You should use Spring Boot DevTools dependency for LiveReload and Fast restart.

You need also to put manually this dependency after you open your project:

https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.5.2


Create a  ```system.properties``` file in root folder of the project with ```java.runtime.version=11``` inside it. Java version must match java version in pom.xml

Add ApplicationContext.xml in src/main/resources with this content:
```xml
<bean class="java.net.URI" id="dbUrl">
    <constructor-arg value="#{systemEnvironment['DATABASE_URL']}"/>
</bean>

<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
<property name="url" value="#{ 'jdbc:postgresql://' + @dbUrl.getHost() + @dbUrl.getPath() }"/>
<property name="username" value="#{ @dbUrl.getUserInfo().split(':')[0] }"/>
<property name="password" value="#{ @dbUrl.getUserInfo().split(':')[1] }"/>
</bean>
```
(If you get Multiple root tags syntax error for above file, just ignore it)


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
> heroku apps:rename <new-name> # (works only if in current app repo, else must supply app name, check Heroku documentation) 
```

Go to Heroku site, and add postgres addon to your app from resources tab of your app, or do it from the console.
You can check if the addon is added with:
```shell
> heroku addons
```

Add Controller, Model, Repository, and Service classes.

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

Heroku doesn't create tables for you even if you use JPA because it uses a SimpleDataSource from what I understood. \
I couldn't make them using heroku pg:psql, but I succeeded using pgAdmin, like this: \
Go to your heroku's app resources on the site, select heroku Postgres. \
On the site that you're redirected to go to Settings and then on Database credentials click view credentials. \
Go to pgAdmin and create a new server. \
For Host name/address use Host from your heroku Postgres credentials \
For maintenance database use Database from your heroku Postgres credentials \
Same for user and password. 

If connected successfully, you should see a bunch of databases. \
Use Ctrl+F to search for your database name from Postgres. \
Create a new table with the same table name used in your Model class \
Create columns using the same column name used  in your model class

When making an HTTP Request, use the Java variable name set in the Model class. (In my case is 'name') Ex.: for a POST Request:

```https://<your-app-name>.herokuapp.com/api/<path-name>```   NOTE: /api/<path-name> is our path from the ```ControllerClass```
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

# To save your full database url locally in a file:
# (You can name it whatever you want, I named it HEROKU_POSTGRESQL)

#---Windows Powershell
> heroku config > HEROKU_POSTGRESQL 

#---Linux shell
> heroku config | grep HEROKU_POSTGRESQL
```


