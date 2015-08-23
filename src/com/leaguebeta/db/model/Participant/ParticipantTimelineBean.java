package com.leaguebeta.db.model.Participant;

public class ParticipantTimelineBean {
	ParticipantTimelineDataBean	assistedLaneDeathsPerMinDeltas;	//	ParticipantTimelineDataBean	Assisted lane deaths per minute timeline data
	ParticipantTimelineDataBean	assistedLaneKillsPerMinDeltas;	//	ParticipantTimelineDataBean	Assisted lane kills per minute timeline data
	ParticipantTimelineDataBean	creepsPerMinDeltas;	//	ParticipantTimelineDataBean	Creeps per minute timeline data
	ParticipantTimelineDataBean	csDiffPerMinDeltas;	//	ParticipantTimelineDataBean	Creep score difference per minute timeline data
	ParticipantTimelineDataBean	damageTakenDiffPerMinDeltas;	//	ParticipantTimelineDataBean	Damage taken difference per minute timeline data
	ParticipantTimelineDataBean	damageTakenPerMinDeltas;	//	ParticipantTimelineDataBean	Damage taken per minute timeline data
	ParticipantTimelineDataBean	goldPerMinDeltas;	
	String lane;	//	string	Participant's lane (Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
	String role;	//	string	Participant's role (Legal values: DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT)
	ParticipantTimelineDataBean	xpDiffPerMinDeltas;
	ParticipantTimelineDataBean	xpPerMinDeltas;
	
	public ParticipantTimelineBean(ParticipantTimelineDataBean assistedLaneDeathsPerMinDeltas,
			ParticipantTimelineDataBean assistedLaneKillsPerMinDeltas, ParticipantTimelineDataBean creepsPerMinDeltas,
			ParticipantTimelineDataBean csDiffPerMinDeltas, ParticipantTimelineDataBean damageTakenDiffPerMinDeltas,
			ParticipantTimelineDataBean damageTakenPerMinDeltas, ParticipantTimelineDataBean goldPerMinDeltas,
			String lane, String role, ParticipantTimelineDataBean xpDiffPerMinDeltas,
			ParticipantTimelineDataBean xpPerMinDeltas) {
		super();
		this.assistedLaneDeathsPerMinDeltas = assistedLaneDeathsPerMinDeltas;
		this.assistedLaneKillsPerMinDeltas = assistedLaneKillsPerMinDeltas;
		this.creepsPerMinDeltas = creepsPerMinDeltas;
		this.csDiffPerMinDeltas = csDiffPerMinDeltas;
		this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
		this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
		this.goldPerMinDeltas = goldPerMinDeltas;
		this.lane = lane;
		this.role = role;
		this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
		this.xpPerMinDeltas = xpPerMinDeltas;
	}
	
	public ParticipantTimelineDataBean getAssistedLaneDeathsPerMinDeltas() {
		return assistedLaneDeathsPerMinDeltas;
	}
	public void setAssistedLaneDeathsPerMinDeltas(ParticipantTimelineDataBean assistedLaneDeathsPerMinDeltas) {
		this.assistedLaneDeathsPerMinDeltas = assistedLaneDeathsPerMinDeltas;
	}
	public ParticipantTimelineDataBean getAssistedLaneKillsPerMinDeltas() {
		return assistedLaneKillsPerMinDeltas;
	}
	public void setAssistedLaneKillsPerMinDeltas(ParticipantTimelineDataBean assistedLaneKillsPerMinDeltas) {
		this.assistedLaneKillsPerMinDeltas = assistedLaneKillsPerMinDeltas;
	}
	public ParticipantTimelineDataBean getCreepsPerMinDeltas() {
		return creepsPerMinDeltas;
	}
	public void setCreepsPerMinDeltas(ParticipantTimelineDataBean creepsPerMinDeltas) {
		this.creepsPerMinDeltas = creepsPerMinDeltas;
	}
	public ParticipantTimelineDataBean getCsDiffPerMinDeltas() {
		return csDiffPerMinDeltas;
	}
	public void setCsDiffPerMinDeltas(ParticipantTimelineDataBean csDiffPerMinDeltas) {
		this.csDiffPerMinDeltas = csDiffPerMinDeltas;
	}
	public ParticipantTimelineDataBean getDamageTakenDiffPerMinDeltas() {
		return damageTakenDiffPerMinDeltas;
	}
	public void setDamageTakenDiffPerMinDeltas(ParticipantTimelineDataBean damageTakenDiffPerMinDeltas) {
		this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
	}
	public ParticipantTimelineDataBean getDamageTakenPerMinDeltas() {
		return damageTakenPerMinDeltas;
	}
	public void setDamageTakenPerMinDeltas(ParticipantTimelineDataBean damageTakenPerMinDeltas) {
		this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
	}
	public ParticipantTimelineDataBean getGoldPerMinDeltas() {
		return goldPerMinDeltas;
	}
	public void setGoldPerMinDeltas(ParticipantTimelineDataBean goldPerMinDeltas) {
		this.goldPerMinDeltas = goldPerMinDeltas;
	}
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ParticipantTimelineDataBean getXpDiffPerMinDeltas() {
		return xpDiffPerMinDeltas;
	}
	public void setXpDiffPerMinDeltas(ParticipantTimelineDataBean xpDiffPerMinDeltas) {
		this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
	}
	public ParticipantTimelineDataBean getXpPerMinDeltas() {
		return xpPerMinDeltas;
	}
	public void setXpPerMinDeltas(ParticipantTimelineDataBean xpPerMinDeltas) {
		this.xpPerMinDeltas = xpPerMinDeltas;
	}	
	
}
