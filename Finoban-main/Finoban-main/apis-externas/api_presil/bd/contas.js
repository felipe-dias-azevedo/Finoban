const knex = require("./connection");

function insertClient(client) {
    return knex("Cliente").insert(client);
};

function selectAllClient() {
    return knex("Cliente").select("*");
};

function findClient(cnpj) {
    return knex("Cliente").where("cnpj", cnpj);
};

function deleteClient(cnpj) {
    return knex("Cliente").where("cnpj", cnpj).del();
};

function updateClient(cnpj, client) {
    return knex("Cliente").where("cnpj", cnpj).update(client);
};


module.exports = {
    insertClient,
    selectAllClient,
    findClient,
    deleteClient,
    updateClient
};