package net.goatiq.lumberjacking;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.goatiq.lumberjacking.RecipeManager.ChoppingBlockManager;
import net.goatiq.lumberjacking.blocks.BlockClass;
import net.goatiq.lumberjacking.blocks.entity.BlockEntitiesClass;
import net.goatiq.lumberjacking.items.GroupClass;
import net.goatiq.lumberjacking.items.ItemClass;
import net.goatiq.lumberjacking.util.ConfigClass;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Lumberjacking implements ModInitializer {
	public static final String Namespace = "lumberjacking";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(Namespace);
	private static ConfigClass ConfigFile = new ConfigClass();
	public static Map<String,Float> ConfigData = new LinkedHashMap<>();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        try
		{
            ConfigFile.Init();
			ConfigData = ConfigFile.Export(); //storing only the data maps as we will use it later on with injection methods

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        ItemClass.InitItems();
		BlockClass.InitBlocks();
		BlockEntitiesClass.InitBlockEntities();
		GroupClass.InitItemGroups();
		ChoppingBlockManager.InitRecipe();
		//
		LOGGER.info("Done loading mod");
		ConfigFile = null; // clearing config values from memory as there are useless now
    }
}