const express = require('express');

const app = express();

function setupHandler(app) {
  app.get('/', (req, res) => {
    res.send('Hello World!');
  });
}

function startHttpServer() {
    return new Promise(resolve=>{
        const app = express();
        setupHandler(app);

        const port = process.env.Port && parseInt(process.env.Port) || 3000;
        app.listen(port, () => {
            resolve();
        })
    });
}

function main() {
    console.log('Starting server...');

    return startHttpServer();
}

main().then(() => console.log('Microservice started!'))
.catch(err => console.error('Error starting microservice', err));