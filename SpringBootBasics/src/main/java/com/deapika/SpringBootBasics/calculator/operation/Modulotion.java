package com.deapika.SpringBootBasics.calculator.operation;

import com.deapika.SpringBootBasics.calculator.Operation;
import org.springframework.stereotype.Component;

@Component
public class Modulotion implements Operation {
  
  @Override
  public int apply(int lhs, int rhs) {
    return lhs % rhs;
  }
  
  @Override
  public boolean handles(char op) {
    return '%' == op;
  }
}
