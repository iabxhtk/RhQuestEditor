package org.yologn.RhQuestEditor.QuestStructs;

import java.util.ArrayList;

public class QItemData {
    public int item_id;
    public short item_num;
    public byte no_limit_status;
    public int random_opt_level;
    public int fix_opt_num;
    public ArrayList<QItemOption> itemOptions = new ArrayList<>();
}
