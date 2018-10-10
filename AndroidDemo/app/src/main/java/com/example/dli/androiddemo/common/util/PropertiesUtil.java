package com.example.dli.androiddemo.common.util;

import android.content.Context;

import com.example.dli.androiddemo.App;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesUtil {

    private final static String NAME = "application.properties";

    public static String getValue(String key) {
        Properties properties = new Properties();
        InputStream in;
        Context context = App.getContext();
        if (context == null) return "";
        try {
            in = context.getAssets().open(NAME);
            properties.load(new InputStreamReader(in, "utf-8"));
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }


    public static void setValue(String keyName, String keyValue) {
        Properties props = new Properties();
        Context context = App.getContext();
        if (context == null) return;
        try {
            props.load(context.openFileInput(NAME));
            OutputStream out = context.openFileOutput(NAME, Context.MODE_PRIVATE);
            Enumeration<?> e = props.propertyNames();
            if (e.hasMoreElements()) {
                while (e.hasMoreElements()) {
                    String s = (String) e.nextElement();
                    if (!s.equals(keyName)) {
                        props.setProperty(s, props.getProperty(s));
                    }
                }
            }
            props.setProperty(keyName, keyValue);
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
