var firebase = require("firebase/app");
var firebaseConfig = require("../config");
var updateOrderStatus = require('../util/updateDocument');
var {
    pendingInvoice,
    deliveringInvoice
} = require('./stub/invoice');

require("firebase/firestore");

describe('Change Order Status', function () {
    before(function () {
        if (!firebase.apps.length) {
            firebase.initializeApp(firebaseConfig);
        }
    });

    beforeEach(function () {
        var db = firebase.firestore();

        db.collection("test-invoices").doc('TI1').set(pendingInvoice)
            .catch(function (error) {
                console.error("Error writing document: ", error);
            });

        db.collection("test-invoices").doc('TI2').set(deliveringInvoice)
            .catch(function (error) {
                console.error("Error writing document: ", error);
            });
    });

    afterEach(function () {
        var db = firebase.firestore();

        db.collection("test-invoices").doc("TI1").delete().catch(function (error) {
            console.error("Error removing document: ", error);
        });
    });

    describe('when status is "Pending"', function () {
        it('should change order status to "Delivering"', function (done) {
            test('Delivering', pendingInvoice, 'TI1', done);
        });

        it('should change order status to "Cancelled by admin"', function (done) {
            test('Cancelled by admin', pendingInvoice, 'TI1', done);
        });
    });

    describe('when status is "Delivering"', function () {
        it('should change order status to "Delivered"', function (done) {
            test('Delivered', deliveringInvoice, 'TI2', done);
        });
    });

    function test(expected, invoiceStub, doc, done) {
        const invoicesCollection = 'test-invoices';

        updateOrderStatus(invoicesCollection, doc, invoiceStub.status, expected);

        var db = firebase.firestore();
        const docRef = db.collection(invoicesCollection).doc(doc);
        docRef.get().then(function (doc) {
            if (!doc.exists) {
                done(new Error("No such document"));
            }

            const actualStatus = doc.data().status;
            if (actualStatus !== expected) {
                done(new Error("Status is " + actualStatus));
            }

            done();
        });
    }
})