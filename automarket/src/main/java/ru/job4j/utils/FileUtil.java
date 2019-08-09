package ru.job4j.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.nio.file.Paths;

public class FileUtil {

    private static final String PROFILE_PHOTO_DIR;

    private static final String CAR_PHOTO_DIR;

    private static final String DEFAULT_PHOTO_LOCATION = Paths.get("automarket", "resources", "images", "nophoto.png").toString();

    private static final Logger LOG = LogManager.getLogger(FileUtil.class.getName());

    static {
        PROFILE_PHOTO_DIR = Paths.get(
                "automarket", "images", "profile"
        ).toString();
        CAR_PHOTO_DIR = Paths.get(
                "automarket", "images", "car"
        ).toString();
        File ppd = new File(PROFILE_PHOTO_DIR);
        ppd.mkdirs();
        File cfd = new File(CAR_PHOTO_DIR);
        cfd.mkdirs();
    }

    public static String saveProfilePhoto(InputStream in, String fileName) {
        return saveFile(in, Paths.get(PROFILE_PHOTO_DIR, fileName).toString());
    }

    public static String saveCarPhoto(InputStream in, String fileName) {
       return saveFile(in, Paths.get(CAR_PHOTO_DIR, fileName).toString());
    }

    private static String saveFile(InputStream in, String destination) {
        LOG.debug("###############################################");
        LOG.debug("Saving file ...");

        if (!destination.contains(".")) {
            LOG.debug("Object references to nophoto.png");
            return null;
        }

        LOG.debug("File destination: {}", destination);
        String result = null;
        try {
            byte[] buffer = new byte[4096];
            LOG.debug("Writing file ...");
            try (BufferedInputStream bis = new BufferedInputStream(in);
                 OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
                while (bis.read(buffer) != -1) {
                    out.write(buffer);
                }
            }
            in.close();
            LOG.debug("Writing complete");
            result = destination;
        } catch (Exception e) {
            LOG.debug("Fail to write file. Cause: ", e.getMessage());
        }
        LOG.debug("###############################################");
        return destination;
    }

    public static void removeFile(String dest) {
        if (dest != null) {
            File file = new File(dest);
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
