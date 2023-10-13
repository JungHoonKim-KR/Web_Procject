function goform() {
    let userName = document.querySelector("#username").value;
    let email = document.querySelector("#emailId").value;
    let password = document.querySelector("#password").value;
    let phoneNumber = document.querySelector("#phoneNumber").value;
    let regUsername= /^.{2,6}$/
    let regEmailId = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    let regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/i;
    let regPhoneNumber =/^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$/;


    if (userName == "") {
        alert("닉네임을 입력해주세요");
        return false;

    }else if(userName.length<2 || userName.length>6){
        alert("이름은 2~6글자 사이만 가능합니다.")
    }
    //if userName reg

    if (email == "") {
        alert("이메일을 입력해주세요.");
        return false;

    } else if (!regEmailId.test(email)) {
        alert("이메일 형식이 올바르지 않습니다.");
        return false;
    } //if email reg

    if (password == "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    } else if (!regPassword.test(password)) {
        alert("비밀번호 형식이 올바르지 않습니다.");
        return false;
    }

    if (phoneNumber == "") {
        alert("전화번호를 입력해주세요.");
        return false;
    }else if(!regPhoneNumber.test(phoneNumber)){
        alert("전화번호 형식이 올바르지 않습니다.");
        return false;
    }

   document.querySelector("#form").submit();

}