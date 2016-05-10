package com.niopullus.app;

import java.util.ArrayList;

/**
 * Created by Owen on 4/28/2016.
 */
public class Question {

    private String question;
    private ArrayList<String> answers;
    private int rightAnswerIndex;

    public Question(String question, ArrayList<String> answers, int rightAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.rightAnswerIndex = rightAnswerIndex;
    }

    public String getQuestion() {
        return this.question;
    }

    public ArrayList<String> getAnswers() {
        return this.answers;
    }

    public int getRightAnswerIndex() {
        return this.rightAnswerIndex;
    }

    public boolean isRightAnswer(int index) {
        return index == this.rightAnswerIndex;
    }

}
