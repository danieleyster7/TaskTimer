package deyster.timer.util;

import java.io.Serializable;

import deyster.timer.model.WHDTask;

/* A wrapper class that is serializable so that a list of whdtickets can be easily written as an object to a file */
public class WHDTaskArrayWrapper implements Serializable
{
	public WHDTask whdArray[];
	
	public WHDTaskArrayWrapper(int size) {
		whdArray = new WHDTask[size];
	};
}
