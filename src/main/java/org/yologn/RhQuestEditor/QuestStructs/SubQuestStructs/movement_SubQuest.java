package org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs;

import org.yologn.RhQuestEditor.QuestIO;

import java.io.IOException;
import java.util.ArrayList;

public class movement_SubQuest extends SubQuest {
    public int id_count;
    public ArrayList<stMovementDestId> m_DestList = new ArrayList<>();

    @Override
    public void Load(QuestIO reader, int FILE_VERSION) throws IOException {
        super.Load(reader, FILE_VERSION);
        id_count = reader.readInt();

        for (int i = 0; i < id_count; ++i) {
            stMovementDestId mdid = new stMovementDestId();
            mdid.uiRegion = reader.readInt();
            mdid.uiRoom = reader.readInt();
            m_DestList.add(mdid);
        }
    }

    public movement_SubQuest(movement_SubQuest other) {
        super(other);
        this.id_count = other.id_count;
        for(int i = 0; i < other.m_DestList.size(); i++)
        {
            stMovementDestId t = new stMovementDestId();
            t.uiRoom = other.m_DestList.get(i).uiRoom;
            t.uiRegion = other.m_DestList.get(i).uiRegion;
            this.m_DestList.add(t);
        }
    }
    public movement_SubQuest()
    {

    }
}
