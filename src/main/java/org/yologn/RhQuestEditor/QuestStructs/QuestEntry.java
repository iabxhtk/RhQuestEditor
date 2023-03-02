package org.yologn.RhQuestEditor.QuestStructs;

import org.yologn.RhQuestEditor.QuestIO;
import org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs.*;
import org.yologn.RhQuestEditor.Util;

import java.io.IOException;
import java.util.ArrayList;
import org.yologn.RhQuestEditor.Util.EDITOR_TYPE;
public class QuestEntry {
    private final int FILE_VERSION;
    private EDITOR_TYPE editor_type;

    public QTitleInfo titleInfo;
    public String q_name = "";
    public String q_desc = "";

    public int quest_ID;
    //npc data
    public int owner_NPC;
    public int reward_NPC;

    //player data
    public short minReqLevel;
    public short maxReqLevel;
    public short recommendedLevel;
    public short playerClass;
    public short playerClass2;
    public byte interConnectQuest;

    public int parentQuestID;
    public int childQuestID;

    public byte disable;
    public byte iterationQuest;
    public byte partyQuest;
    public byte guildCreateQuest;

    public long reward_EXP;
    public long reward_Money;

    public int rewardFlag;
    public int itemCountForSelect;
    public short rewardItemsCount;
    public ArrayList<QItemData> rewardItems = new ArrayList<>();
    public short requiredItemsCount;
    public ArrayList<QItemData> requiredItems = new ArrayList<>();

    public short dialogsCount;
    public ArrayList<QuestDialog> dialogList = new ArrayList<>();

    public short subQuestCount;
    public ArrayList<SubQuest> m_SubQuests = new ArrayList<>();

    public int iTimeLimit;
    public byte bUseEventTime;
    public _SYSTEMTIME m_EventTimeStart = new _SYSTEMTIME();
    public _SYSTEMTIME m_EventTimeEnd = new _SYSTEMTIME();

    public ArrayList<QuestSpecialCondition> SpecialConditionList = new ArrayList<>();
    public QuestSpecialCondition m_special_reward = new QuestSpecialCondition();
    public int Special_Condition_Count;// size of 24


    public byte m_bAlarmDisable;
    public byte m_bChangeJobQuest;
    public int m_ChangeJobType;
    public _SYSTEMTIME m_AcceptStartDate = new _SYSTEMTIME();
    public _SYSTEMTIME m_AcceptEndDate = new _SYSTEMTIME();
    public int m_AcceptHour;
    public int m_AcceptMinute;
    public int m_AcceptDurationTime;
    public int m_ExecuteDurationTime;
    public String m_AcceptWeek = "       ";         //size of 7
    public int m_reward_num;
    public ArrayList<stLadderReward> m_LadderRewards = new ArrayList<>();
    public int m_EntryFee;
    public String m_InfoMsg;
    public String m_InfoMsg2;
    public String m_InfoMsg3;
    public byte m_bMainQuest;
    public byte m_bGuildMasterQuest;
    public byte m_bUseLevelExpTable;
    public byte m_bUseLevelMoneyTable;
    public byte m_bBossQuest;
    public byte m_bSimpleQuest;
    public byte m_bUseLevelNotyMsg;
    public String m_LevelNoti = "";
    public String m_LevelNoti2 = "";
    public byte m_bNoPartyQuest;
    public byte m_bLadderQuest;
    public byte m_bPartyShareQuest;

    public byte m_bDailyQuest;
    public byte m_bWeeklyQuest;
    public byte m_bMonthlyQuest;
    public byte m_IsGuildQuest;
    public _SYSTEMTIME m_MissionStartDate = new _SYSTEMTIME();
    public _SYSTEMTIME m_MissionEndDate = new _SYSTEMTIME();
    public String m_MissionWeek = "       ";

    @Override
    public String toString() {
        return quest_ID + " - " + q_name;
    }

