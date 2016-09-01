package de.jablab.sebschlicht.android.kits;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class EncodeMobileVideoRunnable implements Runnable {

    protected File fPlain;

    protected File fMobile;

    public EncodeMobileVideoRunnable(
            File fPlain,
            File fMobile) {
        this.fPlain = fPlain;
        this.fMobile = fMobile;
    }

    @Override
    public void run() {
        List<String> params = new LinkedList<String>();
        params.add("avconv");
        params.add("-i");
        params.add(fPlain.getAbsolutePath());

        // video
        params.add("-c:v");
        params.add("libx264");
        params.add("-b:v");
        params.add("500k");
        params.add("-vprofile");
        params.add("baseline");

        // audio
        params.add("-c:a");
        params.add("aac");
        params.add("-b:a");
        params.add("128k");
        params.add("-strict");
        params.add("experimental");

        params.add(fMobile.getAbsolutePath());

        ProcessBuilder procBuilder = new ProcessBuilder(params);
        procBuilder.inheritIO();
        try {
            Process process = procBuilder.start();
            System.out.println("encoding " + fPlain.getAbsolutePath());
            int result = process.waitFor();
            System.out.println("encoding of " + fPlain.getAbsolutePath()
                    + " finished: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
