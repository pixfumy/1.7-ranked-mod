package io.github.marinersfan824.rankedmod.mixinterface;

import net.minecraft.util.math.BlockBox;

public interface ISpiralStaircase {
    void setPortalRoomBox(BlockBox boundingBox);
    BlockBox getPortalRoomBox();
}
