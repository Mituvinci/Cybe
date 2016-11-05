package app.wistem.com.cybe;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mitu on 11/3/16.
 */

public class FileClass {

    private static String[] items;
    private static String[] items1;

    public static boolean isExternalStorageAvailable() {

        String state = Environment.getExternalStorageState();
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but
            // all we need
            // to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }

        if (mExternalStorageAvailable == true
                && mExternalStorageWriteable == true) {
            return true;
        } else {
            return false;
        }
    }


        public static boolean getMusicStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MUSIC), albumName);
        if (!file.mkdirs()) {
           // Log.e(LOG_TAG, "Directory not created");
            return false;
        }
        return true;
    }


    public static boolean isSdCardPresent (){

        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (isSDPresent)
            return true;
        else
        return false;
    }

    public static String[] AllAudioOfMemory(){


            final ArrayList<File> songs = getAudioList(Environment
                    .getExternalStorageDirectory());

        Log.d("audioSize", "AllAudioOfMemory: "+Environment.getExternalStorageDirectory());


            items = new String[songs.size()];

            for (int i = 0; i < songs.size(); i++) {
                items[i] = songs.get(i).getName().toString().replace(".mp3", "").replace(".amr", "");
                Log.d("song", items[i]);

        }
        return items;
    }

    public static String[] AllVideOfMemory(){

        final ArrayList<File> video = getVideoList(Environment
                .getExternalStorageDirectory());

        items1 = new String[video.size()];

        for(int i=0;i<video.size();i++){
            items1[i] = video.get(i).getName().toString().replace(".mp4","").replace(".mng","").replace(".avi","").replace(".mov","").replace(".3gp","");
            Log.d("song",items1[i]);
        }
        return  items1;
    }

    public static ArrayList<File> getAudioList(File root) {
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


    public static ArrayList<File> getVideoList(File root) {
        ArrayList<File> al = new ArrayList<>();
        File[] files = root.listFiles();
        if (root.exists()&& files != null) {

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() && !files[i].isHidden()) {
                    al.addAll(getAudioList(files[i]));
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
