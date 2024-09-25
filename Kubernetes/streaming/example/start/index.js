const express = require('express');
const fs = require("fs");

const app = express();

if(!process.env.PORT) {
    throw new Error("PORT is not defined in the environment variables");
}

const port = process.env.PORT;

app.get('/', (req, res) => {
  res.send('Hello World!');
});

app.get("/video", (req, res) => {
    const path = "./static/test.mp4";

    fs.stat(path, (err, stats) => {
        if (err) {
            console.error("An error occurred: ", err);
            res.sendStatus(500);
            return;
        }

        res.writeHead(200, {
            "Content-Length": stats.size,
            "Content-Type": "video/mp4",
        });

        fs.createReadStream(path).pipe(res);
    });
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});