function addSkill(name) {
    var xhttp = new XMLHttpRequest();
    var params = 'skillName='+name
    xhttp.responseType = 'json'
    xhttp.open("POST", "http://localhost:8084/joboonja/user/1/skill/add", true)
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(params)
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.response)
        }
    }
}
