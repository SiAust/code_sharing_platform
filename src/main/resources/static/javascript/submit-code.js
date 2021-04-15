function send() {

    const code = document.getElementById("code_snippet")
    const time = document.getElementById("time_restriction")
    const views = document.getElementById("views_restriction")

    let js_object = {
        "code": code.value,
        "time": time.value,
        "views": views.value
    };

    let json = JSON.stringify(js_object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status === 200) {
        code.value = "// write another snippet"
        time.value = ""
        views.value = ""
        alert("Success!");
    } else {
        alert("Failure: status code " + xhr.status)
        console.log(js_object)
        console.log(json)
    }
}