package p.vanessa.yp4_feelsbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class Emotion {

    private transient String emotionType = "None";
    private String comment;
    private Date date;

    Emotion() {
        this.date = new Date();
    }

    Emotion(Date date){
        this.date = date;
    }

    public void saveDate(Date date){
        this.date = date;
    }

    public void saveComment(String comment){
        if (comment.length() <= 100){
            this.comment = comment;
        }
        else{
            // TELL THE USER MAX LENGTH IS 100
        }
    }

    // Functions that gets the information
    public Date getDate(){
        return this.date;
    }

    public String getComment(){
        return this.comment;
    }

    public String getEmotionType(){
        return emotionType;
    }

    // change to format of date so we can save it
    public String formatDateToISO(){
        TimeZone timezone = TimeZone.getTimeZone("MST");
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateformat.setTimeZone(timezone);
        return dateformat.format(this.date);
    }

    // change it back so we can display it to the user
    public static Date toCalendar(String isoTime){
        try{
            isoTime = isoTime + "Z";
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss");
            Date finalResult = dateformat.parse(isoTime);
            return finalResult;
        }catch(Exception ex){
            return new Date();
        }
    }

}
