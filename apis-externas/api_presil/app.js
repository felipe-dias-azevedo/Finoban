// Express
const express = require('express');
const app = express();
const admin = require('./routes/admin');
const ob = require('./routes/openbanking');
const bodyParser = require('body-parser');
const port = 3333;

// BodyParser
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

// Rotas
app.get('/', (req, res) => res.send('API funcionando'));
app.use("/admin", admin);
app.use("/openbanking/v1", ob);

app.listen(port, () => console.log("Aplicação rodando no Endereço: http://localhost:3333"));