var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
})

var myModal = document.getElementById('myModal');
var bsModal = new bootstrap.Modal(document.getElementById('myModal'));
myModal.addEventListener('show.bs.modal', function (event) {
    // Botão que acionou o modal
    var button = event.relatedTarget;
    // Extrair informações dos atributos data-bs-*
    var id = button.getAttribute('data-bs-id');

    // Atualizar o conteúdo do modal
    var modalTitle = myModal.querySelector('.modal-title');
    var modalButton = myModal.querySelector('.modal-footer #delete');

    modalTitle.textContent = 'Exclusão da OS ' + id;
    modalButton.addEventListener('click', function(){
        deleteServiceOrder(button,id);
        bsModal.hide();
    });
});

function deleteServiceOrder(button, id){
    var row = button.parentNode.parentNode.parentNode;
    // button->span->td->tr
    var request = new XMLHttpRequest();
    request.open("GET","serviceOrderUpdate?action=remove&service-id="+id,true);
    request.send();
    request.onreadystatechange = function() {
        if(this.status === 200 && this.readyState === 4) {
            var response = JSON.parse(this.responseText);
            if(response) row.parentNode.removeChild(row);
        }
    }
}