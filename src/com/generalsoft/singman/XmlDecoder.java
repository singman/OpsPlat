package com.generalsoft.singman;
import java.io.*;
import java.beans.*;
import java.awt.Point;
/**
 * User: singman
 * Date: 14-8-6
 * Time: 下午2:12
 * Project:CFS_v0.2
 * Usage:
 */
public class XmlDecoder {
    public static void main (String args[]) throws Exception {
        SystemParam param = new SystemParam();

        param.setSERVER_IP("10.1.30.5");
        param.setSERVER_POR("22");
        param.setUSER_NAME("weblogic");
        param.setPASSWORD("weblogic");

        XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream("Sample.xml")));
        encoder.writeObject(param);
        encoder.close();
        System.out.println(param);
        XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream("Sample.xml")));
        SystemParam sample2 = (SystemParam)decoder.readObject();
        decoder.close();
        System.out.println(sample2);
    }
}