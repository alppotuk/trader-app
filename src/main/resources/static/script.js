let currentPage = 0;
let chart;


function switchTab(type) {
    const tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => tab.classList.remove('active'));
    event.target.classList.add('active');
    fetchData(type);
}

function toggleTableVisibility() {
    const tableContainer = document.getElementById('tableContainer');
    tableContainer.classList.toggle('visible');
}

function fetchData(type = 'balance') {
    const pageSize = document.getElementById('pageSize').value;

    axios.get(`/api/v1/balances?index=${currentPage}&size=${pageSize}`)
        .then(response => {
            const data = response.data;
            const reversedContent = [...data.content].reverse();
            updateChart(reversedContent, type);
            updatePagination(data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}



function updateChart(balances, type) {
    const ctx = document.getElementById('tradeChart').getContext('2d');

    if (chart) {
        chart.destroy();
    }

    const rootStyles = getComputedStyle(document.documentElement);
        const borderColor = rootStyles.getPropertyValue(
            type === 'balance' ? '--chart-balance-line' : '--chart-pnl-line'
        ).trim();
        const pointColor = rootStyles.getPropertyValue(
            type === 'balance' ? '--chart-balance-dot' : '--chart-pnl-dot'
        ).trim();

    const labels = balances.map(b => new Date(b.dateTime).toLocaleString());
    const values = type === 'balance'
        ? balances.map(b => b.balance)
        : balances.map(b => b.pnl);

    chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
               label: type === 'balance' ? 'Balance' : 'PnL',
               data: values,
               borderColor: borderColor,
               backgroundColor: pointColor,
               pointBorderColor: pointColor,
               pointBackgroundColor: pointColor,
               pointRadius: 5,
               tension: 0.2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,

        }
    });
}

function updatePagination(data) {
    document.getElementById('pageInfo').textContent = `Page ${data.number + 1}`;
    document.getElementById('prevPage').disabled = data.first;
    document.getElementById('nextPage').disabled = data.last;
}

function changePage(direction) {
    currentPage += direction;
    fetchData(document.querySelector('.tab.active').textContent.split(' ')[0].toLowerCase());
}

fetchData();