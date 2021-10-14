using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Finoban.Api.Cifra.Models.Converters;
using Finoban.Api.Cifra.Models.Requests;
using Finoban.Api.Cifra.Models.Responses;
using Finoban.Api.Repository;
using Newtonsoft.Json;

namespace Finoban.Api.Service
{
    public class SerasaService : ISerasaRepository
    {
        public async Task<ScoreSerasaResponse> GetScore(string cpf)
        {
            var scoreSerasaRequest = new ScoreSerasaRequest
            {
                CPF = Convert.ToInt32(cpf)
            };
            var jsonRequest = scoreSerasaRequest.ConverterRequestParaCamelCase();
            var stringContent = new StringContent(jsonRequest, Encoding.UTF8, "application/json");
            var url = "http://localhost:8082/consultas/v1/score";
            var httpClient = new HttpClient();
            var httpResponseMessage = await httpClient.PostAsync(url, stringContent);
            var content = await httpResponseMessage.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<ScoreSerasaResponse>(content);
        }
    }
}