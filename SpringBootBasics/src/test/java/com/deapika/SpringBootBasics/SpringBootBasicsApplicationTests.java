package com.deapika.SpringBootBasics;

import com.deapika.SpringBootBasics.calculator.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootBasicsApplication.class)
public class SpringBootBasicsApplicationTests {
  
  @Autowired
  private Calculator calculator;
  
  @Test
  public void doingMultiplicationShouldSucceed() {
    calculator.calculate(12, 13, '*');
  }

	@Test(expected = IllegalArgumentException.class)
  public void doingDivisionShouldFail() {
    calculator.calculate(12, 13, 'x');
  }

}
