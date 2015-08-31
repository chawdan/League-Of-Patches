const SECONDS_IN_MIN = 60;
const NUM_OF_ITEMS = 7;
const R_S_5 = "RANKED_SOLO_5X5";
const R_T_3 = "RANKED_TEAM_3x3";
const R_T_5 = "RANKED_TEAM_5X5";
/*===============================LOADING MATCH HISTORY=========================*/
function loadMatchHistoryInfo(json) {/*work on this*/
  for (var match in json){
    //the list of participants and their participant id's - will be useful later
    var participantIdentities = match.participantIdentities;
    for (var participant in participantIdentities){/*for some reason, there is only 1 user, and that's the
      user who requested his info's data, so this will only go through 1 expected iteration*/
      var participantId = partcipantId.participantIdentities;
      var profileIcon = profileIcon.participantIdentities;
      var summonerName = summonerName.participantIdentities;
      var summonerId = summonerId.participantIdentities;
      loadIconsOfSummoner(profileIcon, summonerName, summonerId);
    }

    var matchCreation = Date(parseInt(match.matchCreation));
    var matchDuration = parseInt(match.matchDuration)/SECONDS_IN_MIN;
    var queueType = match.queueType; /*perform a switch statement on this. see consts*/

    /*taking care of runes and stats*/
    var firstParticipant = match.participants[0];//as far as we know, there is only one person in this list

    /*taking care of runes*/
    var runes = [];
    for(var rune in participant.runes){/*now we have the runes*/
      runes.push(rune);
    }
    loadIconsOfRunes(runes);

    /*taking care of items*/
    var items = [];
    for(var i = 0; i < NUM_OF_ITEMS; i++){
      if(typeof participant["item"+i] !== 'undefined'){/*means this item exists*/
        item.push(participant["item"+i]);
      }
    }
    loadIconsOfItems(items);

    /*kda and wins*/
    var kills = participant.kills;
    var deaths = participant.deaths;
    var assists = participant.assists;
    var winner = participant.winner; /*careful, this is a boolean*/
    loadKda(kills, deaths, assists, winner);

    /*damage calcs*/
    var magicDamageDealtToChampions = participant.magicDamageDealtToChampions;
    var physicalDamageDealtToChampions = participant.physicalDamageDealtToChampions;
    var trueDamageDealtToChampions = participant.trueDamageDealtToChampions;
    var totalDamageDealtToChampions = participant.totalDamageDealtToChampions;
    loadDamageDealt(magicDamageDealtToChampions, physicalDamageDealtToChampions,
      trueDamageDealtToChampions, totalDamageDealtToChampions);

    var magicDamageTaken = participant.magicDamageTaken;
    var physicalDamageTaken = participant.physicalDamageTaken;
    var trueDamageTaken = participant.trueDamageTaken;
    var totalDamageTaken = participant.totalDamageTaken;
    loadDamageTaken(magicDamageTaken, physicalDamageTaken, trueDamageTaken, totalDamageTaken);

    var wardsPlaced = participant.wardsPlaced;
    var visionWardsBoughtInGame = participant.visionWardsBoughtInGame;
    var sightWardsBoughtInGame = participant.sightWardsBoughtInGame;
    loadWardCount(wardsPlaced, visionWardsBoughtInGame, sightWardsBoughtInGame);

  }
}
/*-----UI METHODS-----*/
/*--Quick note: Rolando, check out the Kendo UI for Sortable,
  it does wonders, and each of these matches will be 1 of the tabs. It'd be great if
  the user could drag and sort the matches themselves--*/
