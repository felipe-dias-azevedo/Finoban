Feature: Gestão

Scenario: Resgatar regiões registradas no banco de dados para simulação dado que usuário não sabe valor do imóvel
  Given que sou um usuário
  And desejo realizar uma simulação em uma determinada região
  When clicar para escolher a região
  Then deverá retornar todas as regiões registradas no banco de dados