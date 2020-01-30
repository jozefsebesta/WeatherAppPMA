package com.example.weatherapppma.ui;

import android.content.Context;
import android.util.Log;

import com.example.weatherapppma.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWriter {
    static String fileName = "data.txt";

    static private Context context;
    public static void Init(Context context)
    {
        FileWriter.context = context;
    }

    private static void DeleteFile()
    {
        context.deleteFile(fileName);
    }

    public static void RemoveLineFromFile(int pos)
    {
        String[] fileRecords = FileWriter.Read().split("\n");
        ArrayList<String> arr = new ArrayList<>();
        arr.addAll(Arrays.asList(fileRecords));

        for(int i = 0; i< arr.size(); i++)
        {
            if(arr.get(i).equals(""))
                arr.remove(arr.get(i));
        }

        arr.remove(pos);

        try{
            DeleteFile();
            for(int i = 0; i< arr.size(); ++i) {
                if(!arr.get(i).equals(""))
                    Write(arr.get(i));
            }
        }
        catch (Exception e) {}
    }


    public static void Write(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_APPEND));
            outputStreamWriter.write(data + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String Read() {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
