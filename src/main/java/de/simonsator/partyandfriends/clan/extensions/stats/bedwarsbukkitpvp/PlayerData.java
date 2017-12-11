package de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp;

public class PlayerData {
	public final int wins;
	public final int deaths;
	public final int kills;
	public final int destroyedBeds;
	public final int games;
	public final int points;

	public PlayerData(int wins, int destroyedBeds, int deaths, int kills, int games, int points) {
		this.wins = wins;
		this.destroyedBeds = destroyedBeds;
		this.deaths = deaths;
		this.kills = kills;
		this.games = games;
		this.points = points;
	}

}
