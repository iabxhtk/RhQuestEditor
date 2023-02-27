package org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs;

import org.yologn.RhQuestEditor.QuestIO;
import java.io.IOException;
import java.util.ArrayList;

public class Kill_SubQuest extends SubQuest {
    public int target_num;
    public ArrayList<Kill_SubQuest_Targets> m_KillMonTypes = new ArrayList<>();
    public int m_equipWeaponType;
    public short m_killCounter;
    public byte m_bKillMonster;
    public int m_iKillPlayerMinLevel;

    @Override
    public void Load(QuestIO reader, int FILE_VERSION)throws IOException
    {
        super.Load(reader, FILE_VERSION);

        target_num = reader.readInt();
        for (int i = 0; i < target_num; ++i) {
            Kill_SubQuest_Targets ksubQT = new Kill_SubQuest_Targets();
            ksubQT.target_type = reader.readInt();
            m_KillMonTypes.add(ksubQT);
        }
        m_equipWeaponType = reader.readInt();
        m_killCounter = reader.readShort();
        m_bKillMonster = reader.readByte();
        m_iKillPlayerMinLevel = reader.readInt();
    }

    public Kill_SubQuest(Kill_SubQuest other) {
        super(other);
        this.target_num = other.target_num;
        this.m_equipWeaponType = other.m_equipWeaponType;
        this.m_killCounter = other.m_killCounter;
        this.m_bKillMonster = other.m_bKillMonster;
        this.m_iKillPlayerMinLevel = other.m_iKillPlayerMinLevel;
        for(int i = 0; i < other.m_KillMonTypes.size(); i++)
        {
            Kill_SubQuest_Targets t = new Kill_SubQuest_Targets();
            t.target_type = other.m_KillMonTypes.get(i).target_type;
            this.m_KillMonTypes.add(t);
        }
    }
    public Kill_SubQuest()
    {

    }
}
