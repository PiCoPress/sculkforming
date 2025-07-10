package net.picopress.mc.mods.sculkforming.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SculkBlock;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(SculkBlock.class)
public class SculkBlockMixin {
    /**
     * @author picopress
     * @reason I think nobody overwrites this method
     */
    @Overwrite
    private BlockState getRandomGrowthState(LevelAccessor accessor, BlockPos pos, RandomSource random, boolean bl) {
        BlockState state;
        final int test = random.nextInt(11);
        if (test == 0) {
            state = Blocks.SCULK_SHRIEKER.defaultBlockState().setValue(SculkShriekerBlock.CAN_SUMMON, bl);
        } else if(test < 8) {
            state = Blocks.SCULK_SENSOR.defaultBlockState();
        } else {
            state = Blocks.SCULK_CATALYST.defaultBlockState();
        }

        return state.hasProperty(BlockStateProperties.WATERLOGGED) && !accessor.getFluidState(pos).isEmpty()? state.setValue(BlockStateProperties.WATERLOGGED, true): state;

    }
}
