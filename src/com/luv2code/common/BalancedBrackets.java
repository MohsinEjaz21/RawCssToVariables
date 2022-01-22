package com.luv2code.common;

import java.util.Stack;

public class BalancedBrackets {

  public static boolean isBracketBalanced(String str) {
    StringBuffer stringBuffer = new StringBuffer(str);
    Stack<Integer> brackets = new Stack<Integer>();

    for (int charIndex = 0; charIndex < stringBuffer.length(); charIndex++) {

      if (stringBuffer.charAt(charIndex) == '(' || stringBuffer.charAt(charIndex) == '{' || stringBuffer.charAt(
          charIndex) == '[') {

        brackets.push(charIndex);
      } else if (stringBuffer.charAt(charIndex) == ')' || stringBuffer.charAt(charIndex) == '}' || stringBuffer.charAt(
          charIndex) == ']') {

        boolean isUnMatchClosingBracket = brackets.empty();
        if(isUnMatchClosingBracket) {          
          replaceExtraBracketsWithMinusOne(stringBuffer, charIndex);
        }else {
          replaceOpenBracketsWithZero(stringBuffer,brackets);
          replaceCloseBracketsWithOne(stringBuffer, charIndex);
          brackets.pop();
        }
      }
    }

    // if stack is not empty then pop out all
    // elements from it and replace -1 at that
    // index of the string
    while (!brackets.empty()) {
      stringBuffer.replace(brackets.peek(), 1, "-1"); 
      brackets.pop();
    }

    return stringBuffer.indexOf("-1") == -1;
  }
  
  public static void replaceOpenBracketsWithZero(StringBuffer stringBuffer, Stack<Integer> brackets) {
    stringBuffer.replace(brackets.peek(), brackets.peek() + 1, "0");
  }

  public static void replaceCloseBracketsWithOne(StringBuffer stringBuffer, int charIndex) {
    stringBuffer.replace(charIndex, charIndex + 1, "1"); 
  }

  public static void replaceExtraBracketsWithMinusOne(StringBuffer stringBuffer, int charIndex) {
    stringBuffer.replace(charIndex, charIndex + 1, "-1");
  }


  
}