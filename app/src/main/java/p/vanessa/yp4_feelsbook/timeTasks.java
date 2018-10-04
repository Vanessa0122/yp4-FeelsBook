package p.vanessa.yp4_feelsbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Simple class used to reformat Dates, into ISO and vice-versa
public class timeTasks {

    public String format(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.format(date);
    }

    // Converts the Iso back into a Date Time
    public Date recordTime(String isoTime){
        try {
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date finalResult = df1.parse(isoTime);
            return finalResult;
        } catch (Exception ex){
            return new Date();
        }
    }
}
