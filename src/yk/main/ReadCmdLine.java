package yk.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCmdLine {
    public static void main(String args[]) {
        //RunInit();
        //String aa[]=Run("adb shell dumpsys window | grep mUnrestrictedScreen");
        //System.out.println(aa[0]);
    }

    public static void RunInit()
    {
        String Inf="adb shell \"dumpsys window | grep mUnrestrictedScreen\"";
        String[] getStrings = Run("adb devices");
        //Com.DeviceLists=new String[100];
        String aa="\t";
        int sum=0;

        for(int i=1;i<getStrings.length;i++)
        {
            String tmp[]=getStrings[i].split(aa);
            if(tmp.length>=2)
            {
                if(tmp[1].equals("device"))
                {
                    Com.DeviceLists[i]=tmp[0];
                    Com.status=1;
                    System.out.println(tmp[0]);
                    sum++;
                }
            }
        }
        Com.Devices=sum;
    }

    public static String[] Run(String _in)
    {
        Process process = null;
        List<String> processList = new ArrayList<String>();
        try {
            process = Runtime.getRuntime().exec(_in);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] toBeStored = processList.toArray(new String[processList.size()]);

        return toBeStored ;
    }

}