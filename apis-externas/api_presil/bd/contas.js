const knex = require("./connection");

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
    insertClient,
    selectAllClient,
    findClient,
    deleteClient,
    updateClient
};