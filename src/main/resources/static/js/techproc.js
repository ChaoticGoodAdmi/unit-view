function openTp() {
    $('.tp-details').addClass("d-none");
    $('.material-details').addClass("d-none");
    let chosenTp = document.getElementById("tp-list").value;
    if (chosenTp !== "0") {
        let tpBlockChosen = document.getElementById("tp" + chosenTp);
        let matBlockChosen = document.getElementById("mat" + chosenTp);
        if (tpBlockChosen !== null) tpBlockChosen.classList.remove("d-none");
        if (matBlockChosen !== null) matBlockChosen.classList.remove("d-none");
    }
}