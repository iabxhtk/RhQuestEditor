package org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs;

import org.yologn.RhQuestEditor.QuestIO;

import java.io.IOException;
import java.util.ArrayList;

public class Goto_SubQuest extends SubQuest {
    public int m_DestinationType;
    public short dicnt; // deliver item count
    public short qicnt; // quest item count
    public ArrayList<Goto_SubQuest_DeliverItem> m_DeliverItems = new ArrayList<>();
    public ArrayList<GoTo_SubQuest_QuestItem> m_TempGainedQuestItems = new ArrayList();

    public String m_intro_dlg_filename = "";
    public String m_reintro_dlg_filename = "";

    @Override
    public void Load(QuestIO reader, int FILE_VERSION)throws IOException
    {
        super.Load(reader, FILE_VERSION);

        m_DestinationType = reader.readInt();
        dicnt = reader.readShort();

        for (int i = 0; i < dicnt; ++i) {
            Goto_SubQuest_DeliverItem gSubDi = new Goto_SubQuest_DeliverItem();
            gSubDi.type = reader.readInt();
            gSubDi.cnt = reader.readShort();
            m_DeliverItems.add(gSubDi);
        }

        qicnt = reader.readShort();
        for (int g = 0; g < qicnt; ++g) {
            GoTo_SubQuest_QuestItem gSubQi = new GoTo_SubQuest_QuestItem();

            gSubQi.type = reader.readInt();
            gSubQi.cnt = reader.readShort();

            m_TempGainedQuestItems.add(gSubQi);
        }


        m_intro_dlg_filename = reader.readLengthString();
        m_reintro_dlg_filename = reader.readLengthString();
    }

    public Goto_SubQuest(Goto_SubQuest other) {
        super(other);
        this.m_DestinationType = other.m_DestinationType;
        this.dicnt = other.dicnt;
        this.qicnt = other.qicnt;
        for(int i = 0; i < other.m_DeliverItems.size(); i++)
        {
            Goto_SubQuest_DeliverItem t = new Goto_SubQuest_DeliverItem();
            t.cnt = other.m_DeliverItems.get(i).cnt;
            t.type = other.m_DeliverItems.get(i).type;
            this.m_DeliverItems.add(t);
        }
        for(int i = 0; i < other.m_TempGainedQuestItems.size(); i++)
        {
            GoTo_SubQuest_QuestItem t = new GoTo_SubQuest_QuestItem();
            t.cnt = other.m_TempGainedQuestItems.get(i).cnt;
            t.type = other.m_TempGainedQuestItems.get(i).type;
            this.m_TempGainedQuestItems.add(t);
        }
        this.m_intro_dlg_filename = other.m_intro_dlg_filename;
        this.m_reintro_dlg_filename = other.m_reintro_dlg_filename;
    }

    public Goto_SubQuest() {
        super();
    }
}
