using System.Data.SQLite;
using System;
using Finoban.Api.Cifra.Models;

namespace Finoban.Api.Cifra.Dal.SQLite
{
    public class ConnectionSQLite
    {
        public Cliente Cliente { get; set; }
        public ConnectionSQLite(string cnpj)
        {
            SQLiteConnection sqlite_conn;
            sqlite_conn = CreateConnection();
            CreateTable(sqlite_conn);
            Cliente = ReadCliente(sqlite_conn, cnpj);
        }

        public SQLiteConnection CreateConnection()
        {
            SQLiteConnection sqlite_conn;
            sqlite_conn = new SQLiteConnection("Data Source=serasa.db; Version = 3; New = True; Compress = True;");
            try
            {
                sqlite_conn.Open();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            return sqlite_conn;
        }

        public void CreateTable(SQLiteConnection conn)
        {
            SQLiteCommand sqlite_cmd;
            string Createsql = "CREATE TABLE IF NOT EXISTS Cliente (cnpj INT PRIMARY KEY NOT NULL, nome TEXT, patrimonio REAL, data DATE)";
            sqlite_cmd = conn.CreateCommand();
            sqlite_cmd.CommandText = Createsql;
            sqlite_cmd.ExecuteNonQuery();
        }
        public Cliente ReadCliente(SQLiteConnection conn, string cnpj)
        {
            //conn.Open();
            Cliente cliente = new Cliente();
            SQLiteDataReader sqlite_datareader;
            SQLiteCommand sqlite_cmd;
            sqlite_cmd = conn.CreateCommand();
            sqlite_cmd.CommandText = $"SELECT * FROM Cliente where cnpj = '{cnpj}'";

            sqlite_datareader = sqlite_cmd.ExecuteReader();
            while (sqlite_datareader.Read())
            {
                double patrimonio = sqlite_datareader.GetDouble(2);
                DateTime date = DateTime.Parse(sqlite_datareader.GetString(3));
                int anoNascimento = date.Year;
                bool possuiConta = true;
                cliente = new Cliente
                {
                    AnoNascimento = anoNascimento,
                    Patrimonio = patrimonio,
                    PossuiConta = possuiConta
                };
                Console.WriteLine(patrimonio);
            }
            conn.Close();

            return cliente;
        }
    }
}
