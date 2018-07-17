package file;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {

    public final static String FILE_PATH = "/sdcard/android/data/realrealsound";
    public static final String directory = "/sdcard/android/data/realrealsoundConfig";

    public static void rename(String oldName, String newName){
        Log.e("파일매니저", oldName + " / " +newName);
        File old = new File(FILE_PATH + "/" +oldName);
        File name = new File(FILE_PATH + "/" + newName);
        if(newName.equals(".mp3"))
        {
            newName = System.currentTimeMillis() + ".mp3";
            name = new File(FILE_PATH + "/" + newName);
        }

        if(old.exists())
        {
            old.renameTo(name);
        }
    }

    public static void fileInit(String filePath){
        File file = new File(filePath);

        //파일 경로가 있으면 있는거고 없으면 새로 만들기
        if (!(file.exists()))
        {
            file.mkdirs();
        }

    }

    public static void makeTextFile(String directory, String fileName, String value){
        File saveFile = new File(directory + "/" + fileName);

        try{
            FileOutputStream fos = new FileOutputStream(saveFile);
            fos.write(value.getBytes());
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean DeleteConfigFile(){
        File f = new File(directory + "/config.txt");
        try{
            f.delete();
            return true;
        }
        catch (Exception e)
        {
            System.out.println("파일 삭제 에러");
            return false;
        }
    }

    public static void makeTextFile(String directory, String fileName, int value){
        File saveFile = new File(directory + "/" + fileName);

        try{
            FileOutputStream fos = new FileOutputStream(saveFile);
            fos.write((value + "").getBytes());
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String readTextFile(String directory, String fileName)
    {
        String fullpath = directory + "/" + fileName;
        String content = "";
        try
        {
            FileInputStream fis = new FileInputStream(fullpath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            content =  br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            return content;
        }
    }

}
