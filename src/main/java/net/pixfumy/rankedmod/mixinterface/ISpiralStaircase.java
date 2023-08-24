package net.pixfumy.rankedmod.mixinterface;

import net.minecraft.util.math.BlockBox;

public interface ISpiralStaircase {
    void setPortalRoomBox(BlockBox boundingBox);
    BlockBox getPortalRoomBox();
}
