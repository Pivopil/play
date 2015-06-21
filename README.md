# play
Movie Management Web Application with RESTful API (AngularJS - Scala - Play - Slick - MySQL)

# How to run

# 1) Open terminal and execute commands: 

git clone https://github.com/Pivopil/play.git
cd play
bower install

# 2) Set correct application.conf properties 

themoviedb.apiKey=your_themoviedb_key
db.default.user=correct_username 
db.default.password=correct_password

# 3) Open terminal and execute command in project root folder:

sbt run

If your MySQL credentionals and themoviedb key are correct in application.conf, Slick will automatically create movie_db with two empty tables and you will see localhost:9000/#/ search page. The defaul value for search query is "Titanic".

Then you can go to the "View favourite movie lists" page and create some lists.

Using "breadcrumbs" you can back to the search page and add some movies to your lists. 

# 4) Options

You can also create movie_db from a dump movie_db.sql in the root project folder.

