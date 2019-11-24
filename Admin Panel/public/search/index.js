document.addEventListener('DOMContentLoaded', function () {
    var email = getParameterByName('email');
    var form = document.getElementById('TrackForm');
    var input = document.getElementById('TrackInput');

    if (email) {
        findOrder(email);
    } else {
        document.getElementById('loader').style.display = 'none';
        form.style.display = 'block';
    }

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        findOrder(input.value);
    });
});

function findOrder(email) {
    document.getElementById('TrackForm').style.display = 'none';
    document.getElementById('loader').style.display = 'block';
    document.getElementById('Result').innerHTML = '';
    document.querySelector('.track__result').style.display = 'none';
    document.getElementById('notFound').style.display = 'none';

    var db = firebase.firestore();

    db.collection("invoices").where("userEmail", "==", email).get()
        .then(function (querySnapshot) {
            var result = [];

            querySnapshot.forEach(function (doc) {
                result.push({
                    id: doc.id,
                    data: doc.data()
                });
            });

            if (result.length) {
                renderTable(result);
                document.getElementById('notFound').style.display = 'none';
            } else {
                document.getElementById('email').innerText = email;
                document.getElementById('notFound').style.display = 'block';
                document.getElementById('TrackForm').style.display = 'block';
                document.getElementById('loader').style.display = 'none';
            }
        });
}

function renderTable(data) {
    var coloredRow = false;
    var html = '<table><tr>' +
        '<th>ID</th>' +
        '<th>Name</th>' +
        '<th>Email</th>' +
        '<th>Address</th>' +
        '<th>Phone Number</th>' +
        '<th>Date</th>' +
        '<th>Products</th>' +
        '<th>Total</th>' +
        '<th>Status</th></tr>';

    data.forEach(order => {
        coloredRow = !coloredRow;

        var id = order.data.id || '-';
        var userName = order.data.userName || '-';
        var userEmail = order.data.userEmail || '-';
        var address = order.data.address || '-';
        var phoneNumber = order.data.phoneNumber || '-';
        var date = order.data.date || '-';
        var products = order.data.products || '-';
        var total = order.data.products.reduce((a, b) => a + b.quantity * b.price, 0);
        var status = order.data.status || '-';

        html += '<tr class="' +
            (coloredRow ? 'orders__colored-row' : 'orders__ordinary-row') +
            '"><td>' + id;
        html += '</td><td>' + userName;
        html += '</td><td>' + userEmail;
        html += '</td><td>' + address;
        html += '</td><td>' + phoneNumber;
        html += '</td><td>' + date;
        html += '</td><td>' + renderProducts(products);
        html += '</td><td>' + total;

        var statusClass = getStatusClass(status);
        html += '</td><td><span class="oreders__status--' + statusClass + '">' + status + '</span>';
        html += '</td></tr>';
    });

    html += '</table>';

    document.getElementById('Result').innerHTML = html;
    document.querySelector('.track__result').style.display = 'block';
    document.getElementById('TrackForm').style.display = 'block';
    document.getElementById('loader').style.display = 'none';
}

function renderProducts(products) {
    var html = '';

    products.forEach(function (product, index) {
        if (index > 0) {
            html += '<br>';
        }
        html += 'Name: ' + product.name + '<br>';
        html += 'Price: ' + product.price + '<br>';
        html += 'Quantity: ' + product.quantity + '<br>';
    });

    return html;
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

function getStatusClass(status) {
    var statusClass;
    switch (status) {
        case 'Pending':
            statusClass = 'pending';
            break;
        case 'Delivering':
            statusClass = 'delivering';
            break;
        case 'Delivered':
            statusClass = 'delivered';
            break;
        default:
            statusClass = 'cancelled';
            break;
    }
    return statusClass;
}