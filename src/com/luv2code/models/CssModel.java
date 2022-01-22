package com.luv2code.models;
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

public class CssModel {

  private String colorKey ="";
  private String colorValue ="";
  private int colorCount =0;
  
  public CssModel() {
    this.colorKey ="";
    this.colorValue="";
    this.colorCount=0;
  }

  public CssModel(String colorKey, String colorValue, int colorCount) {
    this();
    this.colorKey = colorKey;
    this.colorValue = colorValue;
    this.colorCount = colorCount;
  }

  public String getColorKey() {
    return colorKey;
  }

  public void setColorKey(String colorKey) {
    this.colorKey = colorKey;
  }

  public String getColorValue() {
    return colorValue;
  }

  public void setColorValue(String colorValue) {
    this.colorValue = colorValue;
  }
  
  

  public int getColorCount() {
    return colorCount;
  }

  public void setColorCount(int colorCount) {
    this.colorCount = colorCount;
  }

  public void incColorCount(int count) {
    this.colorCount = count+=1;
  }

  
  @Override
  public String toString() {
    return  "/* "+colorCount+" */ "+colorKey+ " : "+ colorValue +" ; ";
  }
  
  
  
 
}

