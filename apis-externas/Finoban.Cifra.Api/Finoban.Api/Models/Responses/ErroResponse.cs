﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Cifra.Models.Responses
{
    public class ErroResponse
    {
        public bool Ok { get; set; }
        public int Status { get; set; }
        public string Message{ get; set; }
    }
}
