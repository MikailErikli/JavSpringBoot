window.onload=function () {

    fetch('list')
      .then(response => response.json())
      .then(json => processJson(json));

};

function processJson(rayons){

    var table = document.getElementById("rayons-list");

    for (var i = 0; i < rayons.length; i++) {

        tr = table.insertRow(-1);
        tr.insertCell(-1).innerHTML=rayons[i].id;
        tr.insertCell(-1).innerHTML=rayons[i].theme;

    }

}