package com.luv2code.common;

  public class RegexUtils {

    public static String findColorInText(String cssLine) {
      cssLine = cssLine.trim();
      String foundRgbColor = "";
      
      boolean isNoBrace = cssLine.indexOf("{") == -1;
      boolean isWhite =cssLine.indexOf("white")> 0;
      boolean isBlack =cssLine.indexOf("black")> 0;
      boolean isHexColor =cssLine.indexOf("#")> 0;
      boolean isRgb =cssLine.indexOf("rgb")> 0;
            
      if(isRgb) {
        foundRgbColor =  cssLine.substring(cssLine.indexOf("rgb"));
      }else if(isNoBrace && isHexColor) {
        foundRgbColor =  cssLine.substring(cssLine.indexOf("#"));
      }
      else if(isNoBrace && isWhite) {
        foundRgbColor =  "white";
      }else if(isNoBrace && isBlack) {
        foundRgbColor =  "black";
      }
      
      foundRgbColor = foundRgbColor.replace("!important","");     
      foundRgbColor = foundRgbColor.replaceAll(";", "").trim();     

      return foundRgbColor;
    }
     
  }
