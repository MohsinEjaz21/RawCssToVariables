package com.luv2code.common;


public class BalancedBracketsUsingString {

    public boolean isBalanced(String str) {
            
        if (null == str || ((str.length() % 2) != 0)) {
            return false;
        } else {
            char[] ch = str.toCharArray();
            for (char c : ch) {
                if (!(c == '{' || c == '[' || c == '(' || c == '}' || c == ']' || c == ')')) {
                    return false;
                }

            }
        }

        while (str.contains("()") || str.contains("[]") || str.contains("{}")) {
            str = str.replaceAll("\\(\\)", "")
                .replaceAll("\\[\\]", "")
                .replaceAll("\\{\\}", "");
        }
        return (str.length() == 0);

    }

}