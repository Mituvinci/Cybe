package app.wistem.com.cybe;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mitu on 11/3/16.
 */

public class FileClass {

    private static String[] itemsAudio;
    private static String[] itemsVideo;


    public static boolean isSdCardPresent() {

        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (isSDPresent)
            return true;
        else
            return false;
    }

    public static String[] AllAudioOfMemory() {


        final ArrayList<File> songs = getAudioList(Environment
                .getExternalStorageDirectory());

        itemsAudio = new String[songs.size()];

        for (int i = 0; i < songs.size(); i++) {
            itemsAudio[i] = songs.get(i).getName().toString().replace(".mp3", "").replace(".amr", "");

        }
        return itemsAudio;
    }

    public static String[] AllVideOfMemory() {

        final ArrayList<File> video = getVideoList(Environment
                .getExternalStorageDirectory());

        itemsVideo = new String[video.size()];

        for (int i = 0; i < video.size(); i++) {
            itemsVideo[i] = video.get(i).getName().toString().replace(".mp4", "").replace(".mng", "").replace(".avi", "").replace(".mov", "").replace(".3gp", "");
        }
        return itemsVideo;
    }

    private static ArrayList<File> getAudioList(File root) {
        ArrayList<File> al = new ArrayList<>();
        File[] files = root.listFiles();

        // Log.d("audioSizeroot", "AllAudioOfMemory: "+files.length);

        if (root.exists() && files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() && !files[i].isHidden()) {
                    al.addAll(getAudioList(files[i]));
                } else if (files[i].getName().endsWith(".mp3")
                        || files[i].getName().endsWith(".amr")) {
                    al.add(files[i]);
                }
            }
        }
        return al;
    }


    private static ArrayList<File> getVideoList(File root) {
        ArrayList<File> al = new ArrayList<>();
        File[] files = root.listFiles();
        if (root.exists() && files != null) {

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() && !files[i].isHidden()) {
                    al.addAll(getVideoList(files[i]));
                } else if (files[i].getName().endsWith(".mp4")
                        || files[i].getName().endsWith(".3gp")
                        || files[i].getName().endsWith(".mng")
                        || files[i].getName().endsWith(".avi")
                        || files[i].getName().endsWith(".mov")) {
                    al.add(files[i]);
                }
            }
        }
        return al;
    }
}
