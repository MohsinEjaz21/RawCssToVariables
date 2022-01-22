package com.luv2code.junit_testcases;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

import com.luv2code.common.BalancedBrackets;

public class BalanceBracketTest {
  private BalancedBrackets balancedBracketsUsingString;

  @Before
  public void setup() {
      balancedBracketsUsingString = new BalancedBrackets();
  }

  @Test
  public void givenInvalidCharacterString_whenCheckingForBalance_shouldReturnFalse() {
      boolean result = BalancedBrackets.isBracketBalanced("abc[](){}");
      assertThat(result).isTrue();
  }
  
  @Test
  public void givenEvenLengthBalancedString_whenCheckingForBalance_shouldReturnTrue() {
      boolean result = BalancedBrackets.isBracketBalanced("{[()]}");
      assertThat(result).isTrue();
  }
  
  @Test
  public void givenEvenLengthBalancedString_fixing_missing_bracket_shouldReturnTrue() {
      boolean result = BalancedBrackets.isBracketBalanced("1px solid rgba(0,0,0,.12");
      assertThat(result).isFalse();
  }
  
  
}
