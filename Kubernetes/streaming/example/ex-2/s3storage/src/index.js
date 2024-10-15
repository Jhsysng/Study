// index.js
const express = require('express');
const { S3Client, GetObjectCommand, HeadObjectCommand } = require('@aws-sdk/client-s3');

const app = express();
const port = process.env.PORT || 4000;
const bucketName = process.env.BUCKET || "tfk8sdocker-test";
const region = process.env.REGION || "us-east-1";
const accessKeyId = process.env.ACCESS_KEY_ID;
const secretAccessKey = process.env.SECRET_ACCESS_KEY;

function createS3Client() {
    const s3Client = new S3Client({
        region,
        credentials: {
            accessKeyId,
            secretAccessKey,
        },
    });
    return s3Client;
}

app.get("/video", async (req, res) => {
    const videoPath = req.query.path;
    const s3Client = createS3Client();

    if (!videoPath) {
        res.status(400).send("쿼리 매개변수 'path'가 필요합니다.");
        return;
    }

    try {
        // 객체 메타데이터 조회
        const headParams = {
            Bucket: bucketName,
            Key: videoPath,
        };
        const headCommand = new HeadObjectCommand(headParams);
        const headData = await s3Client.send(headCommand);

        const contentLength = headData.ContentLength;
        const contentType = headData.ContentType || "video/mp4";

        // 객체 가져오기
        const getParams = {
            Bucket: bucketName,
            Key: videoPath,
        };
        const getCommand = new GetObjectCommand(getParams);
        const data = await s3Client.send(getCommand);
        const videoStream = data.Body; // 스트림입니다.

        // 응답 헤더 설정
        res.writeHead(200, {
            "Content-Length": contentLength,
            "Content-Type": contentType,
        });

        // 스트림을 응답에 파이핑
        videoStream.pipe(res);

    } catch (err) {
        console.error("An error occurred: ", err);
        if (err.name === 'NoSuchKey') {
            res.status(404).send('파일을 찾을 수 없습니다.');
        } else if (err.name === 'Forbidden' || err.name === 'AccessDenied') {
            res.status(403).send('접근이 거부되었습니다.');
        } else {
            res.sendStatus(500);
        }
    }
});

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});