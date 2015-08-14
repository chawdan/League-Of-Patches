/**
 * Ray Zhang 2015, For entertainment purposes only.
 * This js file handles the main api calling to the league server.
 */
var API_KEY = '378e48f7-5e59-4929-80f4-2cf5fe85a73f';
var region = "na";
var name;
var lastSelectedSortType = 0;
var selectMap = [];
$( function(){
	$('#region').change(function(){
		region = $('#region').val();
		alert("region changed.");
	});
	$('#name').change(function(){
		name = $('#name').val().toLowerCase().replace(/\s+/g, '');
	})
})
function fetchSummonerID(){
	region = $('#region').val();
	name = $('#name').val().toLowerCase().replace(/\s+/g, '');
	var data;
	$.ajax({
		url: 'https://' + region + '.api.pvp.net/api/lol/' + region + '/v1.4/summoner/by-name/' + name + '?api_key=' + API_KEY,
		type: 'GET',
        dataType: 'json',
        data: {
        	//empty because we are GETTING, not POSTING
        },
        success: function (json) {
        	if(confirmation(json[""+name].name)){
        		alert("Loading your data right now.");
        		fetchMatchHistory(json[""+name]);
        		lastSelectedSortType = $('#queryType').val();
        	}
        	else
        		$('#name').val('');
        },            
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert("Was not able to process request : " + errorThrown);
        }
	});
}

function confirmation(sumName){
	if(confirm("Is this your summoner name : " + sumName + " ?"))
		return true;
	else
		return false;
}

function fetchMatchHistory(json){
	var summonerID = json.id;
	$.ajax({
		url: 'https://' + region + '.api.pvp.net/api/lol/' + region + '/v1.3/game/by-summoner/' + summonerID + '/recent?api_key=' + API_KEY,
		type: 'GET',
        dataType: 'json',
        data: {
        	//empty because we are GETTING, not POSTING
        },
        success: function (json) {
        	fetchPlayerRankings(summonerID, region, function(rankJson){
        		sendMatchToServer(json, summonerID, rankJson);
        	})
        },            
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert("Fetching match history failed: " + errorThrown);
        }
	});
}

function fetchPlayerRankings(summonerID, region, callback){
	$.ajax({
		url: 'https://' + region + '.api.pvp.net/api/lol/' + region + '/v2.5/league/by-summoner/' + summonerID + '/entry?api_key=' + API_KEY,
		type: 'GET',
        dataType: 'json',
        data: {
        	//empty because we are GETTING, not POSTING
        },
        success: function (json) {
        	alert(JSON.stringify(json));
        	if(typeof callback === "function")
        		callback(json);
        },            
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert("Fetching match history failed: " + errorThrown);
        }
	});
}

//not yet implemented anywhere - will not use backend for this
function getLeaderboardBySummoner(summonerID, region){
	$.ajax({
		url: 'https://' + region + '.api.pvp.net/api/lol/' + region + '/v2.5/league/by-summoner/' + summonerID + '?api_key=' + API_KEY,
		type: 'GET',
        dataType: 'json',
        data: {
        	//empty because we are GETTING, not POSTING
        },
        success: function (json) {
        	alert(JSON.stringify(json));
        	console.log(JSON.stringify(json));
        },            
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert("Fetching match history failed: " + errorThrown);
        }
	})
}

function sendMatchToServer(json, summonerID, rankJson){
	json["playerId"] = summonerID;
	json["region"] = region;
	json["sortType"] = $('#queryType').val();
	json["rank"] = rankJson[""+summonerID];
	var dataSent = JSON.stringify(json);
	$.ajax({
		url: 'League/postMatches',
		type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: dataSent,
        success: function (json) {
        	alert("SUCCESS : " + JSON.stringify(json));
        	loadDiv(json);
        },            
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert("Sending match history failed: " + errorThrown);
        }
	});
}

function loadDiv(json){
	var fragment = "";
	$("#populateDiv").empty();
	for (var key in json) {
		fragment = "";
		  if (json.hasOwnProperty(key)) {
			  fragment += "key : " + key + " json data: " + JSON.stringify(json[key]) + '<div id="'+key+'gameDataGrid" class="gameDataGrid"></div><button id='+key+' class="champQuery">Click For More Info</button><br>'
			  +'<select id="'+key+'select" class="champSelectQuery">'+
				'<option value="na_collection_champ">North America</option>'+
				'<option value="euw_collection_champ">Western Europe</option>'+
				'<option value="eune_collection_champ">Northeastern Europe</option>'+
				'<option value="br_collection_champ">Brazil</option>'+
				'<option value="oce_collection_champ">Oceania</option>'+
				'<option value="kr_collection_champ">Korea</option>'+
				'</select>' +
				'<select id="'+key+'rankSelectFilter" class="champFilterQuery">'+
				'<option value=-2>Display All Ranks</option>'+
				'<option value=0>Display This Rank Only</option>'+
				'<option value=-1>Display This Rank And Lower</option>'+
				'<option value=1>Display This Rank And Higher</option>'+
				'</select>'+
				'<select id="'+key+'rankSelect" class="champFilterQuery">'+
				'<option value=1>Bronze</option>'+
				'<option value=2>Silver</option>'+
				'<option value=3>Gold</option>'+
				'<option value=4>Platinum</option>'+
				'<option value=5>Diamond</option>'+
				'<option value=6>Master</option>'+
				'<option value=7>Challenger</option>'+
				'</select>'+
				'<select id="'+key+'divisionSelect" class="champFilterQuery">'+
				'<option value=1>V</option>'+
				'<option value=2>VI</option>'+
				'<option value=3>III</option>'+
				'<option value=4>II</option>'+
				'<option value=5>I</option>'+
				'</select>'+
				'<div id="'+key+'champDiv" class ="champDiv"></div>';
			  	$("#populateDiv").append(fragment);
				loadKendoGrid(json, key);
		  }
	}
}
$(document).on('click', '.champQuery', function(){
	var id = this.id;
	alert(id + " clicked!");
	if(lastSelectedSortType != 1) //I aint saying she a gold digger, but she aint messin with no broke buttons
		return;
	alert("Value: " + $("#"+id+"select").val());
	fetchChampionKDA(id, $("#"+id+"select").val());
});

function fetchChampionKDA(championId, region){
	var filterOption = $("#" + championId + "rankSelectFilter").val();
	var rankSelect = $("#" + championId + "rankSelect").val();
	var divSelect = $("#" + championId + "divisionSelect").val();
	$.ajax({
		url: 'League/showWeeklyAvg?championId='+championId+'&region='+region+"&filterOption="+filterOption+"&rankSelect="+rankSelect+"&divSelect="+divSelect,
		type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        data: {},//should be none
        success: function (json) {
        	alert("SUCCESS : " + JSON.stringify(json));
        	loadChampionKDA(championId, region, json);
        },            
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert("Retrieving champion data failed - You dunfukked: " + errorThrown);
        }
	});
}

function loadChampionKDA(championId, region, json){
	$("#"+championId+"champDiv").append("Total amounts of kda for " + region + " : " + JSON.stringify(json));
}

