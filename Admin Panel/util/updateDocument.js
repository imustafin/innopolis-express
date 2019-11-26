var firebase = require("firebase/app");

require("firebase/firestore");

function updateDocument(collection, doc, param, value) {
    var db = firebase.firestore();
    var orderRef = db.collection(collection).doc(doc);
    orderRef.update({
        [param]: value
    });
}

function updateOrderStatus(collection, doc, currentValue, newValue) {
    if (currentValue === 'Pending' && (newValue === 'Delivering' || newValue === 'Cancelled by admin')) {
        updateDocument(collection, doc, 'status', newValue);
    } else if (currentValue === 'Delivering' && newValue === 'Delivered') {
        updateDocument(collection, doc, 'status', newValue);
    }
}

module.exports = updateOrderStatus;