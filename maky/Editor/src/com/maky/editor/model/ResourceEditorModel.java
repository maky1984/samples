package com.maky.editor.model;

import java.io.IOException;

import com.maky.app.model.Model;
import com.maky.app.resource.RLinesMap;
import com.maky.app.resource.Resource;
import com.maky.app.resource.ResourceManager;
import com.maky.game.lines.LinesResources;
import com.maky.util.log.Logger;

public class ResourceEditorModel extends Model {

	public void createLevelResource(short[][] level) {
		int maxValue = 0;
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				if (maxValue < level[i][j]) {
					maxValue = level[i][j];
				}
			}
		}
		RLinesMap map = new RLinesMap(LinesResources.MAP_LEVEL1, level, maxValue + 1, "New name", "New description");
		try {
			Resource resource = ResourceManager.getInstance().addAndSaveResource(map, Resource.STORE_TYPE_FILE,
					"level1.map");
			if (resource == null) {
				notifyViewShowMessage(" Resource already exists.");
			} else {
				notifyViewShowMessage(" Resource created.");
			}
		} catch (IOException e) {
			notifyViewShowMessage(e.getMessage());
			Logger.getInstance(this).logException(" Exception from createLevelResource:", e);
		}
	}

}
