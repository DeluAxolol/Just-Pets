package com.bretzelfresser.rendering.rendering;

import com.bretzelfresser.entity.Jerboa;
import com.bretzelfresser.rendering.model.JerboaModel;
import com.bretzelfresser.rendering.model.ModModelLayerLocations;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JerboaRenderer extends MobRenderer<Jerboa, JerboaModel> {
    public JerboaRenderer(EntityRendererProvider.Context context) {
        super(context, new JerboaModel(context.bakeLayer(ModModelLayerLocations.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(Jerboa jerboa) {
        return jerboa.getVariant().getTextureLocation();
    }
}
