package snownee.fastscroll.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.screens.MouseSettingsScreen;
import net.minecraft.client.gui.screens.Screen;

@Mixin(OptionInstance.class)
public class OptionInstanceMixin {

	@Shadow
	Object value;

	@Inject(at = @At("HEAD"), method = "get", cancellable = true)
	private void fastscroll_get(CallbackInfoReturnable<Object> ci) {
		if (Minecraft.getInstance().screen instanceof MouseSettingsScreen) {
			return;
		}
		if (this == (Object) Minecraft.getInstance().options.mouseWheelSensitivity() && Screen.hasControlDown()) {
			ci.setReturnValue((Double) value * 4);
		}
	}

}
