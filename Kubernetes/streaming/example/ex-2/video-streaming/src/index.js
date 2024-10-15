const express = require('express');
const { MongoClient, ObjectId } = require('mongodb');
const http = require('http');

const app = express();

const PORT = process.env.PORT || 3000;
const VIDEO_STORAGE_HOST = process.env.VIDEO_STORAGE_HOST || 'localhost';
const VIDEO_STORAGE_PORT = parseInt(process.env.VIDEO_STORAGE_PORT) || 4000;
const DBHOST = process.env.DBHOST || 'mongodb://localhost:27017';
const DBNAME = process.env.DBNAME || 'video-streaming';

function main() {
    console.log('Attempting to connect to MongoDB at:', DBHOST);
    return MongoClient.connect(DBHOST, { useUnifiedTopology: true })
        .then(client => {
            console.log('Connected successfully to MongoDB');
            const db = client.db(DBNAME);
            const videosCollection = db.collection('videos');

            app.get("/video", (req, res) => {
                let videoId;
                try {
                    videoId = new ObjectId(req.query.id);
                } catch (error) {
                    res.status(400).send("Invalid video ID");
                    return;
                }

                videosCollection.findOne({ _id: videoId })
                    .then(videoRecord => {
                        if (!videoRecord) {
                            res.status(404).send("Video not found");
                            return;
                        }
                        console.log('Forwarding request to video storage');
                        const forwardRequest = http.request({
                            host: VIDEO_STORAGE_HOST,
                            port: VIDEO_STORAGE_PORT,
                            path: `/video?path=${videoRecord.videoPath}`,
                            method: 'GET',
                            headers: req.headers
                        },
                        forwardResponse => {
                            res.writeHead(forwardResponse.statusCode, forwardResponse.headers);
                            forwardResponse.pipe(res);
                        });
                        forwardRequest.on('error', (err) => {
                            console.error('Error forwarding request to video storage:', err);
                            res.status(500).send('Error retrieving video from storage');
                        });
                        req.pipe(forwardRequest);
                    })
                    .catch(err => {
                        console.error("Database query failed:", err);
                        res.status(500).send("Internal server error");
                    });
            });

            app.listen(PORT, () => {
                console.log(`Server is running on http://localhost:${PORT}`);
            });
        });
}

main()
    .then(() => {
        console.log('Server started successfully');
    })
    .catch(err => {
        console.error('Failed to start the server:', err);
        process.exit(1);
    });