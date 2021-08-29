using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Cifra.Models.Responses
{
    public class ScoreSerasaResponse
    {
        public bool Ok { get; set; }
        public int Status { get; set; }
        public DataScoreSerasa Data { get; set; }
    }
}
