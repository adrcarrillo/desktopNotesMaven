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

public class App {
    public static void main ( String[] args ) {

        BufferedReader reader;

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

        Gson gson = new Gson();
        User user = gson.fromJson(reader, User.class);
        String id = user.getId();
        String pc = user.getPc();
        String mobile = user.getMobile();

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF");
        }

        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        ZonedDateTime cn = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter timeFormated = DateTimeFormatter.ofPattern("HH:mm:ss");
        String localTimeStringLocal = timeFormated.format(now);
        String localTimeStringAsia = timeFormated.format(cn);
        DateTimeFormatter timeFormatGretting = DateTimeFormatter.ofPattern("H");
        String localTimeStringGretting = timeFormatGretting.format(now);
        int localhour = Integer.parseInt(localTimeStringGretting);

        JFrame frame = new JFrame ("Desktop Notes");
        JLabel label = new JLabel();

        String sarray[] = new String[10];
        sarray[0] = "Hi!, " + getGreeting(localhour);
        sarray[1] = "";
        sarray[2] = "Date: " + now.toLocalDate();
        sarray[3] = "Mexico Time: " + localTimeStringLocal;
        sarray[4] = "Shanghai Time: " + localTimeStringAsia;
        sarray[5] = "Week: " + now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        sarray[6] = "";
        sarray[7] = "ID: " + id;
        sarray[8] = "PC: " + pc;
        sarray[9] = "Mobile: " + mobile;

        String valueLabel = "";
        for (int j=0 ; j < sarray.length; j++){
            valueLabel += "<html>" + sarray[j] + "<br/>";
        }
        label.setText(valueLabel + "</html>");
        frame.add(label,BorderLayout.CENTER);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("ico.png"));
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    public static String getGreeting(int x) {
        String greeting = new String();
        if (6<=x&&x<=11) {
            greeting = "\nGood morning!\n";
        } else if (12<=x&&x<=18) {
            greeting = "\nGood afternoon!\n";
        } else if (19<=x&&x<=22) {
            greeting = "\nGood night!\n";
        } else {
            greeting = "\nGo to sleep!\n";
        }
        return greeting;
    }
}
