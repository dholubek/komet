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

import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDED_DEFINITION;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDED_DESCRIPTIONS;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDED_FIELDS;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDED_FQN;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDING_DEFINITIONS;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDING_FIELDS;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDING_FQN;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.ADDING_OTHER_NAME;
import static dev.ikm.komet.kview.mvvm.view.pattern.PatternStateTest.PatternState.EMPTY_PATTERN;
import static org.carlfx.axonic.tools.StateMachineCLI.beginConsoleSession;
import org.carlfx.axonic.State;
import org.carlfx.axonic.StateMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PatternBuilder Test")
public class PatternStateTest {

    public enum PatternState implements State {

        // initial state and nothing has been added to a blank pattern
        // the chooser form will look like Add Pattern Definition | Add Description | Add Fields
        EMPTY_PATTERN("EmptyPattern"),

        // one of purpose or meaning has been added but not both
        // the chooser form will look like Add Pattern Definition | Add Description | Add Fields
        ADDING_DEFINITIONS("AddingDefintions"),

        // both purpose or meaning have been added
        // the chooser form will look like Edit Pattern Definition | Add Description | Add Fields
        ADDED_DEFINITION("AddedDefintions"),

        // the Add FQN panel has been opened at least once but has not been filled out and clicked 'done'
        // the chooser form will look like Edit Pattern Definition | Add Description | Add Fields
        ADDING_FQN("AddingFQN"),

        // an FQN has been added (there can be only one) but an other name has not been added yet
        // the chooser form will look like Edit Pattern Definition | Add Description | Add Fields
        ADDED_FQN("AddedFQN"),

        // an FQN has been added (there can be only one); the other name panel has been opened but has not been completed and clicked 'done'
        // ??? should this happen only once?
        // the chooser form will look like Edit Pattern Definition | Add Description | Add Fields

        ADDING_OTHER_NAME("AddingOtherName"),

        // the FQN has been added and at lease one other name has been added but no fields have been added
        ADDED_DESCRIPTIONS("AddedDescriptions"),

        ADDING_FIELDS("AddingFields"),

        ADDED_FIELDS("AddedFields")
        ;

        final String name;

        PatternState(String name){
            this.name = name;
        }
        @Override
        public String getName() {
            return name;
        }
    }

    private static StateMachine createPatternSM() {
        StateMachine patternSM = StateMachine.create(statePattern ->
                statePattern
                        .initial(EMPTY_PATTERN)
                        .t("addDefinitions")
                        .s(ADDING_DEFINITIONS)
                        // all definitions are added when we have a purpose and meaning and the user clicks done
                        .t("addAllDefinitions")
                        .s(ADDED_DEFINITION)
                        .t("addingFqn")
                        .s(ADDING_FQN)
                        .t("addedFqn")
                        .s(ADDED_FQN)
                        .t("addingOtherName")
                        .s(ADDING_OTHER_NAME)
                        .t("otherNameDone")
                        .s(ADDING_OTHER_NAME)
                        // all descriptions are added when we have a fully qualified name, an other name and the user submits both
                        .t("addAllDescriptions")
                        .s(ADDED_DESCRIPTIONS)
                        .t("addingFields")
                        .s(ADDING_FIELDS)
                        // at least one field has been added
                        .t("addedFields")
                        .s(ADDED_FIELDS)
        );
        //TODO when() clauses

        return patternSM;
    }

    @Test
    @DisplayName("Create Pattern State and Transition Test")
    void patternStateTest() {
        //TODO
    }
    
    public static void main(String[] args) {
        // create a pattern state machine
        StateMachine patternStateMachine = createPatternSM();
        
        beginConsoleSession(patternStateMachine);
    }

}
