/*
 * Copyright © 2015 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ikm.komet.kview.mvvm.view.pattern;

import static dev.ikm.komet.kview.events.pattern.PropertyPanelEvent.CLOSE_PANEL;
import static dev.ikm.komet.kview.events.pattern.ShowPatternFormInBumpOutEvent.DESCRIPTION_NAME;
import static dev.ikm.komet.kview.events.pattern.ShowPatternFormInBumpOutEvent.SHOW_ADD_DEFINITION;
import static dev.ikm.komet.kview.events.pattern.ShowPatternFormInBumpOutEvent.SHOW_EDIT_FIELDS;
import static dev.ikm.komet.kview.mvvm.viewmodel.PatternPropertiesViewModel.STATE_MACHINE;
import static dev.ikm.komet.kview.mvvm.viewmodel.PatternViewModel.PATTERN_TOPIC;
import dev.ikm.komet.framework.events.EvtBusFactory;
import dev.ikm.komet.kview.events.pattern.PropertyPanelEvent;
import dev.ikm.komet.kview.events.pattern.ShowPatternFormInBumpOutEvent;
import dev.ikm.komet.kview.mvvm.viewmodel.PatternPropertiesViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.carlfx.axonic.StateMachine;
import org.carlfx.cognitive.loader.InjectViewModel;

import java.util.UUID;

public class PatternFormChooserController {

    @FXML
    private Button addEditDefinitionButton;

    @FXML
    private Button addEditDescription;

    @FXML
    private Button addEditFields;

    @FXML
    private Button closePropertiesButton;


    @InjectViewModel
    private PatternPropertiesViewModel patternPropertiesViewModel;

    public PatternFormChooserController() {}

    @FXML
    private void initialize() {}

    @FXML
    private void showDefinitionForm(ActionEvent actionEvent) {
        actionEvent.consume();
        EvtBusFactory.getDefaultEvtBus().publish(getPatternTopic(), new ShowPatternFormInBumpOutEvent(actionEvent.getSource(), SHOW_ADD_DEFINITION));
    }

    @FXML
    private void showDescriptionForm(ActionEvent actionEvent) {
        actionEvent.consume();
        ObjectProperty<StateMachine> stateMachineObjectProperty = patternPropertiesViewModel.getProperty(STATE_MACHINE);
        // definition section is done so make the addAllDefinitions transition
        stateMachineObjectProperty.get().t("addingDefinitions");
        EvtBusFactory.getDefaultEvtBus().publish(getPatternTopic(), new ShowPatternFormInBumpOutEvent(actionEvent.getSource(), DESCRIPTION_NAME));
    }

    @FXML
    private void showFieldsForm(ActionEvent actionEvent) {
        actionEvent.consume();
        EvtBusFactory.getDefaultEvtBus().publish(getPatternTopic(), new ShowPatternFormInBumpOutEvent(actionEvent.getSource(), SHOW_EDIT_FIELDS));
    }

    @FXML
    private void closePropertiesPanel(ActionEvent actionEvent) {
        actionEvent.consume();
        EvtBusFactory.getDefaultEvtBus().publish(getPatternTopic(), new PropertyPanelEvent(actionEvent.getSource(), CLOSE_PANEL));
    }

    private UUID getPatternTopic() {
        return patternPropertiesViewModel.getPropertyValue(PATTERN_TOPIC);
    }

    public void setDefinitionButtonToEdit() {
        this.addEditDefinitionButton.setText("EDIT PATTERN DEFINITIONS");
    }
}
