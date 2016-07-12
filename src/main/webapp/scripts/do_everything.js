function onButtonClick(){
    var postIdEl = document.getElementById("postId");

    var xmlhttp = new XMLHttpRequest();
    var url = "craft/demo/posts/" + postIdEl.value;
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            fetchAll();
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function fetchAll(){
    var xmlhttp = new XMLHttpRequest();
    var url = "craft/demo/posts";
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var results = JSON.parse(xmlhttp.responseText);
            renderAll(results)
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function renderAll(results) {
    var out = '<table class="table table-striped">' +
        '<thead><tr>'+
        '<th>id</th>' +
        '<th>user id</th>' +
        '<th>title</th>' +
        '<th>body</th>' +
        '</tr></thead><tbody>';

    var i;
    var p;
    for(i = 0; i < results.length; i++) {
        p = results[i];
        out += '<tr><td>' + p.id + '</td><td>' + p.userId + '</td><td>' + p.title + '</td><td>' + p.body + '</td></tr>'
    }
    out += '</tbody></table>';
    document.getElementById("drawArea").innerHTML = out;
}