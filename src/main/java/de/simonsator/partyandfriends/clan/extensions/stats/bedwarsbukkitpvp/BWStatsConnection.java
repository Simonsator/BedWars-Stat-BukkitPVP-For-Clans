package de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp;

import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class BWStatsConnection extends SQLCommunication {
	protected BWStatsConnection(String pDatabase, String pURL, String pUserName, String pPassword) {
		super(pDatabase, pURL, pUserName, pPassword);
	}

	public PlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select Beds, Kills, Deaths, Wins, Games, Points from `"
					+ DATABASE + "`." + "bw_stats WHERE UUID='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next())
				return new PlayerData(rs.getInt("Wins"), rs.getInt("Beds"), rs.getInt("Deaths"),
						rs.getInt("Kills"), rs.getInt("Games"), rs.getInt("Points"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, stmt);
		}
		return null;
	}

}
