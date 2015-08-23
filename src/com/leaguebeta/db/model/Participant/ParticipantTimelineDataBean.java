package com.leaguebeta.db.model.Participant;

public class ParticipantTimelineDataBean {
	double tenToTwenty;	//	double	Value per minute from 10 min to 20 min
	double thirtyToEnd;	//	double	Value per minute from 30 min to the end of the game
	double twentyToThirty;	//	double	Value per minute from 20 min to 30 min
	double zeroToTen;	//	double	Value per minute from the beginning of the game to 10 min
	public ParticipantTimelineDataBean(double zeroToTen, double tenToTwenty,  double twentyToThirty, double thirtyToEnd) {
		super();
		this.tenToTwenty = tenToTwenty;
		this.thirtyToEnd = thirtyToEnd;
		this.twentyToThirty = twentyToThirty;
		this.zeroToTen = zeroToTen;
	}
	public double getTenToTwenty() {
		return tenToTwenty;
	}
	public void setTenToTwenty(double tenToTwenty) {
		this.tenToTwenty = tenToTwenty;
	}
	public double getThirtyToEnd() {
		return thirtyToEnd;
	}
	public void setThirtyToEnd(double thirtyToEnd) {
		this.thirtyToEnd = thirtyToEnd;
	}
	public double getTwentyToThirty() {
		return twentyToThirty;
	}
	public void setTwentyToThirty(double twentyToThirty) {
		this.twentyToThirty = twentyToThirty;
	}
	public double getZeroToTen() {
		return zeroToTen;
	}
	public void setZeroToTen(double zeroToTen) {
		this.zeroToTen = zeroToTen;
	}
}

