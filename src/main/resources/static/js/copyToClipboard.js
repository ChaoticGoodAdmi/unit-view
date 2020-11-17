function copyToClipboard() {
    const copyText = document.getElementById("art");
    const textArea = document.createElement("textarea");
    textArea.value = copyText.textContent;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("Copy");
    textArea.remove();
    showCopiedMsg();
}

function showCopiedMsg() {
    document.getElementById("copied-msg").innerHTML = "<span class='text-light'>Скопировано!</span>"
    setTimeout(() => {
        document.getElementById("copied-msg").innerHTML = "";
    }, 2000);
}

$(document).ready(function () {
    $('.clickable-row').each(function () {
        let button = $(this).find('td').children().get(0);
        button.addEventListener('click', function (ev) {
            ev.stopPropagation();
        }, true);
    });
});

function copyTableRow(buttonId) {
    let button = document.getElementById(buttonId);
    button.disabled = true;
    let indexOfDelimiter = buttonId.indexOf('_');
    let id = buttonId.substr(indexOfDelimiter + 1, buttonId.length - indexOfDelimiter);
    let articleRow = document.getElementById('subArt_' + id);
    const textArea = document.createElement("textarea");
    textArea.value = articleRow.textContent;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("Copy");
    textArea.remove();
    replaceWithMessage(articleRow, button);
}

function replaceWithMessage(articleRow, button) {
    const original = articleRow.innerText;
    articleRow.innerText = 'cкопировано';
    articleRow.classList.add('font-italic');
    setTimeout(() => {
        articleRow.innerHTML = original;
        articleRow.classList.remove('font-italic');
        button.disabled = false;
    }, 500);
}