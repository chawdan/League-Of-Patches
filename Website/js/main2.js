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
function getChamp(weekstart, weekend, yearstart, yearend, divisionstart, divisionend, rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId, region){
  $.ajax({
  url: buildDateQuery(weekstart, weekend, yearstart, yearend) +
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

/*
function fetchChamp(weekstart, weekend, yearstart, yearend, divisionstart, divisionend,
		rankingstart, rankingend, matchDurationStart, matchDurationEnd, championId, region){
	$.ajax({
		url: 'League/showAggregateChampion?
			+'&division='+divisionstart+'&division='+divisionend+'&rank='+rankingstart+'&rank='+rankingend+'&matchDuration='
			+matchDurationStart+'&matchDuration='+matchDurationEnd+'&championId='+championId+"&region="+region,
		type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        data: {},
        success: function (json) {
        	alert("SUCCESS : " + JSON.stringify(json));
        	loadDiv(json);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert("Sending match history failed: " + errorThrown);
        }
	})

}*/
