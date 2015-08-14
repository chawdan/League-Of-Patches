/**
 * 
 */
function loadKendoGrid(kendoGridJson, key){
	console.log(JSON.stringify(kendoGridJson[key]));
	$("#" + key + "gameDataGrid").kendoGrid({
	    dataSource: {
	        data: kendoGridJson[key].list,
	        schema: {
	            model: {
	                fields: {
	                    gameType: { type: "string" },
	                    gameDuration: { type: "number" },
	                    championsKilled: { type: "number" },
	                    assists: { type: "number" },
	                    goldSpent : {type: "number"},
	                    level : {type: "number"},
	                    totalDamageTaken : {type: "number"},
	                    totalDamageDealt : {type: "number"},
	                    totalTimeCrowdControlDealt : {type: "number"},
	                    turretsKilled : {type: "number"},
	                    wardPlaced : {type: "number"},
	                    win : {type: "boolean"}
	                }
	            }
	        },
	        pageSize: 20
	    },
	    height: 550,
	    scrollable: true,
	    sortable: true,
	    filterable: true,
	    pageable: {
	        input: true,
	        numeric: false
	    },
	    columns: [
	        { field: "gameType", title: "Game Type", format: "{0:c}", width: "80px" },
	        { field: "gameDuration", title: "Game Duration", width: "80px" },
	        { field: "championsKilled", title: "Kills", width: "80px" },
	        { field: "assists", title: "Assists", width: "80px" },
	        { field: "goldSpent", title: "Gold Spent", width: "80px"},
	        { field: "level", title: "Level", width: "80px"},
	        { field: "totalDamageTaken", title: "Damage Taken", width: "80px"},
	        { field: "totalDamageDealt", title: "Damage Dealt", width: "80px"},
	        { field: "totalTimeCrowdControlDealt", title: "Crowd Control(seconds)", width: "80px"},
	        { field: "turretsKilled", title: "Turrets Killed", width: "80px"},
	        { field: "wardPlaced", title: "Wards Placed", width: "80px"},
	        { field: "win", title: "Win", width: "80px"},
	    ]
	});
}