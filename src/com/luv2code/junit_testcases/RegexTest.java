package com.luv2code.junit_testcases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.luv2code.app.DynamicCss;
import com.luv2code.common.Constants;
import com.luv2code.common.RegexUtils;
import com.sun.net.httpserver.Authenticator.Success;

class RegexTest {

  @Test
  void test() {
    RegexUtils regexColor = new RegexUtils();
    System.out.println(regexColor.findColorInText("color: #fff !important;"));
    System.out.println(regexColor.findColorInText("--color_7: rgba(120, 130, 140, .13);"));
  }
  
}
