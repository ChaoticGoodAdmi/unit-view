let searchInput = document.getElementById("searchPattern");

function search() {
    let searchPattern = searchInput.value;
    let url = location.protocol + '//' + location.host + location.pathname;
    window.open(url + "?search=" + searchPattern, "_self");
}

$(document).ready(function () {
    const url_string = window.location.href;
    const url = new URL(url_string);
    console.log(url.searchParams.get("search"))
    searchInput.value = url.searchParams.get("search");
    searchInput.focus();
    moveCursorToEnd(searchInput);
})
$(document).keypress(function (e) {
    if (e.which === 13) {
        $("#searchBtn").click();
    }
});

function moveCursorToEnd(el) {
    if (typeof el.selectionStart == "number") {
        el.selectionStart = el.selectionEnd = el.value.length;
    } else if (typeof el.createTextRange != "undefined") {
        el.focus();
        var range = el.createTextRange();
        range.collapse(false);
        range.select();
    }
}