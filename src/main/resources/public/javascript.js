var http = new XMLHttpRequest();

function sendRequestGET(path, query) {
    console.log('path = ' + path);
    http.open('GET', path + '?' + query);
    http.send();
}

http.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
        document.getElementById('table').innerHTML = this.responseText;
    }
};

function loadTable() {
    sendRequestGET('start');
}

function addGame() {

    sendRequestGET('addgame', 'country0=' + document.getElementById('country0').value +
        '&country1=' + document.getElementById('country1').value +
        '&goals1=' + document.getElementById('goals1').value +
        '&goals2=' + document.getElementById('goals2').value);
}

// Kopie von https://javalin.io/tutorials/websocket-example
//
//
// small helper function for selecting element by id
let id = id => document.getElementById(id);

//Establish the WebSocket connection and set up event handlers
let ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/livematch");
ws.onmessage = msg => updateTable(msg);
ws.onclose = () => alert("WebSocket connection closed");

function sendAndClear() {
    var json = JSON.stringify({'country0': document.getElementById('simcountry1').value,
        'country1': document.getElementById('simcountry2').value});
    ws.send(JSON.stringify(json));
}

function updateTable(msg) { // Update table
    let data = JSON.parse(msg.data);
    console.log("updateTable");
    console.log(data);
    // id("table").insertAdjacentHTML("afterbegin", data.userMessage);
    document.getElementById('simgoals1').innerHTML = data.livematch.goals1;
    document.getElementById('simgoals2').innerHTML = data.livematch.goals2;
    document.getElementById('table').innerHTML = data.table;
}
//
//
// Kopie Ende

/* Kopiert von:
// https://code.tutsplus.com/tutorials/real-time-sports-application-using-nodejs--cms-30594
// refresh data
setInterval(function() {
    data.getData().then((result) => {
        // Update latest results for when new client's connect
        latestData = result;

        // send it to all connected clients
        io.emit('data', result);

        console.log('Last updated: ' + new Date());
    });
}, 300000);
Kopie Ende
 */
