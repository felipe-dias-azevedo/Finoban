Feature: Gestão de Simulaçoes realizadas pelo usuário


  Scenario: Simulação dado que desejo escolher uma regiao
    Given que sou um usuario
    And desejo realizar uma simulacao
    When escolher que nao conheco o valor do imovel
    Then devera retornar todas as regioes cadastradas no banco

  Scenario: Simulação de financiamento imobiliar dado que o usuário não sabe a região e escolhe a região centro
    Given que sou um usuario
    And desejo realizar uma simulacao
    When inserir os dados
      | renda | regiao | tempoFinanciamento | porcentagemRenda | valorImovel |
      | 50000 | Centro | 15                 | 15               | 2500000     |
    And realizar a simulacao
    Then devera retornar os dados da simulacao