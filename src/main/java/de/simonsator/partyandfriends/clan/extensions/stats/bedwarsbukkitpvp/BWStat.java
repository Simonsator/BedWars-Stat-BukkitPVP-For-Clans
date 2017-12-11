package de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BWStat implements ClanStat {
	private final String NAME;
	protected final BWStatsConnection CONNECTION;
	private final Matcher KILLS_MESSAGE;
	private final Matcher WINS_MESSAGE;
	private final Matcher PLAYED_MESSAGE;
	private final Matcher DEATHS_MESSAGE;
	private final Matcher DESTROYED_MESSAGE;
	private final Matcher POINTS_MESSAGES;

	public BWStat(Configuration pMessages, BWStatsConnection pCon) {
		NAME = pMessages.getString("Name");
		CONNECTION = pCon;
		KILLS_MESSAGE = Pattern.compile("[KILLED]", Pattern.LITERAL).matcher(pMessages.getString("Kills"));
		WINS_MESSAGE = Pattern.compile("[WINS]", Pattern.LITERAL).matcher(pMessages.getString("Wins"));
		DEATHS_MESSAGE = Pattern.compile("[DEATHS]", Pattern.LITERAL).matcher(pMessages.getString("Deaths"));
		DESTROYED_MESSAGE = Pattern.compile("[DESTROYED]", Pattern.LITERAL).matcher(pMessages.getString("DestroyedBeds"));
		PLAYED_MESSAGE = Pattern.compile("[PLAYED]", Pattern.LITERAL).matcher(pMessages.getString("Played"));
		POINTS_MESSAGES = Pattern.compile("[POINTS]", Pattern.LITERAL).matcher(pMessages.getString("Points"));
	}

	@Override
	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<PlayerData> playerData = new ArrayList<PlayerData>();
		for (PAFPlayer player : players) {
			PlayerData data = CONNECTION.getPlayerData(player.getUniqueId());
			if (data != null)
				playerData.add(data);
		}
		int wins = 0;
		int deaths = 0;
		int games = 0;
		int kills = 0;
		int destroyedBeds = 0;
		int points = 0;
		for (PlayerData data : playerData) {
			wins += data.wins;
			deaths += data.deaths;
			kills += data.kills;
			destroyedBeds += data.destroyedBeds;
			games += data.games;
			points += data.points;
		}
		pSender.sendMessage(KILLS_MESSAGE.replaceFirst(kills + ""));
		pSender.sendMessage(DEATHS_MESSAGE.replaceFirst(deaths + ""));
		pSender.sendMessage(DESTROYED_MESSAGE.replaceFirst(destroyedBeds + ""));
		pSender.sendMessage(WINS_MESSAGE.replaceFirst(wins + ""));
		pSender.sendMessage(PLAYED_MESSAGE.replaceFirst(games + ""));
		pSender.sendMessage(POINTS_MESSAGES.replaceFirst(points / playerData.size() + ""));
	}

	@Override
	public String getName() {
		return NAME;
	}
}
