package com.luv2code.junit_testcases;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

import com.luv2code.common.BalancedBracketsUsingString;

public class BalancedBracketsUsingStringTest {
  private BalancedBracketsUsingString balancedBracketsUsingString;

  @Before
  public void setup() {
      balancedBracketsUsingString = new BalancedBracketsUsingString();
  }

  @Test
  public void givenInvalidCharacterString_whenCheckingForBalance_shouldReturnFalse() {
      boolean result = balancedBracketsUsingString.isBalanced("abc[](){}");
      assertThat(result).isFalse();
  }
  
  @Test
  public void givenEvenLengthBalancedString_whenCheckingForBalance_shouldReturnTrue() {
      boolean result = balancedBracketsUsingString.isBalanced("{[()]}");
      assertThat(result).isTrue();
  }
}
