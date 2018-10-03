package p.vanessa.yp4_feelsbook;
import java.util.Date;


public class Emotion_Love extends Emotion{

    private String emotionType = "Love";
    Emotion_Love(Date date) {super (date);}

    public String getEmotionType() {
        return this.emotionType;
    }

    @Override
    public String toString() {
        return this. emotionType;
    }
}
