package project.util;

import javafx.scene.paint.*;
import javafx.scene.text.*;

import java.util.*;

public class SQLFormat {

    public static ArrayList<Text> formatSQLText(String sql)
    {
        String keyWordsLF = ",from,where,join,group,having,order,(select,";
        String keyWords = ",with,select,by,in,and,or,top,distinct,as,on,update,delete,";
        String combiningKeyWords = ",right,left,inner,";
       sql = sql.trim().replaceAll(" +", " ");
        ArrayList<Text> list = new ArrayList<>();
        String[] tokens = sql.split(" ");
        boolean lastCombining = false;

        for (String s : tokens)

            {
                Text text = new Text();
                if (keyWordsLF.contains("," + s.toLowerCase() + ","))
                    {
                        if (lastCombining)
                            {
                                text.setText(s.toUpperCase() + " ");
                                lastCombining=false;
                            }
                        else
                            {
                                text.setText("\n" + s.toUpperCase() + " ");
                            }
                        text.setFill(Color.DARKBLUE);
                    }
                else if (keyWords.contains("," + s.toLowerCase() + ","))
                    {
                        text.setText(s.toUpperCase() + " ");
                        text.setFill(Color.DARKBLUE);
                    }
                else if (combiningKeyWords.contains("," + s.toLowerCase() + ","))
                    {
                        // these get a linefeed but the next keyword doesn't
                        text.setText("\n" + s.toUpperCase() + " ");
                        text.setFill(Color.DARKBLUE);
                        lastCombining=true;
                    }
                else
                    {
                        text.setText(s + " ");
                        text.setFill(Color.BLACK);
                    }
                list.add(text);
            }
        // for debugging
       /* Text text = new Text();
        text.setText("\nOriginal SQL for debugging: " + sql);
        list.add(text);*/

        return list;
    }
}
