using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Cifra.Models.Responses
{
    public class ContaResponse
    {
        public bool Ok { get; set; }
        public int Status { get; set; }
        public DataContaResponse Data { get; set; }
    }
}
