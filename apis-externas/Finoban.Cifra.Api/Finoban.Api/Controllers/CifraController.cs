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
using Finoban.Api.Repository;
using Finoban.Api.Service;

namespace Finoban.Api.Controllers
{

    [ApiController]
    [Route("openbanking/v1")]
    public class CifraController : ControllerBase
    {
        private CalculoFinanciamento calculoFinanciamento;
        private ConnectionSQLite conexaoSQLite;
        private ISerasaRepository _repository;

        public CifraController()
        {
            this._repository = new SerasaService();
        }
        
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
                conexaoSQLite = new ConnectionSQLite(usuario.CPF);
                var dataNascimento = conexaoSQLite.Cliente.AnoNascimento;
                var idade = DateTime.Now.AddYears(-dataNascimento).Year;
                var dataRequestSerasa = this._repository.GetScore(usuario.CPF).Result;
                if (dataRequestSerasa.Data.Score == 0)
                {
                    var erroResponse = new ErroResponse
                    {
                        Ok = false,
                        Status = 400,
                    };
                    return BadRequest(erroResponse);
                }
                calculoFinanciamento = new CalculoFinanciamento(idade, conexaoSQLite.Cliente.Patrimonio, 
                    usuario.TempoFinanciamento, dataRequestSerasa.Data.Score, usuario.ValorImovel);
                
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
                conexaoSQLite = new ConnectionSQLite(model.CPF);

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
