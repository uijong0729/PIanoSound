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
    public static final short SUBMIT = 2; //승인
    public static final short RESERVE = 1; //보류
    public static final short REJECT = 0; //거절

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


    public static String textToJson(String str)
    {
        //도 레 미 파 파    솔
        // "Note" : [{index: , milisecond: , note: , idle: , kr: } ]

        char strArray[] = str.toCharArray();
        StringBuffer result = new StringBuffer("[");
        int count = 0;
        Long milisecond = 0L;
        Long idle = 0L;
        short isSubmit = 0; //거절, 보류, 제출

        for(int i = 0 ; i < strArray.length ; i++)
        {
            int doremi = transferReverse(strArray[i]);

            if(doremi == -1)
            {
                if(strArray[i] == ' ')
                {
                    milisecond += 500;
                }
                if(strArray[i] == '.'){
                    idle += 500;
                }
            }
            else
            {
                if(i == strArray.length-1)
                {
                    result.append("{index:" + count++);
                    result.append(", milisecond:" + milisecond);
                    result.append(", note:" + doremi);
                    result.append(", kr: " + strArray[i]);
                    result.append(", idle: " + idle + "}");
                    milisecond = 0L;
                    idle = 0L;
                }
                else
                {
                    if(strArray[i+1] == '#' || strArray[i+1] == '!')
                    {
                        switch (strArray[i+1])
                        {
                            case '#': doremi += 10; break;
                            case '!': doremi += 7; break;
                            default:
                        }

                        result.append("{index:" + count++);
                        result.append(", milisecond:" + milisecond);
                        result.append(", note:" + doremi);
                        result.append(", kr: " + strArray[i]);
                        result.append(", idle: " + idle + "}, ");
                        milisecond = 0L;
                        idle = 0L;

                        i++;    //한칸 건너뜀
                    }
                    else
                    {
                        if(i != 0)
                        {
                            result.append("{index:" + count++);
                            result.append(", milisecond:" + milisecond);
                            result.append(", note:" + doremi);
                            result.append(", kr: " + strArray[i]);
                            result.append(", idle: " + idle + "}, ");
                            milisecond = 0L;
                            idle = 0L;
                        }

                    }

                }


            }
        }

        result.append("]");

        return result.toString();
    }

    public static int transferReverse(char note){
        int result = 0;

        switch (note)
        {
            case '도':
                result = 0;
                break;

            case '레':
                result = 1;
                break;

            case '미':
                result = 2;
                break;

            case '파':
                result = 3;
                break;

            case '솔':
                result = 4;
                break;

            case '라':
                result = 5;
                break;

            case '시':
                result = 6;
                break;
            default:
                result = -1;

        }

        return result;

    }



}
