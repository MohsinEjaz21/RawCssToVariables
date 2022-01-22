package com.luv2code.common;

  public class RegexUtils {

    public static String findColorInText(String cssLine) {
      cssLine = cssLine.trim();
      String foundRgbColor = "";
      
      boolean isNoBrace = cssLine.indexOf("{") == -1;
      boolean isWhite =cssLine.indexOf("white")> 0;
      boolean isBlack =cssLine.indexOf("black")> 0;
      boolean isHexColor =cssLine.indexOf("#")> 0;
      boolean isContainsUrl =cssLine.indexOf("url")> -1;
      boolean isRgb =cssLine.indexOf("rgb")> 0;      
      boolean isGreaterThan =cssLine.indexOf(">")> 0;
      boolean isContainHover =cssLine.indexOf(":hover")> 0;
      boolean isContainsBracket =cssLine.indexOf("[")> 0;
      boolean isContainDot =cssLine.indexOf(".")> 0;
      boolean isContainRgb =cssLine.indexOf("rgb")> 0;


      if(isContainsUrl||!isNoBrace || isGreaterThan || (isContainDot &&  !isContainRgb)) {
        return null;
      }
      
      if(isRgb) {
        foundRgbColor =  cssLine.substring(cssLine.indexOf("rgb"));
      }else if(isHexColor) {
        foundRgbColor =  cssLine.substring(cssLine.indexOf("#"));
      }
      else if(isWhite) {
        foundRgbColor =  "white";
      }else if(isBlack) {
        foundRgbColor =  "black";
      }
      
      foundRgbColor = foundRgbColor.replace("!important","");     
      foundRgbColor = foundRgbColor.replaceAll(";", "").trim();     

      return foundRgbColor; 
    }
     
  }
