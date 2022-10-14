package com.deapika.SpringBootBasics;

import com.deapika.SpringBootBasics.calculator.operation.Multiplication;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class MultiplicationTest {

  private final Multiplication addition = new Multiplication();
  
  @Test
  public void shouldMatchOperation() {
    assertThat(addition.handles('*')).isTrue();
    assertThat(addition.handles('/')).isFalse();
  }
  
  @Test
  public void shouldCorrectlyApplyFormula() {
    assertThat(addition.apply(2, 2)).isEqualTo(4);
    assertThat(addition.apply(12, 10)).isEqualTo(120);
  }
}
