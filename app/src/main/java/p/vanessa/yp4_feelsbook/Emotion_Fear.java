package p.vanessa.yp4_feelsbook;
import java.util.Date;


public class Emotion_Fear extends Emotion{

    private String emotionType = "Fear";
    Emotion_Fear(Date date) {super (date);}

    public String getEmotionType() {
        return this.emotionType;
    }

    @Override
    public String toString() {

        return this.emotionType + this.getDate().toString();
    }
}
