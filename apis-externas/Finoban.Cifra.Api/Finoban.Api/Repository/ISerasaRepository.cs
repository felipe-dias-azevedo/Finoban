using System.Threading.Tasks;
using Finoban.Api.Cifra.Models.Responses;

namespace Finoban.Api.Repository
{
    public interface ISerasaRepository
    {
        Task<ScoreSerasaResponse> GetScore(string cpf);
    }
}