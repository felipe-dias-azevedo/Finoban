using Finoban.Api.Cifra.Models.Requests;
using Finoban.Api.Cifra.Models.Responses;
using Finoban.Api.Dal.MySQL;
using Finoban.Api.Cifra.Dal.SQLite;
using Finoban.Api.Models;
using Finoban.Api.Models.Requests;
using Finoban.Api.Models.Responses;
using Microsoft.AspNetCore.Mvc;
using System;
using Finoban.Api.Cifra.Models;

namespace Finoban.Api.Controllers
{

    [ApiController]
    [Route("openbanking/v1")]
    public class CifraController : ControllerBase
    {

        public CalculoFinanciamento calculoFinanciamento;
        public ConnectionMySQL conexaoMySQL;
        public ConnectionSQLite conexaoSQLite;
        public ScoreSerasa scoreSerasa;

        [HttpPost("financiamento")]
        public IActionResult GetUsuario([FromBody] UsuarioResponse usuario)
        {
            if (!(usuario is UsuarioResponse))
            {
                var erroResponse = new ErroResponse
                {
                    Ok = false,
                    Status = 400,
                };

                return BadRequest(erroResponse);
            }
            else
            {
                //conexaoMySQL = new ConnectionMySQL("18.207.233.50", "Cifra", "root", "urubu100");

                //double patrimonio = conexaoMySQL.SelectPatrimonio(usuario.CNPJ).Result;

                //calculoFinanciamento = new CalculoFinanciamento();
                //var dataNascimento = conexaoMySQL.SelectYearNasc(usuario.CNPJ);
                //int dateNow = dataNascimento.Result.Year;
                //var date = DateTime.Now.AddYears(-dateNow);
                //int idade = date.Year;
                conexaoSQLite = new ConnectionSQLite(usuario.CNPJ);
                var dataNascimento = conexaoSQLite.Cliente.AnoNascimento;
                var idade = DateTime.Now.AddYears(-dataNascimento).Year;
                scoreSerasa = new ScoreSerasa();
                var score = scoreSerasa.GetScore(usuario.CNPJ).Result;
                if (score == 0)
                {
                    var erroResponse = new ErroResponse
                    {
                        Ok = false,
                        Status = 400,
                    };
                    return BadRequest(erroResponse);
                }
                calculoFinanciamento = new CalculoFinanciamento(idade, conexaoSQLite.Cliente.Patrimonio, 
                    usuario.TempoFinanciamento, score, usuario.ValorImovel);
                FinanciamentoRequest financiamento = new FinanciamentoRequest
                {
                    Ok = true,
                    Status = 200,
                    Data = new DataFinanciamentoRequest
                    {
                        Taxa = Math.Round(calculoFinanciamento.Taxa,2),
                        TaxaAdministracao = Math.Round(calculoFinanciamento.TaxaAdmin,2),
                        DFI = Math.Round(calculoFinanciamento.DFI,2),
                        MIP = Math.Round(calculoFinanciamento.MIP,2),
                        TaxaTotal = Math.Round(calculoFinanciamento.TaxaTotal,2)
                    }
                };
                financiamento.Data.TaxaTotal = Math.Round(financiamento.Data.Taxa + financiamento.Data.MIP +
                    financiamento.Data.TaxaAdministracao + financiamento.Data.DFI, 2);
                return Ok(financiamento);
            }
        }
        [HttpPost("conta")]
        public IActionResult GetConta([FromBody] ContaRequest model)
        {
            if (!(model is ContaRequest))
            {
                var erroResponse = new ErroResponse
                {
                    Ok = false,
                    Status = 400
                };

                return BadRequest(erroResponse);
            }
            else
            {
                conexaoSQLite = new ConnectionSQLite(model.CNPJ);

                var possuiConta = conexaoSQLite.Cliente.PossuiConta;

                if (!possuiConta)
                {
                    var erroResponse = new ErroResponse
                    {
                        Ok = false,
                        Status = 400,
                    };
                    return BadRequest(erroResponse);
                }
                else
                {
                    var contaResponse = new ContaResponse
                    {
                        Ok = true,
                        Status = 200,
                        Data = new DataContaResponse
                        {
                            Banco = "Banco Cifra",
                            PossuiConta = possuiConta
                        }
                    };
                    return Ok(contaResponse);
                }
            }
        }


    }
}
