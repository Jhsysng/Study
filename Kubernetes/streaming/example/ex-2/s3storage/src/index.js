const express = require('express');
const aws = require('aws-sdk');
const S3Client = require('@aws-sdk/client-s3');
const s3 = new aws.S3();

const app = express();
const port = process.env.PORT || 4000;
const bucket = process.env.BUCKET || "tfk8sdocker-test";
const region = process.env.REGION || "us-east-1";
const accessKeyId = process.env.ACCESS_KEY_ID;
const secretAccessKey = process.env.SECRET_ACCESS_KEY;


function createBlobService() {
    const blobService = new S3Client({
        region,
        credentials: {
            accessKeyId,
            secretAccessKey,
        },
    });
    return blobService;
}


app.get("/video", (req, res) => {
    const videoPath = req.query.path;
    const blobService = createBlobService();

    const containerName = "video";

    blobService.getBlobProperties(containerName, videoPath, (err, properties) => {
        if (err) {
            console.error("An error occurred: ", err);
            res.sendStatus(500);
            return;
        }

        const videoStream = blobService.createReadStream(containerName, videoPath);

        res.writeHead(200, {
            "Content-Length": properties.contentLength,
            "Content-Type": "video/mp4",
        });

        videoStream.pipe(res);
    }
    );
})

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});

