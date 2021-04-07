using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Cifra.Models
{
    public class Cliente
    {
        public double Patrimonio { get; set; }
        public int AnoNascimento { get; set; }
        public bool PossuiConta { get; set; }
    }
}
