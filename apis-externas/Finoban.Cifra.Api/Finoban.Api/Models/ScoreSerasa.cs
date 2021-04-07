using Finoban.Api.Cifra.Models.Requests;
using Finoban.Api.Cifra.Models.Responses;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Finoban.Api.Cifra.Models
{
    public class ScoreSerasa
    {
        public async Task<int> GetScore(string cnpj)
        {
            var scoreSerasaRequest = new ScoreSerasaRequest
            {
                CNPJ = int.Parse(cnpj)
            };

            var jsonRequest = ConverterRequestParaCamelCase(scoreSerasaRequest);
            var stringContent = new StringContent(jsonRequest, Encoding.UTF8, "application/json");
            var url = "http://localhost:8082/consultas/v1/score";
            var httpClient = new HttpClient();
            var httpResponseMessage = await httpClient.PostAsync(url, stringContent);
            var content = await httpResponseMessage.Content.ReadAsStringAsync();
            var scoreSerasaResponse = JsonConvert.DeserializeObject<ScoreSerasaResponse>(content);

            return scoreSerasaResponse.Data.Score;
        }

        #region Converters
        private static string ConverterRequestParaCamelCase(object resquest)
        {

            DefaultContractResolver contractResolver = new DefaultContractResolver
            {
                NamingStrategy = new CamelCaseNamingStrategy()
            };

            var jsonRequest = JsonConvert.SerializeObject(resquest, new JsonSerializerSettings
            {
                ContractResolver = contractResolver,
                Formatting = Formatting.None
            });

            return jsonRequest;
        }
        #endregion
    }
}
