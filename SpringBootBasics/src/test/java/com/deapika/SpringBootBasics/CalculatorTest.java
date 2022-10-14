package com.deapika.SpringBootBasics;

import com.deapika.SpringBootBasics.calculator.Calculator;
import com.deapika.SpringBootBasics.calculator.Operation;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyChar;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalculatorTest {

  private Calculator calculator;
  private Operation mockOperation;
  
  @Before
  public void setup() {
    mockOperation = Mockito.mock(Operation.class);
    calculator = new Calculator(Collections.singletonList(mockOperation));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void throwExceptionWhenNoSuitableOperationFound() {
    when(mockOperation.handles(anyChar())).thenReturn(false);
    calculator.calculate(2, 2, '*');
  }
  
  @Test
  public void shouldCallApplyMethodWhenSuitableOperationFound() {
    when(mockOperation.handles(anyChar())).thenReturn(true);
    when(mockOperation.apply(2, 2)).thenReturn(4);
    calculator.calculate(2, 2, '*');
    verify(mockOperation, times(1)).apply(2, 2);
  }
}
