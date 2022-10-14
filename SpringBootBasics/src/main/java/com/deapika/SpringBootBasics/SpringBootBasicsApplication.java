package com.deapika.SpringBootBasics;

import com.deapika.SpringBootBasics.calculator.Calculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootBasicsApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(SpringBootBasicsApplication.class, args);
    
    var calculator = ctx.getBean(Calculator.class);
    
    calculator.calculate(137, 21, '+');
    calculator.calculate(137, 21, '*');
    calculator.calculate(137, 21, '-');
    calculator.calculate(137, 21, '/');
    calculator.calculate(137, 21, '%');
//    calculator.calculate(137, 21, 'x');
	}
  
//  @Bean
//  public Calculator calculator(Collection<Operation> operations) {
//    return new Calculator(operations);
//  }

//  @Bean
//  public ApplicationRunner calculationRunner(Calculator calculator) {
//    return args -> {
//      System.out.println("Start Application Runner");
//      calculator.calculate(137, 21, '+');
//      calculator.calculate(137, 21, '*');
//      calculator.calculate(137, 21, '-');
//      System.out.println("End Application Runner");
//    };
//  }
  
  @Bean
  public ApplicationRunner calculationRunner(Calculator calculator,
                          @Value("${lhs}") int lhs,
                          @Value("${rhs}") int rhs,
                          @Value("${op}") char op) {
    return args -> calculator.calculate(lhs, rhs, op);
  }
}
