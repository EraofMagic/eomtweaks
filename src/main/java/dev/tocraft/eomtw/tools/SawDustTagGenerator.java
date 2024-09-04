package dev.tocraft.eomtw.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class SawDustTagGenerator {

    private static final ITag<Item> planks = ForgeRegistries.ITEMS.tags().getTag(ItemTags.PLANKS);
    private static final ITag<Item> logs = ForgeRegistries.ITEMS.tags().getTag(ItemTags.LOGS);

    public static void generate()  {
        File dir = new File(Minecraft.getInstance().gameDirectory.getPath(),"EoMDumps");
        if (!dir.exists())
            dir.mkdirs();

        File file = new File(dir, "itemNameDump.txt");
        file.delete();
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Item element : planks) {
                fileWriter.write("\n" + "\"" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(element)) + "\"");
                fileWriter.flush();
            }

            for (Item element : logs) {
                fileWriter.write("\n" + "\"" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(element)) + "\"");
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
