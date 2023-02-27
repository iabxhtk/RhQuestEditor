package org.yologn.RhQuestEditor.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.ShortStringConverter;
import org.yologn.RhQuestEditor.QuestStructs.QItemOption;
import org.yologn.RhQuestEditor.QuestStructs.QuestEntry;
import org.yologn.RhQuestEditor.QuestStructs.stLadderReward;
import org.yologn.RhQuestEditor.Util;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LadderTabController implements Initializable {
    @FXML
    private CheckBox ladderQuestCheck;
    @FXML
    private CheckBox mondayCheck;
    @FXML
    private CheckBox tuesdayCheck;
    @FXML
    private CheckBox wednesdayCheck;
    @FXML
    private CheckBox thursdayCheck;
    @FXML
    private CheckBox fridayCheck;
    @FXML
    private CheckBox saturdayCheck;
    @FXML
    private CheckBox sundayCheck;
    @FXML
    private TextField chatMsgField1;
    @FXML
    private TextField chatMsgField2;
    @FXML
    private TextField chatMsgField3;
    @FXML
    private TextField acceptHourField;
    @FXML
    private TextField acceptMinuteField;
    @FXML
    private TextField acceptDurationField;
    @FXML
    private TextField executeDurationField;
    @FXML
    private TextField ladderOptTypeField;
    @FXML
    private TextField ladderOptValueField;
    @FXML
    private Button addLadderRankBtn;
    @FXML
    private Button delLadderRankBtn;
    @FXML
    private Button addLadderOptionBtn;
    @FXML
    private Button removeLadderOptionBtn;
    @FXML
    private TableColumn<stLadderReward, Integer> ladderRankingCol;
    @FXML
    private TableColumn<stLadderReward, Long> ladderCroneCol;
    @FXML
    private TableColumn<stLadderReward, Long> ladderExpCol;
    @FXML
    private TableColumn<stLadderReward, Integer> ladderTypeCol;
    @FXML
    private TableColumn<stLadderReward, Short> ladderQtyCol;
    @FXML
    private TableColumn<stLadderReward, Integer> ladderRewardLvlCol;
    @FXML
    private TableColumn ladderRandomOptCol;
    @FXML
    private TableColumn<QItemOption, Integer> ladderStatCol;
    @FXML
    private TableColumn<QItemOption, Integer> ladderStatValueCol;
    @FXML
    private DatePicker acceptStartDatePicker;
    @FXML
    private DatePicker acceptEndDatePicker;
    @FXML
    private TableView<stLadderReward> ladderRewardTable;
    @FXML
    private TableView<QItemOption> ladderOptionTable;
    @FXML
    private ComboBox<QuestEntry> questBox;

    public void setQuestBox(ComboBox<QuestEntry> questBox) {
        this.questBox = questBox;
    }

    public void setRewardsList(ArrayList<stLadderReward> list) {
        ladderRewardTable.setItems(FXCollections.observableArrayList(list));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addLadderRankBtn.setOnAction(e -> {
            var selectedQuestEntry = questBox.getSelectionModel().getSelectedItem();
            var obj = new stLadderReward();
            obj.ranking = selectedQuestEntry.m_LadderRewards.size() + 1;
            selectedQuestEntry.m_LadderRewards.add(obj);
            selectedQuestEntry.m_reward_num++;
            ladderRewardTable.getItems().add(obj);
            ladderRewardTable.refresh();
        });

        delLadderRankBtn.setOnAction(e -> {
            var selectedQuestEntry = questBox.getSelectionModel().getSelectedItem();
            var index = ladderRewardTable.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                selectedQuestEntry.m_LadderRewards.remove(index);
                selectedQuestEntry.m_reward_num--;
                ladderRewardTable.getItems().remove(index);
                ladderRewardTable.refresh();
            }
        });
        addLadderOptionBtn.setOnAction(e -> {
            if (ladderOptTypeField.getText() != null && !ladderOptTypeField.getText().isEmpty()
                    && ladderOptValueField.getText() != null && !ladderOptValueField.getText().isEmpty()) {

                var option = new QItemOption();
                option.type = Integer.parseInt(ladderOptTypeField.getText());
                option.value = Integer.parseInt(ladderOptValueField.getText());

                var selectedLadderReward = ladderRewardTable.getSelectionModel().getSelectedItem();
                selectedLadderReward.item.itemOptions.add(option);
                selectedLadderReward.item.fix_opt_num++;
                ladderOptionTable.getItems().add(option);
                ladderOptionTable.refresh();
            }

        });
        removeLadderOptionBtn.setOnAction(e -> {
            var index = ladderOptionTable.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                stLadderReward selectedLadderReward = ladderRewardTable.getSelectionModel().getSelectedItem();
                selectedLadderReward.item.itemOptions.remove(index);
                selectedLadderReward.item.fix_opt_num--;
                ladderOptionTable.getItems().remove(index);
                ladderOptionTable.refresh();
            }
        });


        ladderRewardTable.getSelectionModel().selectedItemProperty().addListener((observable1, oldSub, newSub) -> {
            if (newSub != null) {
                ladderOptionTable.setItems(FXCollections.observableArrayList(newSub.item.itemOptions));
            } else {
                ladderOptionTable.getItems().clear();
            }
        });
        ladderRewardTable.setEditable(true);


        ladderStatCol.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<>(optionData.getValue().type));
        ladderStatCol.setOnEditCommit((TableColumn.CellEditEvent<QItemOption, Integer> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).type = (event.getNewValue());
        });
        ladderStatCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ladderStatCol.setEditable(true);

        ladderStatValueCol.setCellValueFactory(optionData -> new ReadOnlyObjectWrapper<>(optionData.getValue().value));
        ladderStatValueCol.setOnEditCommit((TableColumn.CellEditEvent<QItemOption, Integer> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).value = (event.getNewValue());
        });
        ladderStatValueCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ladderStatValueCol.setEditable(true);

        ladderOptionTable.setEditable(true);


        ladderRankingCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<>(QItemData.getValue().ranking));
        ladderRankingCol.setOnEditCommit((TableColumn.CellEditEvent<stLadderReward, Integer> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).ranking = (event.getNewValue());
        });
        ladderRankingCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ladderRankingCol.setEditable(true);

        ladderCroneCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<>(QItemData.getValue().money));
        ladderCroneCol.setOnEditCommit((TableColumn.CellEditEvent<stLadderReward, Long> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).money = (event.getNewValue());
        });
        ladderCroneCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        ladderCroneCol.setEditable(true);

        ladderExpCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<>(QItemData.getValue().exp));
        ladderExpCol.setOnEditCommit((TableColumn.CellEditEvent<stLadderReward, Long> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).exp = (event.getNewValue());
        });
        ladderExpCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        ladderExpCol.setEditable(true);

        ladderTypeCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<>(QItemData.getValue().item.item_id));
        ladderTypeCol.setOnEditCommit((TableColumn.CellEditEvent<stLadderReward, Integer> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).item.item_id = (event.getNewValue());
        });
        ladderTypeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ladderTypeCol.setEditable(true);


        ladderRewardLvlCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<>(QItemData.getValue().item.random_opt_level));
        ladderRewardLvlCol.setOnEditCommit((TableColumn.CellEditEvent<stLadderReward, Integer> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).item.random_opt_level = (event.getNewValue());
        });
        ladderRewardLvlCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ladderRewardLvlCol.setEditable(true);

        ladderQtyCol.setCellValueFactory(QItemData -> new ReadOnlyObjectWrapper<>(QItemData.getValue().item.item_num));
        ladderQtyCol.setOnEditCommit((TableColumn.CellEditEvent<stLadderReward, Short> event) -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).item.item_num = (event.getNewValue());
        });
        ladderQtyCol.setCellFactory(TextFieldTableCell.forTableColumn(new ShortStringConverter()));
        ladderQtyCol.setEditable(true);

        ladderRandomOptCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<stLadderReward, CheckBox>, ObservableValue<CheckBox>>) arg0 -> {
            stLadderReward user = arg0.getValue();
            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().setValue(Util.byteToBool(user.item.no_limit_status));
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {

                    user.item.no_limit_status = Util.boolToByte(new_val);
                }
            });
            return new SimpleObjectProperty<CheckBox>(checkBox);
        });


    }


    public void savePropsToEntry(QuestEntry entry) {
        entry.m_InfoMsg = chatMsgField1.getText();
        entry.m_InfoMsg2 = chatMsgField2.getText();
        entry.m_InfoMsg3 = chatMsgField3.getText();
        entry.m_bLadderQuest = Util.boolToByte(ladderQuestCheck.isSelected());

        byte[] byteBuff = entry.m_AcceptWeek.getBytes();
        byteBuff[0] = Util.boolToByte(mondayCheck.isSelected());
        byteBuff[1] = Util.boolToByte(tuesdayCheck.isSelected());
        byteBuff[2] = Util.boolToByte(wednesdayCheck.isSelected());
        byteBuff[3] = Util.boolToByte(thursdayCheck.isSelected());
        byteBuff[4] = Util.boolToByte(fridayCheck.isSelected());
        byteBuff[5] = Util.boolToByte(saturdayCheck.isSelected());
        byteBuff[6] = Util.boolToByte(sundayCheck.isSelected());

        entry.m_AcceptWeek = new String(byteBuff, StandardCharsets.UTF_8);
        entry.m_AcceptStartDate.setDate(acceptStartDatePicker.getValue());
        entry.m_AcceptEndDate.setDate(acceptEndDatePicker.getValue());
        entry.m_AcceptHour = Integer.parseInt(acceptHourField.getText());
        entry.m_AcceptMinute = Integer.parseInt(acceptMinuteField.getText());
        entry.m_AcceptDurationTime = Integer.parseInt(acceptDurationField.getText());
        entry.m_ExecuteDurationTime = Integer.parseInt(executeDurationField.getText());
    }

    public void setPropsFromEntry(QuestEntry entry) {
        chatMsgField1.setText(entry.m_InfoMsg);
        chatMsgField2.setText(entry.m_InfoMsg2);
        chatMsgField3.setText(entry.m_InfoMsg3);

        ladderQuestCheck.setSelected(Util.byteToBool(entry.m_bLadderQuest));
        mondayCheck.setSelected(Util.byteToBool((byte) entry.m_AcceptWeek.charAt(0)));
        tuesdayCheck.setSelected(Util.byteToBool((byte) entry.m_AcceptWeek.charAt(1)));
        wednesdayCheck.setSelected(Util.byteToBool((byte) entry.m_AcceptWeek.charAt(2)));
        thursdayCheck.setSelected(Util.byteToBool((byte) entry.m_AcceptWeek.charAt(3)));
        fridayCheck.setSelected(Util.byteToBool((byte) entry.m_AcceptWeek.charAt(4)));
        saturdayCheck.setSelected(Util.byteToBool((byte) entry.m_AcceptWeek.charAt(5)));
        sundayCheck.setSelected(Util.byteToBool((byte) entry.m_AcceptWeek.charAt(6)));

        try {
            acceptStartDatePicker.setValue(LocalDate.of(entry.m_AcceptStartDate.wYear, entry.m_AcceptStartDate.wMonth + 1, entry.m_AcceptStartDate.wDay + 1));
            acceptEndDatePicker.setValue(LocalDate.of(entry.m_AcceptStartDate.wYear, entry.m_AcceptStartDate.wMonth + 1, entry.m_AcceptStartDate.wDay + 1));
        } catch (DateTimeException e) {
            e.printStackTrace();
        }

        acceptHourField.setText(Integer.toString(entry.m_AcceptHour));
        acceptMinuteField.setText(Integer.toString(entry.m_AcceptMinute));
        acceptDurationField.setText(Integer.toString(entry.m_AcceptDurationTime));
        executeDurationField.setText(Integer.toString(entry.m_ExecuteDurationTime));
    }
}
