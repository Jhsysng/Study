const express = require('express');
const http = require('http');
const { S3Client, HeadObjectCommand, GetObjectCommand } = require('@aws-sdk/client-s3');

const app = express();

if (!process.env.PORT) {
    throw new Error("PORT is not defined in the environment variables");
}

if (!process.env.BUCKET) {
    throw new Error("BUCKET is not defined in the environment variables");
}

if (!process.env.REGION) {
    throw new Error("REGION is not defined in the environment variables");
}

if (!process.env.ACCESS_KEY_ID) {
    throw new Error("ACCESS_KEY_ID is not defined in the environment variables");
}

if (!process.env.SECRET_ACCESS_KEY) {
    throw new Error("SECRET_ACCESS_KEY is not defined in the environment variables");
}

const port = process.env.PORT;
const bucket = process.env.BUCKET;
const region = process.env.REGION;
const accessKeyId = process.env.ACCESS_KEY_ID;
const secretAccessKey = process.env.SECRET_ACCESS_KEY;
const VIDEO_STORAGE_HOST = process.env.VIDEO_STORAGE_HOST;
const VIDEO_STORAGE_PORT = parseInt(process.env.VIDEO_STORAGE_PORT);

const s3Client = new S3Client({
    region,
    credentials: {
        accessKeyId,
        secretAccessKey,
    },
});

app.get("/video", (req, res) => {
    const forwardRequest = http.request(
        {
            host: VIDDEO_STORAGE_HOST,
            port: VIDEO_STORAGE_PORT,
            path: "/video?path=video/test.mpt",
            method: "GET",
            headers: req.headers
        },
        forwardResponse => {
            res.writeHead(forwardResponse.statusCode, forwardResponse.headers);
            forwardResponse.pipe(res);
        }
    );

    req.pipe(forwardRequest);
})

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});