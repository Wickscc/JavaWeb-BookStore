function createXmlHttpRequest() {
    return new XMLHttpRequest();
}

/*
* method 请求方法
* url 请求地址
* params 请求内容
* callback 得到数据后调用的方法
* type 返回数据的类型
* */
function ajax(option) {
    var reqObj = createXmlHttpRequest();
    var data;

    reqObj.open(option.method,option.url);

    if (option.method.toUpperCase() === "POST") {
        reqObj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    }

    reqObj.send(option.params);

    reqObj.onreadystatechange = function (ev) {
        if (reqObj.status === 200 && reqObj.readyState === 4) {
            if (option.type.toLowerCase() === "json") {
                data = eval("(" + reqObj.responseText + ")");
            }else if (type.toLowerCase() === "xml"){
                data = reqObj.responseXML;
            }else if (type.toLowerCase() === "text"){
                data = reqObj.responseText;
            }else {
                data = reqObj.responseText;
            }

            option.callback(data);
        }
    }
}