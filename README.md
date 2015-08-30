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

League Of Patches isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.
