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
package dev.ikm.komet.framework;

import java.util.ArrayList;

import dev.ikm.tinkar.entity.SemanticEntityVersion;

public class EditedConceptTracker {

	private static ArrayList<SemanticEntityVersion> edits = new ArrayList<>();

	public static ArrayList<SemanticEntityVersion> getEdits() {
		return edits;
	}

	public static void addEdit(SemanticEntityVersion edit) {
		edits.removeIf(ex_edit -> ex_edit.referencedComponentNid() == edit.referencedComponentNid());
		edits.add(edit);
	}

	public static void removeEdits() {
		edits = new ArrayList<>();
	}

}
