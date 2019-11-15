document.addEventListener('DOMContentLoaded', function () {
    var orderId = getParameterByName('orderId');
    var form = document.getElementById('TrackForm');
    var input = document.getElementById('TrackInput');

    if (orderId) {
        findOrder(orderId);
    } else {
        document.getElementById('loader').style.display = 'none';
        form.style.display = 'block';
    }

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        var orderId = input.value;
        findOrder(orderId);
    });
});

function findOrder(orderId) {
    document.getElementById('TrackForm').style.display = 'none';
    document.getElementById('loader').style.display = 'block';
    document.getElementById('Result').innerHTML = '';
    document.querySelector('.track__result').style.display = 'none';

    var db = firebase.firestore();

    db.collection("invoices").where("id", "==", orderId).get()
        .then(function (querySnapshot) {
            querySnapshot.forEach(function (doc) {
                renderResult(doc.data());
            })
        });
}

function renderResult(data) {
    var result = '<table class="track__result-table">' +
        '<tr><td>ID</td><td>' + data.id +
        '</td></tr></tr><td>Date</td><td>' + data.date +
        '</td></tr></tr><td>Products</td><td>' + renderProducts(data.products) +
        '</td></tr></tr><td>Status</td><td>' + data.status +
        '</td></tr></table>'

    document.getElementById('Result').innerHTML = result;
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