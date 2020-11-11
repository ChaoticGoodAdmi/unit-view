function openTp() {
    $('.tp-details').addClass("d-none");
    let chosenTp = document.getElementById("tp-list").value;
    console.log(chosenTp);
    if (chosenTp !== "0") {
        let id = "tp" + chosenTp;
        console.log(id);
        let tpBlockChosen = document.getElementById(id);
        console.log(tpBlockChosen);
        tpBlockChosen.classList.remove("d-none");
    }
}