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