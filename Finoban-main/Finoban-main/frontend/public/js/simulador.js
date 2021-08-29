google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawColColors);

function drawColColors() {
    var data = new google.visualization.DataTable();
    data.addColumn('timeofday', 'Time of Day');
    data.addColumn('number', 'Motivation Level');
    data.addColumn('number', 'Energy Level');

    data.addRows([
    [{v: [8, 0, 0], f: '8 am'}, 1, .25],
    [{v: [9, 0, 0], f: '9 am'}, 2, .5],
    [{v: [10, 0, 0], f:'10 am'}, 3, 1],
    [{v: [11, 0, 0], f: '11 am'}, 4, 2.25],
    [{v: [12, 0, 0], f: '12 pm'}, 5, 2.25],
    [{v: [13, 0, 0], f: '1 pm'}, 6, 3],
    [{v: [14, 0, 0], f: '2 pm'}, 7, 4],
    [{v: [15, 0, 0], f: '3 pm'}, 8, 5.25],
    [{v: [16, 0, 0], f: '4 pm'}, 9, 7.5],
    [{v: [17, 0, 0], f: '5 pm'}, 10, 10],
    ]);

    var options = {
    title: 'Motivation and Energy Level Throughout the Day',
    colors: ['#9575cd', '#33ac71'],
    hAxis: {
        title: 'Time of Day',
        format: 'h:mm a',
        viewWindow: {
        min: [7, 30, 0],
        max: [17, 30, 0]
        }
    },
    vAxis: {
        title: 'Rating (scale of 1-10)'
    }
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('bar_chart'));
    chart.draw(data, options);
}

function request() {
    // var xhttp = new XMLHttpRequest();
    // xhttp.open("GET", 'http://localhost:5000/users');
    // xhttp.setRequestHeader('Content-Type', 'application/json');
    // xhttp.setRequestHeader('Access-Control-Allow-Origin', '*')
    // xhttp.onreadystatechange = () => {
    //     if (this.readyState == 4 && this.status == 200) {
    //         console.log(xhttp.response)
    //     }
    // }
    // xhttp.send()

    var header = new Headers({
        "Content-Type": "application/json"
    });
    fetch('http://localhost:5000/users', {
        method: 'GET',
        headers: header,
        mode: 'no-cors'
    }).then(data => {
        console.log(data.json());
    }).catch(error => {
        console.log(error);
    })
}