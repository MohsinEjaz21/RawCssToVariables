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
import com.luv2code.models.CssModel;

import static com.luv2code.common.Utils.*;
import static com.luv2code.common.BalancedBrackets.*;

public class DynamicCss {

  public static String READ_CSS_PATH;
  public static String WRITE_CSS_PATH;
  public static String LINE_BREAK;
  public static Integer NEW_COLOR_INDEX;
  public static String COLOR_PREFIX;
  public static StringBuffer tempVarColorsBuffer;
  public static HashMap<String, CssModel> colorCount;

  FileReader fileReader;
  BufferedReader bufferedReader;

  FileWriter fileWriter;
  BufferedWriter bufferedWriter;

  LinkedHashMap<String, CssModel> allColors;
  StringBuilder newCssBuilder;

  public DynamicCss() {
    READ_CSS_PATH = "src/assets/read.css";
    WRITE_CSS_PATH = "src/assets/write.css";
    LINE_BREAK = Constants.LINE_BREAK;
    allColors = new LinkedHashMap<String, CssModel>();
    newCssBuilder = new StringBuilder();
    tempVarColorsBuffer = new StringBuffer();
    NEW_COLOR_INDEX = 1;
    COLOR_PREFIX = "--color_";
  }

  public DynamicCss(String readPath, String writePath) {
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
    while ((currentLine = bufferedReader.readLine()) != null) {
      String colorFoundInText = RegexUtils.findColorInText(currentLine);
      addMissingColor(colorFoundInText);
      updateExistingColorCount(colorFoundInText);

      CssModel foundCssModel = allColors.get(colorFoundInText);

      if (!isNull(colorFoundInText) && foundCssModel != null) {
        // create hashmap which keep track of color count

        String lineBeforeRawCssChange = currentLine;
        String colorKey = allColors.get(colorFoundInText).getColorKey();
        currentLine = currentLine.replace(colorFoundInText, wrapColorKeyWithVar(colorKey));
        currentLine = fixMissingBracket(currentLine, lineBeforeRawCssChange);
        currentLine = addMissingColon(currentLine);
        System.out.print("CurrentLine" + currentLine);
      }
      newCssBuilder.append(currentLine);
      newCssBuilder.append(LINE_BREAK);
    }
  }

  public void appendVarToNewCss() {
    tempVarColorsBuffer.append(":root{" + LINE_BREAK);

    for (Entry<String, CssModel> colorEntry : allColors.entrySet()) {
      
      CssModel currCssModel = colorEntry.getValue();
      String key = currCssModel.getColorKey().replace(";", "").trim();
      String value = currCssModel.getColorValue().replace(";", "").trim();
      
      
      
      // System.out.println("value --- "+value);
      if (value.startsWith("rgba") && value.endsWith(")") && !isBracketBalanced(value)) {
        value = value.substring(0, value.length() - 2);
      }
      
      currCssModel.setColorKey(key);
      currCssModel.setColorValue(value);
      tempVarColorsBuffer.append(currCssModel.toString() + LINE_BREAK);
    }

    tempVarColorsBuffer.append("}" + LINE_BREAK);
    newCssBuilder.insert(0, tempVarColorsBuffer);

  }

  public String fixMissingBracket(String currentLine, String lineBeforeReplaceVars) {
    currentLine = currentLine.replace(";", "");
    boolean isLastCharBracket = (lineBeforeReplaceVars.lastIndexOf(")") > lineBeforeReplaceVars.length() - 3);
    boolean isContainsLinearGradient = lineBeforeReplaceVars.contains("linear-gradient");
    if (isLastCharBracket && isContainsLinearGradient) {
      currentLine = currentLine + ")";
    }
    return currentLine;
  }

  public void addMissingColor(String colorFoundInText) {
    CssModel cssModel = allColors.get(colorFoundInText);
    if (!isNull(colorFoundInText) && cssModel == null) {
      int colorCount = 0;
      String colorKey = COLOR_PREFIX + NEW_COLOR_INDEX;
      allColors.put(colorFoundInText, new CssModel(colorKey, colorFoundInText, colorCount));
      NEW_COLOR_INDEX++;
    }
  }

  public void updateExistingColorCount(String colorFoundInText) {
    CssModel cssModel = allColors.get(colorFoundInText);
    if (!isNull(colorFoundInText) && cssModel != null) {
      cssModel.incColorCount(cssModel.getColorCount());
      allColors.replace(colorFoundInText, cssModel);
    }
  }

  public String addMissingColon(String currentLine) {
    return currentLine = currentLine.indexOf(";") > -1 ? currentLine : currentLine + ";";
  }

  public String wrapColorKeyWithVar(String str) {
    return "var(" + str + ")";
  }

}
