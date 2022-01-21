package com.luv2code.junit_testcases;


import org.junit.Before;
import org.junit.Test;

import com.luv2code.app.DynamicCss;
import com.luv2code.common.Constants;
import com.luv2code.common.RegexUtils;
import com.sun.net.httpserver.Authenticator.Success;

public class RegexTest {
  RegexUtils regexColor;

  
  @Before
  public void setup() {
     regexColor = new RegexUtils();

  }

  
  @Test
  public void test() {
    System.out.println(regexColor.findColorInText("color: #fff !important;"));
    System.out.println(regexColor.findColorInText("--color_7: rgba(120, 130, 140, .13);"));
  }
  
}
