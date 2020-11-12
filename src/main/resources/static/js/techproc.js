function openTp() {
    $('.tp-details').addClass("d-none");
    let chosenTp = document.getElementById("tp-list").value;
    if (chosenTp !== "0") {
        let id = "tp" + chosenTp;
        let tpBlockChosen = document.getElementById(id);
        tpBlockChosen.classList.remove("d-none");
    }
}