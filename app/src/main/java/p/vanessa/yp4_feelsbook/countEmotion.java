package p.vanessa.yp4_feelsbook;


import java.util.ArrayList;


public class countEmotion {

    private ArrayList<Emotion> EmotionArray = MainActivity.emotionList;
    private Integer  love= 0;
    private Integer  joy= 0;
    private Integer  surprise= 0;
    private Integer  anger= 0;
    private Integer  sadness= 0;
    private Integer  fear= 0;




    //Adds and emotion object to the array
    public void addEmotion(Emotion emotion){
        EmotionArray.add(0, emotion);
    }


    //Removes and emotion object from the array
    public void removeEmotion(int index){
        this.EmotionArray.remove(index);
    }

    //returns the emotion in the array at a specific index
    public Emotion getEmotion(int index){
        return this.EmotionArray.get(index);
    }

    //It updates the emotion in the array in a specific index
    public void updateEmotion(int index, Emotion emotion){
        EmotionArray.set(index,emotion);
    }








    public void refreshCount(int number, String emotion){
        switch(emotion){
            case "love":
                love += 1;

            case "joy":
                joy += 1;

            case "surprise":
                surprise += 1;

            case "anger":
                anger += 1;

            case "sadness":
                sadness += 1;

            case "fear":
                fear += 1;

        }

    }
