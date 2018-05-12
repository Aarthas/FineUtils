package com.arthas.fine_device;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;




public class AssetModule
{



    public static StringBuffer getText(String path)
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            InputStream open = Logic.getApp().getAssets().open(path);

            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(open, "utf-8"));
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            open.close();
            reader.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return sb;
    }

    public static <E> E getBean(String string, Class<E> class1)
    {
        try
        {
            StringBuffer text = getText(string);
            return GsonManager.fromJson(text.toString(), class1);
        } catch (Exception e)
        {
        }
        return null;
    }


}
