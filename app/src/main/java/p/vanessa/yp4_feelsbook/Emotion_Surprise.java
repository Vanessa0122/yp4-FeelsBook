package p.vanessa.yp4_feelsbook;
import java.util.Date;


public class Emotion_Surprise extends Emotion{

    private String emotionType = "Surprise";
    Emotion_Surprise(Date date) {
        super (date);
    }

    public String getEmotionType() {

        return this.emotionType;
    }

    @Override
    public String toString() {
        return "You felt " + this.emotionType + " on " + this.getDate().toString();
    }
}
