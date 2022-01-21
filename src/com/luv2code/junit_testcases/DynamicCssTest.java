package com.luv2code.junit_testcases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.luv2code.app.DynamicCss;

class DynamicCssTest {

  @Test
  void test() {
    String READ_CSS_PATH = "src/assets/read.css";
    String WRITE_CSS_PATH = "src/assets/write1.css";
    DynamicCss classInstance = new DynamicCss(READ_CSS_PATH, WRITE_CSS_PATH);
    System.out.println(DynamicCss.READ_CSS_PATH);
    System.out.println(DynamicCss.WRITE_CSS_PATH);

    classInstance.readRawCss();
    classInstance.writeCleanDynamicCss();
    System.out.print("I AM DONE");
  }

}
