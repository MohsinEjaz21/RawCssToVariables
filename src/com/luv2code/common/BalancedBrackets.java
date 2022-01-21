package com.luv2code.common;

import java.util.Stack;

public class BalancedBrackets {

//    public static boolean isBracketBalanced(String str) {
//            
//        if (null == str || ((str.length() % 2) != 0)) {
//            return false;
//        } else {
//            char[] ch = str.toCharArray();
//            for (char c : ch) {
//                if (!(c == '{' || c == '[' || c == '(' || c == '}' || c == ']' || c == ')')) {
//                    return false;
//                }
//
//            }
//        }
//
//        while (str.contains("()") || str.contains("[]") || str.contains("{}")) {
//            str = str.replaceAll("\\(\\)", "")
//                .replaceAll("\\[\\]", "")
//                .replaceAll("\\{\\}", "");
//        }
//        return (str.length() == 0);
//
//    }
    
    public static boolean isBracketBalanced(String str)
    {
        StringBuffer a = new StringBuffer(str);
        Stack<Integer> st = new Stack<Integer>();
     
        // run the loop upto end of the string
        for (int i = 0; i < a.length(); i++)
        {
     
            // if a[i] is opening bracket then push
            // into stack
            if (a.charAt(i) == '(' || a.charAt(i) == '{' || a.charAt(i) == '[')
                st.push(i);
             
            // if a[i] is closing bracket ')'
            else if (a.charAt(i) == ')' || a.charAt(i) == '}' ||a.charAt(i) == ']')
            {
     
                // If this closing bracket is unmatched
                if (st.empty())
                    a.replace(i, i + 1, "-1");
                 
                else
                {
     
                    // replace all opening brackets with 0
                    // and closing brackets with 1
                    a.replace(i, i + 1, "1");
                    a.replace(st.peek(), st.peek() + 1, "0");
                    st.pop();
                }
            }
        }
     
        // if stack is not empty then pop out all
        // elements from it and replace -1 at that
        // index of the string
        while (!st.empty())
        {
            a.replace(st.peek(), 1, "-1");
            st.pop();
        }
     
        // print final string
        
        System.out.println(a);
        return a.indexOf("-1") == -1;
    }
     
    
    

}