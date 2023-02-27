package org.yologn.RhQuestEditor.AchieveEditor;

import org.yologn.RhQuestEditor.QuestIO;
import org.yologn.RhQuestEditor.TypesMap;
import javafx.animation.FadeTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AchieveWindow implements Initializable {

    private ObservableList<AchieveBin> itemsData;
    public TableColumn<AchieveBin,Integer> idCol;
    public TableColumn<AchieveBin,String> nameCol;
    public TableView<AchieveBin> achieveTable;

    public TextField idField;
    public TextField koreanNameField;
    public TextField uniqueNameField;
    public TextField achieveType;
    public TextField useOptionCountField;
    public TextField iconNameField;
    public ComboBox<String> opt1Box;
    public ComboBox<String> opt2Box;
    public ComboBox<String> opt3Box;
    public ComboBox<String> opt4Box;
    public ComboBox<String> opt5Box;

    public TextField opt1ValField;
    public TextField opt2ValField;
    public TextField opt3ValField;
    public TextField opt4ValField;
    public TextField opt5ValField;

    public Button saveEditsBtn;
    public Button saveFileBtn;
    public Button cloneTitleBtn;

    public Label logLabel;

    public AchieveWindow()
    {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemsData = FXCollections.observableArrayList();
        try {
            loadEntries();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setupInitialValues();
        setupButtons();
    }
    private void loadEntries() throws IOException {
        String path = "achieve.bin";
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        QuestIO reader = new QuestIO(in);

        while(in.available() > 0)
        {
            AchieveBin entry = new AchieveBin();
            entry.load(reader);
            itemsData.add(entry);
        }
        in.close();
    }
    private void writeEntries() throws IOException {
        String path = "achieve";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String string  = dateFormat.format(new Date());
        Files.copy(Paths.get(path + ".bin"),Paths.get(path +"_"+ string + ".bin") , StandardCopyOption.REPLACE_EXISTING);
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path + ".bin")));
        QuestIO writer = new QuestIO(out);
        for (AchieveBin entry : itemsData) {
            entry.write(writer);
        }
        out.close();
    }
    private void setupButtons()
    {
        cloneTitleBtn.setOnAction(e -> {
            AchieveBin selected = achieveTable.getSelectionModel().getSelectedItem();
            if(selected != null)
            {
                AchieveBin newItem = new AchieveBin(selected);
                newItem.id = itemsData.get(itemsData.size()-1).id + 1;
                itemsData.add(newItem);
                achieveTable.getSelectionModel().selectLast();
                playFadeMessage("Title cloned ..", 2000);
            }
        });
        saveFileBtn.setOnAction(e -> {
            try {
                writeEntries();
                playFadeMessage("File saved..", 2000);
            } catch (IOException e1) {
                e1.printStackTrace();
                playFadeMessage("Error saving f ..", 2000);
            }
        });
        saveEditsBtn.setOnAction(e -> {
            AchieveBin selected = achieveTable.getSelectionModel().getSelectedItem();
            if(selected != null)
            {
                selected.id = Integer.parseInt(idField.getText());
                selected.koreanName = koreanNameField.getText();
                selected.titleName = uniqueNameField.getText();
                selected.IconName = iconNameField.getText();
                selected.useOptionCount = Integer.parseInt(useOptionCountField.getText());
                selected.AchieveType = Integer.parseInt(achieveType.getText());

                selected.ItemOptionValue_1 = Integer.parseInt(opt1ValField.getText());
                selected.ItemOptionValue_2 = Integer.parseInt(opt2ValField.getText());
                selected.ItemOptionValue_3 = Integer.parseInt(opt3ValField.getText());
                selected.ItemOptionValue_4 = Integer.parseInt(opt4ValField.getText());
                selected.ItemOptionValue_5 = Integer.parseInt(opt5ValField.getText());
                if(opt1Box.getSelectionModel().getSelectedItem() != null)
                {
                    selected.ItemOptionType1 = (int)TypesMap.getAttributeTypesMap().get(opt1Box.getSelectionModel().getSelectedItem());
                }
                else
                    selected.ItemOptionType1 = 0;

                if(opt2Box.getSelectionModel().getSelectedItem() != null)
                {
                    selected.ItemOptionType2 = (int)TypesMap.getAttributeTypesMap().get(opt2Box.getSelectionModel().getSelectedItem());
                }
                else
                    selected.ItemOptionType2 = 0;

                if(opt3Box.getSelectionModel().getSelectedItem() != null)
                {
                    selected.ItemOptionType3 = (int)TypesMap.getAttributeTypesMap().get(opt3Box.getSelectionModel().getSelectedItem());
                }
                else
                    selected.ItemOptionType3 = 0;

                if(opt4Box.getSelectionModel().getSelectedItem() != null)
                {
                    selected.ItemOptionType4 = (int)TypesMap.getAttributeTypesMap().get(opt4Box.getSelectionModel().getSelectedItem());
                }
                else
                    selected.ItemOptionType4 = 0;

                if(opt5Box.getSelectionModel().getSelectedItem() != null)
                {
                    selected.ItemOptionType5 = (int)TypesMap.getAttributeTypesMap().get(opt5Box.getSelectionModel().getSelectedItem());
                }
                else
                    selected.ItemOptionType5 = 0;
                playFadeMessage("Edits saved..", 2000);
            }

        });
    }
    private void setupInitialValues()
    {

        nameCol.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().koreanName));
        idCol.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<Integer>(itemData.getValue().id));
        achieveTable.setItems(itemsData);
        ObservableList<String> boxAttrList = FXCollections.observableArrayList(new ArrayList<String>(TypesMap.getAttributeTypesMap().keySet()));
        opt1Box.setItems(boxAttrList);
        opt2Box.setItems(boxAttrList);
        opt3Box.setItems(boxAttrList);
        opt4Box.setItems(boxAttrList);
        opt5Box.setItems(boxAttrList);
        // TABLE LISTENER TO POPULATE TEXTFIELD ON SELECTION
        achieveTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null)
            {
                idField.setText(Integer.toString(newValue.id));
                koreanNameField.setText(newValue.koreanName);
                uniqueNameField.setText(newValue.titleName);
                iconNameField.setText(newValue.IconName);
                achieveType.setText(Integer.toString(newValue.AchieveType));
                useOptionCountField.setText(Integer.toString(newValue.useOptionCount));

                if(newValue.ItemOptionType1 == 0)
                {
                    opt1Box.getSelectionModel().select(null);
                }
                else
                    opt1Box.getSelectionModel().select(newValue.ItemOptionType1);
                if(newValue.ItemOptionType2 == 0)
                {
                    opt2Box.getSelectionModel().select(null);
                }
                else
                    opt2Box.getSelectionModel().select(newValue.ItemOptionType2);
                if(newValue.ItemOptionType3 == 0)
                {
                    opt3Box.getSelectionModel().select(null);
                }
                else
                    opt3Box.getSelectionModel().select(newValue.ItemOptionType3);
                if(newValue.ItemOptionType4 == 0)
                {
                    opt4Box.getSelectionModel().select(null);
                }
                else
                    opt4Box.getSelectionModel().select(newValue.ItemOptionType4);
                if(newValue.ItemOptionType5 == 0)
                {
                    opt5Box.getSelectionModel().select(null);
                }
                else
                    opt5Box.getSelectionModel().select(newValue.ItemOptionType5);
                opt1ValField.setText(Integer.toString(newValue.ItemOptionValue_1));
                opt2ValField.setText(Integer.toString(newValue.ItemOptionValue_2));
                opt3ValField.setText(Integer.toString(newValue.ItemOptionValue_3));
                opt4ValField.setText(Integer.toString(newValue.ItemOptionValue_4));
                opt5ValField.setText(Integer.toString(newValue.ItemOptionValue_5));
            }
        });
    }
    private void playFadeMessage(String message, int duration)
    {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), logLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        logLabel.setText(message);

        ft.play();
    }
}
