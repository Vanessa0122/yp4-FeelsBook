package p.vanessa.yp4_feelsbook;

import android.content.Context;
import android.support.annotation.;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EmotionTasks {
    private List emotionList;
    private Context message;

    public EmotionTasks(Context message, List<Emotion> emotionList){
        this.message = message;
        this.emotionList = emotionList;
    }

    @Override
    public int getEmotionCount(){
        return this.emotionList.size();
    }

    @Override
    public Object getItem(int index){
        return emotionList.get(index);
    }

    @Override
    public long getIndexID(int index){
        return index;
    }

    public View getView (int index, View instantDisplay, ViewGroup parent){
        View view = View.inflate(message, R.layout.display_emotion, null);

        TextView emoView = view.findViewById(R.id.displayEmo);
        TextView emoDataView = view.findViewById(R.id.displayEmoData);

        emoView.setText(emotionList.get(emotionIndex).getDate());
        emoDataView.setText(emotionList.get(index).getEmotionType);

        view.setTag(emotionList.get(index).getEmotionType());
        return view;
;
    }

}
