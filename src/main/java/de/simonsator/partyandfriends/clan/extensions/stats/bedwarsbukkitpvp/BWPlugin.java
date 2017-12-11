package de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp.configuration.BWConfig;
import de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp.configuration.BWMessages;
import de.simonsator.partyandfriends.utilities.Language;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

public class BWPlugin extends PAFExtension {
	private Configuration messages;
	private Configuration config;
	private BWStatsConnection connection;

	@Override
	public void onEnable() {
		try {
			config = new BWConfig(new File(getConfigFolder(), "config.yml")).getCreatedConfiguration();
			messages = new BWMessages(Language.OWN, new File(getConfigFolder(), "messages.yml")).getCreatedConfiguration();
			connection = new BWStatsConnection(config.getString("database.db"), "jdbc:mysql://" + config.getString("database.host") + ":" + config.getInt("database.port"), config.getString("database.user"), config.getString("database.password"));
			((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new BWStat(messages, connection), this);
		} catch (IOException e) {
		}
	}

}
