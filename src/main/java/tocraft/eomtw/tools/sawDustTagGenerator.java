package tocraft.eomtw.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class sawDustTagGenerator {

    private static final ITag<Item> planks = ForgeRegistries.ITEMS.tags().getTag(ItemTags.PLANKS);
    private static final ITag<Item> logs = ForgeRegistries.ITEMS.tags().getTag(ItemTags.LOGS);

    public static void generate()  {
        File dir = new File(Minecraft.getInstance().gameDirectory.getPath(),"eomDumps");
        if (!dir.exists())
            dir.mkdirs();

        File file = new File(dir, "itemNameDump.txt");
        file.delete();
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Item element : planks) {
                fileWriter.write("\n" + "\"" + element.getRegistryName().toString() + "\"");
                fileWriter.flush();
            }

            for (Item element : logs) {
                fileWriter.write("\n" + "\"" + element.getRegistryName().toString() + "\"");
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
