function send() {
    let obj1 = document.getElementById("code_snippet").value;
    let obj2 = document.getElementById("time_restriction").value;
    let obj3 = document.getElementById("views_restriction").value;

    let answer = {
        code: obj1,
        time: obj2,
        views: obj3
    };
    let json = JSON.stringify(answer);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
        alert("Success!");
    } else {
        alert("Error!")
    }
}