package p.vanessa.yp4_feelsbook;

import java.util.Date;

public abstract class Emotion {

    private transient String emotionType = "None";
    private String comment;
    private Date date;

    Emotion(Date date){

        this.date = date;
    }

    public void saveComment(String comment) {
        if (comment.length() <= 100) {
            this.comment = comment;
        } else {
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


    @Override
    public String toString(){
        return this.emotionType+this.comment+this.date;
    }

}




