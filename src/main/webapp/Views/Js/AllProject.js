function getAllProject() {
    var xhttp = new XMLHttpRequest();
    xhttp.responseType = 'json'
    xhttp.open("GET", "http://localhost:8084/joboonja/project", true)
    xhttp.send()
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        console.log(this.response)
    }
    }
}
