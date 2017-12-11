package de.simonsator.partyandfriends.clan.extensions.stats.bedwarsbukkitpvp.configuration;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

public class BWMessages extends LanguageConfiguration {
	public BWMessages(Language pLanguage, File pFile) throws IOException {
		super(pLanguage, pFile);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Name", "BedWars");
		set("Kills", "&7The clan has &a[KILLS] &7kills.");
		set("Deaths", "&7The clan has died &c[DEATHS] &7times.");
		set("DestroyedBeds", "&7The clan has &a[DESTROYED] &7beds.");
		set("Wins", "&7The clan has won &a[WINS] &7times.");
		set("Played", "&7The clan has &a[PLAYED] &7times.");
		set("Points", "&7The clan has &a[POINTS]&7.");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new BWMessages(Language.OWN, FILE)).getCreatedConfiguration();
	}
}
