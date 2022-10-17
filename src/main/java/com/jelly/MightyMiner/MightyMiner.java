package com.jelly.MightyMiner;

import com.jelly.MightyMiner.config.Config;
import com.jelly.MightyMiner.config.CoordsConfig;
import com.jelly.MightyMiner.config.factory.ConfigurationFactory;
import com.jelly.MightyMiner.handlers.KeybindHandler;
import com.jelly.MightyMiner.handlers.MacroHandler;
import com.jelly.MightyMiner.render.BlockRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(name = "MightyMiner", modid = MightyMiner.MODID, version = MightyMiner.VERSION)
public class MightyMiner {
    public static final String MODID = "mightyminer";
    public static final String VERSION = "1.0";

    public static Config config;


    public static List<BlockPos> coords = new ArrayList<>();

    public static ConfigurationFactory configurationFactory = new ConfigurationFactory();

    public static CoordsConfig coordsConfig;
    //thx pizza for fixing this
    public static void onStartGame(){
        coords.clear();
        config = new Config();

        coordsConfig = configurationFactory.create(CoordsConfig.class, new File("config/coords.yml"));

        coordsConfig.blockPos
                .forEach(coords::add);


    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {


        MinecraftForge.EVENT_BUS.register(new MacroHandler());
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
        MinecraftForge.EVENT_BUS.register(new BlockRenderer());
        KeybindHandler.initializeCustomKeybindings();
        MacroHandler.initializeMacro();


        Minecraft.getMinecraft().gameSettings.gammaSetting = 100;

    }


}
