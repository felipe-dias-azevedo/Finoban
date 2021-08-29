using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Models
{
    public class CalculoFinanciamento
    {
        public double Taxa { get; set; }
        public double TaxaAdmin { get; set; }
        public double DFI { get; set; }
        public double MIP { get; set; }
        public double TaxaTotal { get; set; }

        public CalculoFinanciamento(int idade, double valorPatrimonio, int tempoFinanciamento, int score, double valorImovel)
        {
            double taxaIdade = CalcularTaxaIdade(idade, tempoFinanciamento);
            double calcularTaxaScore = CalcularTaxaScore(idade, score);
            TaxaAdmin = 1.4;
            DFI = CalcularTaxaImovel(valorImovel);
            MIP = CalcularTaxaMip(idade);
            TaxaTotal = Taxa + DFI + MIP + TaxaAdmin;
            Taxa = 0.046 + ((calcularTaxaScore * 3 + taxaIdade * 2) / 5);
        }

        public double CalcularTaxaIdade(int idade, int tempoFinanciamento)
        {
            int ideal = 85;
            int max = idade + tempoFinanciamento;
            double x = max / ideal;
            if (max > ideal)
            {
                x = 1.8;
            }

            if (x < 1.0)
            {
                x = 0.7;
            }
            else if (x > 1.0)
            {
                x = 1.4;
            }

            return 3 * Math.Pow(x, 2) + 3.3;
        }

        public double CalcularTaxaMip(int idade)
        {
            double y = 0.0;
            if (idade < 43)
            {
                y = 0.76;
            } else if (idade < 49)
            {
                y = 0.56;
            } else if (idade < 54)
            {
                y = 0.44;
            } else if (idade < 59)
            {
                y = 0.30;
            } else if (idade < 64)
            {
                y = 0.28;
            } else if (idade < 69)
            {
                y = 0.56;
            } else if (idade < 73)
            {
                y = 0.66;
            } else if (idade < 78)
            {
                y = 0.98;
            } else
            {
                y = 1.1;
            }

            return y;
        }

        public double CalcularTaxaScore(int idade, int score)
        {
            int ideal = 0;
            if (idade < 40)
            {
                ideal = 500;
            }
            else if (idade < 50)
            {
                ideal = 650;
            }
            else if (idade < 65)
            {
                ideal = 750;
            }
            else
            {
                ideal = 850;
            }

            double x = score / ideal;

            if (x < 1.0)
            {
                x = 1.0;
            } else
            {
                x = 1.3;
            }
            return -2 * Math.Pow(x,2) + 7;
        }

        public static double CalcularTaxaImovel(double valorImovel)
        {
            double x = 0.0;
            double y = 0.0;
            if (valorImovel > 1_500_000)
            {
                x = 0.3;
                y = 0.7;
                
            } else if (valorImovel > 1_200_000)
            {
                x = 0.6;
                y = 1.1;
            } else if (valorImovel > 900_000)
            {
                x = 0.9;
                y = 1.4;
            } else if (valorImovel > 600_000)
            {
                x = 1.2;
                y = 1.8;
            } else
            {
                x = 1.5;
                y = 2.2;
            }

            return -0.3675 * x + y;
        }
    }
}
