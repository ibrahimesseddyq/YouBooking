let express = require("express");
const app = express();
let fs = require('fs');
const cors = require("cors");
app.use(cors());
const data = fs.readFile("./db.json");
const port = process.env.NODE_ENV ||
    8080;
app.get('/data', (req, res) => {
    res.send(data)
})
app.listen(port, () => {
    console.log(`listen on port ${port}`);
})