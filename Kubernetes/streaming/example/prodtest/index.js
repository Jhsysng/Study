const express = require('express');
const fs = require("fs");
const aws = require("aws-sdk");

const app = express();

if(!process.env.PORT) {
    throw new Error("PORT is not defined in the environment variables");
}

const port = process.env.PORT;
const bucket = process.env.BUCKET;
const region = process.env.REGION;
const accessKeyId = process.env.ACCESS_KEY_ID;
const secretAccessKey = process.env.SECRET_ACCESS_KEY;

function createBlobService() {
    const s3 = new aws.S3({
        region,
        accessKeyId,
        secretAccessKey,
    });

    return s3;
}

app.get("/video", (req, res) => {
    const videoPath = req.query.path;
    
    const s3 = createBlobService();
    const params = {
        Bucket: bucket,
        Key: videoPath,
    };

    s3.headObject(params, (err, data) => {
        if (err) {
            console.error("An error occurred: ", err);
            res.sendStatus(500);
            return;
        }

        res.writeHead(200, {
            "Content-Length": data.ContentLength,
            "Content-Type": "video/mp4",
        });

        const  stream = s3.getObject(params).createReadStream();
        stream.pipe(res).on("error", (error) => {
            console.error("An error occurred: ", error);
            res.sendStatus(500);
        });

        stream.on("end", () => {
            res.end();
        });
    });
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});