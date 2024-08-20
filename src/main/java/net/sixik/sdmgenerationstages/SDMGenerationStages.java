package net.sixik.sdmgenerationstages;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.common.Mod;
import net.sixik.sdmgenerationstages.stage.StageContainer;
import org.slf4j.Logger;

@Mod(SDMGenerationStages.MODID)
public class SDMGenerationStages {

    public static final String MODID = "sdmgenerationstages";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SDMGenerationStages() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::onReload);


    }


    public void onReload(AddReloadListenerEvent event){
        event.addListener(StageContainer.INSTANCE);
    }
}
