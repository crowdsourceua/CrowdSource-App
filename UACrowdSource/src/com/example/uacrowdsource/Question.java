package com.example.uacrowdsource;

import java.io.Serializable;

public class Question implements Serializable
{
	String qId;
	String eId;
	String type;
	String question;
	String choiceA;
	String choiceB;
	String choiceC;
	String choiceD;
	String correctAnswer;
	
	public Question(String qi, String ei, String t, String q, String a, String b, String c, String d, String ca)
	{
		setQId(qi);
		setEId(ei);
		setType(t);
		setQuestion(q);
		setChoiceA(a);
		setChoiceB(b);
		setChoiceC(c);
		setChoiceD(d);
		setCorrectAnswer(ca);
	}
	public String getQId()
	{
		return this.qId;
	}
	private void setQId(String q)
	{
		this.qId = q;
	}
	public String getEId()
	{
		return this.eId;
	}
	private void setEId(String e)
	{
		this.eId = e;
	}
	public String getType()
	{
		return this.type;
	}
	public void setType(String t)
	{
		this.type = t;
	}
	public String getQuestion()
	{
		return this.question;
	}
	public void setQuestion(String q)
	{
		this.question = q;
	}
	public String getChoiceA()
	{
		return this.choiceA;
	}
	public void setChoiceA(String a)
	{
		this.choiceA = a;
	}
	public String getChoiceB()
	{
		return this.choiceB;
	}
	public void setChoiceB(String b)
	{
		this.choiceB = b;
	}
	public String getChoiceC()
	{
		return this.choiceC;
	}
	public void setChoiceC(String c)
	{
		this.choiceC = c;
	}
	public String getChoiceD()
	{
		return this.choiceD;
	}
	public void setChoiceD(String d)
	{
		this.choiceD = d;
	}
	public String getCorrectAnswer()
	{
		return this.correctAnswer;
	}
	public void setCorrectAnswer(String ca)
	{
		this.correctAnswer = ca;
	}
}
