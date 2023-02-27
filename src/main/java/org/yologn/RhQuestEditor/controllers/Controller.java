package org.yologn.RhQuestEditor.controllers;

import javafx.animation.FadeTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.ByteStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.ShortStringConverter;
import org.yologn.RhQuestEditor.AchieveEditor.AchieveWindow;
import org.yologn.RhQuestEditor.QuestIO;
import org.yologn.RhQuestEditor.QuestStructs.*;
import org.yologn.RhQuestEditor.QuestStructs.SubQuestStructs.*;
import org.yologn.RhQuestEditor.TypesMap;
import org.yologn.RhQuestEditor.Util;
import org.yologn.RhQuestEditor.Util.EDITOR_TYPE;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller implements Initializable {
    public ComboBox<QuestEntry> questBox;
    public ComboBox<QuestEntry> childQuestBox;
    public ComboBox<QuestEntry> parentQuestBox;
    public ComboBox<SubQuest> taskBox;
    public ComboBox<String> subQuestType;
    public TextField questNameField;
    public TextField questDescField;

    public TextField obtainNpcField;
    public TextField returnNpcField;
    public TextField expField;
    public TextField moneyField;
    public TextField minLevelField;
    public TextField maxLevelField;
    public TextField completionTimeField;
    public TextField recLevelField;
    public TextField entryFeeField;
    public TextField playerClassField;
    public TextField jobTypeField;
    public TextField choiceNumField;

    public TextField optionValueField;
    public TextField subQuestName;
    public TextField talkTaskNpcField;
    public TextField talk1Field;
    public TextField talk2Field;
    public TextField immediateExpField;
    public TextField immediateCronesField;
    public TextField killCountField;
    public TextField playerMinLevelField;
    public TextField requiredWpnField;
    public TextField collectQtyField;
    public TextField collectIdField;
    public TextField listIdPutField;
    public TextField subSpecialTypeField;
    public TextField subParam1Field;
    public TextField subParam2Field;
    public TextField subParam3Field;
    public TextField subParam4Field;
    public TextField subParam5Field;
    public TextField inputField;
    public TextField specialRwrdTypeField;
    public TextField specialParam1Field;
    public TextField specialParam2Field;
    public TextField specialParam3Field;
    public TextField specialParam4Field;
    public TextField specialParam5Field;
    public TextField mainTimeLimitField;
    public TextField subTimeLimitField;


    public TextField prevSubQuestIdField;
    public TextField titleNameField;
    public TextField titleIdField;

    public TextField subQuestIdField;
    public TextField nextSubQuestIdField;
    public TextField titleOptionValAddField;

    public ComboBox<String> optionTypeField;
    public ComboBox<String> titleOptionTypeAddBox;

    public Button gotoNextBtn;
    public Button gotoPrevBtn;
    public Button addOptionBtn;
    public Button removeOptionBtn;
    public Button addRewardBtn;
    public Button removeRewardBtn;
    public Button saveFileBtn;
    public Button newQuestBtn;
    public Button addStartingItemBtn;
    public Button addQuestTextBtn;
    public Button saveEditsBtn;
    public Button removeQuestTextBtn;
    public Button removeStartingItemBtn;
    public Button addTakeItemBtn;
    public Button removeTakeItemBtn;
    public Button addReturnItemBtn;
    public Button removeReturnItemBtn;
    public Button newTaskBtn;
    public Button removeTaskBtn;
    public Button saveTaskBtn;
    public Button removeMonsterKillBtn;
    public Button addMonsterKillBtn;
    public Button addCollectDropListBtn;
    public Button removeCollectDropListBtn;
    public Button addIDtoListBtn;
    public Button delIdFromListBtn;
    public Button removeQuestBtn;
    public Button delNextBtn;
    public Button delPrevBtn;
    public Button addConditionBtn;
    public Button delConditionBtn;

    public Button cloneQuestBtn;
    public Button disableQuestsBtn;
    public Button titleAddOptionBtn;
    public Button titleRemoveOptionBtn;
    public Button achieveBinBtn;

    public RadioButton nameRadio;
    public RadioButton startNpcRadio;
    public RadioButton rewardNpcRadio;

    public RadioButton soloRadio;
    public RadioButton notifyRadio;
    public RadioButton iterationRadio;
    public RadioButton disabledRadio;
    public RadioButton guildMasterRadio;
    public RadioButton allRadio;


    public Button delMovementEntryBtn;
    public Button addMovementEntryBtn;

    public Button addReqPrevBtn;
    public Button delReqPrevBtn;

    public CheckBox disableCheck;
    public CheckBox iterationCheck;
    public CheckBox partyCheck;
    public CheckBox guildCheck;
    public CheckBox soloCheck;
    public CheckBox mainqCheck;
    public CheckBox gMasterCheck;
    public CheckBox partyShareCheck;
    public CheckBox jobCheck;
    public CheckBox immediateCheck;
    public CheckBox killMonsterBox;
    public CheckBox collectFromMonsterBox;
    public CheckBox bossQuestCheck;
    public CheckBox expTableCheck;
    public CheckBox moneyTableCheck;
    public CheckBox interconnectCheck;
    public CheckBox guildQuestBox;


    public TableColumn checkColumn;
    public TableColumn<QItemData, Short> qtyColumn;
    public TableColumn<QItemData, Integer> typeColumn;
    public TableColumn<QItemData, Integer> rewardLvlColumn;
    public TableColumn<QItemOption, String> statNameCol;
    public TableColumn<QItemOption, Integer> statColumn;
    public TableColumn<QItemOption, Integer> valueColumn;
    public TableColumn<QuestDialog, Byte> dlgStateColumn;
    public TableColumn<QuestDialog, String> dlgColumn;
    public TableColumn<QItemData, Short> startingQuantityColumn;
    public TableColumn<QItemData, Integer> startingTypeColumn;
    public TableColumn<Get_SubQuest_Givers, Byte> dropChanceColumn;
    public TableColumn<Get_SubQuest_Givers, ComboBox> getFromColumn;
    public TableColumn<QuestEntry, Integer> filterQuestIdCol;
    public TableColumn<QuestEntry, String> filterQuestNameCol;

    public TableColumn<QItemOption, String> titleStatNameCol;
    public TableColumn<QItemOption, Integer> titleStatCol;
    public TableColumn<QItemOption, Integer> titleValCol;
    public TableView<QItemOption> titleOptionTable;

    public TableColumn<Goto_SubQuest_DeliverItem, Short> returnItemQuantityColumn;
    public TableColumn<Goto_SubQuest_DeliverItem, Integer> returnItemTypeColumn;
    public TableColumn<GoTo_SubQuest_QuestItem, Short> takeItemQuantityColumn;
    public TableColumn<GoTo_SubQuest_QuestItem, Integer> takeItemTypeColumn;
    public TableColumn<Kill_SubQuest_Targets, Integer> monsterIdColumn;

    public TableColumn<QuestSpecialCondition, Integer> conditionTypeCol;
    public TableColumn<QuestSpecialCondition, Integer> param1Col;
    public TableColumn<QuestSpecialCondition, Integer> param2Col;
    public TableColumn<QuestSpecialCondition, Integer> param3Col;
    public TableColumn<QuestSpecialCondition, Integer> param4Col;
    public TableColumn<QuestSpecialCondition, Integer> param5Col;


    public TableColumn<stMovementDestId, Integer> regionColumn;
    public TableColumn<stMovementDestId, Integer> roomColumn;


    public TableView<QItemData> rewardTable;
    public TableView<QItemOption> optionTable;
    public TableView dlgTable;
    public TableView startingItemsTable;
    public TableView returnItemTable;
    public TableView takeItemTable;
    public TableView monsterKillTable;
    public TableView collectTable;
    public TableView<QuestEntry> filteredTable;
    public TableView specialConditionTable;
    public TableView<stMovementDestId> movementTable;

    public Label fadeLabel;
    public ToggleGroup grp1;
    public ToggleGroup grp2;
    public Tab talkingTab;
    public Tab collectingTab;
    public Tab killingTab;
    public Tab specialTab;
    public Tab generalTab;
    public Tab movementTab;
    public TabPane subQuestTabPane;
    public Tab titleTab;


    public ListView<Byte> previousSubQuestsListView;

    @FXML
    private EtcTabController etcViewController;
    @FXML
    private Tab etcView;
    @FXML
    private LadderTabController ladderViewController;
    @FXML
    private Tab ladderView;


    private EDITOR_TYPE editor_type = EDITOR_TYPE.NORMAL;
    private final QuestHeader questHeader = new QuestHeader();

    private final ObservableList<QuestEntry> itemsData;
    private final Map<Integer, String> achieveInfo = new HashMap<>();


    private void chooseEditorType() {
        List<String> choices = new ArrayList<>();
        choices.add("Normal Quest Editor");
        choices.add("Title Quest Editor");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Normal Quest Editor", choices);
        dialog.setTitle("Editor Type");
        dialog.setContentText("Choose quest editor type:");


        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equalsIgnoreCase("Title Quest Editor"))
                editor_type = EDITOR_TYPE.TITLE;
        }
    }

    private void LoadQuests() throws IOException {
        LoadAchieve();

        String path = "quest.bin";
        if (editor_type == EDITOR_TYPE.TITLE)
            path = "questach.bin";
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        QuestIO reader = new QuestIO(in);

        questHeader.version = reader.readInt();
        questHeader.use_multiple_num = reader.readByte();
        if (questHeader.use_multiple_num > 0) {
            questHeader.multiple_num = reader.readInt();
        }
        questHeader.qsize = reader.readShort();
        for (int i = 0; i < questHeader.qsize; i++) {
            QuestEntry qEntry = new QuestEntry(questHeader.version, editor_type);
            qEntry.Load(reader);


            itemsData.add(qEntry);
        }
        in.close();
    }

    private void LoadAchieve() throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("achieve.bin")));
        QuestIO reader = new QuestIO(in);
        while (true) {
            try {
                reader.readInt();
                int achieveID = reader.readInt();
                reader.readBlock(50);
                String name = reader.getString(50);
                reader.readBlock(100);
                achieveInfo.put(achieveID, name);

            } catch (IOException e) {
                break;
            }
        }
        in.close();
    }

    public Controller() {
        itemsData = FXCollections.observableArrayList();
        try {
            chooseEditorType();
            LoadQuests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //Filtered table

        if (editor_type == EDITOR_TYPE.NORMAL)
            titleTab.setDisable(true);
        setupFilters();
        setupButtonActions();
        setupInitialItems();
        setupListeners();
        ladderViewController.setQuestBox(questBox);
    }


    private void setupButtonActions() {

        // title options
        titleAddOptionBtn.setOnAction(e -> {
            if (titleOptionTypeAddBox.getSelectionModel().getSelectedItem() != null && titleOptionValAddField.getText() != null && !titleOptionValAddField.getText().isEmpty()) {
                QItemOption option = new QItemOption();
                option.type = (int) TypesMap.getAttributeTypesMap().get(titleOptionTypeAddBox.getSelectionModel().getSelectedItem());
                option.value = Integer.parseInt(titleOptionValAddField.getText());
                QItemData selectedReward = questBox.getSelectionModel().getSelectedItem().titleInfo.titleData;
                if (selectedReward != null) {
                    selectedReward.itemOptions.add(option);
                    selectedReward.fix_opt_num++;
                    titleOptionTable.setItems(FXCollections.observableArrayList(selectedReward.itemOptions));
                    titleOptionTable.getSelectionModel().selectLast();
                }
            }

        });
        titleRemoveOptionBtn.setOnAction(e -> {
            QItemData selectedReward = questBox.getSelectionModel().getSelectedItem().titleInfo.titleData;
            if (selectedReward != null) {
                if (selectedReward.itemOptions.remove(titleOptionTable.getSelectionModel().getSelectedItem())) {
                    selectedReward.fix_opt_num--;
                    titleOptionTable.setItems(FXCollections.observableArrayList(selectedReward.itemOptions));
                    titleOptionTable.getSelectionModel().selectLast();
                }
            }
        });

        // prev and next qst buttons
        gotoNextBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            if (selectedEntry.childQuestID != -1) {
                questBox.getSelectionModel().select(selectedEntry.childQuestID);
            }
        });
        gotoPrevBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            if (selectedEntry.parentQuestID != -1) {
                questBox.getSelectionModel().select(selectedEntry.parentQuestID);
            }

        });

        // reward items' options
        addOptionBtn.setOnAction(e -> {
            if (optionTypeField.getSelectionModel().getSelectedItem() != null && optionValueField.getText() != null || !optionValueField.getText().isEmpty()) {
                QItemOption option = new QItemOption();
                option.type = (int) TypesMap.getOptionTypesMap().get(optionTypeField.getSelectionModel().getSelectedItem());
                option.value = Integer.parseInt(optionValueField.getText());
                QItemData selectedReward = rewardTable.getSelectionModel().getSelectedItem();
                if (selectedReward != null) {
                    selectedReward.itemOptions.add(option);
                    selectedReward.fix_opt_num++;
                    optionTable.setItems(FXCollections.observableArrayList(selectedReward.itemOptions));
                    optionTable.getSelectionModel().selectLast();
                }
            }

        });
        removeOptionBtn.setOnAction(e -> {
            QItemData selectedReward = rewardTable.getSelectionModel().getSelectedItem();
            if (selectedReward != null) {
                if (selectedReward.itemOptions.remove(optionTable.getSelectionModel().getSelectedItem())) {
                    selectedReward.fix_opt_num--;
                    optionTable.setItems(FXCollections.observableArrayList(selectedReward.itemOptions));
                    optionTable.getSelectionModel().selectLast();
                }
            }
        });
        // rewards buttons
        addRewardBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            QItemData obj = new QItemData();
            selectedEntry.rewardItems.add(obj);
            selectedEntry.rewardItemsCount++;
            rewardTable.setItems(FXCollections.observableArrayList(selectedEntry.rewardItems));
            rewardTable.getSelectionModel().selectLast();

        });
        removeRewardBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            if (!rewardTable.getItems().isEmpty()) {
                selectedEntry.rewardItems.remove(rewardTable.getSelectionModel().getSelectedItem());
                selectedEntry.rewardItemsCount--;
                rewardTable.setItems(FXCollections.observableArrayList(selectedEntry.rewardItems));
                rewardTable.getSelectionModel().selectLast();
            }
        });

        // starting (required?) items buttons
        addStartingItemBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            QItemData obj = new QItemData();
            selectedEntry.requiredItems.add(obj);
            startingItemsTable.getItems().clear();
            startingItemsTable.getItems().addAll(selectedEntry.requiredItems);
            selectedEntry.requiredItemsCount++;
            startingItemsTable.getSelectionModel().selectLast();
        });
        removeStartingItemBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            if (!startingItemsTable.getItems().isEmpty()) {
                selectedEntry.requiredItems.remove(startingItemsTable.getSelectionModel().getSelectedIndex());
                startingItemsTable.getItems().clear();
                startingItemsTable.getItems().addAll(selectedEntry.requiredItems);
                selectedEntry.requiredItemsCount--;
                startingItemsTable.getSelectionModel().selectLast();
            }
        });

        // quest dialogs buttons
        addQuestTextBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            QuestDialog obj = new QuestDialog();
            obj.quest_state = 0;
            obj.dlg = "";
            selectedEntry.dialogList.add(obj);
            selectedEntry.dialogsCount++;
            dlgTable.getItems().clear();
            dlgTable.getItems().addAll(selectedEntry.dialogList);
            dlgTable.getSelectionModel().selectLast();
        });
        removeQuestTextBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            if (!dlgTable.getItems().isEmpty()) {
                selectedEntry.dialogList.remove(dlgTable.getSelectionModel().getSelectedIndex());
                selectedEntry.dialogsCount--;
                dlgTable.getItems().clear();
                dlgTable.getItems().addAll(selectedEntry.dialogList);
                dlgTable.getSelectionModel().selectLast();
            }
        });

        // get/take items buttons
        addTakeItemBtn.setOnAction(e -> {
            Goto_SubQuest selectedSubQ = (Goto_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (selectedSubQ != null) {
                GoTo_SubQuest_QuestItem obj = new GoTo_SubQuest_QuestItem();
                obj.type = 99999;
                obj.cnt = 0;
                selectedSubQ.m_TempGainedQuestItems.add(obj);
                takeItemTable.getItems().clear();
                if (!selectedSubQ.m_TempGainedQuestItems.isEmpty()) {
                    takeItemTable.getItems().addAll(selectedSubQ.m_TempGainedQuestItems);
                    selectedSubQ.qicnt++;
                }
            }


            takeItemTable.getSelectionModel().selectLast();
        });
        removeTakeItemBtn.setOnAction(e -> {
            Goto_SubQuest selectedSubQ = (Goto_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (selectedSubQ != null && !takeItemTable.getItems().isEmpty()) {
                selectedSubQ.m_TempGainedQuestItems.remove(takeItemTable.getSelectionModel().getSelectedIndex());
                takeItemTable.getItems().clear();
                takeItemTable.getItems().addAll(selectedSubQ.m_TempGainedQuestItems);
                selectedSubQ.qicnt--;
                takeItemTable.getSelectionModel().selectLast();
            }
        });

        // deliver items buttons
        addReturnItemBtn.setOnAction(e -> {
            Goto_SubQuest selectedSubQ = (Goto_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (selectedSubQ != null) {
                Goto_SubQuest_DeliverItem obj = new Goto_SubQuest_DeliverItem();
                obj.type = 99999;
                obj.cnt = 0;
                selectedSubQ.m_DeliverItems.add(obj);
                returnItemTable.getItems().clear();
                returnItemTable.getItems().addAll(selectedSubQ.m_DeliverItems);
                selectedSubQ.dicnt++;
                returnItemTable.getSelectionModel().selectLast();
                returnItemTable.setVisible(false);
                returnItemTable.setVisible(true);
            }

        });
        removeReturnItemBtn.setOnAction(e -> {
            Goto_SubQuest selectedSubQ = (Goto_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (selectedSubQ != null && !returnItemTable.getItems().isEmpty()) {
                selectedSubQ.m_DeliverItems.remove(returnItemTable.getSelectionModel().getSelectedIndex());
                returnItemTable.getItems().clear();
                returnItemTable.getItems().addAll(selectedSubQ.m_DeliverItems);
                selectedSubQ.dicnt--;
                returnItemTable.getSelectionModel().selectLast();
            }
        });
        // add drop list to collect table
        addCollectDropListBtn.setOnAction(e -> {
            Get_SubQuest selectedSubQ = (Get_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (selectedSubQ != null) {
                Get_SubQuest_Givers obj = new Get_SubQuest_Givers();
                selectedSubQ.m_GiverTypes.add(obj);
                selectedSubQ.m_iGiverNum++;
                collectTable.getItems().clear();
                collectTable.setItems(FXCollections.observableArrayList(selectedSubQ.m_GiverTypes));

            }

        });
        removeCollectDropListBtn.setOnAction(e -> {
            Get_SubQuest selectedSubQ = (Get_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (selectedSubQ != null && !collectTable.getItems().isEmpty()) {
                selectedSubQ.m_iGiverNum--;
                selectedSubQ.m_GiverTypes.remove(collectTable.getSelectionModel().getSelectedIndex());
                collectTable.setItems(FXCollections.observableArrayList(selectedSubQ.m_GiverTypes));
                int selection = taskBox.getSelectionModel().getSelectedIndex();
                taskBox.getSelectionModel().select(null);
                taskBox.getSelectionModel().select(selection);
                subQuestTabPane.getSelectionModel().select(collectingTab);
                collectingTab.setDisable(false);
                talkingTab.setDisable(true);
                specialTab.setDisable(true);
                killingTab.setDisable(true);
                generalTab.setDisable(false);
                ((TableColumn) collectTable.getColumns().get(0)).setVisible(false);
                ((TableColumn) collectTable.getColumns().get(0)).setVisible(true);
                ((TableColumn) collectTable.getColumns().get(1)).setVisible(false);
                ((TableColumn) collectTable.getColumns().get(1)).setVisible(true);
            }
        });

        // add monster/box ID to chosen list
        addIDtoListBtn.setOnAction(e -> {
            Get_SubQuest selectedSubQ = (Get_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (selectedSubQ != null) {
                int idToPut = Integer.parseInt(listIdPutField.getText());
                selectedSubQ.m_GiverTypes
                        .get(collectTable.getSelectionModel().getSelectedIndex())
                        .sub_giver
                        .add(idToPut);
                getFromColumn.setCellValueFactory(arg0 -> {
                    Get_SubQuest_Givers user = arg0.getValue();
                    ComboBox combobox = new ComboBox();
                    combobox.setPrefWidth(140);
                    combobox.setItems(FXCollections.observableArrayList(user.sub_giver));

                    return new SimpleObjectProperty<ComboBox>(combobox);
                });
                collectTable.getItems().clear();
                collectTable.getItems().addAll(FXCollections.observableArrayList(selectedSubQ.m_GiverTypes));
                collectTable.setEditable(true);
            }

        });
        delIdFromListBtn.setOnAction(e -> {
            Get_SubQuest selectedSubQ = (Get_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            int selectedDropTableIndex = collectTable.getSelectionModel().getSelectedIndex();
            if (selectedSubQ != null && selectedDropTableIndex != -1) {
                selectedSubQ.m_GiverTypes.get(selectedDropTableIndex).sub_giver
                        .remove(selectedSubQ.m_GiverTypes.get(selectedDropTableIndex).sub_giver.size() - 1);
                getFromColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Get_SubQuest_Givers, ComboBox>, ObservableValue<ComboBox>>() {

                    @Override
                    public ObservableValue<ComboBox> call(
                            TableColumn.CellDataFeatures<Get_SubQuest_Givers, ComboBox> arg0) {
                        Get_SubQuest_Givers user = arg0.getValue();
                        ComboBox combobox = new ComboBox();
                        combobox.setPrefWidth(140);
                        combobox.setItems(FXCollections.observableArrayList(user.sub_giver));

                        return new SimpleObjectProperty<ComboBox>(combobox);
                    }
                });
                collectTable.getItems().clear();
                collectTable.getItems().addAll(FXCollections.observableArrayList(selectedSubQ.m_GiverTypes));
                collectTable.getSelectionModel().selectLast();
                collectTable.setEditable(true);
            }

        });
        // new task and remove task buttons
        newTaskBtn.setOnAction((ActionEvent e) -> {
            List<String> choices = new ArrayList<>();
            choices.add("Talking");
            choices.add("Collecting");
            choices.add("Killing");
            choices.add("Special");
            choices.add("Movement");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Talking", choices);
            dialog.setTitle("Subquest type");
            dialog.setHeaderText("Subquest type");
            dialog.setContentText("Choose sub quest type:");


            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String choice = result.get();
                QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
                SubQuest obj;
                switch (choice) {
                    case "Talking": {
                        obj = new Goto_SubQuest();
                        obj.m_subQuestType = 0;
                        break;
                    }
                    case "Collecting": {
                        obj = new Get_SubQuest();
                        obj.m_subQuestType = 1;
                        break;
                    }
                    case "Killing": {
                        obj = new Kill_SubQuest();
                        obj.m_subQuestType = 2;
                        break;
                    }
                    case "Special": {
                        obj = new SubQuest();
                        obj.m_subQuestType = 3;
                        break;
                    }
                    case "Movement": {
                        obj = new movement_SubQuest();
                        obj.m_subQuestType = 4;
                        break;
                    }
                    default:
                        return;
                }
                obj.m_subQuestTitle = "New SubQuest";
                obj.m_immediateReward = 0;
                obj.m_iTimeLimit = -1;
                obj.m_NextSubQuestID = 0;
                obj.m_ParentQuestID = 0;
                obj.m_reward_money = 0;
                obj.m_reward_xp = 0;
                obj.prevcnt = 1;

                //null fields for killing subq
                killCountField.setText("0");
                playerMinLevelField.setText("1");
                monsterKillTable.getItems().clear();
                subSpecialTypeField.setText("0");
                subParam1Field.setText("0");
                subParam2Field.setText("0");
                subParam3Field.setText("0");
                subParam4Field.setText("0");
                subParam5Field.setText("0");
                talk1Field.setText("");
                talk2Field.setText("");
                obj.subids.add((byte) -1);
                obj.m_SubQuestID = (byte) selectedEntry.m_SubQuests.size();

                selectedEntry.subQuestCount++;
                selectedEntry.m_SubQuests.add(obj);
                taskBox.setItems(FXCollections.observableArrayList(selectedEntry.m_SubQuests));
            }
        });
        removeTaskBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            if (selectedEntry != null && !taskBox.getItems().isEmpty()) {
                selectedEntry.subQuestCount--;
                selectedEntry.m_SubQuests.remove(taskBox.getSelectionModel().getSelectedItem());
                taskBox.getItems().clear();
                taskBox.getItems().addAll(selectedEntry.m_SubQuests);
                if (!taskBox.getItems().isEmpty()) {
                    taskBox.getSelectionModel().selectLast();
                }
            }
        });

        // kill sub quests monster types table buttons
        addMonsterKillBtn.setOnAction(e -> {
            Kill_SubQuest subQ = (Kill_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (subQ != null) {
                Kill_SubQuest_Targets obj = new Kill_SubQuest_Targets();
                obj.target_type = 9999;
                subQ.m_KillMonTypes.add(obj);
                subQ.target_num++;
                monsterKillTable.getItems().clear();
                monsterKillTable.getItems().addAll(subQ.m_KillMonTypes);
                monsterKillTable.getSelectionModel().selectLast();
                monsterKillTable.setVisible(false);
                monsterKillTable.setVisible(true);
            }

        });
        removeMonsterKillBtn.setOnAction(e -> {
            Kill_SubQuest subQ = (Kill_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (subQ != null && !monsterKillTable.getItems().isEmpty()) {
                subQ.m_KillMonTypes.remove(monsterKillTable.getSelectionModel().getSelectedIndex());
                subQ.target_num--;
                monsterKillTable.getItems().clear();
                monsterKillTable.getItems().addAll(subQ.m_KillMonTypes);
                monsterKillTable.getSelectionModel().selectLast();
                monsterKillTable.setVisible(false);
                monsterKillTable.setVisible(true);
            }
        });

        //Quest control buttons
        newQuestBtn.setOnAction(e -> {
            QuestEntry newQuest = new QuestEntry(questHeader.version, editor_type);
            try {
                newQuest.quest_ID = itemsData.get(itemsData.size() - 1).quest_ID + 1;
            } catch (ArrayIndexOutOfBoundsException e1) {
                newQuest.quest_ID = 0;
            }
            if (editor_type == EDITOR_TYPE.TITLE) {
                newQuest.titleInfo = new QTitleInfo();
                newQuest.titleInfo.titleName = "";
            }
            newQuest.q_name = "New Quest";
            newQuest.q_desc = "Blank Description";
            newQuest.owner_NPC = 9999;
            newQuest.reward_NPC = 9999;
            newQuest.minReqLevel = 1;
            newQuest.maxReqLevel = 120;
            newQuest.recommendedLevel = 1;
            newQuest.playerClass = -256;
            newQuest.parentQuestID = -1;
            newQuest.childQuestID = -1;
            newQuest.iTimeLimit = -1;
            newQuest.m_bAlarmDisable = 1;
            newQuest.m_ChangeJobType = 255;
            newQuest.m_AcceptWeek = "       ";
            newQuest.m_InfoMsg = "";
            newQuest.m_InfoMsg2 = "";
            newQuest.m_InfoMsg3 = "";
            newQuest.m_LevelNoti = "";
            newQuest.m_LevelNoti2 = "";
            itemsData.add(newQuest);
            ++questHeader.qsize;
            questBox.setItems(itemsData);
            questBox.getSelectionModel().selectLast();
            subQuestTabPane.getSelectionModel().select(generalTab);
        });
        saveFileBtn.setOnAction(e -> {
            try {
                String path = "quest";
                if (editor_type == EDITOR_TYPE.TITLE) {
                    path = "questach";
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
                String string = dateFormat.format(new Date());
                Files.copy(Paths.get(path + ".bin"), Paths.get(path + "_" + string + ".bin"), StandardCopyOption.REPLACE_EXISTING);
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path + ".bin")));
                QuestIO writer = new QuestIO(out);
                writer.writeInt(questHeader.version);
                writer.writeByte(questHeader.use_multiple_num);
                if (questHeader.use_multiple_num == 1) {
                    writer.writeInt(questHeader.multiple_num);
                }
                writer.writeShort(questHeader.qsize);
                for (QuestEntry questOut : itemsData) {
                    questOut.Write(writer);
                }
                out.close();
                playFadeMessage("Quest file saved...", 2000);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        saveTaskBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            SubQuest selectedSubQuest = taskBox.getSelectionModel().getSelectedItem();

            selectedSubQuest.m_subQuestType = (byte) subQuestType.getSelectionModel().getSelectedIndex();
            selectedSubQuest.m_NextSubQuestID = (byte) Integer.parseInt(nextSubQuestIdField.getText());
            selectedSubQuest.m_SubQuestID = (byte) Integer.parseInt(subQuestIdField.getText());
            selectedSubQuest.m_ParentQuestID = selectedEntry.quest_ID;
            selectedSubQuest.m_subQuestTitle = subQuestName.getText();
            selectedSubQuest.m_reward_money = Integer.parseInt(immediateCronesField.getText());
            selectedSubQuest.m_reward_xp = Integer.parseInt(immediateExpField.getText());
            selectedSubQuest.m_immediateReward = Util.boolToByte(immediateCheck.isSelected());
            selectedSubQuest.m_special_complete_condition[0] = Integer.parseInt(subSpecialTypeField.getText());
            selectedSubQuest.m_special_complete_condition[1] = Integer.parseInt(subParam1Field.getText());
            selectedSubQuest.m_special_complete_condition[2] = Integer.parseInt(subParam2Field.getText());
            selectedSubQuest.m_special_complete_condition[3] = Integer.parseInt(subParam3Field.getText());
            selectedSubQuest.m_special_complete_condition[4] = Integer.parseInt(subParam4Field.getText());
            selectedSubQuest.m_special_complete_condition[5] = Integer.parseInt(subParam5Field.getText());
            selectedSubQuest.m_iTimeLimit = Integer.parseInt(subTimeLimitField.getText());

            EQuestType subQType = EQuestType.fromId(selectedSubQuest.m_subQuestType);
            switch (subQType) {
                case GOTO_TYPE: {
                    Goto_SubQuest subQ = (Goto_SubQuest) selectedSubQuest;

                    subQ.dicnt = (short) subQ.m_DeliverItems.size();
                    subQ.qicnt = (short) subQ.m_TempGainedQuestItems.size();
                    subQ.m_intro_dlg_filename = talk1Field.getText();
                    subQ.m_reintro_dlg_filename = talk2Field.getText();
                    subQ.m_DestinationType = Integer.parseInt(talkTaskNpcField.getText());
                    break;
                }
                case GETITEM_TYPE: {
                    Get_SubQuest subQ = (Get_SubQuest) selectedSubQuest;

                    subQ.m_bGetFromMonster = Util.boolToByte(collectFromMonsterBox.isSelected());
                    subQ.m_getItemCount = Short.parseShort(collectQtyField.getText());
                    subQ.m_getItemType = Integer.parseInt(collectIdField.getText());
                    for (int j = 0; j < subQ.m_iGiverNum; j++) {
                        subQ.m_GiverTypes.get(j).giver_type_num =
                                subQ.m_GiverTypes.get(j).sub_giver.size();
                    }
                    break;
                }
                case KILLMOB_TYPE: {
                    Kill_SubQuest subQ = (Kill_SubQuest) selectedSubQuest;
                    subQ.target_num = subQ.m_KillMonTypes.size();
                    subQ.m_equipWeaponType = Integer.parseInt(requiredWpnField.getText());
                    subQ.m_killCounter = Short.parseShort(killCountField.getText());
                    subQ.m_bKillMonster = Util.boolToByte(killMonsterBox.isSelected());
                    subQ.m_iKillPlayerMinLevel = Integer.parseInt(playerMinLevelField.getText());
                    break;

                }
                default:
                    break;
            }
            playFadeMessage("Task edits saved..", 2000);
        });
        saveEditsBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            // quest name
            selectedEntry.q_name = questNameField.getText();
            // quest description
            selectedEntry.q_desc = questDescField.getText();
            try {
                selectedEntry.parentQuestID = itemsData.get(parentQuestBox.getSelectionModel().getSelectedIndex()).quest_ID;
            } catch (Exception ss) {
                selectedEntry.parentQuestID = -1;
            }
            try {
                selectedEntry.childQuestID = itemsData.get(childQuestBox.getSelectionModel().getSelectedIndex()).quest_ID;
            } catch (Exception ss) {
                selectedEntry.childQuestID = -1;
            }
            if (editor_type == EDITOR_TYPE.TITLE) {
                selectedEntry.titleInfo.titleData.item_id = Integer.parseInt(titleIdField.getText());
                selectedEntry.titleInfo.titleName = titleNameField.getText();
            }
            // owner npc
            selectedEntry.owner_NPC = Integer.parseInt(obtainNpcField.getText());
            // return npc
            selectedEntry.reward_NPC = Integer.parseInt(returnNpcField.getText());
            // exp reward
            selectedEntry.reward_EXP = Long.parseLong(expField.getText());
            // money reward
            selectedEntry.reward_Money = Long.parseLong(moneyField.getText());
            // quest minimum level
            selectedEntry.minReqLevel = Short.parseShort(minLevelField.getText());
            // quest maximum level
            selectedEntry.maxReqLevel = Short.parseShort(maxLevelField.getText());
            // quest recommended level
            selectedEntry.recommendedLevel = Short.parseShort(recLevelField.getText());
            // quests completion time
            selectedEntry.iTimeLimit = Integer.parseInt(completionTimeField.getText());
            // entry fee
            selectedEntry.m_EntryFee = Integer.parseInt(entryFeeField.getText());
            // player class
            selectedEntry.playerClass =  Integer.parseInt(playerClassField.getText());
            // is quest disabled
            selectedEntry.disable = Util.boolToByte(disableCheck.isSelected());
            // is iteration quest
            selectedEntry.iterationQuest = Util.boolToByte(iterationCheck.isSelected());
            // is party quest
            selectedEntry.partyQuest = Util.boolToByte(partyCheck.isSelected());
            // is guild create quest
            selectedEntry.guildCreateQuest = Util.boolToByte(guildCheck.isSelected());
            // is solo quest
            selectedEntry.m_bNoPartyQuest = Util.boolToByte(soloCheck.isSelected());
            // is main quest
            selectedEntry.m_bMainQuest = Util.boolToByte(mainqCheck.isSelected());
            // is guildmaster only quest
            selectedEntry.m_bGuildMasterQuest = Util.boolToByte(gMasterCheck.isSelected());
            // is party share quest ???
            selectedEntry.m_bPartyShareQuest = Util.boolToByte(partyShareCheck.isSelected());
            // is boss  quest
            selectedEntry.m_bBossQuest = Util.boolToByte(bossQuestCheck.isSelected());
            // exp table
            selectedEntry.m_bUseLevelExpTable = Util.boolToByte(expTableCheck.isSelected());
            // money table
            selectedEntry.m_bUseLevelMoneyTable = Util.boolToByte(moneyTableCheck.isSelected());
            // interconnect check
            selectedEntry.interConnectQuest = Util.boolToByte(interconnectCheck.isSelected());
            // is job change quest
            selectedEntry.m_IsGuildQuest = Util.boolToByte(guildQuestBox.isSelected());
            selectedEntry.m_bChangeJobQuest = Util.boolToByte(jobCheck.isSelected());
            // job change type
            selectedEntry.m_ChangeJobType = Integer.parseInt(jobTypeField.getText());
            // how many items from reward u can pick
            selectedEntry.itemCountForSelect = Integer.parseInt(choiceNumField.getText());
            // special rewards
            selectedEntry.m_special_reward.condition_type = Integer.parseInt(specialRwrdTypeField.getText());
            selectedEntry.m_special_reward.param_1 = Integer.parseInt(specialParam1Field.getText());
            selectedEntry.m_special_reward.param_2 = Integer.parseInt(specialParam2Field.getText());
            selectedEntry.m_special_reward.param_3 = Integer.parseInt(specialParam3Field.getText());
            selectedEntry.m_special_reward.param_4 = Integer.parseInt(specialParam4Field.getText());
            selectedEntry.m_special_reward.param_5 = Integer.parseInt(specialParam5Field.getText());
            // time limit
            selectedEntry.iTimeLimit = Integer.parseInt(mainTimeLimitField.getText());
            // chat msg noti


            //notify
            etcViewController.savePropsToEntry(selectedEntry);
            ladderViewController.savePropsToEntry(selectedEntry);


            playFadeMessage("Quest edits saved..", 2000);


        });
        removeQuestBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            itemsData.remove(selectedEntry);
            --questHeader.qsize;
            questBox.setItems(itemsData);
        });

        delNextBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            selectedEntry.childQuestID = -1;
            childQuestBox.getSelectionModel().select(null);
        });
        delPrevBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            selectedEntry.parentQuestID = -1;
            parentQuestBox.getSelectionModel().select(null);
        });

        addConditionBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            QuestSpecialCondition obj = new QuestSpecialCondition();
            selectedEntry.SpecialConditionList.add(obj);
            selectedEntry.Special_Condition_Count++;
            specialConditionTable.getItems().clear();
            specialConditionTable.getItems().addAll(selectedEntry.SpecialConditionList);
            specialConditionTable.getSelectionModel().selectLast();
        });
        delConditionBtn.setOnAction(e -> {
            QuestEntry selectedEntry = questBox.getSelectionModel().getSelectedItem();
            if (!specialConditionTable.getItems().isEmpty()) {
                selectedEntry.SpecialConditionList.remove(specialConditionTable.getSelectionModel().getSelectedIndex());
                selectedEntry.Special_Condition_Count--;
                specialConditionTable.getItems().clear();
                specialConditionTable.getItems().addAll(selectedEntry.SpecialConditionList);
                specialConditionTable.getSelectionModel().selectLast();
            }
        });

        // movement add/remove btns
        addMovementEntryBtn.setOnAction(e -> {
            movement_SubQuest subQ = (movement_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (subQ != null) {
                stMovementDestId obj = new stMovementDestId();
                subQ.m_DestList.add(obj);
                subQ.id_count++;
                movementTable.getItems().clear();
                movementTable.getItems().addAll(subQ.m_DestList);
                movementTable.getSelectionModel().selectLast();
                movementTable.setVisible(false);
                movementTable.setVisible(true);
            }

        });
        delMovementEntryBtn.setOnAction(e -> {
            movement_SubQuest subQ = (movement_SubQuest) taskBox.getSelectionModel().getSelectedItem();
            if (subQ != null && !movementTable.getItems().isEmpty()) {
                subQ.m_DestList.remove(movementTable.getSelectionModel().getSelectedItem());
                subQ.id_count--;
                movementTable.getItems().clear();
                movementTable.getItems().addAll(subQ.m_DestList);
                movementTable.getSelectionModel().selectLast();
                movementTable.setVisible(false);
                movementTable.setVisible(true);
            }
        });

        addReqPrevBtn.setOnAction(e -> {
            String numberStr = prevSubQuestIdField.getText();
            QuestEntry quest = questBox.getSelectionModel().getSelectedItem();
            SubQuest subQuest = taskBox.getSelectionModel().getSelectedItem();
            if (numberStr != null && !numberStr.isEmpty() && quest != null && subQuest != null) {
                byte toAdd = Byte.parseByte(numberStr);
                if (toAdd < 0 || toAdd > 100)
                    return;
                for (int i = 0; i < quest.m_SubQuests.size(); i++) {
                    if (toAdd == quest.m_SubQuests.get(i).m_SubQuestID && quest.m_SubQuests.get(i).m_SubQuestID != subQuest.m_SubQuestID) {
                        if (!subQuest.subids.contains(toAdd)) {
                            //quest.m_SubQuests.get(toAdd).m_NextSubQuestID = subQuest.m_SubQuestID;
                            subQuest.subids.add(toAdd);
                            subQuest.prevcnt++;
                            Collections.sort(subQuest.subids);
                            previousSubQuestsListView.getItems().clear();
                            previousSubQuestsListView.setItems(FXCollections.observableArrayList(subQuest.subids));
                        }
                        break;
                    }
                }

            }
        });
        delReqPrevBtn.setOnAction(e -> {

            QuestEntry quest = questBox.getSelectionModel().getSelectedItem();
            SubQuest subQuest = taskBox.getSelectionModel().getSelectedItem();
            if (quest != null && subQuest != null && previousSubQuestsListView.getSelectionModel().getSelectedItem() != null) {


                if (subQuest.subids.remove(previousSubQuestsListView.getSelectionModel().getSelectedItem()))
                    subQuest.prevcnt--;
                if (subQuest.subids.isEmpty()) {
                    subQuest.prevcnt = 1;
                    subQuest.subids.add((byte) -1);
                }
                Collections.sort(subQuest.subids);
                previousSubQuestsListView.getItems().clear();
                previousSubQuestsListView.setItems(FXCollections.observableArrayList(subQuest.subids));
            }
        });
        //
        cloneQuestBtn.setOnAction(e -> {

            QuestEntry questToCopy = questBox.getSelectionModel().getSelectedItem();
            if (questToCopy == null) {
                playFadeMessage("Select the quest first..", 2000);
                return;
            }
            int id = -1;
            try {
                id = itemsData.get(itemsData.size() - 1).quest_ID + 1;
            } catch (ArrayIndexOutOfBoundsException e1) {
                id = 9999;
            }
            QuestEntry newQuest = new QuestEntry(questHeader.version, editor_type, id, questToCopy);
            newQuest.quest_ID = id;
            itemsData.add(newQuest);
            ++questHeader.qsize;
            questBox.getSelectionModel().select(newQuest);
            playFadeMessage("Quest was cloned...", 2000);
        });
        //Quest control buttons
        disableQuestsBtn.setOnAction(e -> {
            for (QuestEntry q : itemsData) {
                q.disable = Util.boolToByte(questHeader.isAllEnabled);
            }
            disableCheck.setSelected(questHeader.isAllEnabled);
            questHeader.isAllEnabled = !questHeader.isAllEnabled;

        });
        achieveBinBtn.setOnAction(e -> {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(
                        AchieveWindow.class.getResource("views/achieve.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.setTitle("Achieve.bin editor");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) e.getSource()).getScene().getWindow());
            stage.show();
        });
    }

    private void playFadeMessage(String message, int duration) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), fadeLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        fadeLabel.setText(message);

        ft.play();
    }

    private void setupFilters() {
        allRadio.setSelected(true);
        FilteredList<QuestEntry> filteredData = new FilteredList<>(itemsData, s -> true);
        inputField.textProperty().addListener(obs -> {
            String filter = inputField.getText();
            if (nameRadio.isSelected()) {

                if (soloRadio.isSelected())
                    filteredData.setPredicate(s -> s.m_bNoPartyQuest == 1 && s.q_name.toUpperCase().contains(filter.toUpperCase()));
                else if (iterationRadio.isSelected())
                    filteredData.setPredicate(s -> s.iterationQuest == 1 && s.q_name.toUpperCase().contains(filter.toUpperCase()));
                else if (disabledRadio.isSelected())
                    filteredData.setPredicate(s -> s.disable == 1 && s.q_name.toUpperCase().contains(filter.toUpperCase()));
                else if (notifyRadio.isSelected())
                    filteredData.setPredicate(s -> s.m_bUseLevelNotyMsg == 1 && s.q_name.toUpperCase().contains(filter.toUpperCase()));
                else if (guildMasterRadio.isSelected())
                    filteredData.setPredicate(s -> s.m_bGuildMasterQuest == 1 && s.q_name.toUpperCase().contains(filter.toUpperCase()));
                else if (allRadio.isSelected())
                    filteredData.setPredicate(s -> s.q_name.toUpperCase().contains(filter.toUpperCase()));
            } else if (startNpcRadio.isSelected()) {
                filteredData.setPredicate(s -> Integer.toString(s.owner_NPC).toUpperCase().contains(filter.toUpperCase()));
            } else if (rewardNpcRadio.isSelected()) {
                filteredData.setPredicate(s -> Integer.toString(s.reward_NPC).toUpperCase().contains(filter.toUpperCase()));
            } else if ((filter == null || filter.length() == 0) && allRadio.isSelected()) {
                filteredData.setPredicate(s -> true);
            }
        });
        notifyRadio.selectedProperty().addListener(obs -> {
            if (notifyRadio.isSelected()) {
                filteredData.setPredicate(s -> s.m_bUseLevelNotyMsg == 1 && s.q_name.toUpperCase().contains(inputField.getText().toUpperCase()));
            }
        });
        soloRadio.selectedProperty().addListener(obs -> {
            if (soloRadio.isSelected()) {
                filteredData.setPredicate(s -> s.m_bNoPartyQuest == 1 && s.q_name.toUpperCase().contains(inputField.getText().toUpperCase()));
            }
        });
        iterationRadio.selectedProperty().addListener(obs -> {
            if (iterationRadio.isSelected()) {
                filteredData.setPredicate(s -> s.iterationQuest == 1 && s.q_name.toUpperCase().contains(inputField.getText().toUpperCase()));
            }
        });
        disabledRadio.selectedProperty().addListener(obs -> {
            if (disabledRadio.isSelected()) {
                filteredData.setPredicate(s -> s.disable == 1 && s.q_name.toUpperCase().contains(inputField.getText().toUpperCase()));
            }
        });
        guildMasterRadio.selectedProperty().addListener(obs -> {
            if (guildMasterRadio.isSelected()) {
                filteredData.setPredicate(s -> s.m_bGuildMasterQuest == 1 && s.q_name.toUpperCase().contains(inputField.getText().toUpperCase()));
            }
        });
        allRadio.selectedProperty().addListener(obs -> {
            if (allRadio.isSelected()) {
                filteredData.setPredicate(s -> s.q_name.toUpperCase().contains(inputField.getText().toUpperCase()));
            }
        });
        startNpcRadio.selectedProperty().addListener(obs -> {
            if (startNpcRadio.isSelected()) {
                guildMasterRadio.setDisable(true);
                allRadio.setDisable(true);
                soloRadio.setDisable(true);
                guildMasterRadio.setDisable(true);
                notifyRadio.setDisable(true);
                iterationRadio.setDisable(true);
                disabledRadio.setDisable(true);
            }
        });
        rewardNpcRadio.selectedProperty().addListener(obs -> {
            if (rewardNpcRadio.isSelected()) {
                guildMasterRadio.setDisable(true);
                allRadio.setDisable(true);
                soloRadio.setDisable(true);
                guildMasterRadio.setDisable(true);
                notifyRadio.setDisable(true);
                iterationRadio.setDisable(true);
                disabledRadio.setDisable(true);
            }
        });
        nameRadio.selectedProperty().addListener(obs -> {
            if (nameRadio.isSelected()) {
                allRadio.setSelected(true);
                guildMasterRadio.setDisable(false);
                allRadio.setDisable(false);
                soloRadio.setDisable(false);
                guildMasterRadio.setDisable(false);
                notifyRadio.setDisable(false);
                iterationRadio.setDisable(false);
                disabledRadio.setDisable(false);
            }
        });
        filterQuestNameCol.setCellValueFactory(QItemData -> new ReadOnlyStringWrapper(QItemData.getValue().q_name));
        filterQuestIdCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().quest_ID));
        filteredTable.setItems(filteredData);
        filteredTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (filteredTable.getSelectionModel().getSelectedItem() != null) {
                questBox.getSelectionModel().select(newValue.quest_ID);
            }
        });
    }

    private void setupInitialItems() {
        //Quest box with all quest names and corresponding IDs
        questBox.setItems(itemsData);
        questBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<QuestEntry> call(ListView<QuestEntry> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(QuestEntry quest, boolean empty) {
                        super.updateItem(quest, empty);
                        if (!empty) {
                            setText(quest.quest_ID + " - " + quest.q_name);
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        //Child quest box, can choose from all quest names and corresponding IDs
        childQuestBox.setItems(itemsData);
        childQuestBox.setCellFactory(new Callback<ListView<QuestEntry>, ListCell<QuestEntry>>() {
            @Override
            public ListCell<QuestEntry> call(ListView<QuestEntry> param) {

                return new ListCell<QuestEntry>() {
                    @Override
                    public void updateItem(QuestEntry quest, boolean empty) {
                        super.updateItem(quest, empty);
                        if (!empty) {
                            setText(quest.quest_ID + " - " + quest.q_name);
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });


        //Parent quest box, can choose from all quest names and corresponding IDs
        parentQuestBox.setItems(itemsData);
        parentQuestBox.setCellFactory(new Callback<ListView<QuestEntry>, ListCell<QuestEntry>>() {
            @Override
            public ListCell<QuestEntry> call(ListView<QuestEntry> param) {

                return new ListCell<QuestEntry>() {
                    @Override
                    public void updateItem(QuestEntry quest, boolean empty) {
                        super.updateItem(quest, empty);
                        if (!empty) {
                            setText(quest.quest_ID + " - " + quest.q_name);
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        //Subquest type 0-Talking quest, 1- Collecting quest, 2-Killing quest
        subQuestType.getItems().addAll("Talking", "Collecting", "Killing", "Special");
        optionTypeField.setItems(FXCollections.observableArrayList(new ArrayList<String>(TypesMap.getOptionTypesMap().keySet())));
        titleOptionTypeAddBox.setItems(FXCollections.observableArrayList(new ArrayList<String>(TypesMap.getAttributeTypesMap().keySet())));
    }

    private void setupListeners() {
        //QuestBox listener
        questBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends QuestEntry> observable, QuestEntry oldValue, QuestEntry newValue) {

                ladderViewController.setRewardsList(newValue.m_LadderRewards);

                subQuestTabPane.getSelectionModel().select(generalTab);
                questNameField.setText(newValue.q_name);
                questDescField.setText(newValue.q_desc);
                if (newValue.childQuestID == -1) {
                    childQuestBox.getSelectionModel().select(null);
                } else {
                    for (QuestEntry p : itemsData) {
                        if (p.quest_ID == newValue.childQuestID) {
                            childQuestBox.getSelectionModel().select(p);
                            break;
                        }
                    }
                }
                if (newValue.parentQuestID == -1) {
                    parentQuestBox.getSelectionModel().select(null);
                } else {
                    for (QuestEntry p : itemsData) {
                        if (p.quest_ID == newValue.parentQuestID) {
                            parentQuestBox.getSelectionModel().select(p);
                            break;
                        }
                    }
                }
                obtainNpcField.setText(Integer.toString(newValue.owner_NPC));
                returnNpcField.setText(Integer.toString(newValue.reward_NPC));
                expField.setText(Long.toString(newValue.reward_EXP));
                moneyField.setText(Long.toString(newValue.reward_Money));
                minLevelField.setText(Integer.toString(newValue.minReqLevel));
                maxLevelField.setText(Integer.toString(newValue.maxReqLevel));
                completionTimeField.setText(Integer.toString(newValue.iTimeLimit));
                recLevelField.setText(Integer.toString(newValue.recommendedLevel));
                entryFeeField.setText(Integer.toString(newValue.m_EntryFee));
                playerClassField.setText(Integer.toString(newValue.playerClass));
                disableCheck.setSelected(Util.byteToBool(newValue.disable));
                iterationCheck.setSelected(Util.byteToBool(newValue.iterationQuest));
                partyCheck.setSelected(Util.byteToBool(newValue.partyQuest));
                guildCheck.setSelected(Util.byteToBool(newValue.guildCreateQuest));
                soloCheck.setSelected(Util.byteToBool(newValue.m_bNoPartyQuest));
                mainqCheck.setSelected(Util.byteToBool(newValue.m_bMainQuest));
                gMasterCheck.setSelected(Util.byteToBool(newValue.m_bGuildMasterQuest));
                partyShareCheck.setSelected(Util.byteToBool(newValue.m_bPartyShareQuest));
                bossQuestCheck.setSelected(Util.byteToBool(newValue.m_bBossQuest));
                jobCheck.setSelected(Util.byteToBool(newValue.m_bChangeJobQuest));
                expTableCheck.setSelected(Util.byteToBool(newValue.m_bUseLevelExpTable));
                moneyTableCheck.setSelected(Util.byteToBool(newValue.m_bUseLevelMoneyTable));
                interconnectCheck.setSelected(Util.byteToBool(newValue.interConnectQuest));
                guildCheck.setSelected(Util.byteToBool(newValue.m_IsGuildQuest));
                etcViewController.setPropsFromEntry(newValue);
                ladderViewController.setPropsFromEntry(newValue);

                jobTypeField.setText(Integer.toString(newValue.m_ChangeJobType));
                choiceNumField.setText(Integer.toString(newValue.itemCountForSelect));
                specialRwrdTypeField.setText(Integer.toString(newValue.m_special_reward.condition_type));
                specialParam1Field.setText(Integer.toString(newValue.m_special_reward.param_1));
                specialParam2Field.setText(Integer.toString(newValue.m_special_reward.param_2));
                specialParam3Field.setText(Integer.toString(newValue.m_special_reward.param_3));
                specialParam4Field.setText(Integer.toString(newValue.m_special_reward.param_4));
                specialParam5Field.setText(Integer.toString(newValue.m_special_reward.param_5));
                mainTimeLimitField.setText(Integer.toString(newValue.iTimeLimit));


                ///Titlestuff
                if (editor_type == EDITOR_TYPE.TITLE) {
                    titleNameField.setText(newValue.titleInfo.titleName);
                    titleIdField.setText(Integer.toString(newValue.titleInfo.titleData.item_id));
                    ObservableList<QItemOption> optionStats = FXCollections.observableArrayList(newValue.titleInfo.titleData.itemOptions);
                    titleStatCol.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<Integer>(optionData.getValue().type));
                    titleStatCol.setOnEditCommit((TableColumn.CellEditEvent<QItemOption, Integer> event) -> {
                        event.getTableView().getItems().get(event.getTablePosition().getRow()).type = (event.getNewValue());
                    });
                    titleStatCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                    titleStatCol.setEditable(true);

                    titleStatNameCol.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<String>((String) TypesMap.getKeyFromValue(TypesMap.getAttributeTypesMap(), optionData.getValue().type)));

                    titleValCol.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<Integer>(optionData.getValue().value));
                    titleValCol.setOnEditCommit((TableColumn.CellEditEvent<QItemOption, Integer> event) -> {
                        event.getTableView().getItems().get(event.getTablePosition().getRow()).value = (event.getNewValue());
                    });
                    titleValCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                    titleValCol.setEditable(true);
                    titleOptionTable.getItems().clear();
                    titleOptionTable.setItems(optionStats);
                    titleOptionTable.setEditable(true);
                    titleOptionTable.getSelectionModel().selectFirst();
                }

                //SETTING UP REWARD ITEMS TABLE
                ObservableList rewardItems = FXCollections.observableArrayList(itemsData.get(questBox.getSelectionModel().getSelectedIndex()).rewardItems);
                typeColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().item_id));
                typeColumn.setOnEditCommit((TableColumn.CellEditEvent<QItemData, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).item_id = (event.getNewValue());
                });
                typeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                typeColumn.setEditable(true);
                qtyColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Short>(QItemData.getValue().item_num));
                qtyColumn.setOnEditCommit((TableColumn.CellEditEvent<QItemData, Short> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).item_num = (event.getNewValue());
                });
                qtyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ShortStringConverter()));
                qtyColumn.setEditable(true);

                rewardLvlColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().random_opt_level));
                rewardLvlColumn.setOnEditCommit((TableColumn.CellEditEvent<QItemData, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).random_opt_level = (event.getNewValue());
                });
                rewardLvlColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                rewardLvlColumn.setEditable(true);
                checkColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<QItemData, CheckBox>, ObservableValue<CheckBox>>) arg0 -> {
                    QItemData user = arg0.getValue();
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(Util.byteToBool(user.no_limit_status));
                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                            Boolean old_val, Boolean new_val) {

                            user.no_limit_status = Util.boolToByte(new_val);
                        }
                    });
                    return new SimpleObjectProperty<CheckBox>(checkBox);
                });
                rewardTable.getItems().clear();
                rewardTable.getItems().addAll(rewardItems);
                rewardTable.setEditable(true);


                //SETTING UP OPTION STATS COLUMN

                rewardTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<QItemData>() {
                    @Override
                    public void changed(ObservableValue<? extends QItemData> observable,
                                        QItemData oldSub, QItemData newSub) {
                        if (newSub == null)
                            return;
                        ObservableList optionStats = FXCollections.observableArrayList(newSub.itemOptions);
                        statColumn.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<Integer>(optionData.getValue().type));
                        statColumn.setOnEditCommit((TableColumn.CellEditEvent<QItemOption, Integer> event) -> {
                            event.getTableView().getItems().get(event.getTablePosition().getRow()).type = (event.getNewValue());
                        });
                        statColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                        statColumn.setEditable(true);

                        statNameCol.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<String>((String) TypesMap.getKeyFromValue(TypesMap.getOptionTypesMap(), optionData.getValue().type)));

                        valueColumn.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<Integer>(optionData.getValue().value));
                        valueColumn.setOnEditCommit((TableColumn.CellEditEvent<QItemOption, Integer> event) -> {
                            event.getTableView().getItems().get(event.getTablePosition().getRow()).value = (event.getNewValue());
                        });
                        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                        valueColumn.setEditable(true);
                        optionTable.getItems().clear();
                        optionTable.setItems(optionStats);
                        optionTable.setEditable(true);
                        optionTable.getSelectionModel().selectFirst();
                    }
                });

                //RANK START


                //RANK END
                ObservableList dlgList = FXCollections.observableArrayList(itemsData.get(questBox.getSelectionModel().getSelectedIndex()).dialogList);
                dlgStateColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Byte>(QItemData.getValue().quest_state));
                dlgStateColumn.setOnEditCommit((TableColumn.CellEditEvent<QuestDialog, Byte> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).quest_state = (event.getNewValue());
                });
                dlgStateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ByteStringConverter()));
                dlgStateColumn.setEditable(true);
                dlgColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<String>(QItemData.getValue().dlg));
                dlgColumn.setOnEditCommit((TableColumn.CellEditEvent<QuestDialog, String> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).dlg = (event.getNewValue());
                });
                dlgColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                dlgColumn.setEditable(true);
                dlgTable.getItems().clear();
                dlgTable.getItems().addAll(dlgList);
                dlgTable.setEditable(true);
                dlgTable.getSelectionModel().selectFirst();
                if (newValue.dialogsCount == 0) {
                    dlgTable.getItems().clear();
                }
                /////

                ObservableList startItemList = FXCollections.observableArrayList(newValue.requiredItems);
                startingTypeColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().item_id));
                startingTypeColumn.setOnEditCommit((TableColumn.CellEditEvent<QItemData, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).item_id = (event.getNewValue());
                });
                startingTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                startingTypeColumn.setEditable(true);
                startingQuantityColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Short>(QItemData.getValue().item_num));
                startingQuantityColumn.setOnEditCommit((TableColumn.CellEditEvent<QItemData, Short> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).item_num = (event.getNewValue());
                });
                startingQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ShortStringConverter()));
                startingQuantityColumn.setEditable(true);
                startingItemsTable.getItems().clear();
                startingItemsTable.getItems().addAll(startItemList);
                startingItemsTable.setEditable(true);
                startingItemsTable.getSelectionModel().selectFirst();


                /////

                ObservableList<SubQuest> subQuests = null;
                if (!newValue.m_SubQuests.isEmpty()) {
                    subQuests = FXCollections.observableArrayList(newValue.m_SubQuests);
                }
                taskBox.setItems(subQuests);
                taskBox.setCellFactory(new Callback<ListView<SubQuest>, ListCell<SubQuest>>() {
                    @Override
                    public ListCell<SubQuest> call(ListView<SubQuest> param) {

                        return new ListCell<SubQuest>() {
                            @Override
                            public void updateItem(SubQuest quest, boolean empty) {
                                super.updateItem(quest, empty);
                                if (!empty) {
                                    setText(quest.m_SubQuestID + " - " + quest.m_subQuestTitle);
                                    setGraphic(null);
                                } else {
                                    setText(null);
                                }
                            }
                        };
                    }

                });
                Callback<SubQuest, String> extractor = new Callback<SubQuest, String>() {
                    @Override
                    public String call(SubQuest s) {
                        return s.m_SubQuestID + " - " + s.m_subQuestTitle;
                    }
                };
                collectingTab.setDisable(true);
                killingTab.setDisable(true);
                specialTab.setDisable(true);
                talkingTab.setDisable(true);
                specialTab.setDisable(true);
                movementTab.setDisable(true);
                taskBox.getSelectionModel().select(null);
                taskBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SubQuest>() {
                    @Override
                    public void changed(ObservableValue<? extends SubQuest> observable,
                                        SubQuest oldSub, SubQuest newSub) {
                        if (taskBox.getSelectionModel().getSelectedItem() != null) {
                            subQuestTabPane.getSelectionModel().select(generalTab);
                            subQuestName.setText(newSub.m_subQuestTitle);
                            subQuestType.getSelectionModel().select(newSub.m_subQuestType);
                            subQuestIdField.setText(Integer.toString(newSub.m_SubQuestID));
                            nextSubQuestIdField.setText(Integer.toString(newSub.m_NextSubQuestID));

                            immediateCheck.setSelected(Util.byteToBool(newSub.m_immediateReward));
                            immediateCronesField.setText(Long.toString(newSub.m_reward_money));
                            immediateExpField.setText(Long.toString(newSub.m_reward_xp));
                            subSpecialTypeField.setText(Integer.toString(newSub.m_special_complete_condition[0]));
                            subParam1Field.setText(Integer.toString(newSub.m_special_complete_condition[1]));
                            subParam2Field.setText(Integer.toString(newSub.m_special_complete_condition[2]));
                            subParam3Field.setText(Integer.toString(newSub.m_special_complete_condition[3]));
                            subParam4Field.setText(Integer.toString(newSub.m_special_complete_condition[4]));
                            subParam5Field.setText(Integer.toString(newSub.m_special_complete_condition[5]));
                            subTimeLimitField.setText(Integer.toString(newSub.m_iTimeLimit));
                            ObservableList<Byte> prevSybQuestsList = FXCollections.observableArrayList(newSub.subids);
                            previousSubQuestsListView.setItems(prevSybQuestsList);


                            EQuestType questType = EQuestType.fromId(newSub.m_subQuestType);
                            switch (questType) {
                                case GOTO_TYPE: {
                                    Goto_SubQuest subQ = (Goto_SubQuest) newSub;
                                    if (!taskBox.getItems().isEmpty()) {

                                        talkTaskNpcField.setText(Integer.toString(subQ.m_DestinationType));
                                        talk1Field.setText(subQ.m_intro_dlg_filename);
                                        talk2Field.setText(subQ.m_reintro_dlg_filename);
                                        ObservableList returnItemsList = FXCollections.observableArrayList(subQ.m_DeliverItems);
                                        returnItemTypeColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().type));
                                        returnItemTypeColumn.setOnEditCommit((TableColumn.CellEditEvent<Goto_SubQuest_DeliverItem, Integer> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).type = (event.getNewValue());
                                        });
                                        returnItemTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                                        returnItemTypeColumn.setEditable(true);
                                        returnItemQuantityColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Short>(QItemData.getValue().cnt));
                                        returnItemQuantityColumn.setOnEditCommit((TableColumn.CellEditEvent<Goto_SubQuest_DeliverItem, Short> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).cnt = (event.getNewValue());
                                        });
                                        returnItemQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ShortStringConverter()));
                                        returnItemQuantityColumn.setEditable(true);
                                        returnItemTable.getItems().clear();
                                        returnItemTable.getItems().addAll(returnItemsList);
                                        returnItemTable.setEditable(true);
                                        ObservableList takeItemsList = FXCollections.observableArrayList(subQ.m_TempGainedQuestItems);
                                        takeItemTypeColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().type));
                                        takeItemTypeColumn.setOnEditCommit((TableColumn.CellEditEvent<GoTo_SubQuest_QuestItem, Integer> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).type = (event.getNewValue());
                                        });
                                        takeItemTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                                        takeItemTypeColumn.setEditable(true);
                                        takeItemQuantityColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Short>(QItemData.getValue().cnt));
                                        takeItemQuantityColumn.setOnEditCommit((TableColumn.CellEditEvent<GoTo_SubQuest_QuestItem, Short> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).cnt = (event.getNewValue());
                                        });
                                        takeItemQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ShortStringConverter()));
                                        takeItemQuantityColumn.setEditable(true);
                                        takeItemTable.getItems().clear();
                                        takeItemTable.getItems().addAll(takeItemsList);
                                        takeItemTable.setEditable(true);

                                        collectingTab.setDisable(true);
                                        killingTab.setDisable(true);
                                        specialTab.setDisable(true);
                                        talkingTab.setDisable(false);
                                        specialTab.setDisable(true);
                                        movementTab.setDisable(true);
                                    }
                                    break;
                                }
                                case GETITEM_TYPE: {
                                    Get_SubQuest subQ = (Get_SubQuest) newSub;
                                    if (!taskBox.getItems().isEmpty()) {
                                        collectQtyField.setText(Short.toString(subQ.m_getItemCount));
                                        collectIdField.setText(Integer.toString(subQ.m_getItemType));
                                        collectFromMonsterBox.setSelected(Util.byteToBool(subQ.m_bGetFromMonster));
                                        dropChanceColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Byte>(QItemData.getValue().drop_percent));
                                        dropChanceColumn.setOnEditCommit((TableColumn.CellEditEvent<Get_SubQuest_Givers, Byte> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).drop_percent = (event.getNewValue());
                                        });
                                        dropChanceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ByteStringConverter()));
                                        dropChanceColumn.setEditable(true);
                                        getFromColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Get_SubQuest_Givers, ComboBox>, ObservableValue<ComboBox>>() {

                                            @Override
                                            public ObservableValue<ComboBox> call(
                                                    TableColumn.CellDataFeatures<Get_SubQuest_Givers, ComboBox> arg0) {
                                                Get_SubQuest_Givers user = arg0.getValue();
                                                ComboBox combobox = new ComboBox();
                                                combobox.setPrefWidth(140);
                                                combobox.setItems(FXCollections.observableArrayList(user.sub_giver));

                                                return new SimpleObjectProperty<ComboBox>(combobox);
                                            }
                                        });

                                        collectTable.getItems().clear();
                                        collectTable.getItems().addAll(FXCollections.observableArrayList(subQ.m_GiverTypes));
                                        collectTable.setEditable(true);

                                        killingTab.setDisable(true);
                                        talkingTab.setDisable(true);
                                        specialTab.setDisable(true);
                                        collectingTab.setDisable(false);
                                        specialTab.setDisable(true);
                                        movementTab.setDisable(true);
                                    }
                                    break;
                                }
                                case KILLMOB_TYPE: {
                                    Kill_SubQuest subQ = (Kill_SubQuest) newSub;
                                    if (!taskBox.getItems().isEmpty()) {
                                        killCountField.setText(Integer.toString(subQ.m_killCounter));
                                        playerMinLevelField.setText(Integer.toString(subQ.m_iKillPlayerMinLevel));
                                        requiredWpnField.setText(Integer.toString(subQ.m_equipWeaponType));
                                        killMonsterBox.setSelected(Util.byteToBool(subQ.m_bKillMonster));

                                        monsterIdColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().target_type));
                                        monsterIdColumn.setOnEditCommit((TableColumn.CellEditEvent<Kill_SubQuest_Targets, Integer> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).target_type = (event.getNewValue());
                                        });
                                        monsterIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                                        monsterIdColumn.setEditable(true);

                                        monsterKillTable.getItems().clear();
                                        monsterKillTable.getItems().addAll(FXCollections.observableArrayList(subQ.m_KillMonTypes));
                                        monsterKillTable.setEditable(true);

                                        collectingTab.setDisable(true);
                                        talkingTab.setDisable(true);
                                        specialTab.setDisable(true);
                                        killingTab.setDisable(false);
                                        specialTab.setDisable(true);
                                        movementTab.setDisable(true);
                                    }
                                    break;
                                }
                                case SPECIAL_TYPE: {
                                    collectingTab.setDisable(true);
                                    talkingTab.setDisable(true);
                                    specialTab.setDisable(true);
                                    killingTab.setDisable(true);
                                    specialTab.setDisable(false);
                                    movementTab.setDisable(true);
                                    break;
                                }
                                case MOVEMENT_TYPE: {
                                    movement_SubQuest subQ = (movement_SubQuest) newSub;
                                    if (!taskBox.getItems().isEmpty()) {
                                        regionColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().uiRegion));
                                        regionColumn.setOnEditCommit((TableColumn.CellEditEvent<stMovementDestId, Integer> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).uiRegion = (event.getNewValue());
                                        });
                                        regionColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                                        regionColumn.setEditable(true);
                                        roomColumn.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().uiRoom));
                                        roomColumn.setOnEditCommit((TableColumn.CellEditEvent<stMovementDestId, Integer> event) -> {
                                            event.getTableView().getItems().get(event.getTablePosition().getRow()).uiRoom = (event.getNewValue());
                                        });
                                        roomColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                                        roomColumn.setEditable(true);

                                        movementTable.getItems().clear();
                                        movementTable.getItems().addAll(FXCollections.observableArrayList(subQ.m_DestList));
                                        movementTable.setEditable(true);

                                        collectingTab.setDisable(true);
                                        talkingTab.setDisable(true);
                                        specialTab.setDisable(true);
                                        killingTab.setDisable(true);
                                        specialTab.setDisable(true);
                                        movementTab.setDisable(false);
                                        break;
                                    }
                                }
                                default: {
                                    collectingTab.setDisable(true);
                                    killingTab.setDisable(true);
                                    specialTab.setDisable(true);
                                    talkingTab.setDisable(true);
                                    specialTab.setDisable(true);
                                    movementTab.setDisable(true);
                                    break;
                                }
                            }
                            if (newValue.subQuestCount == 0) {
                                collectingTab.setDisable(true);
                                talkingTab.setDisable(true);
                                specialTab.setDisable(true);
                                killingTab.setDisable(true);
                                generalTab.setDisable(true);
                                specialTab.setDisable(true);
                            }

                            subQuestName.setText(newSub.m_subQuestTitle);

                            if (newValue.subQuestCount == 0) {
                                taskBox.getSelectionModel().clearSelection();
                                taskBox.getItems().clear();
                            }
                        } else {
                            subQuestName.setText("");
                            subQuestType.getSelectionModel().select(null);
                            immediateCheck.setSelected(false);
                            immediateExpField.setText("0");
                            immediateCronesField.setText("0");
                            talkTaskNpcField.setText("0");
                            talk1Field.setText("");
                            talk2Field.setText("");
                            returnItemTable.getItems().clear();
                            takeItemTable.getItems().clear();
                            monsterKillTable.getItems().clear();
                            killCountField.setText("0");
                            playerMinLevelField.setText("0");
                            requiredWpnField.setText("0");
                            subSpecialTypeField.setText("0");
                            subParam1Field.setText("0");
                            subParam2Field.setText("0");
                            subParam3Field.setText("0");
                            subParam4Field.setText("0");
                            subParam5Field.setText("0");
                            collectingTab.setDisable(true);
                            talkingTab.setDisable(true);
                            specialTab.setDisable(true);
                            killingTab.setDisable(true);
                        }
                    }
                });
                if (newValue.subQuestCount == 0) {
                    taskBox.getSelectionModel().clearSelection();
                    taskBox.setItems(null);
                }
                //SETTING UP SPECIAL CONDITION  TABLE
                ObservableList specialConditions = FXCollections.observableArrayList(itemsData.get(questBox.getSelectionModel().getSelectedIndex()).SpecialConditionList);
                conditionTypeCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().condition_type));
                conditionTypeCol.setOnEditCommit((TableColumn.CellEditEvent<QuestSpecialCondition, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).condition_type = (event.getNewValue());
                });
                conditionTypeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                conditionTypeCol.setEditable(true);
                param1Col.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().param_1));
                param1Col.setOnEditCommit((TableColumn.CellEditEvent<QuestSpecialCondition, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).param_1 = (event.getNewValue());
                });
                param1Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                param1Col.setEditable(true);

                param2Col.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().param_2));
                param2Col.setOnEditCommit((TableColumn.CellEditEvent<QuestSpecialCondition, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).param_2 = (event.getNewValue());
                });
                param2Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                param2Col.setEditable(true);

                param3Col.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().param_3));
                param3Col.setOnEditCommit((TableColumn.CellEditEvent<QuestSpecialCondition, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).param_3 = (event.getNewValue());
                });
                param3Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                param3Col.setEditable(true);

                param4Col.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().param_4));
                param4Col.setOnEditCommit((TableColumn.CellEditEvent<QuestSpecialCondition, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).param_4 = (event.getNewValue());
                });
                param4Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                param4Col.setEditable(true);

                param5Col.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<Integer>(QItemData.getValue().param_5));
                param5Col.setOnEditCommit((TableColumn.CellEditEvent<QuestSpecialCondition, Integer> event) -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).param_5 = (event.getNewValue());
                });
                param5Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                param5Col.setEditable(true);
                specialConditionTable.getItems().clear();
                specialConditionTable.getItems().addAll(specialConditions);
                specialConditionTable.setEditable(true);

            }
        });

    }
}
