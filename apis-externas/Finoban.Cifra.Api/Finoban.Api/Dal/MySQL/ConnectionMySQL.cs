using MySql.Data.MySqlClient;
using System;
using System.Threading.Tasks;

namespace Finoban.Api.Dal.MySQL
{
    public class ConnectionMySQL
    {

        public MySqlConnectionStringBuilder stringBuilder;

        public ConnectionMySQL(string server, string database, string userID, string password)
        {
            stringBuilder = new MySqlConnectionStringBuilder
            {
                Server = server,
                Database = database,
                UserID = userID,
                Password = password,
                SslMode = MySqlSslMode.Required
            };
        }

        public ConnectionMySQL(MySqlConnectionStringBuilder stringBuilder)
        {
            this.stringBuilder = stringBuilder;
        }

        public async void SelectAllTable()
        {
            using (var conn = new MySqlConnection(stringBuilder.ConnectionString))
            {
                Console.WriteLine("Estabelecendo conexão...");
                await conn.OpenAsync();

                using (var command = conn.CreateCommand())
                {
                    command.CommandText = "SELECT * FROM teste;";

                    using (var reader = await command.ExecuteReaderAsync())
                    {
                        while (await reader.ReadAsync())
                        {
                            Console.WriteLine(string.Format(
                                "Lendo da Tabela ['id':{0}, 'nome':'{1}']",
                                reader.GetInt32(0),
                                reader.GetString(1)));
                        }
                    }
                }
                Console.WriteLine("Fechando conexão...");
            }

        }
        public async Task<double> SelectPatrimonio(string cnpj)
        {
            double patrimonio = 0.0;
            using (var conn = new MySqlConnection(stringBuilder.ConnectionString))
            {
                Console.WriteLine("Estabelecendo conexão...");
                await conn.OpenAsync();

                using (var command = conn.CreateCommand())
                {
                    command.CommandText = $"SELECT * FROM Cliente where cnpj = '{cnpj}';";

                    using (var reader = await command.ExecuteReaderAsync())
                    {
                        while (await reader.ReadAsync())
                        {
                            Console.WriteLine(string.Format(
                                "Lendo da Tabela [cnpj:{0}, nome:'{1}', patrimonio: {2}, dataNascimento: {3}]",
                                reader.GetString(0),
                                reader.GetString(1),
                                reader.GetDouble(2),
                                reader.GetString(3)));

                            patrimonio = reader.GetDouble(2);
                        }
                    }
                }
                Console.WriteLine("Fechando conexão...");
            }
            return patrimonio;
        }

        public async Task<DateTime> SelectYearNasc(string cnpj)
        {
            DateTime date = new DateTime();
            using (var conn = new MySqlConnection(stringBuilder.ConnectionString))
            {
                Console.WriteLine("Estabelecendo conexão...");
                await conn.OpenAsync();

                using (var command = conn.CreateCommand())
                {
                    command.CommandText = $"SELECT * FROM Cliente where cnpj = '{cnpj}';";

                    using (var reader = await command.ExecuteReaderAsync())
                    {
                        while (await reader.ReadAsync())
                        {
                            Console.WriteLine(string.Format(
                                "Lendo da Tabela [cnpj:{0}, nome:'{1}', patrimonio: {2}, dataNascimento: {3}]",
                                reader.GetString(0),
                                reader.GetString(1),
                                reader.GetDouble(2),
                                reader.GetString(3)));


                            date = DateTime.Parse(reader.GetString(3));
                        }
                    }
                }
                Console.WriteLine("Fechando conexão...");
            }
            return date;
        }

        public async Task<bool> VerifyPossuiConta(string cnpj)
        {
            bool possuiConta = false;
            using (var conn = new MySqlConnection(stringBuilder.ConnectionString))
            {
                Console.WriteLine("Estabelecendo conexão...");
                await conn.OpenAsync();

                using (var command = conn.CreateCommand())
                {
                    command.CommandText = $"SELECT * FROM Cliente where cnpj = '{cnpj}';";

                    using (var reader = await command.ExecuteReaderAsync())
                    {
                        while (await reader.ReadAsync())
                        {
                            Console.WriteLine(string.Format(
                                "Lendo da Tabela [cnpj:{0}, nome:'{1}', patrimonio: {2}, dataNascimento: {3}]",
                                reader.GetString(0),
                                reader.GetString(1),
                                reader.GetDouble(2),
                                reader.GetString(3)));
                            possuiConta = true;
                        }
                    }
                }
                Console.WriteLine("Fechando conexão...");
            }
            return possuiConta;
        }
    }

}
