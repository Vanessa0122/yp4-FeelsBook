package p.vanessa.yp4_feelsbook;
import java.util.Date;


public class Emotion_Anger extends Emotion{

    private String emotionType = "Anger";
    Emotion_Anger(Date date) {super (date);}

    public String getEmotionType() {
        return this.emotionType;
    }

    @Override
    public String toString() {
        return this.emotionType;
    }
}
