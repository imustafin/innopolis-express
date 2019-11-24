'use strict';

const functions = require('firebase-functions');
const nodemailer = require('nodemailer');

const gmailEmail = functions.config().gmail.email;
const gmailPassword = functions.config().gmail.password;
const mailTransport = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: gmailEmail,
        pass: gmailPassword,
    },
});

const APP_NAME = 'Innopolis Express';

exports.sendStatusChangeEmail = functions.firestore
    .document('invoices/{invoiceId}')
    .onWrite((change, context) => {
        var modifiedOrder = change.after.data();

        if (modifiedOrder.status === 'Pending') {
            return sendNewOrderEmail(modifiedOrder);
        }

        return sendStatusChangeEmail(modifiedOrder);
    });

async function sendStatusChangeEmail(modifiedOrder) {
    const mailOptions = {
        from: `${APP_NAME} <noreply@firebase.com>`,
        to: modifiedOrder.userEmail,
    };

    mailOptions.subject = `Your order status change on ${APP_NAME}`;
    mailOptions.text = `The status of your order with ID '${modifiedOrder.id}' was changed to '${modifiedOrder.status}'. \nYou can track it here: https://admin-inno-express.firebaseapp.com/track/?orderId=${modifiedOrder.id}`;
    await mailTransport.sendMail(mailOptions);
    console.log('Status change email sent to:', modifiedOrder.userEmail);
    return null;
}

async function sendNewOrderEmail(newOrder) {
    const mailOptions = {
        from: `${APP_NAME} <noreply@firebase.com>`,
        to: newOrder.userEmail,
    };

    mailOptions.subject = `New order on ${APP_NAME}`;
    mailOptions.text = `You created a new order on ${APP_NAME}! \n\nIts current status is '${newOrder.status}'. You can track it here: https://admin-inno-express.firebaseapp.com/track/?orderId=${newOrder.id} \n\nThank you for your order.`;
    await mailTransport.sendMail(mailOptions);
    console.log('New order email sent to:', newOrder.userEmail);
    return null;
}