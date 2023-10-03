var stompClient;
var messageElement=document.getElementById("messageElement")
var roomId=document.getElementById("roomId").value
var writer=document.getElementById("writer").value
var contentList=document.getElementById("contentList")
function connect() {
    let socket=new SockJS("/ws/chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({},onConnected,onError)

}
//입장 시 connect
connect();
function onConnected(){
    stompClient.subscribe("/sub/chat/room"+roomId,function (e){
        showMessage(JSON.parse(e.body))
    });
    let message={
        type:"ENTER",
        roomId:roomId,
        writer:writer,
        message:""
    }
    stompClient.send("pub/chat/enter",{},JSON.stringify(message))
}

function showMessage(payload){
    let htmlSpanElement1 = document.createElement("span");
    htmlSpanElement1.innerHTML=payload.writer
    contentList.append(htmlSpanElement1)

    let htmlSpanElement2 = document.createElement("span");
    htmlSpanElement2.innerHTML=payload.content
    contentList.append(htmlSpanElement2)

}

function onError(error) {
    // connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    // connectingElement.style.color = 'red';
}

function sendMessage(event) {
    event.preventDefault()

    //messageInput은 입력창의 text값
    let messageContent = messageElement.value.trim();

    if (messageContent && stompClient) {
        let chatMessage = {
            type: 'TALK',
            roomId: roomId,
            writer: writer,
            message: messageElement.value
        };

        stompClient.send("/pub/chat/room"+roomId, {}, JSON.stringify(chatMessage));
        messageElement.value = '';
    }
}