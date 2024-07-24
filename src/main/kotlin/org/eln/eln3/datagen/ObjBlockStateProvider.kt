package org.eln.eln3.datagen

import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder
import net.neoforged.neoforge.common.data.ExistingFileHelper
import org.eln.eln3.Eln3
import org.eln.eln3.registry.ElnBlockEntities

class ObjBlockStateProvider(output: PackOutput, existingFileHelper: ExistingFileHelper) : BlockStateProvider(output, Eln3.MODID, existingFileHelper) {


    override fun registerStatesAndModels() {
        val testBlock = ElnBlockEntities.SIMPLE_TEST_BLOCK.get()

        val customBuilder = models().getBuilder(ElnBlockEntities.SIMPLE_TEST_BLOCK.id.path).customLoader{ t, l ->
            ObjModelBuilder.begin(t, l)
        }
            .modelLocation(modLoc("models/block/maceratorb/maceratorb2.obj"))
            .flipV(true)
            .end()
            .texture("None", modLoc("block/maceratorb2"))
            .renderType("minecraft:cutout")
        simpleBlock(testBlock, customBuilder)
    }


}