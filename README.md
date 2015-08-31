# League Of Patches(Entry for Riot API Challenge 2.0's 2nd Prompt)
Want to know how strong/weak champions become after Riot made their changes in the recent patch(Cough AP Items patch Cough)? Well look no more! Here is the link for our submission: <LINK HERE>

After every patch, the win rates of champions fluxuate depending on how severe the nerfs/buffs on the champions/items are. As an entry to the League of Legends API Challenge 2.0, we want to guide the great community that is League of Legends in what are the ripple effects of the changes and how severe they are in changing the meta. With a weekly updating basis, the items and champions' win rate, kda, and popularity are shown in the front page of our website, displayed in a clear and concise timeline graph.

# Technologies Used
## Backend
  - Java Servlet Asynchronous backend (Multithreaded for each client as well)
  - Fully working REST API(from our side)
  - Jersey Java for backend API connection
  - Mongodb as a Nosql database

## Frontend
  - Angularjs
  - Kendo UI for charts and statistics display
  - JQuery and AJAX to communicate with Riot's static API
  
# How To Use Our Website
## The Intro 
Upon entering our website, the user is confronted with the 4 different choices listed below, with a slight overview of each in the main screen. The intro's main job is to guide the users to the different functionalities of the website.

## The Champions
The champions will be listed in a sortable list. The user can drag the champions list positions around for better visualization. However, the list is first sorted alphabetically. Upon clicking the champion's name or its image, the user will be directed to a page for a much expanded version of statistics, displaying a wide variety of data. 

#### This page is for those who want to:
  - find the *win rate, KDA* of a champion
  - find how much damage the champion *does* on average
  - find how much damage the champion *takes* on average
  - find which *lanes* are most popular for this champion
  - find which *roles* are most popular for this champion
  - find many *per minute differences* of the champion, such as Creep Score, Gold, etc.
  
#### This page has the following queries:
  - find the statistics of a champion in a specific range of ranking and division. Say you want to only know statistics for people between Silver 4 and Platinum 2? No problem!
  - find the statistics of a champion in a specific date range in weekly endpoints. Say you want to know statistics for champion info between week 20 and week 30 of year 2015? Or week 20 of 2013 to week 40 of 2015? No problem!
  - find the statistics of a champion in matches that end before the 20 minute mark? 30 minute mark? 40 minute mark? This would be very helpful for analyzing whether a champion is strong in lategame vs earlygame! **wink wink**

## The Items
The items will be listed in a sortable list. It is similar to the champions in that it will be easy to navigate and drag and drop to sort the items. The items have similar statistics upon clicking on the specific name or the image of the item.

#### This page is for those who want to:
  - find the *win rate, KDA* of an item
  - find the average damage dealt by champions who have this item *will this item give you higher dps?*
  - find the average damage received by champions who have this item *will you be able to tank more with this item?*
  - find which champions use this item the most
  - find which role buys this item most frequently 

#### This page has the following queries:
  - find the stats for the item when a specific *champion* uses it
  - find the stats for the item when a specific *role* uses it
  - find the stats for the item when a specific *lane* uses it
  - find the stats for the item for games that end before the twenty/thirty/fourty/etc minute mark. This would be very helpful in analyzing how strong an item allows the champions to scale! **wink wink**
  - find stats for the item in a specific time frame, plotted as a scatterplot graph as weeks as the x axis. *This would be very beneficial for knowing how much stronger/weaker an item became after a patch!* **WINKS FURIOUSLY** 


## The Bans
This page, albeit the most simple in the website, has vital information about which champions are priority bans and which are not. 
## The User's Account

# Our Goals In The Project
  - Allow the user to clearly and concisely see the difference in KDA, damage/damage taken, usage/popularity, scaling, specificity, skill level of a champion before and after patches. The data currently in the Nosql database holds 10 thousand North America, Korean, European(both west and north east), etc matches as a starting point for comparison for the AP Items. ✓
  - We want the display of the data to be clear and intuitive for the user, and allow the user to draw conclusions based on the data received. ✓
  - When the user enters his username, his matches will be populated in the database, and as each match gets added into the database, the values will be aggregated. Groupings of general champion/item/mastery/runes/bans is performed in K-mean clustering algorithm when the query is retrieved and displays an intuitive relationship between each character. Using local optimums from the clustering, the champion is then given a ranking on how big the change was between weeks. ✓
  - Allow for future patches to also be analyzed similarly, by allowing user-based contributions when they enter their username. ✓
  - After the analysis of data based on the statistics shown on the graphs, the users can then engage in a forum-like environment to decide for themselves which champions had the biggest impact in their opinion. ✗
  - Allow for real time updating of player information(When a match from a known player[a player that has interacted with our api before] is done, the server will detect and increment it). ✗
  - Have fun using the Riot Games API! ✓
  - Use our secret information to win all of our mid-lane matchups. ✓
  - Win the Riot Games API Contest 2.0 ?

# Participants
## Frontend
###Rolando Cruz, the Revered Inventor.
![alt tag](http://i.ytimg.com/vi/vcf4Yk5C_uE/maxresdefault.jpg)
## Backend
###Ray Zhang, the Machine Herald.
![alt tag](http://i.ytimg.com/vi/Lwe_0plVzGI/maxresdefault.jpg)
## Story
Together, the mishmash of Rolando and Ray have always been accomplices or foes on the rift of justice. Their ability power driven skills caused the likes of Riven, Vayne, Katarina, and other hyper-carries to shiver in fear. One day, when they were busy at work defeating their mid-lane enemies, a shining light illuminated the entire field. The hand of Riot emerged from the clouds and worked its magic throughout the lands. The shop keepers were forced by law to change the price of Needlessly Large Rods, Liandry's, and more. It was the law of the land - noone could disobey the almighty Riot. That day was known as the Pretty Big Patch(™), or otherwise known as Patch 5.13. 

With this change in the Pretty Big Patch, Rolando and Ray found their Luden's Echo staff losing its shine and power, as their enemies finally were able to win a single match. This was obviously not acceptable for the two masterminds.

With their ability to speak to machines and adept ability in coding, they went hard at work to retrieve data from Riot Games and analyze the before and after effects of the Pretty Big Patch(™). This was the fruit of their work.

---

# Legal Jibber Jabber
League Of Patches isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.
