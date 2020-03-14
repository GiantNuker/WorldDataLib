package io.github.giantnuker.worlddata;

import java.io.File;

/**
 * @author Indigo Amann
 */
public interface WorldIOCalback {
    void onWorldSave(File worldDirectory, File rootDirectory);
    void onWorldLoad(File worldDirectory, File rootDirectory);
}
