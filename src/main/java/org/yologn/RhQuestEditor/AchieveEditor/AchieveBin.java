package org.yologn.RhQuestEditor.AchieveEditor;

import org.yologn.RhQuestEditor.QuestIO;
import org.yologn.RhQuestEditor.Util;

import java.io.IOException;

public class AchieveBin {
    public int disable;
    public int id;
    public String titleName;
    public String koreanName;
    public int AchieveType;
    public int useOptionCount;
    public int ItemOptionType1;
    public int ItemOptionValue_1;
    public int ItemOptionType2;
    public int ItemOptionValue_2;
    public int ItemOptionType3;
    public int ItemOptionValue_3;
    public int ItemOptionType4;
    public int ItemOptionValue_4;
    public int ItemOptionType5;
    public int ItemOptionValue_5;
    public String  IconName;

    void load(QuestIO reader) throws IOException {
        disable = reader.readInt();
        id = reader.readInt();
        titleName = reader.getString(50);
        koreanName = reader.getString(50);
        AchieveType = reader.readInt();
        useOptionCount = reader.readInt();
        ItemOptionType1 = reader.readInt();
        ItemOptionValue_1 = reader.readInt();
        ItemOptionType2 = reader.readInt();
        ItemOptionValue_2 = reader.readInt();
        ItemOptionType3 = reader.readInt();
        ItemOptionValue_3 = reader.readInt();
        ItemOptionType4 = reader.readInt();
        ItemOptionValue_4 = reader.readInt();
        ItemOptionType5 = reader.readInt();
        ItemOptionValue_5 = reader.readInt();
        IconName = reader.getString(52);
    }
    void write(QuestIO writer) throws IOException {
        writer.writeInt(disable);
        writer.writeInt(id);
        writer.writeBlock(Util.stringToBt(titleName, 50));
        writer.writeBlock(Util.stringToBt(koreanName, 50));
        writer.writeInt(AchieveType);
        writer.writeInt(useOptionCount);
        writer.writeInt(ItemOptionType1);
        writer.writeInt(ItemOptionValue_1);
        writer.writeInt(ItemOptionType2);
        writer.writeInt(ItemOptionValue_2);
        writer.writeInt(ItemOptionType3);
        writer.writeInt(ItemOptionValue_3);
        writer.writeInt(ItemOptionType4);
        writer.writeInt(ItemOptionValue_4);
        writer.writeInt(ItemOptionType5);
        writer.writeInt(ItemOptionValue_5);
        writer.writeBlock(Util.stringToBt(IconName, 52));
        writer.flush();

    }

    public AchieveBin() {
    }

    public AchieveBin(AchieveBin other) {
        this.disable = other.disable;
        this.id = other.id;
        this.titleName = other.titleName;
        this.koreanName = other.koreanName;
        this.AchieveType = other.AchieveType;
        this.useOptionCount = other.useOptionCount;
        this.ItemOptionType1 = other.ItemOptionType1;
        this.ItemOptionValue_1 = other.ItemOptionValue_1;
        this.ItemOptionType2 = other.ItemOptionType2;
        this.ItemOptionValue_2 = other.ItemOptionValue_2;
        this.ItemOptionType3 = other.ItemOptionType3;
        this.ItemOptionValue_3 = other.ItemOptionValue_3;
        this.ItemOptionType4 = other.ItemOptionType4;
        this.ItemOptionValue_4 = other.ItemOptionValue_4;
        this.ItemOptionType5 = other.ItemOptionType5;
        this.ItemOptionValue_5 = other.ItemOptionValue_5;
        this.IconName = other.IconName;
    }
}
