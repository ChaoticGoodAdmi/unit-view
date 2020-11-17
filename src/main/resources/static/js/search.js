let searchInput = document.getElementById("searchPattern");
let filterInput = document.getElementById("filterPattern");

function search() {
    let searchPattern = searchInput.value;
    let url = location.protocol + '//' + location.host + "/api/units/all";
    window.open(url + "?search=" + searchPattern, "_self");
}

$(document).ready(function () {
    const url_string = window.location.href;
    const url = new URL(url_string);
    searchInput.value = url.searchParams.get("search");
    searchInput.focus();
    moveCursorToEnd(searchInput);
})

$(document).keypress(function (e) {
    const isSearchFocused = document.activeElement === searchInput;
    const isFilterFocused = document.activeElement === filterInput;
    if (e.which === 13)
        if (isSearchFocused) $("#searchBtn").click();
        else if (isFilterFocused) $("#filterBtn").click();
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