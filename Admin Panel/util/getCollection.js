var firebase = require("firebase/app");

require("firebase/firestore");

function getCollectionByParam(collection, param, value) {
    var db = firebase.firestore();

    return db.collection(collection).where(param, "==", value).get()
        .then(function (querySnapshot) {
            const result = [];
            querySnapshot.forEach(function (doc) {
                result.push(doc.data());
            });

            return result;
        });
}

module.exports = {
    getCollectionByParam
};