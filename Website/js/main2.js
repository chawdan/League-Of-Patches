function buildDateQuery(weekstart, weekend, yearstart, yearend){
  /*creates the queryString for most of the AJAX calls*/
  return "weekDate="+weekstart+'&weekDate='+weekend+'&yearDate='+yearstart+'&yearDate='+yearend;
}
function buildRankQuery(divisionstart, divisionend, rankingstart, rankingend){
  return '&division='+divisionstart+'&division='+divisionend+'&rank='+rankingstart+'&rank='+rankingend;
}
function buildRegion(region){
  return "&region="+region;
}
function buildMatchDuration(matchDurationStart, matchDurationEnd){
  '&matchDuration='+matchDurationStart+'&matchDuration='+matchDurationEnd
}


/*====================================AGGREGATE===================================*/
/*  Params: weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId */
function getChamp(weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId, region){
  $.ajax({
  url: '/League/showAggregateChampion?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRankQuery(divisionstart, divisionend, rankingstart, rankingend) +
      buildRegion(region) +
      buildMatchDuration(matchDurationStart, matchDurationEnd) +
      '&championId=' + championId,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadChampInfo(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}

function getAggregateChamp(weekstart, weekend, yearstart, yearend, championId, region){
  $.ajax({
  url: '/League/showAllAggregateChampion?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRegion(region) +
      '&championId=' + championId,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadAggregateChampInfo(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}

/* weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId, region */
function getItem(weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId, itemId, region){
  $.ajax({
  url: '/League/showAggregateItem?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRankQuery(divisionstart, divisionend, rankingstart, rankingend) +
      buildRegion(region) +
      buildMatchDuration(matchDurationStart, matchDurationEnd) +
      '&championId=' + championId +
      '&itemId=' + itemId,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadItemInfo(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}

function getAggregateItem(weekstart, weekend, yearstart, yearend, itemId, region){
  $.ajax({
  url: '/League/showAllAggregateItem?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRegion(region) +
      '&itemId=' + itemId,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadAggregateItemInfo(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}

/* Masteries and runes */
function getRune(weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId, runeId, region){
  $.ajax({
  url: '/League/showAggregateRune?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRankQuery(divisionstart, divisionend, rankingstart, rankingend) +
      buildRegion(region) +
      buildMatchDuration(matchDurationStart, matchDurationEnd) +
      '&championId=' + championId +
      '&runeId=' + runeId,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadRuneInfo(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}

function getMastery(weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId, masteryId, region){
  $.ajax({
  url: '/League/showAggregateMastery?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRankQuery(divisionstart, divisionend, rankingstart, rankingend) +
      buildRegion(region) +
      buildMatchDuration(matchDurationStart, matchDurationEnd) +
      '&championId=' + championId +
      '&masteryId=' + masteryId,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadMasteryInfo(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}

function getBans(weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, championId, pickTurn, region){
  $.ajax({
  url: '/League/showAggregateBans?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRankQuery(divisionstart, divisionend, rankingstart, rankingend) +
      buildRegion(region) +
      '&championId=' + championId +
      '&pickTurn=' + pickTurn,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadBanInfo(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}'/League/showAggregateItem?'+

/*====================================SINGULAR===================================*/
/*weekstart, weekend, yearstart, yearend, teamId, matchId*/
function getTeam(weekstart, weekend, yearstart, yearend, teamId, matchId, region){
  $.ajax({
  url: '/League/showTeam?'+buildDateQuery(weekstart, weekend, yearstart, yearend) +
      buildRegion(region) +
      '&teamId=' + teamId +
      '&matchId=' + matchId,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    loadTeam(json);
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}
/*------AJAX REDIRECTS(still singular)-------*/
function getRiotMatch(region, matchId, includeTimeline){
  $.ajax({
  url: '/League/callRiotMatch?'+
      'matchId=' + matchId +
      '&region=' + region +
      '&includeTimeline=' + includeTimeline,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    /* STUB HERE. DO WHATEVER YOU WANT WITH THIS. */
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}
function getRiotLeague(region, summonerId){
  $.ajax({
  url: '/League/callRiotLeague?'+
      'summonerId=' + summonerId +
      '&region=' + region,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    /* STUB HERE. DO WHATEVER YOU WANT WITH THIS. */
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}
function getRiotSummoner(region, name){
  $.ajax({
  url: '/League/callRiotSummoner?'+
      'name'+name+
      '&region=' + region,
  type: 'GET',
  dataType: 'json',
  contentType: 'application/json',
  data: {/*empty because it's a GET*/},
  success: function(json){
    /* STUB HERE. DO WHATEVER YOU WANT WITH THIS. */
  },
  error: function (XMLHttpRequest, textStatus, errorThrown) {
    alert("Sending match history failed: " + errorThrown);
  }})
}
