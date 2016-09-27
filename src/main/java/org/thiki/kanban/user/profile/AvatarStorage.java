package org.thiki.kanban.user.profile;

import org.thiki.kanban.foundation.common.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by xubt on 26/09/2016.
 */
public class AvatarStorage {
    public static final String FILES_LOCATION = "files/avatars/";

    public boolean store(String userName, File avatar) throws IOException {
        File directory = new File(FILES_LOCATION);
        if (!directory.exists()) {
            FileUtil.forceMkdir(directory);
        }
        return false;
    }
}
