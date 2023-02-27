package org.yologn.RhQuestEditor.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.yologn.RhQuestEditor.QuestStructs.QuestEntry;
import org.yologn.RhQuestEditor.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class EtcTabController implements Initializable {
    public CheckBox notifyCheck;
    public TextField notify1Field;
    public TextField notify2Field;


    public void savePropsToEntry(QuestEntry entry)
    {
        entry.m_bUseLevelNotyMsg = Util.boolToByte(notifyCheck.isSelected());
        if (notifyCheck.isSelected()) {
            entry.m_LevelNoti = notify1Field.getText();
            entry.m_LevelNoti2 = notify2Field.getText();
        }
    }
    public void setPropsFromEntry(QuestEntry entry)
    {
        notifyCheck.setSelected(Util.byteToBool(entry.m_bUseLevelNotyMsg));
        if (entry.m_bUseLevelNotyMsg > 0) {
            notify1Field.setDisable(false);
            notify1Field.setText(entry.m_LevelNoti);
            notify2Field.setDisable(false);
            notify2Field.setText(entry.m_LevelNoti2);
        } else {
            notify1Field.setDisable(true);
            notify2Field.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notifyCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                notify1Field.setDisable(false);
                notify2Field.setDisable(false);
            } else {
                notify1Field.setDisable(true);
                notify2Field.setDisable(true);
            }
        });
    }
}
