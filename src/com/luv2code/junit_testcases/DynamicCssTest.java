package com.luv2code.junit_testcases;


import org.junit.Before;
import org.junit.Test;

import com.luv2code.app.DynamicCss;
import com.luv2code.common.BalancedBracketsUsingString;

public class DynamicCssTest {
  DynamicCss classInstance;
  String READ_CSS_PATH;
  String WRITE_CSS_PATH;
  
  @Before
  public void setup() {
    READ_CSS_PATH = "src/assets/read.css";
    WRITE_CSS_PATH = "src/assets/write1.css";
    classInstance = new DynamicCss(READ_CSS_PATH, WRITE_CSS_PATH);
  }

  
  @Test
  public void test() {
    classInstance.readRawCss();
    classInstance.writeCleanDynamicCss();
    System.out.print("I AM DONE");
  }

}
 