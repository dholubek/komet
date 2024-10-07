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
package dev.ikm.komet.kview.state.pattern;

import static dev.ikm.komet.kview.state.pattern.PatternState.ADDED_DEFINITIONS;
import static dev.ikm.komet.kview.state.pattern.PatternState.ADDED_DESCRIPTIONS;
import static dev.ikm.komet.kview.state.pattern.PatternState.ADDED_FIELDS;
import static dev.ikm.komet.kview.state.pattern.PatternState.ADDED_FQN;
import static dev.ikm.komet.kview.state.pattern.PatternState.ADDING_DEFINITIONS;
import static dev.ikm.komet.kview.state.pattern.PatternState.ADDING_FIELDS;
import static dev.ikm.komet.kview.state.pattern.PatternState.ADDING_FQN;
import static dev.ikm.komet.kview.state.pattern.PatternState.ADDING_OTHER_NAME;
import static dev.ikm.komet.kview.state.pattern.PatternState.EMPTY_PATTERN;
import org.carlfx.axonic.StateMachine;

public class PatternStateCreator {

    public static StateMachine createPatternStateMachine() {
        StateMachine patternSM = StateMachine.create(statePattern ->
                statePattern
                        .initial(EMPTY_PATTERN)
                        .t("addingDefinitions")
                        .s(ADDING_DEFINITIONS)
                        // all definitions are added when we have a purpose and meaning and the user clicks done
                        .t("addAllDefinitions")
                        // after adding both definitions, we need to get back to the pattern form chooser screen
                        .s(ADDED_DEFINITIONS)
                        .t("addingFqn")
                        .s(ADDING_FQN)
                        .t("addedFqn")
                        .s(ADDED_FQN)
                        .t("addingOtherName")
                        .s(ADDING_OTHER_NAME)
                        .t("addedOtherName")
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
        return patternSM;
    }
}
