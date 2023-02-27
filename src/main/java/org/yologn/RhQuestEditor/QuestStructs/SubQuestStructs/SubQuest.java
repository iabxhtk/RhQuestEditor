package org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs;

import org.yologn.RhQuestEditor.QuestIO;

import java.io.IOException;
import java.util.ArrayList;

public class SubQuest {
    public byte m_subQuestType;
    public byte m_SubQuestID;
    public short prevcnt;
    public ArrayList<Byte> subids = new ArrayList<>();
    public byte subid;

    public ArrayList<String> m_presubQuestIDs = new ArrayList<>();;

    public byte m_NextSubQuestID;
    public int m_ParentQuestID;
    public String m_subQuestTitle;
    public byte m_immediateReward;
    public long m_reward_xp;
    public long m_reward_money;
    public int m_iTimeLimit;
    public int[] m_special_complete_condition = new int[6];

    public String toString() {
        return m_SubQuestID + " - " + m_subQuestTitle;

    }

    public void Load(QuestIO reader, int FILE_VERSION) throws IOException {
        m_subQuestType = reader.readByte();
        m_SubQuestID = reader.readByte();
        prevcnt = reader.readShort();

        for (int t = 0; t <prevcnt; ++t) {
            byte subId = reader.readByte();
            subids.add(subId);
        }

        m_NextSubQuestID = reader.readByte();
        m_ParentQuestID = reader.readInt();


        m_subQuestTitle = reader.readLengthString();
        m_immediateReward = reader.readByte();
        if(FILE_VERSION >= 28)
        {
            m_reward_xp = reader.readInt64();
            m_reward_money = reader.readInt64();
        }
        else
        {
            m_reward_xp = reader.readInt();
            m_reward_money = reader.readInt();
        }

        m_iTimeLimit = reader.readInt();
        for (int i = 0; i < 6; i++) {
            m_special_complete_condition[i] = reader.readInt();
        }
    }

    public SubQuest(SubQuest other) {
        this.m_subQuestType = other.m_subQuestType;
        this.m_SubQuestID = other.m_SubQuestID;
        this.prevcnt = other.prevcnt;
        this.subid = other.subid;
        this.m_presubQuestIDs = other.m_presubQuestIDs;
        this.m_NextSubQuestID = other.m_NextSubQuestID;
        this.m_ParentQuestID = other.m_ParentQuestID;
        this.m_subQuestTitle = other.m_subQuestTitle;
        this.m_immediateReward = other.m_immediateReward;
        this.m_reward_xp = other.m_reward_xp;
        this.m_reward_money = other.m_reward_money;
        this.m_iTimeLimit = other.m_iTimeLimit;
        for(int i = 0; i < 6; i++)
        {
            this.m_special_complete_condition[i] = other.m_special_complete_condition[i];
        }
        this.subids.addAll(other.subids);
    }
    public SubQuest()
    {

    }
}
