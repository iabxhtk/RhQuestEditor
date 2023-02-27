package org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs;

public enum EQuestType {
    GOTO_TYPE(0x0),
    GETITEM_TYPE(0x1),
    KILLMOB_TYPE(0x2),
    SPECIAL_TYPE(0x3),
    MOVEMENT_TYPE(0x4),
    ETC_TYPE(0x5),
    DLGLISTEN_TYPE(0x6),
    INTO_TYPE(0x7),
    ITEM_TYPE(0x8),
    QUEST_TYPE(0x9),
    CHECK_TYPE(0xA),
    UNKNOWN(0xB),
    NONE(-1);
    private int id; // Could be other data type besides int

    private EQuestType(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public static EQuestType fromId(int id) {
        for (EQuestType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return NONE;
    }

}
