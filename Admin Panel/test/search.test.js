var firebase = require("firebase/app");
var firebaseConfig = require("../config");
var invoiceStub = require('./stub/invoice');
var {
    getCollectionByParam
} = require('../util/getCollection');

require("firebase/firestore");

describe('Search', function () {
    before(function () {

        if (!firebase.apps.length) {
            firebase.initializeApp(firebaseConfig);
        }

        var db = firebase.firestore();

        db.collection("test-invoices").doc('TI1').set(invoiceStub)
            .catch(function (error) {
                console.error("Error writing document: ", error);
            });
    });

    after(function () {
        var db = firebase.firestore();

        db.collection("test-invoices").doc("TI1").delete().catch(function (error) {
            console.error("Error removing document: ", error);
        });
    });

    describe('by order id', function () {
        it('should show the status and information abouth the order', function (done) {
            const orderId = 'TEST_ID';
    
            getCollectionByParam("test-invoices", "id", orderId)
                .then(function (result) {
                    if (result.length) {
                        done();
                    } else {
                        done(new Error('Result list is empty'));
                    }
                });
        });
    
        it('should inform client that ID is incorrect and there is no such order', function (done) {
            const orderId = 'NONEXISTING_ID';
    
            getCollectionByParam("test-invoices", "id", orderId)
                .then(function (result) {
                    if (result.length) {
                        done(new Error('Result list is not empty'));
                    } else {
                        done();
                    }
                });
        });
    });

    describe('by user email', function () {
        it('should show the list of user orders with the passed email', function (done) {
            const orderId = 'TEST_ID';
    
            getCollectionByParam("test-invoices", "id", orderId)
                .then(function (result) {
                    if (result.length) {
                        done();
                    } else {
                        done(new Error('Result list is empty'));
                    }
                });
        });
    
        it('should inform client that there is no orders with such email', function (done) {
            const orderId = 'NONEXISTING_ID';
    
            getCollectionByParam("test-invoices", "id", orderId)
                .then(function (result) {
                    if (result.length) {
                        done(new Error('Result list is not empty'));
                    } else {
                        done();
                    }
                });
        });
    })
});