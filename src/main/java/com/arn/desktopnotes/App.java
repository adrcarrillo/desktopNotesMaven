package com.arn.desktopnotes;

import java.awt.BorderLayout;
import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.google.gson.Gson;

public class App implements Runnable {

    BufferedReader reader;
    Thread t=null;
    String valueLabel = "";
    JFrame f;
    JLabel l;
    Gson gson;
    User user;

    //private static void Appi() {
    App() {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF");
        }

        f = new JFrame ("Desktop Notes");
        l = new JLabel();

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("ico.png"));

        t = new Thread(this);
        t.start();
    
        f.add(l,BorderLayout.CENTER);
        f.setIconImage(img.getImage());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300,200);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    @Override
    public void run() {
        try {
            while(true) {

                try {
                    String userPath = System.getProperty("user.home");
                    reader = new BufferedReader(new FileReader(userPath + "/user.json"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                return;
                }
                finally {
                }
            
                gson = new Gson();
                user = gson.fromJson(reader, User.class);
                String id = user.getId();
                String pc = user.getPc();
                String mobile = user.getMobile();
            
                ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
                ZonedDateTime cn = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Shanghai"));
                DateTimeFormatter timeFormated = DateTimeFormatter.ofPattern("HH:mm:ss");
                String localTimeStringLocal = timeFormated.format(now);
                String localTimeStringAsia = timeFormated.format(cn);
                DateTimeFormatter timeFormatGretting = DateTimeFormatter.ofPattern("H");
                String localTimeStringGretting = timeFormatGretting.format(now);
                int localhour = Integer.parseInt(localTimeStringGretting);
            
                String sarray[] = new String[10];
                sarray[0] = "Hi!, " + Greeting.getGreeting(localhour);
                sarray[1] = "";
                sarray[2] = "Date: " + now.toLocalDate();
                sarray[3] = "Mexico Time: " + localTimeStringLocal;
                sarray[4] = "Shanghai Time: " + localTimeStringAsia;
                sarray[5] = "Week: " + now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                sarray[6] = "";
                sarray[7] = "ID: " + id;
                sarray[8] = "PC: " + pc;
                sarray[9] = "Mobile: " + mobile;
            
                //String valueLabel = "";
                for (int j=0 ; j < sarray.length; j++){
                    valueLabel += "<html>" + sarray[j] + "<br/>";
                }
                //l.setText(valueLabel + "</html>");

                printTime();
                Thread.sleep(5000);
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
    }

    public void printTime() {
        System.out.println("Step....");
        l.setText(valueLabel + "</html>");

    }

    public static void main ( String[] args ) {

        //Appi();
        //App app = new App();
        new App();

    }



}
