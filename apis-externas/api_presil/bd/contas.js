const knex = require("./connection");

async function isConnect() {
    return knex.raw("SELECT 1 AS ok FROM Cliente LIMIT 1");
}

function insertClient(client) {
    return knex("Cliente").insert(client);
};

function selectAllClient() {
    return knex("Cliente").select("*");
};

function findClient(cpf) {
    return knex("Cliente").where("cnpj", cpf);
};

function deleteClient(cpf) {
    return knex("Cliente").where("cnpj", cpf).del();
};

function updateClient(cpf, client) {
    return knex("Cliente").where("cnpj", cpf).update(client);
};


module.exports = {
    isConnect,
    insertClient,
    selectAllClient,
    findClient,
    deleteClient,
    updateClient
};