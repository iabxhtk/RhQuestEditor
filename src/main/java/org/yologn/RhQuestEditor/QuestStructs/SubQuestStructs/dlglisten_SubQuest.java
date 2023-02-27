package org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs;

import org.yologn.RhQuestEditor.QuestIO;

import java.io.IOException;
import java.util.ArrayList;

public class dlglisten_SubQuest extends SubQuest{
    public short dfcnt;
    public ArrayList<String> m_dlgFiles = new ArrayList<>();

    @Override
    public void Load(QuestIO reader, int FILE_VERSION) throws IOException {
        super.Load(reader, FILE_VERSION);

        dfcnt = reader.readShort();

        for (int i = 0; i < dfcnt; ++i) {
            String dlg_fileName = reader.readLengthString();
            m_dlgFiles.add(dlg_fileName);
        }
    }
}
