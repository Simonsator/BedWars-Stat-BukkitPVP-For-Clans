package de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp.configuration;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class BWConfig extends ConfigurationCreator {
	public BWConfig(File file) throws IOException {
		super(file);
		readFile();
		loadDefaultValues();
		saveFile();
	}

	private void loadDefaultValues() {
		set("database.host", "localhost");
		set("database.port", 3306);
		set("database.db", "minecraft");
		set("database.user", "root");
		set("database.password", "password");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new BWConfig(FILE)).getCreatedConfiguration();
	}
}