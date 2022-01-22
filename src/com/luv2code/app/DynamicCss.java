package com.luv2code.app;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.luv2code.common.Constants;
import com.luv2code.common.RegexUtils;
import static com.luv2code.common.Utils.*;
import static com.luv2code.common.BalancedBrackets.*;

public class DynamicCss {

  public static String READ_CSS_PATH;
  public static String WRITE_CSS_PATH;
  public static String LINE_BREAK;
  public static Integer NEW_COLOR_INDEX;
  public static String COLOR_PREFIX;
  public static StringBuffer  tempVarColorsBuffer;
  public static HashMap<String , Integer> colorCount;
  
  FileReader fileReader;
  BufferedReader bufferedReader;

  FileWriter fileWriter;
  BufferedWriter bufferedWriter;

  LinkedHashMap<String, String> allColors;
  StringBuilder newCssBuilder;

  public DynamicCss() {
    READ_CSS_PATH = "src/assets/read.css";
    WRITE_CSS_PATH = "src/assets/write.css";
    LINE_BREAK = Constants.LINE_BREAK;
    allColors = new LinkedHashMap<String, String>();
    newCssBuilder = new StringBuilder();
    tempVarColorsBuffer = new StringBuffer();
    NEW_COLOR_INDEX=1;
    COLOR_PREFIX="--color_";
  }

  public DynamicCss(String readPath , String writePath) {
    this();
    READ_CSS_PATH = readPath;
    WRITE_CSS_PATH = writePath;
  }
  
  public static void main(String[] args) {
    DynamicCss classInstance = new DynamicCss();
    classInstance.readRawCss();
    classInstance.writeCleanDynamicCss();
    System.out.print("I AM DONE 2");
  }

  public void readRawCss() {
    try {
      fileReader = new FileReader(READ_CSS_PATH);
      bufferedReader = new BufferedReader(fileReader);
      appendNewCssToBuilder();
      appendVarToNewCss();
      bufferedReader.close();
      fileReader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void writeCleanDynamicCss() {
    try {
      fileWriter = new FileWriter(WRITE_CSS_PATH);
      bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(newCssBuilder.toString());
      tempVarColorsBuffer.setLength(0);
      newCssBuilder.setLength(0);
      bufferedWriter.close();
      fileWriter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void appendNewCssToBuilder() throws IOException {
    String currentLine;
    while ((currentLine = bufferedReader.readLine())!=null) {
      String colorFoundInText = RegexUtils.findColorInText(currentLine);
      addMissingColor(colorFoundInText);

      if (!isNull(colorFoundInText) && isNull(allColors.get(colorFoundInText))) {
//        create hashmap which keep track of color count
        String lineBeforeRawCssChange = currentLine;
        currentLine = currentLine.replace(colorFoundInText, wrapColorKeyWithVar(allColors.get(colorFoundInText)));
        currentLine = fixMissingBracket(currentLine,lineBeforeRawCssChange);
        currentLine = addMissingColon(currentLine);
        System.out.print("CurrentLine"+currentLine);
      }
      newCssBuilder.append(currentLine);
      newCssBuilder.append(LINE_BREAK);
    }
  }

  public void appendVarToNewCss() {    
    tempVarColorsBuffer.append(":root{" + LINE_BREAK);

    for (Entry<String, String> colorEntry :  allColors.entrySet()) {
      String value = colorEntry.getKey().replace(";", "").trim();
      String key = colorEntry.getValue().replace(";", "").trim();
//      System.out.println("value --- "+value);
      if(value.startsWith("rgba") && value.endsWith(")") && !isBracketBalanced(value) ) {
        value=value.substring(0 , value.length()-2);
      }
      
      tempVarColorsBuffer.append(key + " :" + value + ";" + LINE_BREAK);
    }
    
    tempVarColorsBuffer.append("}" + LINE_BREAK);
    newCssBuilder.insert(0 ,tempVarColorsBuffer);

  }
  
  public String fixMissingBracket(String currentLine , String lineBeforeReplaceVars) {
    currentLine = currentLine.replace(";", "");
    boolean isLastCharBracket = (lineBeforeReplaceVars.lastIndexOf(")") > lineBeforeReplaceVars.length()-3) ;
    boolean isContainsLinearGradient = lineBeforeReplaceVars.contains("linear-gradient");
    if (isLastCharBracket && isContainsLinearGradient ) {
      currentLine = currentLine + ")";
    }
    return currentLine;
  }
  
  public void addMissingColor(String colorFoundInText) {
    if (!isNull(colorFoundInText) && isNull(allColors.get(colorFoundInText))) {
      allColors.put(colorFoundInText, COLOR_PREFIX + NEW_COLOR_INDEX);
      NEW_COLOR_INDEX++;
    }
  }

  public String addMissingColon(String currentLine) {
    return currentLine = currentLine.indexOf(";") > -1 ? currentLine : currentLine + ";";
  }
  
  public String wrapColorKeyWithVar(String str) {
   return "var(" + str + ")";
  }
 
}

