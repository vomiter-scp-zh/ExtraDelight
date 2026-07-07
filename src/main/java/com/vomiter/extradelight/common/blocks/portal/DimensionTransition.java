//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.vomiter.extradelight.common.blocks.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

public record DimensionTransition(ServerLevel newLevel, Vec3 pos, Vec3 speed, float yRot, float xRot, boolean missingRespawnBlock, PostDimensionTransition postDimensionTransition) {
    public static final PostDimensionTransition DO_NOTHING = (p_352417_) -> {
    };
    public static final PostDimensionTransition PLAY_PORTAL_SOUND = DimensionTransition::playPortalSound;
    public static final PostDimensionTransition PLACE_PORTAL_TICKET = DimensionTransition::placePortalTicket;

    public DimensionTransition(ServerLevel p_348637_, Vec3 p_348645_, Vec3 p_348472_, float p_348548_, float p_348664_, PostDimensionTransition p_352139_) {
        this(p_348637_, p_348645_, p_348472_, p_348548_, p_348664_, false, p_352139_);
    }

    public DimensionTransition(ServerLevel p_348609_, Entity p_352432_, PostDimensionTransition p_352373_) {
        this(p_348609_, findAdjustedSharedSpawnPos(p_348609_, p_352432_), Vec3.ZERO, 0.0F, 0.0F, false, p_352373_);
    }

    private static void playPortalSound(Entity entity) {
        if (entity instanceof ServerPlayer serverplayer) {
            serverplayer.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
        }

    }

    private static void placePortalTicket(Entity entity) {
        placePortalTicket(BlockPos.containing(entity.position()), entity);
    }
    public static void placePortalTicket(BlockPos pos, Entity entity) {
        Level var3 = entity.level();
        if (var3 instanceof ServerLevel serverlevel) {
            serverlevel.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(pos), 3, pos);
        }

    }


    public static DimensionTransition missingRespawnBlock(ServerLevel level, Entity entity, PostDimensionTransition postDimensionTransition) {
        return new DimensionTransition(level, findAdjustedSharedSpawnPos(level, entity), Vec3.ZERO, 0.0F, 0.0F, true, postDimensionTransition);
    }

    private static Vec3 findAdjustedSharedSpawnPos(ServerLevel newLevel, Entity entity) {
        return Vec3.atBottomCenterOf(adjustSpawnLocation(newLevel, newLevel.getSharedSpawnPos()));
    }

    private static BlockPos adjustSpawnLocation(ServerLevel level, BlockPos pos) {
        BlockPos blockpos = level.getSharedSpawnPos();
        Vec3 vec3 = blockpos.getCenter();
        int i = level.getChunkAt(blockpos).getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos.getX(), blockpos.getZ()) + 1;
        return BlockPos.containing(vec3.x, (double)i, vec3.z);
    }


    @FunctionalInterface
    public interface PostDimensionTransition {
        void onTransition(Entity var1);

        default PostDimensionTransition then(PostDimensionTransition transition) {
            return (p_352242_) -> {
                this.onTransition(p_352242_);
                transition.onTransition(p_352242_);
            };
        }
    }
}
