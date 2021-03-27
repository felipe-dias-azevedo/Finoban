using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Models
{
    public class CalculoFinanciamento
    {
        public double Taxa { get; set; }

        public CalculoFinanciamento()
        {
            Taxa = 0.0;
        }

        public double CalcularTaxa(double renda)
        {

            double aux;
            if (renda >= 45000)
            {
                aux = (-1.75 * 25) + 53.75;
                Taxa = ((aux * 12) / 100) / 100;
            }

            if (renda < 45_000 && renda >= 40_000)
            {
                aux = (-1.5 * 5) + 47.5;
                Taxa = ((aux * 12) / 100) / 100;
            }

            if (renda < 40_000 && renda >= 35_000)
            {
                aux = (-1.2 * 5) + 41;
                Taxa = ((aux * 12) / 100) / 100;
            }

            if (renda < 35000 && renda >= 30000)
            {
                aux = (-1 * 5) + 35;
                Taxa = ((aux * 12) / 100) / 100;
            }
            if (renda < 25000)
            {
                aux = (-0.8 * 5) + 29;
                Taxa = ((aux * 12) / 100) / 100;
            }

            return Taxa;
        }

        //public double CalcularTaxaAdministracao(int idade)
        //{

        //    if (idade > 30)
        //        return 0.0;
        //}
    }
}
