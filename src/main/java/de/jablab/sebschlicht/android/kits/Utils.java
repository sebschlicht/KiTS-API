package de.jablab.sebschlicht.android.kits;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import de.jablab.sebschlicht.android.AndroidTools;

public class Utils {

    private static final int NUM_THREADS = 4;

    private static final File DIR_RES_RAW =
            new File("/develop/workspace/android/KinderserienRaten/res/raw");

    private static final File DIR_RES_IMG =
            new File("/develop/workspace/android/KinderserienRaten/res");

    private static final File DIR_RES_IMG_HDPI = new File(
            "/develop/workspace/android/KinderserienRaten/res/drawable-hdpi");

    private static final File DIR_SRC_IMG_UI =
            new File("/home/sebschlicht/Pictures/kits/ui");

    private static final File DIR_SRC_IMG_SERIES =
            new File("/home/sebschlicht/Pictures/kits/series");

    private static final File DIR_SRC_MUSIC_MOBILE =
            new File("/home/sebschlicht/Music/kits/mobile");

    private static final File DIR_SRC_VIDEOS_SERVER =
            new File("/home/sebschlicht/Videos/kits/server/");

    private static final File DIR_SRC_VIDEOS_MOBILE =
            new File("/home/sebschlicht/Videos/kits/mobile/");

    private static void
        copyAndroidFiles(File dirSource, File dirDestination, String prefix)
                throws IOException {
        if (!dirSource.isDirectory()) {
            throw new IllegalArgumentException("source \""
                    + dirSource.getAbsolutePath() + "\" is not a directory!");
        }
        if (!dirDestination.exists() && !dirDestination.mkdirs()) {
            throw new IllegalArgumentException(
                    "failed to create destination directory at \""
                            + dirDestination + "\"!");
        }
        System.out.println("copying " + dirSource.getAbsolutePath() + " to "
                + dirDestination.getAbsolutePath());
        for (File file : dirSource.listFiles()) {
            String fileName = FilenameUtils.removeExtension(file.getName());

            if (file.isFile()) {
                String resourceName = ((prefix == null) ? "" : prefix)
                        + AndroidTools.toValidResourceName(fileName) + "."
                        + FilenameUtils.getExtension(file.getName());
                FileUtils.copyFile(file,
                        new File(dirDestination, resourceName));
            } else if (file.isDirectory()) {
                FileUtils.copyDirectory(file,
                        new File(dirDestination, fileName));
            }
        }
    }

    private static void encodeVideoFiles() {
        ExecutorService execService = Executors.newFixedThreadPool(NUM_THREADS);
        for (File fPlain : DIR_SRC_VIDEOS_SERVER.listFiles()) {
            if (!fPlain.isFile()) {
                continue;
            }
            String fileName = FilenameUtils.removeExtension(fPlain.getName());
            File fMobile = new File(DIR_SRC_VIDEOS_MOBILE, fileName + ".mp4");
            if (fMobile.exists()) {
                continue;
            }
            execService.execute(new EncodeMobileVideoRunnable(fPlain, fMobile));
        }
        execService.shutdown();
        try {
            execService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException(
                    "video encoding took longer than the expected 60 seconds\n"
                            + e.getMessage());
        }
    }

    private static void copyImageFiles() throws IOException {
        //        copyAndroidFiles(DIR_SRC_IMG_SERIES, DIR_RES_IMG_HDPI, null);
        copyAndroidFiles(DIR_SRC_IMG_UI, DIR_RES_IMG, null);
    }

    private static void copyAudioFiles() throws IOException {
        copyAndroidFiles(DIR_SRC_MUSIC_MOBILE, DIR_RES_RAW, "aud_");
    }

    private static void copyVideoFiles() throws IOException {
        copyAndroidFiles(DIR_SRC_VIDEOS_MOBILE, DIR_RES_RAW, "vid_");
    }

    public static void main(String[] args) {
        try {
            copyImageFiles();
            //copyAudioFiles();
            encodeVideoFiles();
            //copyVideoFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done.");
    }
}
