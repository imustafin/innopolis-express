const pendingInvoice = {
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
};

const deliveringInvoice = {
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
    status: 'Delivering'
};

module.exports = { pendingInvoice, deliveringInvoice };