window.onload = function () {
    // 加载导航栏
    $("#head").load("/html/head.html");
    // 加载页脚
    $("#footer").load("/html/footer.html");
    // 加载提示框
    $("#model").load("/html/model.html");
}

function showModel(text) {
    document.getElementById("promptModalBody").innerHTML = text;
    $('#promptModal').modal();
}