package com.leaguebeta.dataAnalysis;

import java.util.ArrayList;
import java.util.List;

import com.leaguebeta.db.model.PlayerGameBean;

public class Stats {//a bean for data analysis
	List<PlayerGameBean> list;
	double avgAssist;
	double avgBarracksKilled;
	double avgChampionsKilled;
	double avgDamageDealtPlayer;
	double avgGold;
	double avgLargestKillingSpree;
	double avgLevel;
	double avgMagicDamageDealtPlayer;
	double avgMagicDamageDealtToChampions;
	double avgMinionsKilled;
	double avgNeutralMinionsKilled;
	double avgNumDeaths;
	double avgPhysicalDamageDealtPlayer;
	double avgPhysicalDamageDealtToChampions;
	double avgPhysicalDamageTaken;
	double avgSightWardsBought;
	double avgSpellsCast;
	double avgTimePlayed;
	double avgTotalDamageDealt;
	double avgTotalDamageDealtToChampions;
	double avgTotalDamageTaken;
	double avgTotalHeal;
	double avgTrueDamageDealtPlayer;
	double avgTrueDamageDealtToChampions;
	double avgTrueDamageTaken;
	double avgTurretsKilled;
	double avgVisionWardsBought;
	double avgWardKilled;
	double avgWardPlaced;
	double avgWin;//need to make boolean 1's and 0's and avg that out for % win
	public Stats(){/*empty on purpose*/}
	public Stats(List<PlayerGameBean> obj){
		list = obj;
		update();
	}
	public void update(){
		for(int i = 0; i < list.size(); i++){
			PlayerGameBean bean = list.get(i);
			avgAssist += (double)(bean.getAssists())/list.size();
			avgBarracksKilled += (double)(bean.getBarracksKilled())/list.size();
			avgChampionsKilled += (double)(bean.getChampionsKilled())/list.size();
			avgGold += (double)(bean.getGold())/list.size();
			avgLargestKillingSpree += (double)(bean.getLargestKillingSpree())/list.size();
			avgLevel += (double)(bean.getLevel())/list.size();
			avgMagicDamageDealtToChampions += (double)(bean.getMagicDamageDealtToChampions())/list.size();
			avgMinionsKilled += (double)(bean.getMinionsKilled())/list.size();
			avgNumDeaths += (double)(bean.getNumDeaths())/list.size();
			avgPhysicalDamageDealtToChampions += (double)(bean.getPhysicalDamageDealtToChampions())/list.size();
			avgPhysicalDamageTaken += (double)(bean.getPhysicalDamageTaken())/list.size();
			avgSightWardsBought += (double)(bean.getSightWardsBought())/list.size();
			//avgSpellsCast += (double)(bean.getSpellsCast())/list.size(); This line needs to be changed
			avgTimePlayed += (double)(bean.getGameDuration())/list.size();
			avgTotalDamageDealt += (double)(bean.getTotalDamageDealt())/list.size();
			avgTotalDamageDealtToChampions += (double)(bean.getTotalDamageDealtToChampions())/list.size();
			avgTotalDamageTaken += (double)(bean.getTotalDamageTaken())/list.size();
			avgTotalHeal += (double)(bean.getTotalHeal())/list.size();
			avgTrueDamageDealtToChampions += (double)(bean.getTrueDamageDealtToChampions())/list.size();
			avgTrueDamageTaken += (double)(bean.getTrueDamageTaken())/list.size();
			avgTurretsKilled += (double)(bean.getTurretsKilled())/list.size();
			avgVisionWardsBought += (double)(bean.getVisionWardsBought())/list.size();
			avgWardKilled += (double)(bean.getWardKilled())/list.size();
			avgWardPlaced += (double)(bean.getWardPlaced())/list.size();
			if(bean.isWin()) avgWin++;
		}
	}
	public double getAvgAssist() {
		return avgAssist;
	}
	public void setAvgAssist(double avgAssist) {
		this.avgAssist = avgAssist;
	}
	public double getAvgBarracksKilled() {
		return avgBarracksKilled;
	}
	public void setAvgBarracksKilled(double avgBarracksKilled) {
		this.avgBarracksKilled = avgBarracksKilled;
	}
	public double getAvgChampionsKilled() {
		return avgChampionsKilled;
	}
	public void setAvgChampionsKilled(double avgChampionsKilled) {
		this.avgChampionsKilled = avgChampionsKilled;
	}
	public double getAvgDamageDealtPlayer() {
		return avgDamageDealtPlayer;
	}
	public void setAvgDamageDealtPlayer(double avgDamageDealtPlayer) {
		this.avgDamageDealtPlayer = avgDamageDealtPlayer;
	}
	public double getAvgGold() {
		return avgGold;
	}
	public void setAvgGold(double avgGold) {
		this.avgGold = avgGold;
	}
	public double getAvgLargestKillingSpree() {
		return avgLargestKillingSpree;
	}
	public void setAvgLargestKillingSpree(double avgLargestKillingSpree) {
		this.avgLargestKillingSpree = avgLargestKillingSpree;
	}
	public double getAvgLevel() {
		return avgLevel;
	}
	public void setAvgLevel(double avgLevel) {
		this.avgLevel = avgLevel;
	}
	public double getAvgMagicDamageDealtPlayer() {
		return avgMagicDamageDealtPlayer;
	}
	public void setAvgMagicDamageDealtPlayer(double avgMagicDamageDealtPlayer) {
		this.avgMagicDamageDealtPlayer = avgMagicDamageDealtPlayer;
	}
	public double getAvgMagicDamageDealtToChampions() {
		return avgMagicDamageDealtToChampions;
	}
	public void setAvgMagicDamageDealtToChampions(double avgMagicDamageDealtToChampions) {
		this.avgMagicDamageDealtToChampions = avgMagicDamageDealtToChampions;
	}
	public double getAvgMinionsKilled() {
		return avgMinionsKilled;
	}
	public void setAvgMinionsKilled(double avgMinionsKilled) {
		this.avgMinionsKilled = avgMinionsKilled;
	}
	public double getAvgNeutralMinionsKilled() {
		return avgNeutralMinionsKilled;
	}
	public void setAvgNeutralMinionsKilled(double avgNeutralMinionsKilled) {
		this.avgNeutralMinionsKilled = avgNeutralMinionsKilled;
	}
	public double getAvgNumDeaths() {
		return avgNumDeaths;
	}
	public void setAvgNumDeaths(double avgNumDeaths) {
		this.avgNumDeaths = avgNumDeaths;
	}
	public double getAvgPhysicalDamageDealtPlayer() {
		return avgPhysicalDamageDealtPlayer;
	}
	public void setAvgPhysicalDamageDealtPlayer(double avgPhysicalDamageDealtPlayer) {
		this.avgPhysicalDamageDealtPlayer = avgPhysicalDamageDealtPlayer;
	}
	public double getAvgPhysicalDamageDealtToChampions() {
		return avgPhysicalDamageDealtToChampions;
	}
	public void setAvgPhysicalDamageDealtToChampions(double avgPhysicalDamageDealtToChampions) {
		this.avgPhysicalDamageDealtToChampions = avgPhysicalDamageDealtToChampions;
	}
	public double getAvgPhysicalDamageTaken() {
		return avgPhysicalDamageTaken;
	}
	public void setAvgPhysicalDamageTaken(double avgPhysicalDamageTaken) {
		this.avgPhysicalDamageTaken = avgPhysicalDamageTaken;
	}
	public double getAvgSightWardsBought() {
		return avgSightWardsBought;
	}
	public void setAvgSightWardsBought(double avgSightWardsBought) {
		this.avgSightWardsBought = avgSightWardsBought;
	}
	public double getAvgSpellsCast() {
		return avgSpellsCast;
	}
	public void setAvgSpellsCast(double avgSpellsCast) {
		this.avgSpellsCast = avgSpellsCast;
	}
	public double getAvgTimePlayed() {
		return avgTimePlayed;
	}
	public void setAvgTimePlayed(double avgTimePlayed) {
		this.avgTimePlayed = avgTimePlayed;
	}
	public double getAvgTotalDamageDealt() {
		return avgTotalDamageDealt;
	}
	public void setAvgTotalDamageDealt(double avgTotalDamageDealt) {
		this.avgTotalDamageDealt = avgTotalDamageDealt;
	}
	public double getAvgTotalDamageDealtToChampions() {
		return avgTotalDamageDealtToChampions;
	}
	public void setAvgTotalDamageDealtToChampions(double avgTotalDamageDealtToChampions) {
		this.avgTotalDamageDealtToChampions = avgTotalDamageDealtToChampions;
	}
	public double getAvgTotalDamageTaken() {
		return avgTotalDamageTaken;
	}
	public void setAvgTotalDamageTaken(double avgTotalDamageTaken) {
		this.avgTotalDamageTaken = avgTotalDamageTaken;
	}
	public double getAvgTotalHeal() {
		return avgTotalHeal;
	}
	public void setAvgTotalHeal(double avgTotalHeal) {
		this.avgTotalHeal = avgTotalHeal;
	}
	public double getAvgTrueDamageDealtPlayer() {
		return avgTrueDamageDealtPlayer;
	}
	public void setAvgTrueDamageDealtPlayer(double avgTrueDamageDealtPlayer) {
		this.avgTrueDamageDealtPlayer = avgTrueDamageDealtPlayer;
	}
	public double getAvgTrueDamageDealtToChampions() {
		return avgTrueDamageDealtToChampions;
	}
	public void setAvgTrueDamageDealtToChampions(double avgTrueDamageDealtToChampions) {
		this.avgTrueDamageDealtToChampions = avgTrueDamageDealtToChampions;
	}
	public double getAvgTrueDamageTaken() {
		return avgTrueDamageTaken;
	}
	public void setAvgTrueDamageTaken(double avgTrueDamageTaken) {
		this.avgTrueDamageTaken = avgTrueDamageTaken;
	}
	public double getAvgTurretsKilled() {
		return avgTurretsKilled;
	}
	public void setAvgTurretsKilled(double avgTurretsKilled) {
		this.avgTurretsKilled = avgTurretsKilled;
	}
	public double getAvgVisionWardsBought() {
		return avgVisionWardsBought;
	}
	public void setAvgVisionWardsBought(double avgVisionWardsBought) {
		this.avgVisionWardsBought = avgVisionWardsBought;
	}
	public double getAvgWardKilled() {
		return avgWardKilled;
	}
	public void setAvgWardKilled(double avgWardKilled) {
		this.avgWardKilled = avgWardKilled;
	}
	public double getAvgWardPlaced() {
		return avgWardPlaced;
	}
	public void setAvgWardPlaced(double avgWardPlaced) {
		this.avgWardPlaced = avgWardPlaced;
	}
	public double getAvgWin() {
		return avgWin;
	}
	public void setAvgWin(double avgWin) {
		this.avgWin = avgWin;
	}
	@Override
	public String toString() {
		return "Stats [avgAssist=" + avgAssist + ", avgBarracksKilled=" + avgBarracksKilled + ", avgChampionsKilled="
				+ avgChampionsKilled + ", avgDamageDealtPlayer=" + avgDamageDealtPlayer + ", avgGold=" + avgGold
				+ ", avgLargestKillingSpree=" + avgLargestKillingSpree + ", avgLevel=" + avgLevel
				+ ", avgMagicDamageDealtPlayer=" + avgMagicDamageDealtPlayer + ", avgMagicDamageDealtToChampions="
				+ avgMagicDamageDealtToChampions + ", avgMinionsKilled=" + avgMinionsKilled
				+ ", avgNeutralMinionsKilled=" + avgNeutralMinionsKilled + ", avgNumDeaths=" + avgNumDeaths
				+ ", avgPhysicalDamageDealtPlayer=" + avgPhysicalDamageDealtPlayer
				+ ", avgPhysicalDamageDealtToChampions=" + avgPhysicalDamageDealtToChampions
				+ ", avgPhysicalDamageTaken=" + avgPhysicalDamageTaken + ", avgSightWardsBought=" + avgSightWardsBought
				+ ", avgSpellsCast=" + avgSpellsCast + ", avgTimePlayed=" + avgTimePlayed + ", avgTotalDamageDealt="
				+ avgTotalDamageDealt + ", avgTotalDamageDealtToChampions=" + avgTotalDamageDealtToChampions
				+ ", avgTotalDamageTaken=" + avgTotalDamageTaken + ", avgTotalHeal=" + avgTotalHeal
				+ ", avgTrueDamageDealtPlayer=" + avgTrueDamageDealtPlayer + ", avgTrueDamageDealtToChampions="
				+ avgTrueDamageDealtToChampions + ", avgTrueDamageTaken=" + avgTrueDamageTaken + ", avgTurretsKilled="
				+ avgTurretsKilled + ", avgVisionWardsBought=" + avgVisionWardsBought + ", avgWardKilled="
				+ avgWardKilled + ", avgWardPlaced=" + avgWardPlaced + ", avgWin=" + avgWin + "]";
	}
}
