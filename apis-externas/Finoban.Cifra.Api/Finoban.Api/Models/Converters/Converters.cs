using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace Finoban.Api.Cifra.Models.Converters
{
    public static class Converters
    {
        public static string ConverterRequestParaCamelCase(this object resquest)
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
    }
}