using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Models.Requests {
    public class FinanciamentoRequest {
        public bool Ok { get; set; }
        public int Status { get; set; }
        public Data Data { get; set; }
    }
}