function loadIconsOfSummoner(profileIcon, summonerName, summonerId){
  /* reads an image file of the icon, and displays summonerName as a clickable URL, with the value
    of summonerId. For now we can redirect the <a href> to the same page. */
    var champImgUrl = "http://ddragon.leagueoflegends.com/cdn/5.15.1/img/champion/" + summonerName;


}
function loadIconsOfRunes(runes){
  /* reads runes information. We get the image associated with the rune ID(which we can get from
    rune.runeId), and append after that the quantity of the runes(which we can get from rune.rank)
    so it will be like "insert icon" x "insert quantity" */
    for (var rune in runes) {
      var runeImage = rune.runeId;
      var numberOfRunes = rune.rank;
    }
}
function loadIconsOfItems(items){
  /* reads items, and gets their ID. Display them in a row underneath the icons of summoners*/
  for (var item in items) {
    var itemId = item.itemId;
  }
}
function loadKda(kills, deaths, assists, winner){
  /* reads in these #'s and displays them in simple number format. For the win,
    display it in the background, as win = green, loss = red */
    for (var kill in kills) {
      Number(kill);
    }
    for (var death in deaths) {
      Number(death);
    }
    for (var assist in assists) {
      Number(assist);
    }

}
function loadDamageDealt(magicDamageDealtToChampions, physicalDamageDealtToChampions,
      trueDamageDealtToChampions, totalDamageDealtToChampions){
  /* reads in the damages, and since all other damage together = totalDamageDealtToChampions,
    create a bar-GUI( that will show what % of total was magic, physical, true, etc */

  /*for the widget to use for the bar-GUI, check out kendo UI's "chart-api" http://dojo.telerik.com/aFOqa <-- yes verbatim*/
}
function loadDamageTaken(magicDamageTaken, physicalDamageTaken, trueDamageTaken, totalDamageTaken){
  /*same thing as loadDamageDealt*/
}
function loadWardCount(wardsPlaced, visionWardsBoughtInGame, sightWardsBoughtInGame){
  /*Just show these in numbers*/
}

/*====================================LOADING JSON=================================*/
/*----UI METHODS(AJAX calls in main.js)----*/
function loadChampInfo(json) {/*stub for now*/
  for (var champ in json){
    var championIdentities = match.championIdentities;
    for (var champion in championIdentities) {
      var championId = championId.championIdentities;
      var champLevel = champLevel.championIdentities;
    }

  }

}

function loadChampAggregateInfo(json) {/*stub for now*/
for (var champInfo in json) {
    var champIdentities = match.champIdentities;
      for (var champ in champIdentities) {
        var top = champ.top;
        var mid = champ.mid;
        var middle = champ.middle;
        var bot = champ.bot;
        var jungle = champ.jungle;

        var duo = champ.duo;
        var none = champ.none;
        var solo = champ.solo;
        var duo_carry = champ.duo_carry;
        var duo_support = champ.duo_support;

        var weekDate = champ.weekDate;
        var yearDate = champ.yearDate;

        var assists = champ.assists;
        var deaths = champ.deaths;
        var kills = champ.kills;

        //wins
      }
  }




}

function loadItemInfo(json) {/*stub for now*/
  for (var item in json) {
    var itemIdentities = match.itemIdentities;
      for (var items in itemIdentities) {
          var itemId = itemId.itemIdentities;

      }
  }
}

function loadItemAggregateInfo(json) {/*stub for now*/



}

function loadRuneInfo(json) {/*stub for now*/
  for (var rune in json) {
    var runeIdentities = match.runeIdentities;
      for (var _rune in runes) {
        var runeId = runeId.runeIdentities;
        var runeRank = rune.rank;
      }
  }
}

function loadBanInfo(json) {/*stub for now*/
  /*BE CAREFUL ABOUT THIS JSON IN PARTICULAR. THE
  FORMAT OF THE JSON ARRAY IS: at [0], it specifies
  the amount of games played ever. then, at [1->end],
  it tells you each ban json object*/

  for (var banInfo in json) {
    var banIdentities = match.banIdentities;
      for (var ban in banIdentities) {
        var qtyOfPlays = ban.qtyOfPlays;
        var pickTurn = ban.pickTurn;
        var rank = ban.rank;
        var weekDate = ban.weekDate;
        var yearDate = ban.yearDate;
        var qty = ban.qty;
      }
  }


}

function loadTeamInfo(json) {/*stub for now*/

}
