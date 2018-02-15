package yk.main;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Start extends JFrame implements ActionListener,Runnable,WindowListener  {

    public static String RunPre(String In){
        String Inf="adb shell dumpsys window | grep mUnrestrictedScreen";
        String ans[]=ReadCmdLine.Run(Inf);
        String Res[]=new String [100];
        if(ans!=null)Res=ans[0].split(" ");
        if(Res[5].equals("error:"))
        {
            int xy[]=Com.nextP(nowrun);
            return In+" "+xy[0]+" "+xy[1]+" "+xy[0]+" "+xy[1];
        }
        else if(Res[5].equals("1920x1080")||Res[5].equals("1080x1920"))
        {
            int xy[]=Com.nextP(nowrun);
            return In+" "+xy[0]+" "+xy[1]+" "+xy[0]+" "+xy[1];
        }
        else {
                //1920 1080
                //1280 720
            int xy[]=Com.nextP(nowrun);
            int a=(int)(xy[0]*1.0*(1280.0/1920.0));
            int b=(int)(xy[0]*1.0*(720.0/1080.0));
            return In+" "+a+" "+b+" "+a+" "+b;
        }

    }


    public static void main(String args[])throws Exception {
        new Start("AAAAAAAA", "zijiaaa");
    }

    public static int nowrun=0;
    private JTextArea jta;

    JButton jbme=new JButton("关于");
    private JTextField jtf,HH,MM;
    public int hh=100,mm=100;
    private static  Queue<String>Q= new LinkedList<String>();
    static int count=0;
    FileSystemView fsv;
    JFileChooser jcf;
    JButton zant,Bye;
    ObjectOutputStream oos;
    int now=0;

    JButton st1, st2,jb2;
    JPanel jp;
    String to=null;
    String myid;

    public Start(String to, String myid) throws  Exception{
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new JLabel(new ImageIcon("pic/login.jpg")));
        this.addWindowListener(this);
        this.to=to;
        this.myid=myid;

        HH=new JTextField("0",3);
        HH.setSize(20,40);
        MM=new JTextField("30",3);
        MM.setSize(20,40);
        Bye=new JButton("定时");

        zant =new JButton("暂停");
        zant.addActionListener(this);

        jta = new JTextArea();
        jta.setEditable(false);
        JPanel panelOutput;
        panelOutput = new JPanel();
        this.add(new JScrollPane(jta),"North");
        //this.add(panelOutput,"North");
        jtf = new JTextField(15);
        st1 = new JButton("双人御魂/觉醒");
        st2 = new JButton("单人御魂/觉醒");

        st1.addActionListener(this);
        st2.addActionListener(this);

        Bye.addActionListener(this);
        jp = new JPanel();
        //jp.add(jtf);
        jp.add(new Label("时"));
        jp.add(HH);
        jp.add(new Label("分"));
        jp.add(MM);
        jp.add(Bye);
        jp.add(st1);
        jp.add(st2);
        jp.add(zant);
        jp.add(jbme);

        this.add(jta, "Center");
        this.add(jp, "South");
        jbme.addActionListener(this);
        this.setTitle("YYS Tools by YK");
        this.setBounds(400, 400, 600, 420);
        this.setVisible(true);

        while(true)
        {
            ReadCmdLine.RunInit();


            jta.setText("等待开始...\r\n");

            while(nowrun!=0)
            {
                if(Com.Devices<=0 && nowrun==1)
                {
                    jta.setText("请检查是否有设备接入？\r\n");
                    nowrun=0;
                    break;
                }
                for(int i=1;i<=Com.Devices;i++) {
                    String Pre = "adb -s " + Com.DeviceLists[i] + " shell input swipe ";
                    Pre = RunPre(Pre) + " " + (138 + Math.abs(new Random().nextInt()) % 200);
                    String getRes[] = ReadCmdLine.Run(Pre);
                    //jta.setText(jta.getText() + );
                    String aaa="No." + (++count) + "  " + Pre + "\r\n";
                    Q.offer(aaa);
                    if(Q.size()>33)
                    {
                        Q.remove();
                    }
                    aaa="";
                    for(String q : Q){
                        aaa+=q;
                    }
                    jta.setText(aaa);
                }
                Thread.sleep(1940+ Math.abs(new Random().nextInt()%2000));
            }
            Thread.sleep(1500);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == st1)
        {
            nowrun=1;
            jta.setText(" ");
            while(Q.size()>0)Q.remove();
            count=0;
            this.setTitle("正在进行双人御魂/觉醒");
        }
        if(e.getSource() == st2)
        {
            nowrun=2;
            jta.setText(" ");
            while(Q.size()>0)Q.remove();
            count=0;
            this.setTitle("正在进行单人御魂/觉醒");
        }
        if(e.getSource()== zant)
        {
            nowrun=0;
            this.setTitle("已暂停");
        }
        if(e.getSource()==Bye)
        {

            int gh=Integer.valueOf(HH.getText());
            int gm=Integer.valueOf(MM.getText());
            int times=3600*gh+60*gm;
            if(gh>100||gh<0||gm<0||gm>=60)
            {
                Object[] options = { "好的","哦" };
                JOptionPane.showOptionDialog(this, "你输入的数据有误，请保证H<100,M<60,或者去掉多余字符", "警告",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
            }
            else
            {
                Com.setClose=times;

                Object[] options = { "好的","哦" };
                JOptionPane.showOptionDialog(this, "已设置倒计时，时间到后会自动停止！", "警告",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
            }
        }
        if(e.getSource()==jbme)
        {
            JOptionPane.showOptionDialog(this, "本辅助只是用于学习，作者不负任何法律责任，交流请联系QQ1625884185", "？？？",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, null, null);
        }
    }

    @Override
    public void run()
    {

    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Start  !!");
    }

    @Override
    public void windowClosing(WindowEvent e) {


    }

    @Override
    public void windowClosed(WindowEvent e) {
        now=1;
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
