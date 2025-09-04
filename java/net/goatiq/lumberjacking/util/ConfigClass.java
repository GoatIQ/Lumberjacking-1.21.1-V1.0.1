package net.goatiq.lumberjacking.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConfigClass {

    private static Path FilePath = Path.of("config/lumberjacking.properties");
    private static final Map<String, Float> Data = new LinkedHashMap<>();

    private void WriteConfig() throws IOException {
        Files.createFile(FilePath);
        List<String> StringToWrite= new LinkedList<>();
        String FinalString= "";
        StringToWrite.add("[ChoppingBlockDurability]");
        StringToWrite.add("#This sections sets the durability of chop amount a chopping block can handle before breaking");
        StringToWrite.add("#Put -1 to make the chopping block unbreakable");
        StringToWrite.add("oak_chopping_block=160");
        StringToWrite.add("spruce_chopping_block=64");
        StringToWrite.add("birch_chopping_block=64");
        StringToWrite.add("jungle_chopping_block=128");
        StringToWrite.add("acacia_chopping_block=128");
        StringToWrite.add("dark_oak_chopping_block=160");
        StringToWrite.add("mangrove_chopping_block=96");
        StringToWrite.add("cherry_chopping_block=96");
        StringToWrite.add("bamboo_chopping_block=128");
        StringToWrite.add("crimson_chopping_block=64");
        StringToWrite.add("warped_chopping_block=64");
        StringToWrite.add("[LogsModification]");
        StringToWrite.add("#This sections sets the strength of logs, you can disable this feature, by default the strength is 2.0f");
        StringToWrite.add("enable=1");
        StringToWrite.add("oak_logs=5.0f");
        StringToWrite.add("spruce_logs=2.0f");
        StringToWrite.add("birch_logs=2.0f");
        StringToWrite.add("jungle_logs=4.0f");
        StringToWrite.add("acacia_logs=4.0f");
        StringToWrite.add("dark_oak_logs=5.0f");
        StringToWrite.add("mangrove_logs=3.0f");
        StringToWrite.add("cherry_logs=3.0f");
        StringToWrite.add("crimson_logs=2.0f");
        StringToWrite.add("warped_logs=2.0f");

        for(int i = 0; i< StringToWrite.size(); i ++)
        {
            FinalString = FinalString + StringToWrite.get(i) + "\n";
        }

        Files.writeString(FilePath,FinalString);

    }

    private boolean LoadConfigFile() throws IOException
    {
        if (Files.exists(FilePath))
        {
            for(String LineRead : Files.readAllLines(FilePath))
            {
                LineRead = LineRead.trim();
                if(IsLineReadable(LineRead))
                {
                    String[] LineParts = LineRead.split("=");
                    if(LineParts.length == 2)
                    {
                        this.Data.put(LineParts[0], Float.valueOf(LineParts[1]));
                    }
                }
            }

            return true;

        } else {

            return false;

        }
    }

    private boolean IsLineReadable(String input)
    {
        if(input.startsWith("#") || input.isBlank() || !input.contains("="))
        {
            return false;

        } else {

            return true;

        }
    }

    public Map<String,Float> Export()
    {
        return this.Data;
    }

    public void Init() throws IOException {
        if(!this.LoadConfigFile())
        {
            this.WriteConfig();
            this.LoadConfigFile();
        }
    }
}
