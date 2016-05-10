package com.niopullus.app;

import java.util.ArrayList;

/**
 * Created by Owen on 4/28/2016.
 */
public class QuestionHandler {

    private static ArrayList<Question> questions = new ArrayList<Question>();
    private static int curQuestion = 0;

    public static void compileQuestions() {
        ArrayList<String> q1answer = new ArrayList<String>();
        q1answer.add("Cutting trees");
        q1answer.add("Gaining access to the internet");
        q1answer.add("Stealing valuable information such as Passcodes and Passwords");
        q1answer.add("Creating a program");
        Question q1 = new Question("What is one of the common uses for hacking?", q1answer, 2);
        QuestionHandler.questions.add(q1);
        ArrayList<String> q2answer = new ArrayList<String>();
        q2answer.add("Reconnaissance");
        q2answer.add("Encapsulation");
        q2answer.add("Scanning");
        q2answer.add("Penetration");
        Question q2 = new Question("Which of these processes is NOT part of the systematic process of hacking?", q2answer, 1);
        QuestionHandler.questions.add(q2);
        ArrayList<String> q3answer = new ArrayList<String>();
        q3answer.add("gather information essential to an attack and enable an attacker to obtain complete profile of an organization's security posture.");
        q3answer.add("Gather web documents on how to hack properly and not get caught.");
        q3answer.add("Going to a renaissance fair.");
        q3answer.add("Getting spy goggles and watching the router you want to hack.");
        Question q3 = new Question("What is the meaning of reconnaissance is hacking terms?", q3answer, 0);
        QuestionHandler.questions.add(q3);
        ArrayList<String> q4answer = new ArrayList<String>();
        q4answer.add("Cell Phone Numbers");
        q4answer.add("Employee’s Names");
        q4answer.add("Security Measures");
        q4answer.add("Power Supply information");
        Question q4 = new Question("What is NOT an example of reconnaissance?", q4answer, 3);
        QuestionHandler.questions.add(q4);
        ArrayList<String> q5answer = new ArrayList<String>();
        q5answer.add("gain a more detailed view of a company's network and to understand what specific computer systems and services are in use.");
        q5answer.add("Using programs to scan the malware of the system in order to find openings in the security");
        q5answer.add("Scanning the radar for possible missiles from the Reds up north.");
        q5answer.add("Utilizing the tools you have to scan the circuitry grid of the hardware of the computer");
        Question q5 = new Question("What is Scanning in the hacking process?", q5answer, 0);
        QuestionHandler.questions.add(q5);
        ArrayList<String> q6answer = new ArrayList<String>();
        q6answer.add("Radar check, grid scan");
        q6answer.add("Ping sweeps,  port scans");
        q6answer.add("Ping Pong, Pong Ping");
        q6answer.add("Grid Check, Radar Scan");
        Question q6 = new Question("What are two main methods of hacking during the scanning process?", q6answer, 1);
        QuestionHandler.questions.add(q6);
        ArrayList<String> q7answer = new ArrayList<String>();
        q7answer.add("Replace your computer every year");
        q7answer.add("Purchase two security programs for double the power");
        q7answer.add("Use Pen and Paper from now on");
        q7answer.add("Change your passwords every month or so");
        Question q7 = new Question("What is an efficient way of protecting your computer from Hackers", q7answer, 3);
        QuestionHandler.questions.add(q7);
        ArrayList<String> q8answer = new ArrayList<String>();
        q8answer.add("By breaking up with you?");
        q8answer.add("By changing your passwords");
        q8answer.add("By enacted purchases that you don’t desire and ruining your credit score.");
        q8answer.add("Change your passwords every month or so");
        Question q8 = new Question("How can a hacker ruin your economic life?", q8answer, 2);
        QuestionHandler.questions.add(q8);
        ArrayList<String> q9answer = new ArrayList<String>();
        q9answer.add("Changing your passwords monthly");
        q9answer.add("Use a two way firewall");
        q9answer.add("Avoid sites that are not protected by Norton");
        q9answer.add("Getting a new computer");
        Question q9 = new Question("What is a sure way of stopping hackers from entering your computer?", q9answer, 1);
        QuestionHandler.questions.add(q9);
        ArrayList<String> q10answer = new ArrayList<String>();
        q10answer.add("5000");
        q10answer.add("3000");
        q10answer.add("2500");
        q10answer.add("10000 and above");
        Question q10 = new Question("What is the fine for 1st degree hacking?", q10answer, 3);
        QuestionHandler.questions.add(q10);
        ArrayList<String> q11answer = new ArrayList<String>();
        q11answer.add("Terrorism");
        q11answer.add("Wood Cutter");
        q11answer.add("Server bytes");
        q11answer.add("Geeks");
        Question q11 = new Question("According to US goverment what is another word to describe hacking?", q11answer, 0);
        QuestionHandler.questions.add(q11);
        ArrayList<String> q12answer = new ArrayList<String>();
        q12answer.add("A slap on the wrist and given a warning.");
        q12answer.add("Up to six months in prison and a 1000$ fine");
        q12answer.add("A fine of the cost of the property damage and 2 weeks in prison.");
        q12answer.add("Getting put in the corner for time out.");
        Question q12 = new Question("What is a hacker charged with when he/she damages the value of the property or computer services, if any, is $500 or less.", q12answer, 1);
        QuestionHandler.questions.add(q12);
        ArrayList<String> q13answer = new ArrayList<String>();
        q13answer.add("5");
        q13answer.add("10");
        q13answer.add("3");
        q13answer.add("2");
        Question q13 = new Question("How many different degrees of computer crime are there?", q13answer, 0);
        QuestionHandler.questions.add(q13);
        ArrayList<String> q14answer = new ArrayList<String>();
        q14answer.add("A code constructor that gets rid of the program");
        q14answer.add("High tech keyboards");
        q14answer.add("Military grade computer systems");
        q14answer.add("Viruses");
        Question q14 = new Question("What do hackers commonly use in order to bypass security?", q14answer, 3);
        QuestionHandler.questions.add(q14);
        ArrayList<String> q15answer = new ArrayList<String>();
        q15answer.add("A program that cleans out the files from your computer");
        q15answer.add("A cold you get from a computer");
        q15answer.add("a piece of code that is capable of copying itself and typically has a detrimental effect, such as corrupting the system or destroying data.");
        q15answer.add("A strain of code that makes your computer run a fever");
        Question q15 = new Question("What is a computer virus?", q14answer, 2);
        QuestionHandler.questions.add(q15);
        ArrayList<String> q16answer = new ArrayList<String>();
        q16answer.add("Downloading files");
        q16answer.add("On FaceBook.com");
        q16answer.add("Through a security program");
        q16answer.add("Anywhere where the program had access and was exposed to the internet.");
        Question q16 = new Question("Where can you catch a computer virus?", q16answer, 3);
        QuestionHandler.questions.add(q16);
    }

    public static Question getQuestion() {
        QuestionHandler.curQuestion++;
        if (QuestionHandler.curQuestion > QuestionHandler.questions.size() - 1) {
            QuestionHandler.curQuestion = 0;
        }
        Question q = QuestionHandler.questions.get(QuestionHandler.curQuestion);
        return q;
    }

}
