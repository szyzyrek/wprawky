package edu.szyrek.wprawki;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.math.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.util.Date;
import android.text.*;
import java.lang.reflect.*;
import java.io.*;
import android.util.*;

class Result {
	public static final int NOT_COMPUTED = 0;
	public static final int HASHCODE_VIOLATED = 1;
	public static final int NOT_EQUALS = 2;
	public static final int RESULT_OK = 3;
	private int result = NOT_COMPUTED;
	
	public Result(int integer){
		result = integer;
	}
	
	public int getValue(){
		return result;
	}
}

public class ListTester
{
private static final int COUNT = 100;
private List<String> list1;
private List<String> list2;
private String result;
private static Random random = new Random();
	
public ListTester(){
	list1 = new ArrayList<String>();
	list2 = new ArrayList<String>();
	result = "";
}

private static int nextRandomInt(int min, int max) throws IllegalArgumentException{
	if (min >= max || min < 0 ){
		throw new IllegalArgumentException("min must be lower than max and not lower than 0");
	}
	return random.nextInt(max-min)+min;
}

public String getResult(){
	return goTest();
}
	
public static <T> Result testEqualsHashCode(T first, T second){
	if(first.equals(second)){
		return (first.hashCode()==second.hashCode() ? new Result(Result.RESULT_OK) : new Result(Result.HASHCODE_VIOLATED));
	}else{
		return new Result(Result.NOT_EQUALS);
	}
}

public static String getFieldsString(Object obj){
	StringBuilder sb = new StringBuilder();
	for (Field f : obj.getClass().getDeclaredFields()){
		sb.append('['+f.getName() +":"+ f.getType()+']'+" - "+f.toString()+"\n");
	}
	return sb.toString();
}

public static String getMethodsString(Object obj){
	StringBuilder sb = new StringBuilder();
	StringBuilder sb2 = new StringBuilder();
	for (Method m : obj.getClass().getDeclaredMethods()){
		boolean first = true;
		for (Type t : m.getParameterTypes()){
			if (first)
				sb2.append(extractSimpleName(t.toString()));
			else
				sb2.append(", "+extractSimpleName(t.toString()));
		}
		sb.append(Modifier.toString(m.getModifiers())+ " " +m.getReturnType() +" " +m.getName() +'('+sb2.toString()+")\n");
		sb2 = new StringBuilder();
	} 
	return sb.toString();
}

public static String extractSimpleName(String qualifiedName){
	if (qualifiedName.contains(".")){
		return qualifiedName.substring(qualifiedName.lastIndexOf('.')+1, qualifiedName.length());
	}else{
		return qualifiedName;
	}
}

public static String getRandomString(String alphabet, int minlen, int maxlen){
	int stringLength = nextRandomInt(minlen, maxlen);
	StringBuilder sb = new StringBuilder();
	for(int i = 0; i <= stringLength; i++){
		sb.append(alphabet.charAt(nextRandomInt(0,alphabet.length()-1)));
	}
	return sb.toString();
}
	
public String goTest(){
	/*StringBuilder builder = new StringBuilder();
	for (int i = 0; i < COUNT; i ++){
		builder.append(i == 0 ? nextRandomInt(1,10) + "" : ", "  nextRandomInt(1,10));
	}
	result= builder.toString();
	*

	return getFieldsString(new String()) + getMethodsString(new String());
	//return testEqualsHashCode(one, two).getValue()==Result.RESULT_OK ? "Sprawdzone" : "siakis blad";
	*/
	try {
	nextRandomInt(-1,3);
	} catch (Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	return getRandomString("qwertyuiopasdfghjklzxcvbnm",4,9);
}
}
