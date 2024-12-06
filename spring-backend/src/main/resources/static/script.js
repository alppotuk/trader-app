let currentPage = 0;
let chart;

function formatDate(date) {
    const d = new Date(date);
    const day = d.getDate().toString().padStart(2, '0');
    const month = (d.getMonth() + 1).toString().padStart(2, '0');
    const year = d.getFullYear().toString().slice(-2);
    const hours = d.getHours().toString().padStart(2, '0');
    const minutes = d.getMinutes().toString().padStart(2, '0');

    return `${day}.${month}.${year} | ${hours}:${minutes}`;
}

function switchTab(type) {
    const tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => tab.classList.remove('active'));
    event.target.classList.add('active');
    fetchData(type);
}

function fetchData(type = 'balance') {
    const pageSize = document.getElementById('pageSize').value;

    axios.get(`/api/v1/balances?index=${currentPage}&size=${pageSize}`)
        .then(response => {
            const data = response.data;
            const reversedContent = [...data.content].reverse();
            updateChart(reversedContent, type);
            updateControls(data, reversedContent);
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

    const labels = balances.map(b => formatDate(b.dateTime));
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
            scales: {
                x: {
                    ticks: {
                        autoSkip: true,
                        maxTicksLimit: 10
                    }
                }
            }
        }
    });
}

function updateControls(data, balances) {
    // Update date range display
    const dateRangeElement = document.getElementById('dateRange');
    if (balances.length > 0) {
        const startDate = formatDate(balances[0].dateTime);
        const endDate = formatDate(balances[balances.length - 1].dateTime);
        dateRangeElement.textContent = `${startDate} - ${endDate}`;
    } else {
        dateRangeElement.textContent = 'No data available';
    }

    // Update pagination buttons
    const prevButton = document.getElementById('prevPage');
    const nextButton = document.getElementById('nextPage');

    prevButton.disabled = data.first;
    nextButton.disabled = data.last;
}

function changePage(direction) {
    currentPage += direction;
    fetchData(document.querySelector('.tab.active').textContent.split(' ')[0].toLowerCase());
}

fetchData();