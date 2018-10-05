package p.vanessa.yp4_feelsbook;
import java.util.Date;


public class Emotion_Sadness extends Emotion{

    private String emotionType = "Sadness";
    Emotion_Sadness(Date date) {super (date);}

    public String getEmotionType() {
        return this.emotionType;
    }

    @Override
    public String toString() {
        return this.emotionType + " " + this.getDate().toString();
    }
}
