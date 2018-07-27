package Utility;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileManager {

    public final static String FILE_PATH = "/sdcard/android/data/realrealsound";
    public static final String directory = "/sdcard/android/data/realrealsoundConfig";
    public static final String jsonDirectory = "/sdcard/android/data/realrealsoundJson";
    public static final String downloadDirectory = "/sdcard/KakaoTalkDownload";

    //파일명 변경
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

    //파일 경로 생성
    public static void fileInit(String filePath){
        File file = new File(filePath);

        //파일 경로가 있으면 있는거고 없으면 새로 만들기
        if (!(file.exists()))
        {
            file.mkdirs();
        }

    }

    //텍스트파일 생성
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

    public static ArrayList<Note> convertJsonToArrayList(JSONArray jr){

        ArrayList<Note> ar = new ArrayList();

        try{
            for(int n = 0 ; n < jr.length() ; n++)
            {
                JSONObject jb = jr.getJSONObject(n);
                //Log.e("note = ", jb.toString());
                ar.add(new Note(jb.getInt("index"), jb.getLong("milisecond"), jb.getInt("note"), jb.getLong("idle")));

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return ar;
    }



}
