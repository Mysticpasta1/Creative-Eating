package com.mystic.eating.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFood.class)
public class MixinEating
{

    @Shadow private boolean alwaysEdible;

    @Inject(method = "onItemRightClick", at = @At("HEAD"), cancellable = true)
    public void onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn, CallbackInfoReturnable<ActionResult<ItemStack>> cir)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (playerIn.canEat(this.alwaysEdible) || itemstack.getItem() instanceof ItemFood && playerIn.isCreative())
        {
            playerIn.setActiveHand(handIn);
            cir.setReturnValue(new ActionResult<>(EnumActionResult.SUCCESS, itemstack));
        }
        else
        {
            cir.setReturnValue(new ActionResult<>(EnumActionResult.FAIL, itemstack));
        }
    }
}
