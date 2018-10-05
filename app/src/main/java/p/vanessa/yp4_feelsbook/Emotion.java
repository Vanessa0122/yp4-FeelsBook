package p.vanessa.yp4_feelsbook;

import java.util.ArrayList;
import java.util.Date;

public abstract class Emotion {

    private transient String emotionType = "None";
    private ArrayList<Emotion> emotionArray = MainActivity.emotionList;
    private String comment;
    private Date date;


    public void addEmotion(Emotion emotionType){
        this.emotionArray.add(emotionType);
    }

    public void removeEmotion(Emotion emotionType){
        this.emotionArray.remove(emotionType);
    }

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


    public int getCount(Emotion emotionType){
        int counter = 0;
        for (int i = 0; i < this.emotionArray.size(); i ++) {
            if (this.emotionArray.get(i).equals(emotionType)) {
                counter++;
            }
        }
        return counter;

    }


    @Override
    public String toString(){
        return this.emotionType+this.comment+this.date;
    }

}




