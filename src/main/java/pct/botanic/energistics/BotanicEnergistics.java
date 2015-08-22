package pct.botanic.energistics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import pct.botanic.energistics.Blocks.AERuneAssembler;
import pct.botanic.energistics.Blocks.tile.TileAERuneAssembler;
import pct.botanic.energistics.Items.PatternATMPT2;
import pct.botanic.energistics.Items.RuneAssemblerCraftingPattern;
import pct.botanic.energistics.references.CoreRefs;


@Mod(modid = CoreRefs.MODID, name = CoreRefs.NAME,version = CoreRefs.VERSIONS,dependencies = CoreRefs.DEPENDENCIES,acceptedMinecraftVersions = "1.7.10")
public class BotanicEnergistics {

    @Mod.Instance()
    public static BotanicEnergistics instance;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event){
        GameRegistry.registerItem(new RuneAssemblerCraftingPattern(), "RuneAssemblerPattern", CoreRefs.MODID);
        GameRegistry.registerTileEntity(TileAERuneAssembler.class, "tile.runeassembler");
        GameRegistry.registerBlock(new AERuneAssembler(), "AERuneAssembler");

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new pct.botanic.energistics.GUI.GUIHandler());
    }

    public static void init(FMLInitializationEvent event){

    }

    public static void postInit(FMLPostInitializationEvent event){

    }

}
