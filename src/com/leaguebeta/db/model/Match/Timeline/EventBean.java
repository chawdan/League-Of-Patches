package com.leaguebeta.db.model.Match.Timeline;

public class EventBean {
	String ascendedType;	//	string	The ascended type of the event. Only present if relevant. Note that CLEAR_ASCENDED refers to when a participants kills the ascended player. (Legal values: CHAMPION_ASCENDED, CLEAR_ASCENDED, MINION_ASCENDED)
	int[] assistingParticipantIds;	//	List[int]	The assisting participant IDs of the event. Only present if relevant.
	//We need to change the participantId's into summonerId's as well
 	String buildingType;	//	string	The building type of the event. Only present if relevant. (Legal values: INHIBITOR_BUILDING, TOWER_BUILDING)
	int creatorId;	//	int	The creator ID of the event. Only present if relevant.
	String eventType;	//	string	Event type. (Legal values: ASCENDED_EVENT, BUILDING_KILL, CAPTURE_POINT, CHAMPION_KILL, ELITE_MONSTER_KILL, ITEM_DESTROYED, ITEM_PURCHASED, ITEM_SOLD, ITEM_UNDO, PORO_KING_SUMMON, SKILL_LEVEL_UP, WARD_KILL, WARD_PLACED)
	int itemAfter;	//	int	The ending item ID of the event. Only present if relevant.
	int itemBefore;	//	int	The starting item ID of the event. Only present if relevant.
	int itemId;	//	int	The item ID of the event. Only present if relevant.
	int killerId;	//	int	The killer ID of the event. Only present if relevant. Killer ID 0 indicates a minion.
	String laneType;	//	string	The lane type of the event. Only present if relevant. (Legal values: BOT_LANE, MID_LANE, TOP_LANE)
	String levelUpType;	//	string	The level up type of the event. Only present if relevant. (Legal values: EVOLVE, NORMAL)
	String monsterType;	//	string	The monster type of the event. Only present if relevant. (Legal values: BARON_NASHOR, BLUE_GOLEM, DRAGON, RED_LIZARD, VILEMAW)
	String participantId;	//	int	The participant ID of the event. Only present if relevant.
	String pointCaptured;	//	string	The point captured in the event. Only present if relevant. (Legal values: POINT_A, POINT_B, POINT_C, POINT_D, POINT_E)
	PositionBean position;	//	Position	The position of the event. Only present if relevant.
	int skillSlot;	//	int	The skill slot of the event. Only present if relevant.
	int teamId;	//	int	The team ID of the event. Only present if relevant.
	long timestamp;	//	long	Represents how many milliseconds into the game the event occurred.
	String towerType;	//	string	The tower type of the event. Only present if relevant. (Legal values: BASE_TURRET, FOUNTAIN_TURRET, INNER_TURRET, NEXUS_TURRET, OUTER_TURRET, UNDEFINED_TURRET)
	int victimId;	//	int	The victim ID of the event. Only present if relevant.
	String wardType;	//	string	The ward type of the event. Only present if relevant. (Legal values: SIGHT_WARD, TEEMO_MUSHROOM, UNDEFINED, VISION_WARD, YELLOW_TRINKET, YELLOW_TRINKET_UPGRADE)
	public EventBean(String ascendedType, int[] assistingParticipantIds, String buildingType, int creatorId,
			String eventType, int itemAfter, int itemBefore, int itemId, int killerId, String laneType,
			String levelUpType, String monsterType, String participantId, String pointCaptured, PositionBean position,
			int skillSlot, int teamId, long timestamp, String towerType, int victimId, String wardType) {
		super();
		this.ascendedType = ascendedType;
		this.assistingParticipantIds = assistingParticipantIds;
		this.buildingType = buildingType;
		this.creatorId = creatorId;
		this.eventType = eventType;
		this.itemAfter = itemAfter;
		this.itemBefore = itemBefore;
		this.itemId = itemId;
		this.killerId = killerId;
		this.laneType = laneType;
		this.levelUpType = levelUpType;
		this.monsterType = monsterType;
		this.participantId = participantId;
		this.pointCaptured = pointCaptured;
		this.position = position;
		this.skillSlot = skillSlot;
		this.teamId = teamId;
		this.timestamp = timestamp;
		this.towerType = towerType;
		this.victimId = victimId;
		this.wardType = wardType;
	}
	public String getAscendedType() {
		return ascendedType;
	}
	public void setAscendedType(String ascendedType) {
		this.ascendedType = ascendedType;
	}
	public int[] getAssistingParticipantIds() {
		return assistingParticipantIds;
	}
	public void setAssistingParticipantIds(int[] assistingParticipantIds) {
		this.assistingParticipantIds = assistingParticipantIds;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getItemAfter() {
		return itemAfter;
	}
	public void setItemAfter(int itemAfter) {
		this.itemAfter = itemAfter;
	}
	public int getItemBefore() {
		return itemBefore;
	}
	public void setItemBefore(int itemBefore) {
		this.itemBefore = itemBefore;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getKillerId() {
		return killerId;
	}
	public void setKillerId(int killerId) {
		this.killerId = killerId;
	}
	public String getLaneType() {
		return laneType;
	}
	public void setLaneType(String laneType) {
		this.laneType = laneType;
	}
	public String getLevelUpType() {
		return levelUpType;
	}
	public void setLevelUpType(String levelUpType) {
		this.levelUpType = levelUpType;
	}
	public String getMonsterType() {
		return monsterType;
	}
	public void setMonsterType(String monsterType) {
		this.monsterType = monsterType;
	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public String getPointCaptured() {
		return pointCaptured;
	}
	public void setPointCaptured(String pointCaptured) {
		this.pointCaptured = pointCaptured;
	}
	public PositionBean getPosition() {
		return position;
	}
	public void setPosition(PositionBean position) {
		this.position = position;
	}
	public int getSkillSlot() {
		return skillSlot;
	}
	public void setSkillSlot(int skillSlot) {
		this.skillSlot = skillSlot;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getTowerType() {
		return towerType;
	}
	public void setTowerType(String towerType) {
		this.towerType = towerType;
	}
	public int getVictimId() {
		return victimId;
	}
	public void setVictimId(int victimId) {
		this.victimId = victimId;
	}
	public String getWardType() {
		return wardType;
	}
	public void setWardType(String wardType) {
		this.wardType = wardType;
	}
}
