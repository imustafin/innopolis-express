var firebase = require("firebase/app");
var firebaseConfig = require("../config");
var {
    getCollectionByParam
} = require('../util/getCollection');

require("firebase/firestore");

describe('Search', function () {
    before(function () {

        firebase.initializeApp(firebaseConfig);

        var db = firebase.firestore();

        db.collection("test-invoices").doc('TI1').set({
                id: 'TEST_ID',
                userName: 'Test Name',
                userEmail: 'test@email.com',
                address: 'Test Address, 1',
                phoneNumber: '+79265757783',
                date: '24-11-2019',
                products: [{
                    name: 'Test Product 1',
                    price: '300',
                    quantity: '2'
                }],
                status: 'Pending'
            })
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
        it('should show the list of user orders with passed email', function (done) {
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
    })
});