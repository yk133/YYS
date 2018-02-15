package yk.main;

import java.util.Random;

public class Com {
    public static String []DeviceLists=new String[100];
    public static int Devices=0,status=0,setClose=~(1<<31);
    public static int PressX[]={1456,1774,888};
    public static int PressY[]={870,260,180};
    public static int AddX[]={150,20,100};
    public static int AddY[]={7,100,40};
    public static int OnlyPressX[]={1400,1744};
    public static int OnlyPressY[]={714,290};
    public static int OnlyAddX[]={100,40};
    public static int OnlyAddY[]={40,100};
    private static int Onlynow=0;
    private static int Onlyall=2;

    private static int now=0;
    private static int all=3;

    public static int[] nextP(int isp)
    {
        int res[]={0,0};

        if(isp==1) {

            res[0] = PressX[now] + Math.abs(new Random().nextInt()) % AddX[now];
            res[1] = PressY[now] + Math.abs(new Random().nextInt()) % AddY[now];
            now++;
            now %= all;
            return res;
        }
        else
        {
            res[0] = OnlyPressX[Onlynow] + Math.abs(new Random().nextInt()) % OnlyAddX[Onlynow];
            res[1] = OnlyPressY[Onlynow] + Math.abs(new Random().nextInt()) % OnlyAddY[Onlynow];
            Onlynow++;
            Onlynow %= Onlyall;
            return res;
        }
    }

}
