const knex = require("knex");

const connectedKnex = knex({
    client: "sqlite3",
    connection: {
        filename: "bd/clients.sqlite3"
    }
});

module.exports = connectedKnex;