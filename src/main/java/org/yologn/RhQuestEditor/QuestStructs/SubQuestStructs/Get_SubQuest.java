package org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs;

import org.yologn.RhQuestEditor.QuestIO;

import java.io.IOException;
import java.util.ArrayList;

public class Get_SubQuest extends SubQuest{
    public int m_getItemType;
    public int m_iGiverNum;
    public ArrayList<Get_SubQuest_Givers> m_GiverTypes = new ArrayList<>();
    public short m_getItemCount;
    public byte m_bGetFromMonster;

    @Override
    public void Load(QuestIO reader, int FILE_VERSION)throws IOException
    {
        super.Load(reader, FILE_VERSION);

        m_getItemType = reader.readInt();
        m_iGiverNum = reader.readInt();

        for (int i = 0; i < m_iGiverNum; ++i) {
            Get_SubQuest_Givers givernum = new Get_SubQuest_Givers();
            givernum.giver_type_num = reader.readInt();

            for (int t = 0; t < givernum.giver_type_num; ++t) {
                int g_type = reader.readInt();
                givernum.sub_giver.add(g_type);
            }
            givernum.drop_percent = reader.readByte();
            m_GiverTypes.add(givernum);
        }
        m_getItemCount = reader.readShort();
        m_bGetFromMonster = reader.readByte();
    }

    public Get_SubQuest(Get_SubQuest other) {
        super(other);
        this.m_getItemType = other.m_getItemType;
        this.m_iGiverNum = other.m_iGiverNum;
        this.m_getItemCount = other.m_getItemCount;
        this.m_bGetFromMonster = other.m_bGetFromMonster;

        for(int i = 0; i < other.m_GiverTypes.size(); i++)
        {
            Get_SubQuest_Givers t = new Get_SubQuest_Givers();
            t.drop_percent = other.m_GiverTypes.get(i).drop_percent;
            t.giver_type_num = other.m_GiverTypes.get(i).giver_type_num;
            t.sub_giver.addAll(other.m_GiverTypes.get(i).sub_giver);
            this.m_GiverTypes.add(t);
        }
    }
    public Get_SubQuest()
    {

    }
}
