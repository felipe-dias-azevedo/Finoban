using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Models.Requests {
    public class DataFinanciamentoRequest {
        public double Taxa { get; set; }
        public double TaxaAdministracao { get; set; }
        public double DFI { get; set; }
        public double MIP { get; set; }
        public double TaxaTotal { get; set; }
    }
}
