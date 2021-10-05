Feature: Gestão de operações matemáticas

Scenario: Soma de dois números
  Given Que tenho um número 72
  And Outro número 55
  When Somar os dois
  Then Deverá retornar 127

Scenario: Subtração de dois números
  Given Que tenho um número 72
  And Outro número 55
  When Subtrair os dois
  Then Deverá retornar 17
