using Finoban.Api.Cifra.Models.Responses;
using Finoban.Api.Dal.MySQL;
using Finoban.Api.Models;
using Finoban.Api.Models.Requests;
using Finoban.Api.Models.Responses;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Finoban.Api.Controllers
{

    [Route("/openbanking/v1")]
    [ApiController]
    public class CifraController : Controller
    {

        public CalculoFinanciamento calculoFinanciamento;
        public Connection conexao;

        [HttpPost("/openbanking/v1/financiamento")]
        public IActionResult GetUsuario([FromBody] UsuarioResponse usuario)
        {
            if (!ModelState.IsValid)
            {
                var erroResponse = new ErroResponse
                {
                    Ok = false,
                    Status = 400
                };

                return Json(erroResponse);
            }
            else
            {
                conexao = new Connection("18.207.233.50", "Cifra", "root", "urubu100");

                double patrimonio = conexao.SelectPatrimonio(usuario.CNPJ).Result;

                calculoFinanciamento = new CalculoFinanciamento();
                var dataNascimento = conexao.SelectYearNasc(usuario.CNPJ);
                int dateNow = dataNascimento.Result.Year;
                var date = DateTime.Now.AddYears(-dateNow);
                int idade = date.Year;


                double taxa = calculoFinanciamento.CalcularTaxa(patrimonio, idade);

                FinanciamentoRequest financiamento = new FinanciamentoRequest
                {
                    Ok = true,
                    Status = 200,
                    Data = new Data
                    {
                        Taxa = Math.Round(taxa, 2),
                        TaxaAdministracao = 0.2,
                        DFI = 0.01,
                        MIP = 0.003
                    }
                };
                financiamento.Data.TaxaTotal = Math.Round(financiamento.Data.Taxa + financiamento.Data.MIP +
                    financiamento.Data.TaxaAdministracao + financiamento.Data.DFI, 2);

                return Json(financiamento);
            }
        }
    }
}
