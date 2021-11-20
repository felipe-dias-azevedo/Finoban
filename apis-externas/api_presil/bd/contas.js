const knex = require("./connection");

async function isConnect() {
    return knex.raw("SELECT 1 AS ok FROM Cliente LIMIT 1");
}

async function insertClient(client) {
    return knex("Cliente").insert(client);
};

async function selectAllClient() {
    return knex("Cliente").select("*");
};

async function findClient(cpf) {
    return knex("Cliente").where("cpf", cpf);
};

async function deleteClient(cpf) {
    return knex("Cliente").where("cpf", cpf).del();
};

async function updateClient(cpf, client) {
    return knex("Cliente").where("cpf", cpf).update(client);
};

module.exports = {
    isConnect,
    insertClient,
    selectAllClient,
    findClient,
    deleteClient,
    updateClient
};