    public QuestEntry(int fileVer, EDITOR_TYPE questType)
    {
        FILE_VERSION = fileVer;
        editor_type = questType;
    }
    public QuestEntry(int fileVer, EDITOR_TYPE _editor_type, int questId, QuestEntry questToCopy)
    {
        FILE_VERSION = fileVer;
        editor_type = _editor_type;
        if(editor_type == EDITOR_TYPE.TITLE)
        {
            titleInfo = new QTitleInfo();
            titleInfo.titleName = questToCopy.titleInfo.titleName;
            titleInfo.titleData.item_id = questToCopy.titleInfo.titleData.item_id;
            titleInfo.titleData.item_num = questToCopy.titleInfo.titleData.item_num;
            titleInfo.titleData.no_limit_status = questToCopy.titleInfo.titleData.no_limit_status;
            titleInfo.titleData.random_opt_level = questToCopy.titleInfo.titleData.random_opt_level;
            titleInfo.titleData.fix_opt_num = questToCopy.titleInfo.titleData.fix_opt_num;;

            if (titleInfo.titleData.fix_opt_num > 0) {
                for (int k = 0; k < titleInfo.titleData.fix_opt_num; k++) {
                    QItemOption itemOpt = new QItemOption();
                    itemOpt.type = questToCopy.titleInfo.titleData.itemOptions.get(k).type;
                    itemOpt.value = questToCopy.titleInfo.titleData.itemOptions.get(k).value;
                    titleInfo.titleData.itemOptions.add(itemOpt);
                }
            }
        }

        q_name = questToCopy.q_name;
        q_desc = questToCopy.q_desc;


        //npc data

        owner_NPC = questToCopy.owner_NPC;
        reward_NPC = questToCopy.reward_NPC;


        //player data
        minReqLevel = questToCopy.minReqLevel;
        maxReqLevel = questToCopy.maxReqLevel;
        recommendedLevel = questToCopy.recommendedLevel;
        playerClass = questToCopy.playerClass;

        //connected quest? no idea yet
        interConnectQuest = questToCopy.interConnectQuest;
        parentQuestID = questToCopy.parentQuestID;
        childQuestID = questToCopy.childQuestID;

        //some flags
        disable = questToCopy.disable;
        iterationQuest = questToCopy.iterationQuest;
        partyQuest = questToCopy.partyQuest;
        guildCreateQuest = questToCopy.guildCreateQuest;

        //reward
        reward_EXP = questToCopy.reward_EXP;
        reward_Money = questToCopy.reward_Money;
        rewardFlag = questToCopy.rewardFlag;
        itemCountForSelect = questToCopy.itemCountForSelect;

        rewardItemsCount = questToCopy.rewardItemsCount;

        for (int n = 0; n < questToCopy.rewardItemsCount; n++) {
            QItemData reward = new QItemData();
            reward.item_id = questToCopy.rewardItems.get(n).item_id;
            reward.item_num = questToCopy.rewardItems.get(n).item_num;
            reward.no_limit_status = questToCopy.rewardItems.get(n).no_limit_status;
            reward.random_opt_level = questToCopy.rewardItems.get(n).random_opt_level;
            reward.fix_opt_num = questToCopy.rewardItems.get(n).fix_opt_num;

            for (int o = 0; o < reward.fix_opt_num; ++o) {
                QItemOption iOption = new QItemOption();
                iOption.type = questToCopy.rewardItems.get(n).itemOptions.get(o).type;
                iOption.value = questToCopy.rewardItems.get(n).itemOptions.get(o).value;
                reward.itemOptions.add(iOption);
            }
            rewardItems.add(reward);
        }
        requiredItemsCount = questToCopy.requiredItemsCount;
        for (int j = 0; j < questToCopy.requiredItemsCount; j++) {
            QItemData required = new QItemData();
            required.item_id = questToCopy.requiredItems.get(j).item_id;
            required.item_num = questToCopy.requiredItems.get(j).item_num;
            requiredItems.add(required);
        }
        dialogsCount = questToCopy.dialogsCount;
        for (int j = 0; j < questToCopy.dialogsCount; j++) {
            QuestDialog dialogs = new QuestDialog();
            dialogs.quest_state = questToCopy.dialogList.get(j).quest_state;
            dialogs.dlg = questToCopy.dialogList.get(j).dlg;
            dialogList.add(dialogs);
        }
        subQuestCount = questToCopy.subQuestCount;
        for (int j = 0; j < questToCopy.subQuestCount; j++) {
            EQuestType qType = EQuestType.fromId(questToCopy.m_SubQuests.get(j).m_subQuestType);
            SubQuest subQ;
            switch(qType)
            {
                case GOTO_TYPE:
                {
                    subQ = new Goto_SubQuest((Goto_SubQuest)questToCopy.m_SubQuests.get(j));
                    m_SubQuests.add(subQ);
                    break;
                }
                case GETITEM_TYPE:
                {
                    subQ = new Get_SubQuest((Get_SubQuest)questToCopy.m_SubQuests.get(j));
                    m_SubQuests.add(subQ);
                    break;
                }
                case KILLMOB_TYPE:
                {
                    subQ = new Kill_SubQuest((Kill_SubQuest)questToCopy.m_SubQuests.get(j));
                    m_SubQuests.add(subQ);
                    break;
                }
                case MOVEMENT_TYPE:
                {
                    subQ = new movement_SubQuest((movement_SubQuest)questToCopy.m_SubQuests.get(j));
                    m_SubQuests.add(subQ);
                    break;
                }
                case SPECIAL_TYPE:
                {
                    subQ = new SubQuest(questToCopy.m_SubQuests.get(j));
                    m_SubQuests.add(subQ);
                    break;
                }
            }
        }

        iTimeLimit = questToCopy.iTimeLimit;
        if (iTimeLimit <= 0) {
            iTimeLimit = -1;
        }
        bUseEventTime = questToCopy.bUseEventTime;
        m_EventTimeStart = new _SYSTEMTIME(questToCopy.m_EventTimeStart);
        m_EventTimeEnd = new _SYSTEMTIME(questToCopy.m_EventTimeEnd);
        Special_Condition_Count = questToCopy.Special_Condition_Count;
        for (int k = 0; k < questToCopy.Special_Condition_Count; ++k) {
            QuestSpecialCondition questSpecial = new QuestSpecialCondition();
            questSpecial.condition_type = questToCopy.SpecialConditionList.get(k).condition_type;
            questSpecial.param_1 = questToCopy.SpecialConditionList.get(k).param_1;
            questSpecial.param_2 = questToCopy.SpecialConditionList.get(k).param_2;
            questSpecial.param_3 = questToCopy.SpecialConditionList.get(k).param_3;
            questSpecial.param_4 = questToCopy.SpecialConditionList.get(k).param_4;
            questSpecial.param_5 = questToCopy.SpecialConditionList.get(k).param_5;
            SpecialConditionList.add(questSpecial);
        }
        m_special_reward.param_1 = questToCopy.m_special_reward.param_1;
        m_special_reward.param_2 = questToCopy.m_special_reward.param_2;
        m_special_reward.param_3 = questToCopy.m_special_reward.param_3;
        m_special_reward.param_4 = questToCopy.m_special_reward.param_4;
        m_special_reward.param_5 = questToCopy.m_special_reward.param_5;

        m_bAlarmDisable = questToCopy.m_bAlarmDisable;
        m_bChangeJobQuest = questToCopy.m_bChangeJobQuest;
        m_ChangeJobType = questToCopy.m_ChangeJobType;
        m_bNoPartyQuest = questToCopy.m_bNoPartyQuest;
        m_bLadderQuest = questToCopy.m_bLadderQuest;
        m_AcceptStartDate= new _SYSTEMTIME(questToCopy.m_AcceptStartDate);
        m_AcceptEndDate= new _SYSTEMTIME(questToCopy.m_AcceptEndDate);
        m_AcceptHour = questToCopy.m_AcceptHour;
        m_AcceptMinute = questToCopy.m_AcceptMinute;
        m_AcceptDurationTime = questToCopy.m_AcceptDurationTime;
        m_ExecuteDurationTime = questToCopy.m_ExecuteDurationTime;
        m_AcceptWeek = questToCopy.m_AcceptWeek;
        m_reward_num = questToCopy.m_reward_num;
        for (int m = 0; m < questToCopy.m_reward_num; ++m) {
            stLadderReward stlr = new stLadderReward();
            stlr.ranking = questToCopy.m_LadderRewards.get(m).ranking;
            stlr.money = questToCopy.m_LadderRewards.get(m).money;
            stlr.exp = questToCopy.m_LadderRewards.get(m).exp;
            stlr.item.item_id = questToCopy.m_LadderRewards.get(m).item.item_id;
            stlr.item.item_num = questToCopy.m_LadderRewards.get(m).item.item_num;
            stlr.item.no_limit_status = questToCopy.m_LadderRewards.get(m).item.no_limit_status;
            stlr.item.random_opt_level = questToCopy.m_LadderRewards.get(m).item.random_opt_level;
            stlr.item.fix_opt_num = questToCopy.m_LadderRewards.get(m).item.fix_opt_num;
            for (int o = 0; o < stlr.item.fix_opt_num; ++o) {
                QItemOption iOption = new QItemOption();
                iOption.type = questToCopy.m_LadderRewards.get(m).item.itemOptions.get(o).type;
                iOption.value = questToCopy.m_LadderRewards.get(m).item.itemOptions.get(o).value;
                stlr.item.itemOptions.add(iOption);
            }
            m_LadderRewards.add(stlr);
        }
        m_EntryFee = questToCopy.m_EntryFee;
        m_InfoMsg = questToCopy.m_InfoMsg;
        m_InfoMsg2 = questToCopy.m_InfoMsg2;
        m_InfoMsg3 = questToCopy.m_InfoMsg3;
        m_bMainQuest = questToCopy.m_bMainQuest;
        m_bGuildMasterQuest = questToCopy.m_bGuildMasterQuest;
        m_bUseLevelExpTable = questToCopy.m_bUseLevelExpTable;
        m_bUseLevelMoneyTable = questToCopy.m_bUseLevelMoneyTable;
        m_bBossQuest = questToCopy.m_bBossQuest;
        m_bSimpleQuest = questToCopy.m_bSimpleQuest;
        m_bUseLevelNotyMsg = questToCopy.m_bUseLevelNotyMsg;
        if (m_bUseLevelNotyMsg > 0) {
            m_LevelNoti = questToCopy.m_LevelNoti;
            m_LevelNoti2 = questToCopy.m_LevelNoti2;
        }
        m_bPartyShareQuest = questToCopy.m_bPartyShareQuest;
    }
    
    
    public void Write(QuestIO writer) throws IOException {
        if(editor_type == EDITOR_TYPE.TITLE)
        {
            writer.writeLengthString(titleInfo.titleName);
            writer.writeInt(titleInfo.titleData.item_id);
            writer.writeShort(titleInfo.titleData.item_num);
            writer.writeByte(titleInfo.titleData.no_limit_status);
            writer.writeInt(titleInfo.titleData.random_opt_level);
            writer.writeInt(titleInfo.titleData.fix_opt_num);

            if (titleInfo.titleData.fix_opt_num > 0) {
                for (int k = 0; k < titleInfo.titleData.fix_opt_num; k++) {
                    writer.writeInt(titleInfo.titleData.itemOptions.get(k).type);
                    writer.writeInt(titleInfo.titleData.itemOptions.get(k).value);
                }
            }
            int padSize = 80 - (titleInfo.titleData.fix_opt_num * 2 * 4);
            if (padSize > 0) {
                writer.writeBlock(new byte[padSize]);
            }
        }

        writer.writeLengthString(q_name);
        writer.writeLengthString(q_desc);
        writer.writeInt(quest_ID);
        writer.writeInt(owner_NPC);
        writer.writeInt(reward_NPC);
        writer.writeShort(minReqLevel);
        writer.writeShort(maxReqLevel);
        writer.writeShort(recommendedLevel);
        writer.writeShort(playerClass);
        writer.writeShort(playerClass2);
        writer.writeByte(interConnectQuest);
        writer.writeInt(parentQuestID);
        writer.writeInt(childQuestID);
        writer.writeByte(disable);
        writer.writeByte(iterationQuest);
        writer.writeByte(partyQuest);
        writer.writeByte(guildCreateQuest);
        if(FILE_VERSION >= 28)
        {
            writer.writeInt64(reward_EXP);
            writer.writeInt64(reward_Money);
        }
        else {
            writer.writeInt((int)reward_EXP);
            writer.writeInt((int)reward_Money);
        }
        writer.writeInt(rewardFlag);
        writer.writeInt(itemCountForSelect);
        writer.writeShort(rewardItemsCount);
        if (rewardItemsCount > 0) {

            for (int j = 0; j < rewardItemsCount; j++) {
                writer.writeInt(rewardItems.get(j).item_id);
                writer.writeShort(rewardItems.get(j).item_num);
                writer.writeByte(rewardItems.get(j).no_limit_status);
                writer.writeInt(rewardItems.get(j).random_opt_level);
                writer.writeInt(rewardItems.get(j).fix_opt_num);
                if (rewardItems.get(j).fix_opt_num > 0) {
                    for (int k = 0; k < rewardItems.get(j).fix_opt_num; k++) {
                        writer.writeInt(rewardItems.get(j).itemOptions.get(k).type);
                        writer.writeInt(rewardItems.get(j).itemOptions.get(k).value);
                    }
                }
                int padSize = 80 - (rewardItems.get(j).fix_opt_num * 2 * 4);
                if (padSize > 0) {
                    writer.writeBlock(new byte[padSize]);
                }
            }
        }
        writer.writeShort(requiredItemsCount);
        if (requiredItemsCount > 0) {
            for (int i = 0; i < requiredItemsCount; i++) {
                writer.writeInt(requiredItems.get(i).item_id);
                writer.writeShort(requiredItems.get(i).item_num);
            }
        }
        writer.writeShort(dialogsCount);
        if (dialogsCount > 0) {
            for (int i = 0; i < dialogsCount; i++) {
                writer.writeByte(dialogList.get(i).quest_state);
                writer.writeLengthString(dialogList.get(i).dlg);
            }
        }
        writer.writeShort(subQuestCount);
        for (int i = 0; i < subQuestCount; i++) {
            writer.writeByte(m_SubQuests.get(i).m_subQuestType);
            writer.writeByte(m_SubQuests.get(i).m_subQuestType);
            writer.writeByte(m_SubQuests.get(i).m_SubQuestID);
            writer.writeShort(m_SubQuests.get(i).prevcnt);
            if (m_SubQuests.get(i).prevcnt > 0) {
                for (int j = 0; j < m_SubQuests.get(i).prevcnt; ++j) {
                    writer.writeByte((byte) m_SubQuests.get(i).subids.get(j));
                }
            }
            writer.writeByte(m_SubQuests.get(i).m_NextSubQuestID);
            writer.writeInt(m_SubQuests.get(i).m_ParentQuestID);
            writer.writeLengthString(m_SubQuests.get(i).m_subQuestTitle);
            writer.writeByte(m_SubQuests.get(i).m_immediateReward);
            if(FILE_VERSION >= 28)
            {
                writer.writeInt64(m_SubQuests.get(i).m_reward_xp);
                writer.writeInt64(m_SubQuests.get(i).m_reward_money);
            }
            else
            {
                writer.writeInt((int)m_SubQuests.get(i).m_reward_xp);
                writer.writeInt((int)m_SubQuests.get(i).m_reward_money);
            }
            writer.writeInt(m_SubQuests.get(i).m_iTimeLimit);
            for (int j = 0; j < 6; j++) {
                writer.writeInt(m_SubQuests.get(i).m_special_complete_condition[j]);
            }

            EQuestType qType = EQuestType.fromId(m_SubQuests.get(i).m_subQuestType);
            switch(qType)
            {
                case GOTO_TYPE:
                {
                    Goto_SubQuest subQ = (Goto_SubQuest)m_SubQuests.get(i);
                    writer.writeInt(subQ.m_DestinationType);
                    writer.writeShort(subQ.dicnt);
                    if (subQ.dicnt > 0) {
                        for (int j = 0; j < subQ.dicnt; ++j) {
                            writer.writeInt(subQ.m_DeliverItems.get(j).type);
                            writer.writeShort(subQ.m_DeliverItems.get(j).cnt);
                        }
                    }
                    writer.writeShort(subQ.qicnt);
                    if (subQ.qicnt > 0) {
                        for (int j = 0; j < subQ.qicnt; ++j) {
                            writer.writeInt(subQ.m_TempGainedQuestItems.get(j).type);
                            writer.writeShort(subQ.m_TempGainedQuestItems.get(j).cnt);
                        }
                    }
                    writer.writeLengthString(subQ.m_intro_dlg_filename);
                    writer.writeLengthString(subQ.m_reintro_dlg_filename);
                    break;
                }
                case GETITEM_TYPE:
                {
                    Get_SubQuest getSubQuest =  (Get_SubQuest)m_SubQuests.get(i);
                    writer.writeInt(getSubQuest.m_getItemType);
                    writer.writeInt(getSubQuest.m_iGiverNum);

                    for (int j = 0; j < getSubQuest.m_GiverTypes.size(); ++j) {
                        writer.writeInt(getSubQuest.m_GiverTypes.get(j).giver_type_num);
                        for (int t = 0; t < getSubQuest.m_GiverTypes.get(j).sub_giver.size(); ++t) {
                            writer.writeInt((int) getSubQuest.m_GiverTypes.get(j).sub_giver.get(t));
                        }
                        writer.writeByte(getSubQuest.m_GiverTypes.get(j).drop_percent);

                    }
                    writer.writeShort(getSubQuest.m_getItemCount);
                    writer.writeByte(getSubQuest.m_bGetFromMonster);
                    break;
                }
                case KILLMOB_TYPE:
                {
                    Kill_SubQuest killSubQuest =  (Kill_SubQuest)m_SubQuests.get(i);
                    writer.writeInt(killSubQuest.target_num);
                    for (int j = 0; j < killSubQuest.target_num; ++j) {
                        writer.writeInt(killSubQuest.m_KillMonTypes.get(j).target_type);
                    }
                    writer.writeInt(killSubQuest.m_equipWeaponType);
                    writer.writeShort(killSubQuest.m_killCounter);
                    writer.writeByte(killSubQuest.m_bKillMonster);
                    writer.writeInt(killSubQuest.m_iKillPlayerMinLevel);
                    break;
                }
                case MOVEMENT_TYPE: //subquest
                {
                    movement_SubQuest subQ =  (movement_SubQuest)m_SubQuests.get(i);
                    writer.writeInt(subQ.id_count);
                    for (int j = 0; j < subQ.id_count; ++j) {
                        writer.writeInt(subQ.m_DestList.get(j).uiRegion);
                        writer.writeInt(subQ.m_DestList.get(j).uiRoom);
                    }
                    break;
                }
            }
        }
        writer.writeInt(iTimeLimit);
        writer.writeByte(bUseEventTime);
        m_EventTimeStart.write(writer);
        m_EventTimeEnd.write(writer);
        writer.writeInt(Special_Condition_Count);
        for (int i = 0; i < Special_Condition_Count; ++i) {
            writer.writeInt(SpecialConditionList.get(i).condition_type);
            writer.writeInt(SpecialConditionList.get(i).param_1);
            writer.writeInt(SpecialConditionList.get(i).param_2);
            writer.writeInt(SpecialConditionList.get(i).param_3);
            writer.writeInt(SpecialConditionList.get(i).param_4);
            writer.writeInt(SpecialConditionList.get(i).param_5);

        }
        writer.writeInt(m_special_reward.condition_type);
        writer.writeInt(m_special_reward.param_1);
        writer.writeInt(m_special_reward.param_2);
        writer.writeInt(m_special_reward.param_3);
        writer.writeInt(m_special_reward.param_4);
        writer.writeInt(m_special_reward.param_5);
        writer.writeByte(m_bAlarmDisable);
        writer.writeByte(m_bChangeJobQuest);
        writer.writeInt(m_ChangeJobType);
        writer.writeByte(m_bNoPartyQuest);
        writer.writeByte(m_bLadderQuest);
        m_AcceptStartDate.write(writer);
        m_AcceptEndDate.write(writer);
        writer.writeInt(m_AcceptHour);
        writer.writeInt(m_AcceptMinute);
        writer.writeInt(m_AcceptDurationTime);
        writer.writeInt(m_ExecuteDurationTime);
        writer.writeBlock(Util.stringToBt(m_AcceptWeek, 7));
        writer.writeInt(m_reward_num);
        for (int i = 0; i < m_reward_num; ++i) {
            writer.writeInt(m_LadderRewards.get(i).ranking);
            if(FILE_VERSION >= 28)
            {
                writer.writeInt((int)m_LadderRewards.get(i).money);
                writer.writeInt((int)m_LadderRewards.get(i).exp);
            }
            else
            {
                writer.writeInt((int)m_LadderRewards.get(i).money);
                writer.writeInt((int)m_LadderRewards.get(i).exp);
            }

            writer.writeInt(m_LadderRewards.get(i).item.item_id);
            writer.writeShort(m_LadderRewards.get(i).item.item_num);
            writer.writeShort(m_LadderRewards.get(i).item.no_limit_status);
            //writer.writeByte((byte)0);
            writer.writeInt(m_LadderRewards.get(i).item.random_opt_level);
            writer.writeInt(m_LadderRewards.get(i).item.fix_opt_num);
            for(int j=0; j<m_LadderRewards.get(i).item.fix_opt_num;j++){
                writer.writeInt(m_LadderRewards.get(i).item.itemOptions.get(j).type);
                writer.writeInt(m_LadderRewards.get(i).item.itemOptions.get(j).value);
            }
            int padSize = 80 - (m_LadderRewards.get(i).item.fix_opt_num * 2 * 4);
            if (padSize > 0) {
                writer.writeBlock(new byte[padSize]);
            }
        }
        writer.writeInt(m_EntryFee);
        writer.writeLengthString(m_InfoMsg);
        writer.writeLengthString(m_InfoMsg2);
        writer.writeLengthString(m_InfoMsg3);
        writer.writeByte(m_bMainQuest);
        writer.writeByte(m_bGuildMasterQuest);
        writer.writeByte(m_bUseLevelExpTable);
        writer.writeByte(m_bUseLevelMoneyTable);
        writer.writeByte(m_bBossQuest);
        writer.writeByte(m_bSimpleQuest);


        if (editor_type == EDITOR_TYPE.NORMAL) {
            writer.writeByte(m_bUseLevelNotyMsg);
            writer.writeLengthString(m_LevelNoti);
            writer.writeLengthString(m_LevelNoti2);
        }
        if(FILE_VERSION >= 25)
        {
            writer.writeByte(m_bPartyShareQuest);
        }
        if(FILE_VERSION >= 26)
        {
            writer.writeByte(m_bDailyQuest);
            writer.writeByte(m_bWeeklyQuest);
            writer.writeByte(m_bMonthlyQuest);
        }
        if (FILE_VERSION >= 27 )
        {
            m_MissionStartDate.write(writer);
            m_MissionEndDate.write(writer);
            writer.writeBlock(Util.stringToBt(m_MissionWeek, 7));
        }
        if (FILE_VERSION >= 30 )
        {
            writer.writeByte(m_IsGuildQuest);
        }
        writer.flush();
    }
    public void Load(QuestIO reader) throws IOException {
        if(editor_type == EDITOR_TYPE.TITLE)
        {
            titleInfo = new QTitleInfo();
            titleInfo.titleName = reader.readLengthString();
            titleInfo.titleData.item_id = reader.readInt();
            titleInfo.titleData.item_num = reader.readShort();
            titleInfo.titleData.no_limit_status = reader.readByte();
            titleInfo.titleData.random_opt_level = reader.readInt();
            titleInfo.titleData.fix_opt_num = reader.readInt();

            for (int o = 0; o < titleInfo.titleData.fix_opt_num; ++o) {
                QItemOption iOption = new QItemOption();
                iOption.type = reader.readInt();
                iOption.value = reader.readInt();
                titleInfo.titleData.itemOptions.add(iOption);
            }
            int pad1 = 80 - (titleInfo.titleData.fix_opt_num * 2 * 4);
            reader.skipBytes(pad1);
        }

        q_name = reader.readLengthString();
        q_desc = reader.readLengthString();

        //npc data
        quest_ID = reader.readInt();
        owner_NPC = reader.readInt();
        reward_NPC = reader.readInt();

        //player data
        minReqLevel = reader.readShort();
        maxReqLevel = reader.readShort();
        recommendedLevel = reader.readShort();


        //playerClass = reader.readShort();
        playerClass = reader.readShort();
        playerClass2 = reader.readShort();
        //connected quest? no idea yet
        interConnectQuest = reader.readByte();
        parentQuestID = reader.readInt();
        childQuestID = reader.readInt();

        //some flags
        disable = reader.readByte();
        iterationQuest = reader.readByte();
        partyQuest = reader.readByte();
        guildCreateQuest = reader.readByte();

        //reward
        if(FILE_VERSION >= 28)
        {
            reward_EXP = reader.readInt64();
            reward_Money = reader.readInt64();
        }
        else
        {
            reward_EXP = reader.readInt();
            reward_Money = reader.readInt();
        }
        rewardFlag = reader.readInt();
        itemCountForSelect = reader.readInt();


        rewardItemsCount = reader.readShort();
        for (int i = 0; i < rewardItemsCount; i++) {
            QItemData reward = new QItemData();
            reward.item_id = reader.readInt();

            if(FILE_VERSION >= 22)
            {
                reward.item_num = reader.readShort();
            }
            else
            {
                reward.item_num = reader.readByte();
            }

            if(FILE_VERSION >= 18)
            {
                reward.no_limit_status = reader.readByte();
                reward.random_opt_level = reader.readInt();
                reward.fix_opt_num = reader.readInt();

                for (int o = 0; o < reward.fix_opt_num; ++o) {
                    QItemOption iOption = new QItemOption();
                    iOption.type = reader.readInt();
                    iOption.value = reader.readInt();
                    reward.itemOptions.add(iOption);
                }
                int pad = 80 - (reward.fix_opt_num * 2 * 4);
                reader.skipBytes(pad);
            }
            rewardItems.add(reward);
        }
        requiredItemsCount = reader.readShort();
        for (int j = 0; j < requiredItemsCount; j++) {
            QItemData required = new QItemData();
            required.item_id = reader.readInt();
            required.item_num = reader.readShort();
            requiredItems.add(required);
        }
        dialogsCount = reader.readShort();
        for (int j = 0; j < dialogsCount; j++) {
            QuestDialog dialogs = new QuestDialog();
            dialogs.quest_state = reader.readByte();
            dialogs.dlg = reader.readLengthString();
            dialogList.add(dialogs);
        }
        subQuestCount = reader.readShort();
        for (int j = 0; j < subQuestCount; j++) {
            SubQuest subQuest;
            int questType = reader.readByte();
            EQuestType eQuestType = EQuestType.fromId(questType);
            switch (eQuestType) {
                case UNKNOWN:
                    subQuest = new SubQuest();
                    subQuest.Load(reader,FILE_VERSION);
                    m_SubQuests.add(subQuest);
                    reader.skipBytes(9);
                    break;
                case GOTO_TYPE:
                    subQuest = new Goto_SubQuest();
                    subQuest.Load(reader, FILE_VERSION);
                    m_SubQuests.add(subQuest);
                    break;
                case GETITEM_TYPE: //get
                    subQuest = new Get_SubQuest();
                    subQuest.Load(reader, FILE_VERSION);
                    m_SubQuests.add(subQuest);
                    break;
                case KILLMOB_TYPE: //kill
                    subQuest = new Kill_SubQuest();
                    subQuest.Load(reader, FILE_VERSION);
                    m_SubQuests.add(subQuest);
                    break;
                case SPECIAL_TYPE: //subquest
                    subQuest = new SubQuest();
                    subQuest.Load(reader,FILE_VERSION);
                    m_SubQuests.add(subQuest);
                    break;
                case MOVEMENT_TYPE: //movement
                    subQuest = new movement_SubQuest();
                    subQuest.Load(reader, FILE_VERSION);
                    m_SubQuests.add(subQuest);
                    break;
                case DLGLISTEN_TYPE: //dlglisten
                    subQuest = new dlglisten_SubQuest();
                    subQuest.Load(reader,FILE_VERSION);
                    m_SubQuests.add(subQuest);
                    break;
                case NONE:
                    System.out.println("BUG");
                    break;
                default:
                    System.out.println("BUG");
                    break;
            }

        }

        iTimeLimit = reader.readInt();
        if (iTimeLimit <= 0) {
            iTimeLimit = -1;
        }
        bUseEventTime = reader.readByte();
        m_EventTimeStart.Load(reader);
        m_EventTimeEnd.Load(reader);


        Special_Condition_Count = reader.readInt();
        for (int i = 0; i < Special_Condition_Count; ++i) {
            QuestSpecialCondition questSpecial = new QuestSpecialCondition();
            questSpecial.condition_type = reader.readInt();
            questSpecial.param_1 = reader.readInt();
            questSpecial.param_2 = reader.readInt();
            questSpecial.param_3 = reader.readInt();
            questSpecial.param_4 = reader.readInt();
            questSpecial.param_5 = reader.readInt();
            SpecialConditionList.add(questSpecial);
        }
        m_special_reward.condition_type = reader.readInt();
        m_special_reward.param_1 = reader.readInt();
        m_special_reward.param_2 = reader.readInt();
        m_special_reward.param_3 = reader.readInt();
        m_special_reward.param_4 = reader.readInt();
        m_special_reward.param_5 = reader.readInt();

        m_bAlarmDisable = reader.readByte();
        m_bChangeJobQuest = reader.readByte();
        m_ChangeJobType = reader.readInt();
        m_bNoPartyQuest = reader.readByte();
        m_bLadderQuest = reader.readByte();

        m_AcceptStartDate.Load(reader);
        m_AcceptEndDate.Load(reader);


        m_AcceptHour = reader.readInt();
        m_AcceptMinute = reader.readInt();
        m_AcceptDurationTime = reader.readInt();
        m_ExecuteDurationTime = reader.readInt();
        m_AcceptWeek = reader.getString(7);
        m_reward_num = reader.readInt();
        for (int i = 0; i < m_reward_num; ++i) {
            stLadderReward stlr = new stLadderReward();
            stlr.ranking = reader.readInt();
            if(FILE_VERSION >= 28)
            {
                stlr.money = reader.readInt();
                stlr.exp = reader.readInt();
            }
            else
            {
                stlr.money = (int)reader.readInt();
                stlr.exp = (int)reader.readInt();
            }
            stlr.item.item_id = reader.readInt();
            stlr.item.item_num = reader.readShort();
            stlr.item.no_limit_status = (byte)reader.readShort();

            stlr.item.random_opt_level = reader.readInt();
            stlr.item.fix_opt_num = reader.readInt();
            for (int o = 0; o < stlr.item.fix_opt_num; ++o) {
                QItemOption iOption = new QItemOption();
                iOption.type = reader.readInt();
                iOption.value = reader.readInt();
                stlr.item.itemOptions.add(iOption);
            }
            int pad = 80 - (stlr.item.fix_opt_num * 2 * 4);
            reader.skipBytes(pad);
            m_LadderRewards.add(stlr);
        }
        m_EntryFee = reader.readInt();
        m_InfoMsg = reader.readLengthString();
        m_InfoMsg2 = reader.readLengthString();
        m_InfoMsg3 = reader.readLengthString();
        m_bMainQuest = reader.readByte();
        m_bGuildMasterQuest = reader.readByte();
        m_bUseLevelExpTable = reader.readByte();
        m_bUseLevelMoneyTable = reader.readByte();
        m_bBossQuest = reader.readByte();
        m_bSimpleQuest = reader.readByte();

        if (editor_type == EDITOR_TYPE.NORMAL) {
            m_bUseLevelNotyMsg = reader.readByte();
            m_LevelNoti = reader.readLengthString();
            m_LevelNoti2 = reader.readLengthString();
        }
        if(FILE_VERSION >= 25)
        {
            m_bPartyShareQuest = reader.readByte();;
        }
        if(FILE_VERSION >= 26)
        {
            m_bDailyQuest = reader.readByte();
            m_bWeeklyQuest = reader.readByte();
            m_bMonthlyQuest = reader.readByte();
        }
        if (FILE_VERSION >= 27 )
        {
            m_MissionStartDate.Load(reader);
            m_MissionEndDate.Load(reader);
            m_MissionWeek = reader.getString(7);
        }
        if (FILE_VERSION >= 30 )
        {
            m_IsGuildQuest = reader.readByte();
        }
    }
}
