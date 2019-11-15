document.addEventListener('DOMContentLoaded', function () {
    firebase.auth().onAuthStateChanged(function (user) {
        if (user) {
            // User is signed in.
            init();
            console.log('User.uid:', user.uid);
        } else {
            console.log('User is not authenticated');
            window.location.href = "/";
            return;
        }
    });
});

function init() {
    fetchOrders();
}

function fetchOrders() {
    var db = firebase.firestore();

    db.collection("invoices").orderBy('date', 'desc').get().then((querySnapshot) => {
        var result = [];

        querySnapshot.forEach((doc) => {
            result.push({
                id: doc.id,
                data: doc.data()
            });
        });
        return result;
    }).then(orders => {
        document.getElementById('loader').style.display = 'none';
        renderTable(orders);

        db.collection("invoices").orderBy('date', 'desc')
            .onSnapshot(function (querySnapshot) {
                var result = [];
                querySnapshot.forEach((doc) => {
                    result.push({
                        id: doc.id,
                        data: doc.data()
                    });
                });
                renderTable(result);
            });
    });
}

function renderTable(orders) {
    var table = document.getElementById("orderTable");
    var coloredRow = false;

    if (!orders || !orders.length) {
        document.getElementById("tableEmpty").style.display = "block";
        return;
    } else {
        document.getElementById("tableEmpty").style.display = "none";
    }

    var html = '<tr>' +
        '<th>Name</th>' +
        '<th>Email</th>' +
        '<th>Address</th>' +
        '<th>Phone Number</th>' +
        '<th>Date</th>' +
        '<th>Products</th>' +
        '<th>Total</th>' +
        '<th>Status</th>' +
        '<th>Actions</th></tr>';

    orders.forEach(order => {
        coloredRow = !coloredRow;

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
            '"><td>' + userName;
        html += '</td><td>' + userEmail;
        html += '</td><td>' + address;
        html += '</td><td>' + phoneNumber;
        html += '</td><td>' + date;
        html += '</td><td>' + renderProducts(products);
        html += '</td><td>' + total;

        var statusClass = getStatusClass(status);
        html += '</td><td><span class="oreders__status--' + statusClass +'">' + status + '</span>';
        html += '</td><td>' + getActionButtons(order.id, status);
        html += '</td></tr>';
    });

    table.innerHTML = html;
    table.style.display = "block";
    bindActionButtons();
}

function renderProducts(products) {
    console.log(products);
    var html = '';

    products.forEach(product => {
        html += 'Name: ' + product.name + '<br>';
        html += 'Price: ' + product.price + '<br>';
        html += 'Quantity: ' + product.quantity + '<br><br>';
    });

    return html;
}

function addOrder(order) {
    var db = firebase.firestore();

    db.collection("invoices").add(order)
        .then(function (docRef) {
            console.log("Document written with ID: ", docRef.id);
        })
        .catch(function (error) {
            console.error("Error adding document: ", error);
        });
}

function getActionButtons(id, status) {
    if (status === 'Pending') {
        return '<div class="orders__accept" data-id="' + id + '">Accept</div>' +
            '<div class="orders__decline" data-id="' + id + '">Decline</div>';
    } else if (status === 'Delivering') {
        return '<div class="orders__delivered" data-id="' + id + '">Delivered</div>';
    } else {
        return '-';
    }
}

function bindActionButtons() {
    var buttons = document.querySelectorAll('.orders__accept');
    buttons.forEach(button => {
        button.addEventListener('click', function (e) {
            e = e || window.event;
            var target = e.target || e.srcElement;
            var id = target.dataset.id;

            updateOrderStatus(id, 'Delivering');
        });
    });

    var buttons = document.querySelectorAll('.orders__decline');
    buttons.forEach(button => {
        button.addEventListener('click', function (e) {
            e = e || window.event;
            var target = e.target || e.srcElement;
            var id = target.dataset.id;

            updateOrderStatus(id, 'Cancelled by admin');
        });
    });

    var buttons = document.querySelectorAll('.orders__delivered');
    buttons.forEach(button => {
        button.addEventListener('click', function (e) {
            e = e || window.event;
            var target = e.target || e.srcElement;
            var id = target.dataset.id;

            updateOrderStatus(id, 'Delivered');
        });
    });
}

function updateOrderStatus(id, status) {
    var db = firebase.firestore();
    var orderRef = db.collection("invoices").doc(id);
    orderRef.update({
        status: status
    });
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