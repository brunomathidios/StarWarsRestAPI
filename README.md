# StarWarsRestAPI

After cloning the application and before run it:

Go go mysql folder and run the following instruction:
docker-compose up

To access the database, open another terminal and run the instruction below:
docer exec -it starwars_mysql bash

Then:
mysql -u stw -p

The password:
stw123

And finally run the instruction below to start using the "starwars" database:
use starwars;