google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawPie);
google.charts.setOnLoadCallback(drawLine);


function drawLine() {
    var data_line = google.visualization.arrayToDataTable([
        ['Ano', 'Banco Cifra', 'S16 Bank', 'Banco do Presil'],
        ['2021', 1000, 1000, 1000],
        ['2022', 1200, 1150, 1100],
        ['2023', 1500, 1300, 1300],
        ['2024', 1900, 1450, 1500],
        ['2025', 2400, 1700, 1800],
        ['2026', 3000, 1950, 2100],
    ]);
    var options_line = {
        title: 'Comparação de taxas de Financiamento durante o tempo',
        curveType: 'function',
        legend: { position: 'bottom' }
    };
    var line = new google.visualization.LineChart(document.getElementById('line_chart'));
    line.draw(data_line, options_line);
}


function drawPie() {
    var data_pie = google.visualization.arrayToDataTable([
        ['Banco', 'Valor taxa'],
        ['Banco Cifra', 7],
        ['S16 Bank', 5.5],
        ['Banco do Presil', 6]
    ]);
    var options_pie = {
        title: 'Comparação de taxas de Financiamento'
    };
    var chart_pie = new google.visualization.PieChart(document.getElementById('pie_chart'));
    chart_pie.draw(data_pie, options_pie);
}