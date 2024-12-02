
function emailReg(email) {
    let emailReg = /^[\da-z]+([\-\.\_]?[\da-z]+)*@[\da-z]+([\-\.]?[\da-z]+)*(\.[a-z]{2,})+$/;
    return emailReg.test(email);
}


function phoneReg(phone) {
    let phoneReg = /^1(3\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\d|9[0-35-9])\d{8}$/;
    return phoneReg.test(phone);
}


function userNameReg(name) {
    let userNameReg = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z]){1,10}$/;
    return userNameReg.test(name);
}


function userPasswordReg(password) {
    let userPasswordReg = /^(\w){8,16}$/;
    return userPasswordReg.test(password);
}



