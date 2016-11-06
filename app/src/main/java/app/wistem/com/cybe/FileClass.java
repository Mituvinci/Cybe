package app.wistem.com.cybe;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitu on 11/3/16.
 */

public class FileClass {

    private static String[] itemsAudio;
    private static String[] itemsVideo;
    private static String[] itemsImage;


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

    public static String[] AllVideOfMemory() {

        final ArrayList<File> video = getVideoList(Environment
                .getExternalStorageDirectory());

        itemsVideo = new String[video.size()];

        for (int i = 0; i < video.size(); i++) {
            itemsVideo[i] = video.get(i).getName().toString().replace(".mp4", "").replace(".mng", "").replace(".avi", "").replace(".mov", "").replace(".3gp", "");
        }
        return itemsVideo;
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



    /*public static String[] AllImagesOfMemory() {


        final ArrayList<File> songs = getImageList(Environment
                .getExternalStorageDirectory());

        itemsImage = new String[songs.size()];

        for (int i = 0; i < songs.size(); i++) {
            itemsImage[i] = songs.get(i).getName().toString();

        }
        return itemsImage;
    }*/
    public static List<File> AllImagesOfMemory() {


        final List<File> image = getImageList(Environment
                .getExternalStorageDirectory());


        return image;
    }

    private static List<File> getImageList(File root) {
        List<File> al = new ArrayList<>();
        File[] files = root.listFiles();

        // Log.d("audioSizeroot", "AllAudioOfMemory: "+files.length);

        if (root.exists() && files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() && !files[i].isHidden()) {
                    al.addAll(getImageList(files[i]));
                } else if (files[i].getName().endsWith(".jpeg")
                        || files[i].getName().endsWith(".img")
                        || files[i].getName().endsWith(".png")
                        || files[i].getName().endsWith(".bmp")
                        ||files[i].getName().endsWith(".jpg")
                        ) {
                    al.add(files[i].getAbsoluteFile());

                    Log.d("image1", "getImageList: "+root.getAbsolutePath());
                 //   Log.d("image2", "getImageList: "+al.get(i));

                   /* Log.d("image1", "getImageList: "+root.getAbsolutePath());
                    Log.d("image2", "getImageList: "+root.getAbsolutePath()+""+al.get(i));
                    Log.d("image3", "getImageList: "+files[i].getAbsolutePath());*/
                }
            }
        }
        return al;
    }
}
