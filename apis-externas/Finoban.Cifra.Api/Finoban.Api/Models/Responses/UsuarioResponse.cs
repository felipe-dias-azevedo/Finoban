using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Models.Responses {
    public class UsuarioResponse {
        public string CPF { get; set; }
        public double Renda { get; set; }
        public int ValorImovel { get; set; }
        public int TempoFinanciamento { get; set; }
    }
}
